package br.com.df.sgp.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.df.sgp.model.Mensagem;
import br.com.df.sgp.model.Usuario;
import br.com.df.sgp.service.CadastroService;
import br.com.df.sgp.util.DataUtils;
import br.com.df.sgp.util.JsfUtil;

@ManagedBean(name= "mensagemController")
@ViewScoped
public class MensagemController implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	private static Logger logger = LoggerFactory.getLogger(MensagemController.class);

	private Usuario usuarioLogado;
	private Mensagem mensagem;
	private Long idMensagem;
	private List<Usuario> usuarios;
	private Long idUsuario;
	
	
	@PostConstruct
	public void init(){		
		this.usuarioLogado = CadastroService.carregarUsuarioPorCpf(JsfUtil.getHttpServletRequest().getUserPrincipal().getName());
		String id = JsfUtil.getRequestParameter("idMensagem");
		if(id != null && !id.equals("")){
			editarMensagem(id);
		}else{
			this.mensagem = new Mensagem();
			this.idMensagem = null;
		}
		carregarDados(this.usuarioLogado);
	}
	
	private void carregarDados(Usuario usuario){
		try{					
			 this.usuarios = CadastroService.carregarColaboradores();
			 PrimeFaces.current().ajax().update("frmMensagem");	
		}catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());			
		}
	}
	
	public void editarMensagem(String id){
		try {
			Long idMensagem = Long.parseLong(id);
			Mensagem mensagem = CadastroService.carregarMensagemPorId(idMensagem);
			if(mensagem!= null){
				this.mensagem = mensagem;
				this.idUsuario = mensagem.getIdDestinatario();
			}			
		    PrimeFaces.current().ajax().update("frmMensagem");
		} catch (Exception e) {
			logger.error("#>> Error: "+ e.getMessage());
		}
	}
	
	public String salvar(){
		try {
			if(this.mensagem.getIdMensagem() == null){
				this.mensagem.setIdRemetente(this.usuarioLogado.getIdUsuario());
				this.mensagem.setIdDestinatario(idUsuario);
				this.mensagem.setDtRegistro(DataUtils.getDataAtual());
				this.mensagem.setStatus(0);
				if(CadastroService.salvarMensagem(this.mensagem)){
					JsfUtil.addInfoMessage(null, "Sua mensagem foi enviada com sucesso."); 
				}else{
					JsfUtil.addInfoMessage(null, "Ocorreu um erro ao enviar a mensagem.");
				}
			}else{
				if(CadastroService.alterarMensagem(this.mensagem)){
					JsfUtil.addInfoMessage(null, "Sua mensagem foi atualizada com sucesso."); 
				}else{
					JsfUtil.addInfoMessage(null, "Ocorreu um erro ao alterar a mensagem.");
				}
			}
		} catch (Exception e) {
			logger.error("#>> Error: "+ e.getMessage());
		}
		 return "/pages/mensagens?faces-redirect=true";
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
