package br.com.df.sgp.model;

import java.io.Serializable;

public class GraficoPorCidade  implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private String cidade;
	private Integer quantidade;
	
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
}
