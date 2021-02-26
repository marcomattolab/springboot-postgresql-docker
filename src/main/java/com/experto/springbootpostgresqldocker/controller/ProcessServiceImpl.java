package com.experto.springbootpostgresqldocker.controller;

import java.net.URL;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.experto.springbootpostgresqldocker.model.ExecutionModel;
import com.experto.springbootpostgresqldocker.repository.ExecutionRespository;
import com.experto.springbootpostgresqldocker.utils.Constant;
import com.experto.springbootpostgresqldocker.utils.Extra;
import com.experto.springbootpostgresqldocker.utils.Item;
import com.experto.springbootpostgresqldocker.utils.Network;
import com.experto.springbootpostgresqldocker.utils.Result;
import com.experto.springbootpostgresqldocker.utils.Root;
import com.experto.springbootpostgresqldocker.utils.Station;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProcessServiceImpl implements ProcessService {
    private static final Logger logger = LoggerFactory.getLogger(ProcessServiceImpl.class);
    
    @Autowired
	private ExecutionRespository execRespository;
	
    @Async("processExecutor")
    @Override
    public void process(String uuid) {
        logger.info("Start Transformation process for uuid: " + uuid);
        String url = Constant.URL_CITYBIK; //TODO Pass-me
        try {
        	logger.info("Processing uuid: "+uuid+" ...........");
            Thread.sleep(20 * 1000);
            
            Result jsonResult = new Result();
            ObjectMapper mapper = new ObjectMapper();
			Root emps = mapper.readValue(new URL(url), Root.class);
			Network n = emps.getNetwork();
			List<Station> ls = n.getStations();
			for(Station s: ls) {
				Double latitude = s.getLatitude();
				Double longitude = s.getLongitude();
				String name = s.getName();
				String id = s.getId();
				Integer free_bikes = s.getFree_bikes();
				Date timestamp = s.getTimestamp();
				Extra ex = s.getExtra();
				Integer slots = ex.getSlots();
				Double free_bikes_perc = (free_bikes.doubleValue() < slots.doubleValue()) 
										? new Double(free_bikes.doubleValue() / slots.doubleValue())
										: new Double("1.0");
				logger.debug("name: "+ name+ " free_bikes: "+free_bikes+ "  slots: " + slots + "    (free_bikes / slots) ===> free_bikes_perc :"+free_bikes_perc);
				

				Item i = new Item(latitude, longitude, name, id, free_bikes, timestamp, slots, free_bikes_perc);
				if (free_bikes_perc<0.3 && free_bikes_perc>=0) {
					jsonResult.addMin(i);
					logger.info("lowerDistribution free_bikes_perc:"+free_bikes_perc);
				}
				if (free_bikes_perc<0.6 && free_bikes_perc>=0.3) {
					jsonResult.addMed(i);
					logger.info("mediumDistribution free_bikes_perc:"+free_bikes_perc);
				}
				if (free_bikes_perc<1 && free_bikes_perc>=0.6) {
					jsonResult.addMax(i);
					logger.info("hightDistribution free_bikes_perc:"+free_bikes_perc);
				}
			}
			logger.debug("jsonResult: "+jsonResult.toString());
			
			logger.info("Change status on DB in progress..");
	    	if (uuid!=null) {
	    		ExecutionModel e = execRespository.findById(uuid).orElse(null);
	    		if (e!=null) {
	    			e.setStatus(Constant.COMPLETED);
	    			e.setUrl(url);
	    			String jsonStr = extractedJson(jsonResult);
	    			e.setResult(jsonStr);
	    			execRespository.save(e);
	    			logger.info("Status on DB of uuid: "+uuid+" changed to "+Constant.COMPLETED);
	    		}
	    	}
	    	logger.info("Change status on DB completed!");
	    	
	    	
            logger.info("Trasformation process has been succesfully completed for uuid: " + uuid + " !!!!!");
        }catch (Exception ie) {
        	ie.printStackTrace();
            logger.error("Error in Trasformation die to  {} for uuid: {}", ie.getMessage(), uuid);
        }
    }

	private String extractedJson(Result jsonResult) {
		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		 try {
		     json = mapper.writeValueAsString(jsonResult);
		     logger.trace("ResultingJSONstring = " + json);
		 } catch (Exception e) {
		     e.printStackTrace();
		     logger.error("Error in extractedJson");
		 }
		 return json;
	}
    
}
