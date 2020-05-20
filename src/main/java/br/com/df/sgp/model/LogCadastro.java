package br.com.df.sgp.model;

import java.io.Serializable;
import java.time.LocalDate;

public class LogCadastro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idLogCadastro;
	private Long idUsuario;
	private Long idCadastro;
	private LocalDate dtRegistro;
	
	private Usuario usuario;
	private Usuario usuarioCadastrado;
	
	
	public Long getIdLogCadastro() {
		return idLogCadastro;
	}
	public void setIdLogCadastro(Long idLogCadastro) {
		this.idLogCadastro = idLogCadastro;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getIdCadastro() {
		return idCadastro;
	}
	public void setIdCadastro(Long idCadastro) {
		this.idCadastro = idCadastro;
	}
	public LocalDate getDtRegistro() {
		return dtRegistro;
	}
	public void setDtRegistro(LocalDate dtRegistro) {
		this.dtRegistro = dtRegistro;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Usuario getUsuarioCadastrado() {
		return usuarioCadastrado;
	}
	public void setUsuarioCadastrado(Usuario usuarioCadastrado) {
		this.usuarioCadastrado = usuarioCadastrado;
	}
	
}
