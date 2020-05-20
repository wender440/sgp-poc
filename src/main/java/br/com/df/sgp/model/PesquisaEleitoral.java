package br.com.df.sgp.model;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.df.sgp.util.DataUtils;

public class PesquisaEleitoral implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idPesquisa;
	private Long idResponsavel;
	private Long idCandidato;
	private String nomeEntrevistado;
	private String endereco;
	private String bairro;
	private String cep;
	private String telefone;
	private String celular;
	private String dataEntrevista;
	private LocalDate dtRegistro;
	private String horaInicio;
	private String horaFim;
	private String pergunta;
	private String porque;
	private String educacao;
	private String geracaoEmpregos;
	private String lazer;
	private String saude;
	private String transporte;
	private String seguranca;
	private String shoopings;
	private String supermercados;
	private String PACS;
	private String outros;
	private Usuario responsavel;
	private Candidato candidato;

	public String getDtRegistroFmt() {
		if (dtRegistro != null) {
			return DataUtils.formatarData(dtRegistro, DataUtils.FORMATO_DATA_DD_MM_AAAA_BARRA);
		}
		return null;
	}

	public Long getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(Long idCandidato) {
		this.idCandidato = idCandidato;
	}

	public Long getIdPesquisa() {
		return idPesquisa;
	}

	public void setIdPesquisa(Long idPesquisa) {
		this.idPesquisa = idPesquisa;
	}

	public Long getIdResponsavel() {
		return idResponsavel;
	}

	public void setIdResponsavel(Long idResponsavel) {
		this.idResponsavel = idResponsavel;
	}

	public String getNomeEntrevistado() {
		return nomeEntrevistado;
	}

	public void setNomeEntrevistado(String nomeEntrevistado) {
		this.nomeEntrevistado = nomeEntrevistado;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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


	public String getDataEntrevista() {
		return dataEntrevista;
	}

	public LocalDate getDtRegistro() {
		return dtRegistro;
	}

	public void setDtRegistro(LocalDate dtRegistro) {
		this.dtRegistro = dtRegistro;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public void setDataEntrevista(String dataEntrevista) {
		this.dataEntrevista = dataEntrevista;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public String getPorque() {
		return porque;
	}

	public void setPorque(String porque) {
		this.porque = porque;
	}

	public String getEducacao() {
		return educacao;
	}

	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}

	public String getGeracaoEmpregos() {
		return geracaoEmpregos;
	}

	public void setGeracaoEmpregos(String geracaoEmpregos) {
		this.geracaoEmpregos = geracaoEmpregos;
	}

	public String getLazer() {
		return lazer;
	}

	public void setLazer(String lazer) {
		this.lazer = lazer;
	}

	public String getSaude() {
		return saude;
	}

	public void setSaude(String saude) {
		this.saude = saude;
	}

	public String getTransporte() {
		return transporte;
	}

	public void setTransporte(String transporte) {
		this.transporte = transporte;
	}

	public String getSeguranca() {
		return seguranca;
	}

	public void setSeguranca(String seguranca) {
		this.seguranca = seguranca;
	}

	public String getShoopings() {
		return shoopings;
	}

	public void setShoopings(String shoopings) {
		this.shoopings = shoopings;
	}

	public String getSupermercados() {
		return supermercados;
	}

	public void setSupermercados(String supermercados) {
		this.supermercados = supermercados;
	}

	public String getPACS() {
		return PACS;
	}

	public void setPACS(String pACS) {
		PACS = pACS;
	}

	public String getOutros() {
		return outros;
	}

	public void setOutros(String outros) {
		this.outros = outros;
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}
}
