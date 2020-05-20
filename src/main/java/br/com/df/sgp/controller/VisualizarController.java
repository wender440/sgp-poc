package br.com.df.sgp.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.df.sgp.model.Mensagem;
import br.com.df.sgp.service.CadastroService;
import br.com.df.sgp.util.DataUtils;
import br.com.df.sgp.util.JsfUtil;

@ManagedBean(name= "visualizarController")
@ViewScoped
public class VisualizarController implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	private static Logger logger = LoggerFactory.getLogger(VisualizarController.class);

	private String remetente;
	private String dtMensagem;
	private String assunto;
	private String mensagem;
	
	@PostConstruct
	public void init(){		
		String id = JsfUtil.getRequestParameter("idMensagem");
		if(id != null && !id.equals("")){
			carregarMensagem(id);
		}
	}
	
	public void carregarMensagem(String id){
		try {
			Long idMensagem = Long.parseLong(id);
			Mensagem mensagem = CadastroService.carregarMensagemPorId(idMensagem);
			if(mensagem!= null){				
				this.remetente = mensagem.getRemetente().getNome();
				this.dtMensagem = DataUtils.formatarData(mensagem.getDtRegistro(), DataUtils.FORMATO_DATA_DD_MM_AAAA_BARRA);
				this.assunto = mensagem.getAssunto();
				this.mensagem = mensagem.getMensagem();
			}			
		    PrimeFaces.current().ajax().update("frmMensagem");
		} catch (Exception e) {
			logger.error("#>> Error: "+ e.getMessage());
		}
	}

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public String getDtMensagem() {
		return dtMensagem;
	}

	public void setDtMensagem(String dtMensagem) {
		this.dtMensagem = dtMensagem;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
