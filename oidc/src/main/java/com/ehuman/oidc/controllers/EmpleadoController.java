package com.ehuman.oidc.controllers;

import java.util.ArrayList;
/* Consulta a bd para confirmar empleados registrados*/
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ehuman.oidc.dto.EmpleadoDto;
import com.ehuman.oidc.dto.EmpleadoTokenDto;
import com.ehuman.oidc.services.ConsultaDB;
import com.ehuman.oidc.services.EmpleadoTokenService;




@CrossOrigin(origins = "*")
@RestController
public class EmpleadoController {
	
	@Autowired
	private ConsultaDB consutla;
	
	@Autowired
	private EmpleadoTokenService emplTokSer;
	
	private static final Logger LOG = LoggerFactory.getLogger(EmpleadoController.class);
	
	@GetMapping("/valida")
	public String empleadoRegistrado(@RequestParam Long numEmp, @RequestParam Long numComp) {
		
		LOG.info("En EmpleadoController : Ingresa a empleadoRegistrado");
		List<EmpleadoDto> empEnc = recuperarRegistro(numEmp,numComp);//localiza datos de empleado en empleados intenos y esternos
		EmpleadoTokenDto empTkEnc =  new EmpleadoTokenDto();
		
		//buscar en tabla de tokens si existe registro
		empTkEnc= emplTokSer.getEmpleadoToken(empEnc.get(0).getNum_cia(), empEnc.get(0).getNum_emp());
		LOG.info("Empleado encontrado: " +empTkEnc);
		
		
		//limpiar datos
		String apellidoPat = emplTokSer.limpiarDatos(empEnc.get(0).getApell_pat());
		String apellidoMat = emplTokSer.limpiarDatos(empEnc.get(0).getApell_mat());
		String nombre = emplTokSer.limpiarDatos(empEnc.get(0).getNombre());
		//generar username
		String username =  apellidoPat+" "+apellidoMat+" "+nombre;
		LOG.info("username:" +username);
		//genero token
		String username2 = empEnc.get(0).getApell_pat()+" "+empEnc.get(0).getApell_mat()+" "+empEnc.get(0).getNombre();
		String token =  emplTokSer.getJWTToken(username, 5000);
		LOG.info("token:  " +token);
		String toke2= emplTokSer.getJWTToken(username2, 5000);
		LOG.info("toke2:  " +toke2);
		
		//if(empTkEnc.getNumCia()!= null && empTkEnc.getNumEmp()!= null && empTkEnc.getClass()!= null &&empTkEnc.getToken()!= null) {
		if(empTkEnc!=null) {	
		LOG.info("aQUI " +empTkEnc.toString());
			emplTokSer.updateEmpleadoToken(empTkEnc, username2);
			return empTkEnc.toString();
		}else /*if(empEnc.get(0).getApell_mat()!= null && empEnc.get(0).getApell_pat()!= null && empEnc.get(0).getNombre()!= null && empEnc.get(0).getNum_cia()!= null && empEnc.get(0).getNum_emp()!= null){*/
			LOG.info("aQUI else " +empEnc.get(0).toString());
			emplTokSer.addRegistroEmpleado(empEnc.get(0));
		 return empEnc.get(0).toString();
		
			
		 }
		
		
		
		
	
	
	
	
	//obtiene regsitros de empleados internos o externos
	public List<EmpleadoDto> recuperarRegistro(Long numEmpleado, Long numeroCompania ) {
		LOG.info("Ingresa a recuperarRegistro");
	String responseUrlRedirectWorkSocial = "";
	List<EmpleadoDto> empleadoEncontrado =  new ArrayList<>();	
	List<EmpleadoDto>empleadoDto= consutla.getEmpleado(numEmpleado, numeroCompania);
	
	if(!empleadoDto.isEmpty()) {
		
	//responseUrlRedirectWorkSocial = "Empleado  interno encontrado " + empleadoDto.get(0).getNum_cia()+" " + empleadoDto.get(0).getNum_emp()+" "+ empleadoDto.get(0).getApell_pat();
		empleadoEncontrado = empleadoDto;
	}else {
	
			//if(empleadoDto.isEmpty()) {
		
				List<EmpleadoDto> empleadoExterno =consutla.getEmpletadoExterno(numEmpleado, numeroCompania);
				if(!empleadoExterno.isEmpty()) {
					//responseUrlRedirectWorkSocial = "Sin correo electronico";
					empleadoEncontrado = empleadoExterno;
				}
				//else {
					//responseUrlRedirectWorkSocial = "Empleado externo encontrado " + empleadoExterno.get(0).getNum_cia()+" " + empleadoExterno.get(0).getNum_emp()+""+ empleadoExterno.get(0).getApell_mat();
				//LOG.info(responseUrlRedirectWorkSocial);
				//}
			//}
	
	}
	LOG.info("empleadoEncontrado: "+empleadoEncontrado);
	return empleadoEncontrado;
	
	
	}
	
	
	

}
