package br.com.df.sgp.model;

import java.io.Serializable;

public class Profissao implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer idProfissao;
	private String descricao;
	
	
	
	public Integer getIdProfissao() {
		return idProfissao;
	}
	public void setIdProfissao(Integer idProfissao) {
		this.idProfissao = idProfissao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
