package br.com.df.sgp.model;

import java.io.Serializable;

public class Cidade implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private Integer idCidade;
	private String nome;
	private Double latitude;
	private Double longitude;
	
	public Integer getIdCidade() {
		return idCidade;
	}
	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
}
