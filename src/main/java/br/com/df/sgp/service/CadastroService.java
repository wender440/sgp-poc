package br.com.df.sgp.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import br.com.df.sgp.constantes.PERFIL;
import br.com.df.sgp.dao.CadastroDAO;
import br.com.df.sgp.dto.FiltroDTO;
import br.com.df.sgp.model.Cidade;
import br.com.df.sgp.model.GraficoPorCidade;
import br.com.df.sgp.model.GrauInstrucao;
import br.com.df.sgp.model.Mensagem;
import br.com.df.sgp.model.Profissao;
import br.com.df.sgp.model.Perfil;
import br.com.df.sgp.model.Usuario;
import br.com.df.sgp.util.SenhaUtils;

public class CadastroService {

	private static CadastroDAO cadastroDAO = new CadastroDAO();

	public static boolean salvarUsuario(Usuario usuario) {
		if (usuario.getIdPerfil().equals(PERFIL.COLABORADOR.getCodigo())
				|| usuario.getIdPerfil().equals(PERFIL.COORDENADOR.getCodigo())) {
			try {
				String senhaAleatoria = SenhaUtils.produzirSenha();
				String senha = SenhaUtils.produzirChaveMD5(senhaAleatoria);
				usuario.setPw(senhaAleatoria);
				usuario.setSenha(senha);
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException("Erro ao gerar a senha do usuário");
			} catch (Exception e) {
				throw new RuntimeException("Erro ao cadastrar o usuário");
			}
		}
		return cadastroDAO.salvarUsuario(usuario);
	}

	public static boolean alterarUsuario(Usuario usuario) {
		return cadastroDAO.alterarUsuario(usuario);
	}

	public static boolean excluirUsuario(Long idUsuario) {
		return cadastroDAO.excluirUsuario(idUsuario);
	}

	public static boolean alterarSenha(Long idUsuario, String senha, String pw) {
		return cadastroDAO.alterarSenha(idUsuario, senha, pw);
	}

	public static List<GraficoPorCidade> carregarGraficoEleitores() {
		return cadastroDAO.carregarGraficoEleitoresPorCidade();
	}

	public static List<GraficoPorCidade> carregarGraficoColaboradores() {
		return cadastroDAO.carregarGraficoColaboradoresPorCidade();
	}

	public static List<Usuario> carregarUsuarios(Usuario usuarioLogado, FiltroDTO filtro) {
		return cadastroDAO.carregarUsuarios(usuarioLogado, filtro);
	}

	public static List<Usuario> carregarColaboradores() {
		return cadastroDAO.carregarColaboradores();
	}

	public static Usuario carregarUsuarioPorCpf(String cpf) {
		return cadastroDAO.carregarUsuarioPorCpf(cpf);
	}

	public static Usuario carregarUsuarioPorId(Long idUsuario) {
		return cadastroDAO.carregarUsuarioPorId(idUsuario);
	}

	public static Usuario autenticarUsuarioPorCpf(String cpf) {
		return cadastroDAO.autenticarUsuarioPorCpf(cpf);
	}

	public static Perfil carregarPerfilPorId(Integer idPerfil) {
		return cadastroDAO.carregarPerfilPorId(idPerfil);
	}

	public static List<Perfil> carregarPerfisPorPerfilUsuario(Integer idPerfil) {
		return cadastroDAO.carregarPerfisPorPerfilUsuario(idPerfil);
	}

	public Cidade carregarCidadePorId(Integer idCidade) {
		return cadastroDAO.carregarCidadePorId(idCidade);
	}

	public static List<Cidade> carregarCidades() {
		return cadastroDAO.carregarCidades();
	}

	public static List<GrauInstrucao> carregarGrauInstrucoes() {
		return cadastroDAO.carregarGrauInstrucoes();
	}

	public static List<Profissao> carregarProfissoes() {
		return cadastroDAO.carregarProfissoes();
	}

	public static Integer carregarTotalUsuarios(Long idUsuario, Integer idPerfil) {
		return cadastroDAO.carregarTotalUsuarios(idUsuario, idPerfil);
	}

	public static boolean salvarMensagem(Mensagem mensagem) {
		return cadastroDAO.salvarMensagem(mensagem);
	}

	public static boolean alterarMensagem(Mensagem mensagem) {
		return cadastroDAO.alterarMensagem(mensagem);
	}

	public static boolean excluirMensagem(Long idMensagem) {
		return cadastroDAO.excluirMensagem(idMensagem);
	}

	public static Mensagem carregarMensagemPorId(Long idMensagem) {
		return cadastroDAO.carregarMensagemPorId(idMensagem);
	}

	public static List<Mensagem> carregarMensagensEnviadas(Long idUsuario) {
		return cadastroDAO.carregarMensagensEnviadas(idUsuario);
	}

	public static List<Mensagem> carregarMensagensRecebidas(Long idUsuario) {
		return cadastroDAO.carregarMensagensRecebidas(idUsuario);
	}

}
