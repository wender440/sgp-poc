package br.com.df.sgp.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.df.sgp.model.Usuario;
import br.com.df.sgp.service.CadastroService;
import br.com.df.sgp.util.JsfUtil;

@ManagedBean(name= "sessaoController")
@SessionScoped
public class SessaoController {

	private Usuario usuario;
	private String nomeUsuario;
	private String cidade;
	private String perfil;
	
	@PostConstruct
	public void init(){
		try {
			this.usuario = CadastroService.carregarUsuarioPorCpf(JsfUtil.getHttpServletRequest().getUserPrincipal().getName());
			if(usuario != null){
				if(usuario.getCidade() != null){cidade = usuario.getCidade().getNome();}
				if(usuario.getPerfil() != null){perfil = usuario.getPerfil().getDescricao();}
				nomeUsuario = usuario.getNome();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
}
