package com.ehuman.oidc.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ehuman.oidc.models.EmpleadosTokenWs;
@Repository
public interface EmpleadoTokenDao extends CrudRepository<EmpleadosTokenWs, Long>{
		public abstract EmpleadosTokenWs findByNumCiaAndNumEmp(Long numCia, Long NumEmp);
}
