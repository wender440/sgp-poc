package br.com.df.sgp.util;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.springframework.web.context.annotation.SessionScope;

@ManagedBean
@SessionScope
public class Profissao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String opcao;
	private String label;
	
	public boolean isEMPREGADO() {
		return getOpcao() != null && getOpcao().equals("EMPREGADO");
	}
	
	public boolean isDESEMPREGADO() {
		return getOpcao() != null && getOpcao().equals("DESEMPREGADO"); 
	}

	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
