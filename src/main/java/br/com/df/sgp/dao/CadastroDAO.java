package br.com.df.sgp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.df.sgp.constantes.PERFIL;
import br.com.df.sgp.dto.FiltroDTO;
import br.com.df.sgp.model.Cidade;
import br.com.df.sgp.model.GraficoPorCidade;
import br.com.df.sgp.model.GrauInstrucao;
import br.com.df.sgp.model.Mensagem;
import br.com.df.sgp.model.Perfil;
import br.com.df.sgp.model.Profissao;
import br.com.df.sgp.model.Usuario;
import br.com.df.sgp.util.DataUtils;

public class CadastroDAO {

	private static Logger logger = LoggerFactory.getLogger(CadastroDAO.class);


	public boolean salvarUsuario(Usuario usuario){
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO usuario (id_responsavel, nome, email, cpf, senha, telefone, celular, whatsapp, telegram, facebook, instagram, twiter, titulo, zona, secao, dt_nascimento, dt_registro, id_cidade, id_perfil, ativo, pw,id_grauInstrucao,id_profissao)");
			sql.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");			
			conn = Conexao.getConnection();					
			pst = conn.prepareStatement(sql.toString());
			pst.setLong(1, usuario.getIdResponsavel());
			pst.setString(2, usuario.getNome().toUpperCase());
			pst.setString(3, usuario.getEmail());
			pst.setString(4, usuario.getCpf());
			pst.setString(5, usuario.getSenha());
			pst.setString(6, usuario.getTelefone());
			pst.setString(7, usuario.getCelular());
			pst.setString(8, usuario.getWhatsapp());
			pst.setString(9, usuario.getTelegram());
			pst.setString(10, usuario.getFacebook());
			pst.setString(11, usuario.getInstagram());
			pst.setString(12, usuario.getTwiter());
			pst.setString(13, usuario.getTitulo());
			pst.setString(14, usuario.getZona());
			pst.setString(15, usuario.getSecao());
			if(usuario.getDtNascimento() != null){
				pst.setDate(16, DataUtils.convertToDate(usuario.getDtNascimento()));
			}else{
				pst.setNull(16, Types.NULL);
			}
			pst.setDate(17, DataUtils.convertToDate(usuario.getDtRegistro()));
			pst.setInt(18, usuario.getIdCidade());
			pst.setInt(19, usuario.getIdPerfil());
			pst.setBoolean(20, usuario.getAtivo());
			pst.setString(21, usuario.getPw());
			pst.setInt(22, usuario.getIdGrauInstrucao());
			pst.setInt(23, usuario.getIdProfissao());
						
			if(pst.executeUpdate() == 1){
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
		}finally {
			Conexao.closeConnection(conn, pst, null);
		}
		return false;
	}
	
	
	public boolean alterarUsuario(Usuario usuario) {
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(
					"UPDATE usuario SET  nome = ?, email = ?, telefone = ?, celular =? , whatsapp =?, telegram=?, facebook=?, instagram=?, twiter=?, dt_nascimento=?, id_cidade=?, id_perfil=?,id_grauInstrucao=?, id_profissao=? ,ativo=?");
			sql.append(" WHERE id_usuario=?");
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, usuario.getNome().toUpperCase());
			pst.setString(2, usuario.getEmail());
			pst.setString(3, usuario.getTelefone());
			pst.setString(4, usuario.getCelular());
			pst.setString(5, usuario.getWhatsapp());
			pst.setString(6, usuario.getTelegram());
			pst.setString(7, usuario.getFacebook());
			pst.setString(8, usuario.getInstagram());
			pst.setString(9, usuario.getTwiter());
			if (usuario.getDtNascimento() != null) {
				pst.setDate(10, DataUtils.convertToDate(usuario.getDtNascimento()));
			} else {
				pst.setNull(10, Types.NULL);
			}
			pst.setInt(11, usuario.getIdCidade());
			pst.setInt(12, usuario.getIdPerfil());
			pst.setInt(13, usuario.getIdGrauInstrucao());
			pst.setInt(14, usuario.getIdProfissao());
			pst.setBoolean(15, usuario.getAtivo());
			pst.setLong(16, usuario.getIdUsuario());

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

	public boolean excluirUsuario(Long idUsuario) {
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM usuario  WHERE id_usuario = ?");
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());

			pst.setLong(1, idUsuario);

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

	public boolean alterarSenha(Long idUsuario, String senha, String pw) {
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE usuario SET senha = ?, pw = ? WHERE id_usuario = ?");
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());

			pst.setString(1, senha);
			pst.setString(2, pw);
			pst.setLong(3, idUsuario);

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

	public List<GraficoPorCidade> carregarGraficoEleitoresPorCidade() {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<GraficoPorCidade> graficos = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT c.nome as cidade, count(u.id_usuario) as quantidade");
			sql.append(" FROM usuario u JOIN cidade c ON u.id_cidade = c.id_cidade");
			sql.append(" WHERE u.id_perfil = 4");
			sql.append(" GROUP BY c.nome;");
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			if (rs.next()) {
				graficos = new ArrayList<GraficoPorCidade>();
				do {
					GraficoPorCidade grafico = new GraficoPorCidade();
					grafico.setCidade(rs.getString("cidade"));
					grafico.setQuantidade(rs.getInt("quantidade"));
					graficos.add(grafico);
				} while (rs.next());
			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Conexao.closeConnection(conn, pst, rs);
		}
		return graficos;
	}

	public List<GraficoPorCidade> carregarGraficoColaboradoresPorCidade() {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<GraficoPorCidade> graficos = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT c.nome as cidade, count(u.id_usuario) as quantidade");
			sql.append(" FROM usuario u JOIN cidade c ON u.id_cidade = c.id_cidade");
			sql.append(" WHERE u.id_perfil = 3");
			sql.append(" GROUP BY c.nome;");
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			if (rs.next()) {
				graficos = new ArrayList<GraficoPorCidade>();
				do {
					GraficoPorCidade grafico = new GraficoPorCidade();
					grafico.setCidade(rs.getString("cidade"));
					grafico.setQuantidade(rs.getInt("quantidade"));
					graficos.add(grafico);
				} while (rs.next());
			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Conexao.closeConnection(conn, pst, rs);
		}
		return graficos;
	}

	public Usuario carregarUsuarioPorCpf(String cpf) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Usuario usuario = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM usuario where cpf=?");
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, cpf);
			rs = pst.executeQuery();

			if (rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setPw(rs.getString("pw"));
				usuario.setEmail(rs.getString("email"));
				usuario.setAtivo(rs.getBoolean("ativo"));
				usuario.setIdCidade(rs.getInt("id_cidade"));
				usuario.setIdGrauInstrucao(rs.getInt("id_grauInstrucao"));
				usuario.setIdProfissao(rs.getInt("id_profissao"));
				usuario.setIdPerfil(rs.getInt("id_perfil"));
				usuario.setIdResponsavel(rs.getLong("id_responsavel"));
				if (rs.getDate("dt_nascimento") != null) {
					usuario.setDtNascimento(DataUtils.convertToLocalDate(rs.getDate("dt_nascimento")));
				}
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setCelular(rs.getString("celular"));
				usuario.setWhatsapp(rs.getString("whatsapp"));
				usuario.setTelegram(rs.getString("telegram"));
				usuario.setInstagram(rs.getString("instagram"));
				usuario.setFacebook(rs.getString("facebook"));
				usuario.setTwiter(rs.getString("twiter"));

				Cidade cidade = carregarCidadePorId(usuario.getIdCidade());
				Perfil perfil = carregarPerfilPorId(usuario.getIdPerfil());
				GrauInstrucao grauInstrucao = carregarGrauInstrucaoPorId(usuario.getIdGrauInstrucao());
				Profissao profissao = carregarProfissoesPorId(usuario.getIdProfissao());
				
				usuario.setCidade(cidade);
				usuario.setPerfil(perfil);
				usuario.setGrauIntrucao(grauInstrucao);
				usuario.setProfissao(profissao);

			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Conexao.closeConnection(conn, pst, rs);
		}
		return usuario;
	}

	public Usuario carregarUsuarioPorId(Long idUsuario) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Usuario usuario = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM usuario where id_usuario=?");
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			pst.setLong(1, idUsuario);
			rs = pst.executeQuery();

			if (rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setPw(rs.getString("pw"));
				usuario.setEmail(rs.getString("email"));
				usuario.setAtivo(rs.getBoolean("ativo"));
				usuario.setIdCidade(rs.getInt("id_cidade"));
				usuario.setIdGrauInstrucao(rs.getInt("id_grauInstrucao"));
				usuario.setIdProfissao(rs.getInt("id_profissao"));
				usuario.setIdPerfil(rs.getInt("id_perfil"));
				usuario.setIdResponsavel(rs.getLong("id_responsavel"));
				if (rs.getDate("dt_nascimento") != null) {
					usuario.setDtNascimento(DataUtils.convertToLocalDate(rs.getDate("dt_nascimento")));
				}
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setCelular(rs.getString("celular"));
				usuario.setWhatsapp(rs.getString("whatsapp"));
				usuario.setTelegram(rs.getString("telegram"));
				usuario.setInstagram(rs.getString("instagram"));
				usuario.setFacebook(rs.getString("facebook"));
				usuario.setTwiter(rs.getString("twiter"));
					
				Cidade cidade = carregarCidadePorId(usuario.getIdCidade());
				Perfil perfil = carregarPerfilPorId(usuario.getIdPerfil());
				GrauInstrucao grauInstrucao = carregarGrauInstrucaoPorId(usuario.getIdGrauInstrucao());
				Profissao profissao = carregarProfissoesPorId(usuario.getIdProfissao());

				usuario.setCidade(cidade);
				usuario.setPerfil(perfil);
				usuario.setGrauIntrucao(grauInstrucao);
				usuario.setProfissao(profissao);
			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Conexao.closeConnection(conn, pst, rs);
		}
		return usuario;
	}

	public Usuario autenticarUsuarioPorCpf(String cpf){
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Usuario usuario = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM usuario where cpf=? AND (id_perfil != 4 AND ativo = true)");			
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, cpf);
            rs = pst.executeQuery();
            
            if(rs.next()){
               usuario = new Usuario();
         	   usuario.setIdUsuario(rs.getLong("id_usuario"));
         	   usuario.setNome(rs.getString("nome"));
         	   usuario.setCpf(rs.getString("cpf"));
         	   usuario.setSenha(rs.getString("senha"));
         	   usuario.setEmail(rs.getString("email"));
         	   usuario.setAtivo(rs.getBoolean("ativo"));
         	   usuario.setIdCidade(rs.getInt("id_cidade"));
         	   usuario.setIdPerfil(rs.getInt("id_perfil"));
         	   
         	   Cidade cidade = carregarCidadePorId(usuario.getIdCidade());
         	   Perfil perfil = carregarPerfilPorId(usuario.getIdPerfil());
         	   
         	   usuario.setCidade(cidade);
         	   usuario.setPerfil(perfil);
         	   
            }		
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
			e.printStackTrace();
		}finally {
			Conexao.closeConnection(conn, pst, rs);
		}
		return usuario;
	}
	

	public List<Usuario> carregarUsuarios(Usuario usuarioLogado, FiltroDTO filtro) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Usuario> usuarios = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM usuario  WHERE 1=1");
			if (usuarioLogado.getIdPerfil().equals(PERFIL.COLABORADOR.getCodigo())) {
				sql.append(" AND id_responsavel = " + usuarioLogado.getIdUsuario());
			}
			if (filtro.getIdPerfil() != null) {
				sql.append(" AND id_perfil = " + filtro.getIdPerfil());
			}
			if (filtro.getIdCidade() != null) {
				sql.append(" AND id_cidade = " + filtro.getIdCidade());
			}
			sql.append(" ORDER BY nome ASC");
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			rs = pst.executeQuery();

			if (rs.next()) {
				usuarios = new ArrayList<Usuario>();
				do {
					Usuario usuario = new Usuario();
					usuario.setIdUsuario(rs.getLong("id_usuario"));
					usuario.setIdResponsavel(rs.getLong("id_responsavel"));
					usuario.setNome(rs.getString("nome"));
					usuario.setCpf(rs.getString("cpf"));
					usuario.setSenha(rs.getString("senha"));
					usuario.setEmail(rs.getString("email"));
					usuario.setTelefone(rs.getString("telefone"));
					usuario.setCelular(rs.getString("celular"));
					usuario.setWhatsapp(rs.getString("whatsapp"));
					usuario.setTelegram(rs.getString("telegram"));
					usuario.setInstagram(rs.getString("instagram"));
					usuario.setFacebook(rs.getString("facebook"));
					usuario.setTwiter(rs.getString("twiter"));
					usuario.setTitulo(rs.getString("titulo"));
					usuario.setZona(rs.getString("zona"));
					usuario.setSecao(rs.getString("secao"));
					if (rs.getDate("dt_nascimento") != null) {
						usuario.setDtNascimento(DataUtils.convertToLocalDate(rs.getDate("dt_nascimento")));
					}
					usuario.setDtRegistro(DataUtils.convertToLocalDate(rs.getDate("dt_registro")));
					usuario.setIdCidade(rs.getInt("id_cidade"));
					usuario.setIdPerfil(rs.getInt("id_perfil"));
					usuario.setAtivo(rs.getBoolean("ativo"));

					Cidade cidade = carregarCidadePorId(usuario.getIdCidade());
					Perfil perfil = carregarPerfilPorId(usuario.getIdPerfil());

					usuario.setCidade(cidade);
					usuario.setPerfil(perfil);
					usuarios.add(usuario);
				} while (rs.next());
			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Conexao.closeConnection(conn, pst, rs);
		}
		return usuarios;
	}

	public List<Usuario> carregarColaboradores() {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Usuario> usuarios = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM usuario  WHERE id_perfil in(1,2,3)");
			sql.append(" ORDER BY nome ASC");
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			rs = pst.executeQuery();

			if (rs.next()) {
				usuarios = new ArrayList<Usuario>();
				do {
					Usuario usuario = new Usuario();
					usuario.setIdUsuario(rs.getLong("id_usuario"));
					usuario.setIdResponsavel(rs.getLong("id_responsavel"));
					usuario.setNome(rs.getString("nome"));
					usuario.setCpf(rs.getString("cpf"));
					usuario.setSenha(rs.getString("senha"));
					usuario.setEmail(rs.getString("email"));
					usuario.setTelefone(rs.getString("telefone"));
					usuario.setCelular(rs.getString("celular"));
					usuario.setWhatsapp(rs.getString("whatsapp"));
					usuario.setTelegram(rs.getString("telegram"));
					usuario.setInstagram(rs.getString("instagram"));
					usuario.setFacebook(rs.getString("facebook"));
					usuario.setTwiter(rs.getString("twiter"));
					usuario.setTitulo(rs.getString("titulo"));
					usuario.setZona(rs.getString("zona"));
					usuario.setSecao(rs.getString("secao"));
					if (rs.getDate("dt_nascimento") != null) {
						usuario.setDtNascimento(DataUtils.convertToLocalDate(rs.getDate("dt_nascimento")));
					}
					usuario.setDtRegistro(DataUtils.convertToLocalDate(rs.getDate("dt_registro")));
					usuario.setIdCidade(rs.getInt("id_cidade"));
					usuario.setIdPerfil(rs.getInt("id_perfil"));
					usuario.setAtivo(rs.getBoolean("ativo"));

					Cidade cidade = carregarCidadePorId(usuario.getIdCidade());
					Perfil perfil = carregarPerfilPorId(usuario.getIdPerfil());

					usuario.setCidade(cidade);
					usuario.setPerfil(perfil);
					usuarios.add(usuario);
				} while (rs.next());
			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Conexao.closeConnection(conn, pst, rs);
		}
		return usuarios;
	}

	public List<Perfil> carregarPerfisPorPerfilUsuario(Integer idPerfil) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Perfil> perfis = null;
		try {
			StringBuilder sql = new StringBuilder();
			if (idPerfil.equals(PERFIL.ADMINISTRADOR.getCodigo())) {
				sql.append("SELECT * FROM perfil");
			} else if (idPerfil.equals(PERFIL.COORDENADOR.getCodigo())) {
				sql.append("SELECT * FROM perfil WHERE id_perfil NOT IN(1,2)");
			} else if (idPerfil.equals(PERFIL.COLABORADOR.getCodigo())) {
				sql.append("SELECT * FROM perfil WHERE id_perfil NOT IN(1,2)");
			}
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			if (rs.next()) {
				perfis = new ArrayList<Perfil>();
				do {
					Perfil perfil = new Perfil();
					perfil.setIdPerfil(rs.getInt("id_perfil"));
					perfil.setNome(rs.getString("nome"));
					perfil.setDescricao(rs.getString("descricao"));
					perfis.add(perfil);
				} while (rs.next());
			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Conexao.closeConnection(conn, pst, rs);
		}
		return perfis;
	}

	public List<Perfil> carregarPerfis() {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Perfil> perfis = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM perfil");
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			if (rs.next()) {
				perfis = new ArrayList<Perfil>();
				do {
					Perfil perfil = new Perfil();
					perfil.setIdPerfil(rs.getInt("id_perfil"));
					perfil.setNome(rs.getString("nome"));
					perfil.setDescricao(rs.getString("descricao"));
					perfis.add(perfil);
				} while (rs.next());
			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Conexao.closeConnection(conn, pst, rs);
		}
		return perfis;
	}

	public Perfil carregarPerfilPorId(Integer idPerfil) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Perfil perfil = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM perfil where id_perfil = ?");
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, idPerfil);
			rs = pst.executeQuery();
			if (rs.next()) {
				perfil = new Perfil();
				perfil.setIdPerfil(rs.getInt("id_perfil"));
				perfil.setNome(rs.getString("nome"));
				perfil.setDescricao(rs.getString("descricao"));
			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Conexao.closeConnection(conn, pst, rs);
		}
		return perfil;
	}

	public Cidade carregarCidadePorId(Integer idCidade) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Cidade cidade = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM cidade where id_cidade = ?");
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, idCidade);
			rs = pst.executeQuery();
			if (rs.next()) {
				cidade = new Cidade();
				cidade.setIdCidade(rs.getInt("id_cidade"));
				cidade.setNome(rs.getString("nome"));
				cidade.setLatitude(rs.getDouble("latitude"));
				cidade.setLongitude(rs.getDouble("longitude"));
			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Conexao.closeConnection(conn, pst, rs);
		}
		return cidade;
	}
	
	
	public GrauInstrucao carregarGrauInstrucaoPorId(Integer idGrauInstrucao) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		GrauInstrucao grauInstrucao = null;
		try { 
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM grau_instrucao WHERE id_grauInstrucao = ?");
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, idGrauInstrucao);
			rs = pst.executeQuery();
			if (rs.next()) {
				grauInstrucao = new GrauInstrucao();
				grauInstrucao.setIdGrauInstrucao(rs.getInt("id_grauInstrucao"));
				grauInstrucao.setDescricao(rs.getString("descricao"));
			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Conexao.closeConnection(conn, pst, rs);
		}
		return grauInstrucao;
	}

	public Profissao carregarProfissoesPorId(Integer idProfissao) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Profissao profissao = null;
		try { 
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM profissao WHERE id_profissao = ?");
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, idProfissao);
			rs = pst.executeQuery();
			if (rs.next()) {
				profissao = new Profissao();
				profissao.setIdProfissao(rs.getInt("id_profissao"));
				profissao.setDescricao(rs.getString("descricao"));
			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Conexao.closeConnection(conn, pst, rs);
		}
		return profissao;
	}

	public List<Cidade> carregarCidades() {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Cidade> cidades = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM cidade ORDER BY nome");
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			if (rs.next()) {
				cidades = new ArrayList<Cidade>();
				do {
					Cidade cidade = new Cidade();
					cidade.setIdCidade(rs.getInt("id_cidade"));
					cidade.setNome(rs.getString("nome"));
					cidade.setLatitude(rs.getDouble("latitude"));
					cidade.setLongitude(rs.getDouble("longitude"));
					cidades.add(cidade);
				} while (rs.next());
			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Conexao.closeConnection(conn, pst, rs);
		}
		return cidades;
	}
	
	
	public List<GrauInstrucao> carregarGrauInstrucoes() {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<GrauInstrucao> grauInstrucoes= null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM grau_instrucao ORDER BY descricao");
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			if (rs.next()) {
				grauInstrucoes = new ArrayList<GrauInstrucao>();
				do {
					GrauInstrucao grauInstrucao = new GrauInstrucao();
					grauInstrucao.setIdGrauInstrucao(rs.getInt("id_grauInstrucao"));
					grauInstrucao.setDescricao(rs.getString("descricao"));
					grauInstrucoes.add(grauInstrucao);
				} while (rs.next());
			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Conexao.closeConnection(conn, pst, rs);
		}
		return grauInstrucoes;
	}
	
	
	public List<Profissao> carregarProfissoes() {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Profissao> profissoes= null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM profissao ORDER BY descricao ASC");
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			if (rs.next()) {
				profissoes = new ArrayList<Profissao>();
				do {
					Profissao  profissao = new Profissao();
					profissao.setIdProfissao(rs.getInt("id_profissao"));
					profissao.setDescricao(rs.getString("descricao"));
					profissoes.add(profissao);
				} while (rs.next());
			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Conexao.closeConnection(conn, pst, rs);
		}
		return profissoes;
	}



	public Integer carregarTotalUsuarios(Long idUsuario, Integer idPerfil) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Integer total = new Integer(0);
		try {
			StringBuilder sql = new StringBuilder();
			if (idPerfil != null) {
				sql.append("SELECT COUNT(*) as total FROM usuario where id_perfil = " + idPerfil
						+ " AND id_responsavel = " + idUsuario);
			} else {
				sql.append("SELECT COUNT(*) as total FROM usuario where id_perfil IN(3,4) AND id_responsavel = "
						+ idUsuario);
			}
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			if (rs.next()) {
				total = rs.getInt("total");
			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Conexao.closeConnection(conn, pst, rs);
		}
		return total;
	}

	public boolean salvarMensagem(Mensagem mensagem) {
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(
					"INSERT INTO mensagem (id_remetente, id_destinatario, dt_registro, assunto, mensagem, ic_status)");
			sql.append(" VALUES(?,?,?,?,?,?)");
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			pst.setLong(1, mensagem.getIdRemetente());
			pst.setLong(2, mensagem.getIdDestinatario());
			pst.setDate(3, DataUtils.convertToDate(mensagem.getDtRegistro()));
			pst.setString(4, mensagem.getAssunto());
			pst.setString(5, mensagem.getMensagem());
			pst.setInt(6, mensagem.getStatus());

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

	public boolean alterarMensagem(Mensagem mensagem) {
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(
					"UPDATE mensagem SET id_destinatario = ?, assunto=?, mensagem=?, ic_status=? WHERE id_mensagem = ?");
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			pst.setLong(1, mensagem.getIdDestinatario());
			pst.setString(2, mensagem.getAssunto());
			pst.setString(3, mensagem.getMensagem());
			pst.setInt(4, mensagem.getStatus());
			pst.setLong(5, mensagem.getIdMensagem());

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

	public boolean excluirMensagem(Long idMensagem) {
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM mensagem WHERE id_mensagem = ?");
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			pst.setLong(1, idMensagem);

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

	public Mensagem carregarMensagemPorId(Long idMensagem) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Mensagem mensagem = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM mensagem  WHERE id_mensagem = " + idMensagem);
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			rs = pst.executeQuery();

			if (rs.next()) {
				mensagem = new Mensagem();
				mensagem.setIdMensagem(rs.getLong("id_mensagem"));
				mensagem.setIdRemetente(rs.getLong("id_remetente"));
				mensagem.setIdDestinatario(rs.getLong("id_destinatario"));
				mensagem.setDtRegistro(DataUtils.convertToLocalDate(rs.getDate("dt_registro")));
				mensagem.setAssunto(rs.getString("assunto"));
				mensagem.setMensagem(rs.getString("mensagem"));
				mensagem.setStatus(rs.getInt("ic_status"));
				Usuario remetente = carregarUsuarioPorId(mensagem.getIdRemetente());
				Usuario destinatario = carregarUsuarioPorId(mensagem.getIdDestinatario());
				mensagem.setRemetente(remetente);
				mensagem.setDestinatario(destinatario);
			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Conexao.closeConnection(conn, pst, rs);
		}
		return mensagem;
	}

	public List<Mensagem> carregarMensagensEnviadas(Long idUsuario) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Mensagem> mensagens = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM mensagem  WHERE id_remetente = " + idUsuario);
			sql.append(" ORDER BY dt_registro DESC");
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			rs = pst.executeQuery();

			if (rs.next()) {
				mensagens = new ArrayList<Mensagem>();
				do {
					Mensagem mensagem = new Mensagem();
					mensagem.setIdMensagem(rs.getLong("id_mensagem"));
					mensagem.setIdRemetente(rs.getLong("id_remetente"));
					mensagem.setIdDestinatario(rs.getLong("id_destinatario"));
					mensagem.setDtRegistro(DataUtils.convertToLocalDate(rs.getDate("dt_registro")));
					mensagem.setAssunto(rs.getString("assunto"));
					mensagem.setMensagem(rs.getString("mensagem"));
					mensagem.setStatus(rs.getInt("ic_status"));
					Usuario remetente = carregarUsuarioPorId(mensagem.getIdRemetente());
					Usuario destinatario = carregarUsuarioPorId(mensagem.getIdDestinatario());
					mensagem.setRemetente(remetente);
					mensagem.setDestinatario(destinatario);
					mensagens.add(mensagem);
				} while (rs.next());
			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Conexao.closeConnection(conn, pst, rs);
		}
		return mensagens;
	}

	public List<Mensagem> carregarMensagensRecebidas(Long idUsuario) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Mensagem> mensagens = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM mensagem  WHERE id_destinatario = " + idUsuario);
			sql.append(" ORDER BY dt_registro DESC");
			conn = Conexao.getConnection();
			pst = conn.prepareStatement(sql.toString());
			rs = pst.executeQuery();

			if (rs.next()) {
				mensagens = new ArrayList<Mensagem>();
				do {
					Mensagem mensagem = new Mensagem();
					mensagem.setIdMensagem(rs.getLong("id_mensagem"));
					mensagem.setIdRemetente(rs.getLong("id_remetente"));
					mensagem.setIdDestinatario(rs.getLong("id_destinatario"));
					mensagem.setDtRegistro(DataUtils.convertToLocalDate(rs.getDate("dt_registro")));
					mensagem.setAssunto(rs.getString("assunto"));
					mensagem.setMensagem(rs.getString("mensagem"));
					mensagem.setStatus(rs.getInt("ic_status"));
					Usuario remetente = carregarUsuarioPorId(mensagem.getIdRemetente());
					Usuario destinatario = carregarUsuarioPorId(mensagem.getIdDestinatario());
					mensagem.setRemetente(remetente);
					mensagem.setDestinatario(destinatario);
					mensagens.add(mensagem);
				} while (rs.next());
			}
		} catch (Exception e) {
			logger.error("#>> Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Conexao.closeConnection(conn, pst, rs);
		}
		return mensagens;
	}
}
