package br.com.df.sgp.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.df.sgp.constantes.PERFIL;
import br.com.df.sgp.dto.FiltroDTO;
import br.com.df.sgp.model.Mensagem;
import br.com.df.sgp.model.Usuario;
import br.com.df.sgp.service.CadastroService;
import br.com.df.sgp.util.JsfUtil;

@ManagedBean(name= "mensagensController")
@ViewScoped
public class MensagensController implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	private static Logger logger = LoggerFactory.getLogger(MensagensController.class);

	private Usuario usuarioLogado;
	private List<Mensagem> enviadas;
	private List<Mensagem> recebidas;
	private Long idMensagem;
	private Mensagem mensagem;
	private List<Usuario> usuarios;
	private Long idUsuario;
	
	
	@PostConstruct
	public void init(){		
		this.usuarioLogado = CadastroService.carregarUsuarioPorCpf(JsfUtil.getHttpServletRequest().getUserPrincipal().getName());
		carregarDados(this.usuarioLogado);
	}
	
	private void carregarDados(Usuario usuario){
		try{					
			 FiltroDTO filtro = new FiltroDTO();
			 filtro.setIdPerfil(PERFIL.COLABORADOR.getCodigo());
			 this.usuarios = CadastroService.carregarUsuarios(usuario, filtro);
			 this.enviadas = CadastroService.carregarMensagensEnviadas(usuario.getIdUsuario());
			 this.recebidas = CadastroService.carregarMensagensRecebidas(usuario.getIdUsuario());
			 PrimeFaces.current().ajax().update("frmTbMensagensEnviadas");
			 PrimeFaces.current().ajax().update("frmTbMensagensRecebidas");
			 PrimeFaces.current().ajax().update("tbMensagensEnviadas");
			 PrimeFaces.current().ajax().update("tbMensagensRecebidas");
		}catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());			
		}
	}
	
	public void excluirMensagem(Mensagem mensagem){
		try {
			 if(mensagem != null){
				 Long idMensagem = mensagem.getIdMensagem();
				 if(CadastroService.excluirMensagem(idMensagem)){
					 JsfUtil.addInfoMessage(null, "Mensagem excluída com sucesso.");  
				  }else{
					  JsfUtil.addErrorMessage(null, "Ocorreu um erro ao excluir a mensagem.");
				  }		
			 }
			 carregarDados(this.usuarioLogado);
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
		}		
	}

	public List<Mensagem> getEnviadas() {
		return enviadas;
	}

	public void setEnviadas(List<Mensagem> enviadas) {
		this.enviadas = enviadas;
	}

	public List<Mensagem> getRecebidas() {
		return recebidas;
	}

	public void setRecebidas(List<Mensagem> recebidas) {
		this.recebidas = recebidas;
	}

	public Long getIdMensagem() {
		return idMensagem;
	}

	public void setIdMensagem(Long idMensagem) {
		this.idMensagem = idMensagem;
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
}
