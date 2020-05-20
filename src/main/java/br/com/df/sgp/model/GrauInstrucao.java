package br.com.df.sgp.model;

import java.io.Serializable;

public class GrauInstrucao  implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer idGrauInstrucao;
	private String descricao;
	
	
	public Integer getIdGrauInstrucao() {
		return idGrauInstrucao;
	}
	public void setIdGrauInstrucao(Integer idGrauInstrucao) {
		this.idGrauInstrucao = idGrauInstrucao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	
}
