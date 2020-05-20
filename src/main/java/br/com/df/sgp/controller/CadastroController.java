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
import br.com.df.sgp.model.Cidade;
import br.com.df.sgp.model.GrauInstrucao;
import br.com.df.sgp.model.Profissao;
import br.com.df.sgp.model.Perfil;
import br.com.df.sgp.model.Usuario;
import br.com.df.sgp.service.CadastroService;
import br.com.df.sgp.util.CPFUtils;
import br.com.df.sgp.util.DataUtils;
import br.com.df.sgp.util.EmailUtils;
import br.com.df.sgp.util.JsfUtil;
import br.com.df.sgp.util.TelefoneUtils;

@ManagedBean(name= "cadastroController")
@ViewScoped
public class CadastroController implements Serializable{
	
	private static Logger logger = LoggerFactory.getLogger(CadastroController.class);

	private static final long serialVersionUID = 1L;
	private Usuario usuarioLogado;
	private Usuario usuario;
	private List<Cidade> cidades;
	private List<Perfil> perfis;
	private List<GrauInstrucao> grauinstrucoes;
	private List<Profissao>profissoes;
	
	private Integer idPerfil;
	private boolean bloquear = false;
	
	@PostConstruct
	public void init(){		
		this.usuarioLogado = CadastroService.carregarUsuarioPorCpf(JsfUtil.getHttpServletRequest().getUserPrincipal().getName());
		String id = JsfUtil.getRequestParameter("idUsuario");
		if(id != null && !id.equals("")){
			editarUsuario(id);
		}else{
			this.usuario = new  Usuario();
		}
		
		this.cidades = CadastroService.carregarCidades();
		this.grauinstrucoes = CadastroService.carregarGrauInstrucoes();
		this.profissoes = CadastroService.carregarProfissoes();
		this.perfis = CadastroService.carregarPerfisPorPerfilUsuario(this.usuarioLogado.getIdPerfil());		
	}
	
	public void editarUsuario(String id){
		try {
			Long idUsuario = Long.parseLong(id);
			Usuario usuario = CadastroService.carregarUsuarioPorId(idUsuario);
			if(usuario!= null){
				if(usuario.getIdResponsavel() != null && !usuario.getIdResponsavel().equals(this.usuarioLogado.getIdUsuario())){
					this.bloquear = true;
				}
			}
			
			this.usuario = usuario;
			this.idPerfil = usuario.getIdPerfil();
			if(this.usuario.getDtNascimento() != null){
				this.usuario.setDtNascimentoTxt(DataUtils.formatarData(this.usuario.getDtNascimento(), DataUtils.FORMATO_DATA_DD_MM_AAAA_BARRA));
			}
			 PrimeFaces.current().ajax().update("frmCadastroUsuario");
		} catch (Exception e) {
			logger.error("#>> Error: "+ e.getMessage());
		}
	}
	
	public String salvar(){
		 boolean isColaborador = false;
		 String cpf = null;
		 try {			  
			  if(idPerfil.equals(PERFIL.COLABORADOR.getCodigo()) || idPerfil.equals(PERFIL.COORDENADOR.getCodigo())){
				  if(this.usuario.getEmail() == null){
					  JsfUtil.addErrorMessage(null, "Para cadastrar este usuário é necessário informar um email.");
				  }else{
					 isColaborador = true;
				  }
			  }
			  this.usuario.setTelefone(TelefoneUtils.clean(this.usuario.getTelefone()));
			  this.usuario.setCelular(TelefoneUtils.clean(this.usuario.getCelular()));
			  this.usuario.setWhatsapp(TelefoneUtils.clean(this.usuario.getWhatsapp()));
			  this.usuario.setTelegram(TelefoneUtils.clean(this.usuario.getTelegram()));
			  if(this.usuario.getDtNascimentoTxt() != null && !this.usuario.getDtNascimentoTxt().equals("")){
				  this.usuario.setDtNascimento(DataUtils.parse(this.usuario.getDtNascimentoTxt(), DataUtils.FORMATO_DATA_DD_MM_AAAA_BARRA));
			  }			
			  
			  this.usuario.setAtivo(true);
			  this.usuario.setIdPerfil(idPerfil);
			 			  
			  if(this.usuario.getIdUsuario() == null){
				  this.usuario.setCpf(CPFUtils.clean(this.usuario.getCpf()));
				  cpf = this.usuario.getCpf();
				  this.usuario.setDtRegistro(DataUtils.getDataAtual());
				  this.usuario.setIdResponsavel(this.usuarioLogado.getIdUsuario());
				  
				  Usuario usuarioConsulta = CadastroService.carregarUsuarioPorCpf(this.usuario.getCpf());			  
				  if(usuarioConsulta == null){
					  if(CadastroService.salvarUsuario(this.usuario)){
						  if(isColaborador == true){
							  Usuario usuario = CadastroService.carregarUsuarioPorCpf(cpf);
							  StringBuilder mensagem = new StringBuilder();
							  mensagem.append("<p>Prezado colaborador, você está recebendo sua senha provisória de acesso ao sistema: <a href='http://www.dfvitoria.com.br:8080/sgp' target='_blank'>SGP - Gestão</a>");
							  mensagem.append("<p>Sua Senha: <strong>" + usuario.getPw() + "</strong></p>");
							  mensagem.append("<h3>Para sua segurança, ao acessar o sistema altere sua senha!</h3><br />");
							  mensagem.append("<h3>ATENÇÃO! PARA ACESSAR O SISTEMA CLIQUE NO ENDEREÇO LOGO ABAIXO:</h3>");
							  mensagem.append("<h3><a href='http://www.dfvitoria.com.br:8080/sgp' target='_blank'>CLIQUE AQUI</a></h3>");
							  EmailUtils.enviarEmailGmail(mensagem.toString(), usuario.getEmail(), "Registro de usuário");
						  }
						  JsfUtil.addInfoMessage(null, "O cadastro foi salvo com sucesso.");  
					  }else{
						  JsfUtil.addErrorMessage(null, "Ocorreu um erro ao salvar o cadastro.");
					  }				  
				  }else{
					  JsfUtil.addErrorMessage(null, "Já existe um registro salvo no sistema com este cpf.");
					  return null;
				  }
			  }else{
				  if(CadastroService.alterarUsuario(this.usuario)){
					  JsfUtil.addInfoMessage(null, "O cadastro foi atualizado com sucesso.");
				  }else{
					  JsfUtil.addErrorMessage(null, "Ocorreu um erro ao atualizar o cadastro.");
				  }	
			  }			  
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
		}
		 return "/pages/index?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}
	
	
	public List<GrauInstrucao> getGrauinstrucoes() {
		return grauinstrucoes;
	}

	public void setGrauinstrucoes(List<GrauInstrucao> grauinstrucoes) {
		this.grauinstrucoes = grauinstrucoes;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public boolean isBloquear() {
		return bloquear;
	}

	public void setBloquear(boolean bloquear) {
		this.bloquear = bloquear;
	}

	public List<Profissao> getProfissoes() {
		return profissoes;
	}

	public void setProfissoes(List<Profissao> profissoes) {
		this.profissoes = profissoes;
	}
	
	
}
