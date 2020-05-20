package br.com.df.sgp.util;

public class TelefoneUtils {

	public static String clean(final String telefone) {
		if(telefone != null && !telefone.equals("")){
		String telefoneLimpo = telefone.trim();
		telefoneLimpo = telefoneLimpo.replaceAll("\\(", "");
		telefoneLimpo = telefoneLimpo.replaceAll("\\)", "");
		telefoneLimpo = telefoneLimpo.replaceAll("\\-", "");
		telefoneLimpo = telefoneLimpo.replaceAll(" ", "");
		return telefoneLimpo;
		}else{
			return null;
		}
	}
	
	public static String format(String telefone) {
		if(telefone != null && !telefone.equals("")){
		StringBuilder builder = new StringBuilder(telefone.replaceAll("[^\\d]", ""));
		builder.insert(0, '(');
		builder.insert(3, ')');
		builder.insert(8, '-');		
		return builder.toString();
		}else{
			return null;
		}
	}
	
}
