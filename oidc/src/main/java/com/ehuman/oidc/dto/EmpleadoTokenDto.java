package com.ehuman.oidc.dto;


import java.util.Date;



public class EmpleadoTokenDto {

	private Long numCia;
	
	
	private Long numEmp;
	

	private String token;
	
	
	private Date fechaMov;


	public Long getNumCia() {
		return numCia;
	}


	public void setNumCia(Long numCia) {
		this.numCia = numCia;
	}


	public Long getNumEmp() {
		return numEmp;
	}


	public void setNumEmp(Long numEmp) {
		this.numEmp = numEmp;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}

	
	

	public Date getFechaMov() {
		return fechaMov;
	}


	public void setFechaMov(Date fechaMov) {
		this.fechaMov = fechaMov;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmpleadoTokenDto [numCia=");
		builder.append(numCia);
		builder.append(", numEmp=");
		builder.append(numEmp);
		builder.append(", token=");
		builder.append(token);
		builder.append(", fechaMov=");
		builder.append(fechaMov);
		builder.append("]");
		return builder.toString();
	}
	
	


}
