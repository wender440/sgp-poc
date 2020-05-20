package br.com.df.sgp.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.df.sgp.constantes.PERFIL;
import br.com.df.sgp.dto.FiltroDTO;
import br.com.df.sgp.model.Cidade;
import br.com.df.sgp.model.Usuario;
import br.com.df.sgp.service.CadastroService;
import br.com.df.sgp.util.JsfUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean(name= "indexController")
@ViewScoped
public class IndexController implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);

	private Usuario usuarioLogado;
	private List<Usuario> eleitores;
	private List<Usuario> colaboradores;
	private List<Cidade> cidades;
	private Integer idCidade;
	
	private int totalEleitores;
	private int totalColaboradores;
	private int totalUsuarios;
	
	private Long idColaborador;
	private Collection<?> arrayList;
	
	
	@PostConstruct
	public void init(){		
		this.usuarioLogado = CadastroService.carregarUsuarioPorCpf(JsfUtil.getHttpServletRequest().getUserPrincipal().getName());
		this.cidades = CadastroService.carregarCidades();
		carregarDados(this.usuarioLogado);
	}
	
	public void selecionarUsuario(){
		try {
			if(idColaborador != null){
				Usuario usuario = CadastroService.carregarUsuarioPorId(idColaborador);
				carregarDados(usuario);
			}else{
				carregarDados(this.usuarioLogado);
			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());		
		}
	}
	
	private void carregarDados(Usuario usuario){
		try{			
			this.totalEleitores = CadastroService.carregarTotalUsuarios(usuario.getIdUsuario(), PERFIL.ELEITOR.getCodigo());
			this.totalColaboradores = CadastroService.carregarTotalUsuarios(usuario.getIdUsuario(), PERFIL.COLABORADOR.getCodigo());
			this.totalUsuarios = CadastroService.carregarTotalUsuarios(usuario.getIdUsuario(), null);
			
			 FiltroDTO filtro = new FiltroDTO();
			 filtro.setIdCidade(idCidade);
			 filtro.setIdPerfil(PERFIL.ELEITOR.getCodigo());
			 this.eleitores = CadastroService.carregarUsuarios(usuario, filtro);
			 
			 filtro.setIdPerfil(PERFIL.COLABORADOR.getCodigo());				 
			 this.colaboradores = CadastroService.carregarUsuarios(usuario, filtro);
			 
			 PrimeFaces.current().ajax().update("frmTotais");
			 PrimeFaces.current().ajax().update("frmTabelaEleitores");
			 PrimeFaces.current().ajax().update("frmTabelaEleitores");
			 PrimeFaces.current().ajax().update("frmTabelaColaboradores");
			 PrimeFaces.current().ajax().update("frmTabelaColaboradores");
			 
	
		}catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());			
		}
	}
	
	public void excluirUsuario(Usuario usuario){
		try {
			 if(usuario != null){
				 Long idUsuario = usuario.getIdUsuario();
				 if(CadastroService.excluirUsuario(idUsuario)){
					 JsfUtil.addInfoMessage(null, "O registro foi excluído com sucesso.");  
				  }else{
					  JsfUtil.addErrorMessage(null, "Ocorreu um erro ao excluir o registro.");
				  }		
			 }
			 carregarDados(this.usuarioLogado);
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
		}		
	}
	
	public void imprimirFormulario() {
		 FacesContext facesContext = FacesContext.getCurrentInstance();
	        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
	        
		 java.io.InputStream reportStream =  facesContext.getExternalContext().getResourceAsStream("/pages/reports/report1.jasper");
		response.setContentType("pages/reports");
		response.setHeader("Content-disposition", "inline;filename=Pesquisa_Eleições_2018.pdf");
			
		try {
            ServletOutputStream servletOutputStream = response.getOutputStream();
 
            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(arrayList);
 
            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, null, datasource);
 
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            facesContext.responseComplete();
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

	public Long getIdColaborador() {
		return idColaborador;
	}

	public void setIdColaborador(Long idColaborador) {
		this.idColaborador = idColaborador;
	}
	
}
