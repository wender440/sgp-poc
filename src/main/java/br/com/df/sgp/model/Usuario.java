package br.com.df.sgp.model;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.df.sgp.util.CPFUtils;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idUsuario;
	private Long idResponsavel;
	private String nome;
	private String email;
	private String cpf;
	private String senha;
	private String telefone;
	private String celular;
	private String whatsapp;
	private String telegram;
	private String facebook;
	private String instagram;
	private String twiter;
	private String titulo;
	private String zona;
	private String secao;
	private LocalDate dtNascimento;
	private LocalDate dtRegistro;
	private Integer idCidade;
	private Integer idPerfil;
	private Integer idGrauInstrucao;
	private Integer idProfissao;
	private Boolean ativo;
	private String pw;
	private Cidade cidade;
	private Perfil perfil;
	private GrauInstrucao grauInstrucao;
	private Profissao profissao;

	private String dtNascimentoTxt;
	private String dtRegistroTxt;

	public Usuario() {
	}

	public String getCpfFmt() {
		if (cpf != null) {
			return CPFUtils.format(cpf);
		}
		return null;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}


	public Long getIdResponsavel() {
		return idResponsavel;
	}

	public void setIdResponsavel(Long idResponsavel) {
		this.idResponsavel = idResponsavel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getIdGrauInstrucao() {
		return idGrauInstrucao;
	}

	public void setIdGrauInstrucao(Integer idGrauInstrucao) {
		this.idGrauInstrucao = idGrauInstrucao;
	}
	
	public Profissao getProfissao() {
		return profissao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getTelegram() {
		return telegram;
	}

	public void setTelegram(String telegram) {
		this.telegram = telegram;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getTwiter() {
		return twiter;
	}

	public void setTwiter(String twiter) {
		this.twiter = twiter;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getSecao() {
		return secao;
	}

	public void setSecao(String secao) {
		this.secao = secao;
	}

	public LocalDate getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public LocalDate getDtRegistro() {
		return dtRegistro;
	}

	public void setDtRegistro(LocalDate dtRegistro) {
		this.dtRegistro = dtRegistro;
	}

	public Integer getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public GrauInstrucao getGrauInstrucao() {
		return grauInstrucao;
	}

	public void setGrauIntrucao(GrauInstrucao grauInstrucao) {
		this.grauInstrucao = grauInstrucao;
	}


	public void setGrauInstrucao(GrauInstrucao grauInstrucao) {
		this.grauInstrucao = grauInstrucao;
	}


	public Integer getIdProfissao() {
		return idProfissao;
	}

	public void setIdProfissao(Integer idProfissao) {
		this.idProfissao = idProfissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getDtNascimentoTxt() {
		return dtNascimentoTxt;
	}

	public void setDtNascimentoTxt(String dtNascimentoTxt) {
		this.dtNascimentoTxt = dtNascimentoTxt;
	}

	public String getDtRegistroTxt() {
		return dtRegistroTxt;
	}

	public void setDtRegistroTxt(String dtRegistroTxt) {
		this.dtRegistroTxt = dtRegistroTxt;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

}
