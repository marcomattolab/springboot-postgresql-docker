package com.experto.springbootpostgresqldocker.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.experto.springbootpostgresqldocker.model.ExecutionModel;
import com.experto.springbootpostgresqldocker.repository.ExecutionRespository;
import com.experto.springbootpostgresqldocker.utils.Constant;
import com.experto.springbootpostgresqldocker.utils.Root;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Primary Services:
 * API post-job  / POST => http://localhost:5000/post-job   
 * 		body=> {"uuid":"2"}  
 * 		Content-Type:application/json
 * 
 * API get-status / GET => http://localhost:5000/get-status?uuid=2
 * API execution/<uuid> / GET => http://localhost:5000/execution/2 
 *  
 *  
 * Optional Servives:
 * API set-complete / GET => http://localhost:5000/set-complete?uuid=2
 * API execution/all / GET => http://localhost:5000/execution/all   
 * API get-json  / GET => http://localhost:5000/get-json 
 * 
 */
@RestController
public class ExecutionController {
	private ExecutionRespository execRespository;
    
    @Autowired
    private ProcessService processService;
    
    @Autowired
    public ExecutionController(ExecutionRespository execRespository) {
        this.execRespository = execRespository;
    }

    @PostMapping("/post-job")
    ResponseEntity<Map<String, String>> postJob(@RequestBody ExecutionModel execution) {
    	ExecutionModel neExec = new ExecutionModel();
    	neExec.setStatus("RUNNING");
    	String uuid = execution.getUuid();
		neExec.setUuid(uuid);
    	neExec.setId(uuid);
        ExecutionModel saved = execRespository.save(neExec);
        
        //Call (in Async) Trasformation process
        processService.process(uuid);
        
        //Return response
        Map<String, String> response = new HashMap<>();
        response.put("message", "Request is under process. ( uuid: " + uuid + " )");
        response.put("result" , saved!=null && saved.getStatus()!=null ? Constant.OK : Constant.KO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    /**
     * Show execution results
     * 
     * @param id execution uuid
     * @return ExecutionModel
     */
    @GetMapping("/execution/{id}")
    ExecutionModel userById(@PathVariable String id) {
        return execRespository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Show all executions 
     * 
     * @return ExecutionModel
     */
    @GetMapping("/execution/all")
    Iterable<ExecutionModel> all() {
        return execRespository.findAll();
    }
    
    
    /**
     * Force execution with specified uuid to "COMPLETED"
     * 
     * @param uuid execution uuid
     * @return Response Message
     */
    @RequestMapping(value="set-complete", method = RequestMethod.GET)
    @ResponseBody ResponseEntity<Map<String, String>> completed(@RequestParam String uuid) {
    	String status = Constant.FAILED;
    	if (uuid!=null) {
    		ExecutionModel e = execRespository.findById(uuid).orElse(null);
    		if (e!=null) {
    			e.setStatus(Constant.COMPLETED);
    			execRespository.save(e);
    			status = Constant.COMPLETED;
    		}
    	}
    	
    	Map<String, String> response = new HashMap<>();
    	String msg = Constant.FAILED.equals(status) 
    				? " (uuid not found)" 
    				: (Constant.COMPLETED.equals(status) ? " (uuid COMPLETED)" : " (uuid Not COMPLETED)");
        response.put("message", "Request has been completed and status changed. " + msg);
        response.put("result" , status);
        return new ResponseEntity<>(response, HttpStatus.OK);
        
    }
    
    /**
     * Do a query on DB to check if transformation has been completed.
     * 
     * @param uuid execution uuid
     * @return Response Message
     */
    @RequestMapping(value="get-status", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Map<String, String>> getStatus(@RequestParam("uuid") String uuid){
    	String status = Constant.KO;
    	ExecutionModel r = execRespository.findById(uuid).orElse(null);
    	if (r!=null){
    		status = Constant.COMPLETED.equals(r.getStatus()) ? Constant.OK : r.getStatus();
    	}
    	Map<String, String> response = new HashMap<>();
    	String msg = Constant.KO.equals(status) 
    				? " (uuid not found)" 
    				: (Constant.OK.equals(status)?" (uuid is completed)":" (uuid is in running)");
        response.put("message", "Request has been completed. " + msg);
        response.put("result" , status);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    /**
     * Perform a GET to URL and return response in JSON
     * 
     * @return response in JSON based on object Root
     */
    @GetMapping("/get-json")
    Root getJson() {
    	String url = Constant.URL_CITYBIK; //TODO Pass URL as input request
        Root emps = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			emps = mapper.readValue(new URL(url), Root.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return emps;
    }
    
    /**
     * Save data on DB
     * 
     * @param execution ExecutionModel
     * @return ExecutionModel
     */
    @PostMapping("/execution/save")
    ExecutionModel save(@RequestBody ExecutionModel execution) {
        return execRespository.save(execution);
    }
    
}
