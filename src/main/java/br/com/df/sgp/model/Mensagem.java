package br.com.df.sgp.model;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.df.sgp.util.DataUtils;

public class Mensagem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idMensagem;
	private Long idRemetente;
	private Long idDestinatario;
	private LocalDate dtRegistro;
	private String assunto;
	private String mensagem;
	private int status;
	
	private Usuario remetente;
	private Usuario destinatario;
	
	public String getDtRegistroFmt(){
		if(dtRegistro != null){
			return DataUtils.formatarData(dtRegistro, DataUtils.FORMATO_DATA_DD_MM_AAAA_BARRA);
		}
		return null;
	}
	
	
	public Long getIdMensagem() {
		return idMensagem;
	}
	public void setIdMensagem(Long idMensagem) {
		this.idMensagem = idMensagem;
	}
	public Long getIdRemetente() {
		return idRemetente;
	}
	public void setIdRemetente(Long idRemetente) {
		this.idRemetente = idRemetente;
	}
	public Long getIdDestinatario() {
		return idDestinatario;
	}
	public void setIdDestinatario(Long idDestinatario) {
		this.idDestinatario = idDestinatario;
	}
	public LocalDate getDtRegistro() {
		return dtRegistro;
	}
	public void setDtRegistro(LocalDate dtRegistro) {
		this.dtRegistro = dtRegistro;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Usuario getRemetente() {
		return remetente;
	}
	public void setRemetente(Usuario remetente) {
		this.remetente = remetente;
	}
	public Usuario getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(Usuario destinatario) {
		this.destinatario = destinatario;
	}
	
}
