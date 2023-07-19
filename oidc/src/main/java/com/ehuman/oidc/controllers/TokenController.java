package com.ehuman.oidc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehuman.oidc.dto.EmpleadoTokenDto;

import com.ehuman.oidc.services.EmpleadoTokenService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/empleado")
public class TokenController {
	
	@Autowired
	private EmpleadoTokenService empleadoService;
	
	private static final Logger LOG = LoggerFactory.getLogger(EmpleadoTokenService.class);
	
@GetMapping("/registra")	
public EmpleadoTokenDto addEmpleadoToken(@RequestBody EmpleadoTokenDto empleadoDto) {
	LOG.info("Ingresa a addEmpleadoToken" );
	EmpleadoTokenDto empleadoTDto =  new EmpleadoTokenDto();
	if(empleadoDto != null) {
	
			//verificar que exista en empleado en tabla con token
			empleadoTDto = empleadoService.getEmpleadoToken(empleadoDto.getNumCia(), empleadoDto.getNumEmp());
    
    //borramos datos si ya estan en la tabla
			if(empleadoTDto.getFechaMov() != null && empleadoTDto.getNumCia()!= null && empleadoTDto.getNumEmp()!= null && empleadoTDto.getToken() != null) {
					LOG.info("empleadoTDto: " +empleadoTDto);
				empleadoService.updateEmpleadoToken(empleadoDto, "sdsfsfsd");
    	
			}
		}
    return empleadoDto;
    }



	
	

}
