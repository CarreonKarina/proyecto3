package com.ehuman.oidc.services;



import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.ehuman.oidc.dao.EmpleadoTokenDao;
import com.ehuman.oidc.dto.EmpleadoDto;
import com.ehuman.oidc.dto.EmpleadoTokenDto;
import com.ehuman.oidc.models.EmpleadosTokenWs;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class EmpleadoTokenService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private EmpleadoTokenDao empeadoDao;
	@Autowired
	private ConsultaDB consulta;
	
	
	private static final Logger LOG = LoggerFactory.getLogger(EmpleadoTokenService.class);
	
	// vefificar datos exitentes en HU_EMPLS_TOKEN_WS
		public EmpleadoTokenDto getEmpleadoToken(Long numCia, Long numEmp) {
			LOG.info("En getEmpleadoToken obtiene datos de empleado por numcia :"+numCia+ " y numEmp: " +numEmp);
			EmpleadosTokenWs empleadoT = new EmpleadosTokenWs();
			EmpleadoTokenDto empDto =  new EmpleadoTokenDto();
			empleadoT= empeadoDao.findByNumCiaAndNumEmp(numCia, numEmp);
			LOG.info("EmpleadoTOKEN: " +empleadoT);
		    if(empleadoT.getFechaMov()!= null && empleadoT.getNumCia()!= null && empleadoT.getNumEmp()!= null && empleadoT.getToken()!= null) {
			 empDto = this.modelToDto(empleadoT);
			LOG.info(empDto.toString());
		    }
			return empDto;
		}
			
			
				
		public void updateEmpleadoToken(EmpleadoTokenDto empleadoTDto, String username)	{
			LOG.info("En deleteEmpleadoToken con datos" +empleadoTDto.toString());
			
			LOG.info("username: "+username);
	
			String token = this.getJWTToken(username, 5000);
		    empleadoTDto.setToken(token);
		    empleadoTDto.setFechaMov(new Date(System.currentTimeMillis()));
		    empeadoDao.save(this.dtoToModel(empleadoTDto));
		    }
		    
		    
		
			
			
		
		
		
		public void addRegistroEmpleado(EmpleadoDto empDto) {
			LOG.info("En addRegistroEmpleado se tienen datos para registro de empleado en HU_EMPLS_TOKEN_WS con datos:" + empDto.toString());
			EmpleadoTokenDto empTkDto =  new EmpleadoTokenDto();
			empTkDto.setFechaMov(new Date(System.currentTimeMillis()));
			empTkDto.setNumCia(empDto.getNum_cia());
			empTkDto.setNumEmp(empDto.getNum_emp());
			String username = empDto.getApell_pat()+" "+empDto.getApell_mat()+" "+empDto.getNombre();
			empTkDto.setToken(getJWTToken(username, 5000));
			empeadoDao.save(this.dtoToModel(empTkDto));
			
		}
		
		
			
			
		
		// convertir model a dto
		public EmpleadoTokenDto modelToDto(EmpleadosTokenWs empToken) {
			EmpleadoTokenDto empTDto = new EmpleadoTokenDto();
			empTDto.setNumCia(empToken.getNumCia());
			empTDto.setNumEmp(empToken.getNumEmp());
			empTDto.setToken(empToken.getToken());
			empTDto.setFechaMov(empToken.getFechaMov());
			return empTDto;
		}
		
		//convertir dto a model
		public EmpleadosTokenWs dtoToModel(EmpleadoTokenDto empTokDto) {
			EmpleadosTokenWs emp = new EmpleadosTokenWs();
			emp.setNumCia(empTokDto.getNumCia());
			emp.setNumEmp(empTokDto.getNumEmp());
			emp.setToken(empTokDto.getToken());
			emp.setFechaMov(empTokDto.getFechaMov());
			return emp;
		}
		
		public String limpiarDatos(String dato) {
			String datoLimpio= dato.replace("/","").replace("+","").replace("=","");
			return datoLimpio;
		}
		
		

		
		
		public String getJWTToken(String username, long tokenExp) {		
			LOG.info("ingresa a getJWTToken con username:  "+ username +" tokenExp:  " + tokenExp);
			String secretKey = "humanRHD";
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");		
			String token = Jwts.builder().setId("venturssotfJWT")
										 .setSubject(username)
										 .claim("authorities", grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
										 .setIssuedAt(new Date(System.currentTimeMillis()))
										 .setExpiration(new Date(System.currentTimeMillis() + tokenExp))
										 //.serializeToJsonWith(secretKey)
										 //.signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
										 .compact();
			LOG.info("token:" +token);
			return  token;
		}
		
		
	
		

}
