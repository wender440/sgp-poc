package br.com.df.sgp.service;


import br.com.df.sgp.dao.PesquisaEleitoralDAO;
import br.com.df.sgp.model.Candidato;
import br.com.df.sgp.model.PesquisaEleitoral;

public class PesquisaEleitoralService {

	private static PesquisaEleitoralDAO pesquisaEleitoralDAO = new PesquisaEleitoralDAO();

	public static boolean salvarPesquisa(PesquisaEleitoral pesquisaEleitoral) {

		return pesquisaEleitoralDAO.salvarPesquisa(pesquisaEleitoral);
	}
	
	
	/*public static boolean salvarCandidato(Candidato candidato) {
		
		return pesquisaEleitoralDAO.salvarCandidato(candidato);
	}*/

}
