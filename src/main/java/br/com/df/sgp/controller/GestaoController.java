package br.com.df.sgp.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.df.sgp.model.Cidade;
import br.com.df.sgp.model.GraficoPorCidade;
import br.com.df.sgp.model.Usuario;
import br.com.df.sgp.service.CadastroService;
import br.com.df.sgp.util.JsfUtil;

@ManagedBean(name= "gestaoController")
@ViewScoped
public class GestaoController implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	private static Logger logger = LoggerFactory.getLogger(GestaoController.class);

	private Usuario usuarioLogado;
	private List<Usuario> eleitores;
	private List<Usuario> colaboradores;
	private List<Cidade> cidades;
	private Integer idCidade;
	
	private List<GraficoPorCidade> dadosEleitores;
	private PieChartModel graficoEleitores;
	
	private List<GraficoPorCidade> dadosColaboradores;
	private PieChartModel graficoColaboradores;
	
	private int totalEleitores;
	private int totalColaboradores;
	private int totalUsuarios;
	
	@PostConstruct
	public void init(){		
		this.usuarioLogado = CadastroService.carregarUsuarioPorCpf(JsfUtil.getHttpServletRequest().getUserPrincipal().getName());
		this.cidades = CadastroService.carregarCidades();
		carregarDados(this.usuarioLogado);
	}
	
	private void carregarDados(Usuario usuario){
		try{			
			carregarGraficoEleitores();
			carregarGraficoColaboradores();
		}catch (Exception e) {
			logger.error(e.getMessage());			
		}
	}
	
	public void carregarGraficoEleitores(){
		try {
			this.graficoEleitores = new PieChartModel();
			this.graficoEleitores.setTitle("Eleitores por Região");
			this.dadosEleitores = CadastroService.carregarGraficoEleitores();
			if(this.dadosEleitores != null && !this.dadosEleitores.isEmpty()){
				for (GraficoPorCidade valorGrafico : dadosEleitores) {
					this.graficoEleitores.set(valorGrafico.getCidade(), valorGrafico.getQuantidade());
				}		      
				this.graficoEleitores.setLegendPosition("w");
				this.graficoEleitores.setShowDataLabels(true);
			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
		}
	}

	public void carregarGraficoColaboradores(){
		try {
			this.graficoColaboradores = new PieChartModel();
			this.graficoColaboradores.setTitle("Colaboradores por Região");
			this.dadosColaboradores = CadastroService.carregarGraficoColaboradores();
			if(this.dadosColaboradores != null && !this.dadosColaboradores.isEmpty()){
				for (GraficoPorCidade valorGrafico : dadosColaboradores) {
					this.graficoColaboradores.set(valorGrafico.getCidade(), valorGrafico.getQuantidade());
				}		      				
				this.graficoColaboradores.setLegendPosition("w");
				this.graficoColaboradores.setShowDataLabels(true);
			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
		}
	}
	
	public List<Usuario> getEleitores() {
		return eleitores;
	}

	public void setEleitores(List<Usuario> eleitores) {
		this.eleitores = eleitores;
	}

	public List<Usuario> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Usuario> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Integer getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}
	
	public PieChartModel getGraficoEleitores() {
		return graficoEleitores;
	}

	public void setGraficoEleitores(PieChartModel graficoEleitores) {
		this.graficoEleitores = graficoEleitores;
	}
	
	public PieChartModel getGraficoColaboradores() {
		return graficoColaboradores;
	}

	public void setGraficoColaboradores(PieChartModel graficoColaboradores) {
		this.graficoColaboradores = graficoColaboradores;
	}

	public int getTotalEleitores() {
		return totalEleitores;
	}

	public void setTotalEleitores(int totalEleitores) {
		this.totalEleitores = totalEleitores;
	}

	public int getTotalColaboradores() {
		return totalColaboradores;
	}

	public void setTotalColaboradores(int totalColaboradores) {
		this.totalColaboradores = totalColaboradores;
	}

	public int getTotalUsuarios() {
		return totalUsuarios;
	}

	public void setTotalUsuarios(int totalUsuarios) {
		this.totalUsuarios = totalUsuarios;
	}
}
