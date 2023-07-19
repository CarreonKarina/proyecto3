package com.ehuman.oidc.dto;

public class EmpleadoDto {
	
	private Long num_cia;
	private Long num_emp;
	private Long numeroCompania;
	//private String clave_compania;
	private String apell_pat;
	private String apell_mat;
	private String nombre;
	
	
	public Long getNum_cia() {
		return num_cia;
	}
	public void setNum_cia(Long num_cia) {
		this.num_cia = num_cia;
	}
	public Long getNum_emp() {
		return num_emp;
	}
	public void setNum_emp(Long num_empl) {
		this.num_emp = num_empl;
	}
	public Long getNumeroCompania() {
		return numeroCompania;
	}
	public void setNumeroCompania(Long numeroCompania) {
		this.numeroCompania = numeroCompania;
	}
	
	
	/*
	 * public String getClave_compania() { return clave_compania; } public void
	 * setClave_compania(String clave_compania) { this.clave_compania =
	 * clave_compania; }
	 */
	
	
	public String getApell_pat() {
		return apell_pat;
	}
	public void setApell_pat(String apell_pat) {
		this.apell_pat = apell_pat;
	}
	public String getApell_mat() {
		return apell_mat;
	}
	public void setApell_mat(String apell_mat) {
		this.apell_mat = apell_mat;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmpleadoDto [num_cia=");
		builder.append(num_cia);
		builder.append(", num_emp=");
		builder.append(num_emp);
		builder.append(", numeroCompania=");
		builder.append(numeroCompania);
		builder.append(", apell_pat=");
		builder.append(apell_pat);
		builder.append(", apell_mat=");
		builder.append(apell_mat);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	

	
	
	

	
	
}
