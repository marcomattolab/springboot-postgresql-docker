package com.experto.springbootpostgresqldocker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.experto.springbootpostgresqldocker.model.ExecutionModel;

@Repository
public interface ExecutionRespository extends JpaRepository<ExecutionModel, String> {
}
