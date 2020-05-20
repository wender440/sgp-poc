package br.com.df.sgp.constantes;

public enum PERFIL {
	
	ADMINISTRADOR(new Integer(1),"Administrador"),
	COORDENADOR(new Integer(2), "Coordenador"),
	COLABORADOR(new Integer(3), "Colaborador"),
	ELEITOR(new Integer(4), "Eleitor");
	
	private Integer codigo;
	private String descricao;
	
	private PERFIL(Integer codigo, String descricao) {
	 this.codigo = codigo;
	 this.descricao = descricao;
	}
	
	public static String getDescricao(Integer codigo){
		for (PERFIL perfil : values()) {
			if(codigo != null){
				if(perfil.codigo.intValue() == codigo.intValue()){
					return perfil.getDescricao();
				}
			}
		}
		return null;
	}
	
	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
}
