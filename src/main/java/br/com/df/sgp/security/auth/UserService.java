package br.com.df.sgp.security.auth;

import org.springframework.stereotype.Service;

import br.com.df.sgp.model.Perfil;
import br.com.df.sgp.model.Usuario;
import br.com.df.sgp.service.CadastroService;


@Service
public class UserService {
	
	public Usuario pesquisarPorCpf(String cpf) {
		Usuario usuario = CadastroService.autenticarUsuarioPorCpf(cpf);
		if(usuario != null){
			Perfil perfil = CadastroService.carregarPerfilPorId(usuario.getIdPerfil());
			usuario.setPerfil(perfil);
		}
		return usuario; 
	}
}
