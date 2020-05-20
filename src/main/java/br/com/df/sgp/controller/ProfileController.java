package br.com.df.sgp.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.df.sgp.model.Usuario;
import br.com.df.sgp.service.CadastroService;
import br.com.df.sgp.util.JsfUtil;
import br.com.df.sgp.util.SenhaUtils;

@ManagedBean(name= "profileController")
@ViewScoped
public class ProfileController implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	private static Logger logger = LoggerFactory.getLogger(ProfileController.class);
	private Usuario usuarioLogado;
	private String senha;
	private String confirmacao;

	
	@PostConstruct
	public void init(){		
		this.usuarioLogado = CadastroService.carregarUsuarioPorCpf(JsfUtil.getHttpServletRequest().getUserPrincipal().getName());
	}

	public void alterarSenha(){
		try {
			 if(this.usuarioLogado != null){
				if(!senha.equals(confirmacao)){
					JsfUtil.addErrorMessage(null, "A senhas digitada são diferentes.");					
				}else{
					String senhaDefinitiva = senha;
					String senhaMd5 = SenhaUtils.produzirChaveMD5(senha);
					if(CadastroService.alterarSenha(this.usuarioLogado.getIdUsuario(), senhaMd5, senhaDefinitiva)){
						JsfUtil.addInfoMessage(null, "Sua senha foi atualizada com sucesso!");						
					}else{
						JsfUtil.addErrorMessage(null, "Ocorreu um erro ao alterar sua senha.");						
					}
				}
			 }
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
		}
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmacao() {
		return confirmacao;
	}

	public void setConfirmacao(String confirmacao) {
		this.confirmacao = confirmacao;
	}
}
