package br.com.df.sgp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.df.sgp.model.Candidato;
import br.com.df.sgp.model.PesquisaEleitoral;
import br.com.df.sgp.util.DataUtils;

public class PesquisaEleitoralDAO {

	private static Logger logger = LoggerFactory.getLogger(PesquisaEleitoral.class);

	public boolean salvarPesquisa(PesquisaEleitoral pesquisaEleitoral) {
		
		Connection conn = null;

		PreparedStatement pst = null;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append(
					"INSERT INTO pesquisa_eleitoral (idResponsavel,nomeEntrevistado, endereco, bairro, cep, telefone , celular, dataEntrevista, "
							+ "horaInicio, horaFim,educacao,geracao_empregos,lazer,saude,transporte,seguranca,shoopings,"
							+ "supermercados,PACS,outros)");
			sql.append("VALUES (?,?,?,?,?,?,?,?,?,?)");
			conn = Conexao.getConnection();

			pst = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, pesquisaEleitoral.getIdResponsavel());
			pst.setString(2, pesquisaEleitoral.getNomeEntrevistado());
			pst.setString(3, pesquisaEleitoral.getEndereco());
			pst.setString(4, pesquisaEleitoral.getBairro());
			pst.setString(5, pesquisaEleitoral.getCep());
			pst.setString(6, pesquisaEleitoral.getTelefone());
			pst.setString(7, pesquisaEleitoral.getCelular());
			pst.setString(8, pesquisaEleitoral.getDataEntrevista());
			pst.setString(9, pesquisaEleitoral.getHoraInicio());
			pst.setString(10, pesquisaEleitoral.getHoraFim());
			pst.setString(11, pesquisaEleitoral.getEducacao());
			pst.setString(12, pesquisaEleitoral.getGeracaoEmpregos());
			pst.setString(13, pesquisaEleitoral.getLazer());
			pst.setString(14, pesquisaEleitoral.getSaude());
			pst.setString(15, pesquisaEleitoral.getTransporte());
			pst.setString(16, pesquisaEleitoral.getSeguranca());
			pst.setString(17, pesquisaEleitoral.getShoopings());
			pst.setString(18, pesquisaEleitoral.getSupermercados());
			pst.setString(19, pesquisaEleitoral.getPACS());
			pst.setString(20, pesquisaEleitoral.getOutros());
			
			if (pst.executeUpdate() == 1) {
				return true;
			}
			
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				logger.error("#>> Error: " + ex.getMessage());
			}
		} finally {
			Conexao.closeConnection(conn, pst, null);
		}
		return false;
	}
	
	/*public boolean salvarCandidato(Candidato candidato) {
		Integer resultado = null;
		Connection conn = null;

		PreparedStatement pst = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(
					"INSERT INTO candidatos (idPesquisa,governador,distrital,federal,senador,pqgovernador,pqdistrital,pqfederal,pqsenador)");
			sql.append("VALUES (?,?,?,?,?,?,?,?,?,?)");
			conn = Conexao.getConnection();

			pst = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, candidato.getIdPesquisa());
			pst.setString(2, candidato.getGovernador());
			pst.setString(3, candidato.getDistrital());
			pst.setString(4, candidato.getFederal());
			pst.setString(5, candidato.getSenador());
			pst.setString(6, candidato.getPqgovernador());
			pst.setString(7, candidato.getPqdistrital());
			pst.setString(8, candidato.getPqfederal());
			pst.setString(9, candidato.getPqsenador());
			
			if (pst.executeUpdate() == 1) {
				return true;
			}
			ResultSet keys = pst.getGeneratedKeys();
			if (keys.next()) {
				resultado = keys.getInt(1);
			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				logger.error("#>> Error: " + ex.getMessage());
			}
		} finally {
			Conexao.closeConnection(conn, pst, null);
		}
		return false;
	}*/
	
	
}
