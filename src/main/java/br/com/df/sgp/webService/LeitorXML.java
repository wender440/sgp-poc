package br.com.df.sgp.webService;

import java.io.InputStream;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.df.sgp.model.Candidato;

public class LeitorXML {

	@SuppressWarnings("unchecked")
	public List<Candidato> carrega(InputStream inputStream){
		XStream stream = new XStream(new DomDriver());
		stream.alias("ObterDeputados", Candidato.class);
		return (List<Candidato>) stream.fromXML(inputStream);
	}
}
