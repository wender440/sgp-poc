package br.com.df.sgp.webService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.df.sgp.model.Candidato;

/***
 * 
 * @author cicer
 *
 */

@ManagedBean
@ApplicationScoped
public class CandidatowebService {

	Candidato candidato = new Candidato();
	List<Candidato> candidatos = new ArrayList<>();

	public Map<String, List<String>> listaCandidatosGovernador() {
		Map<String, List<String>> nomes = new TreeMap<>();

		String path = "E:\\Dev\\Projetos\\sgp-2018\\src\\main\\webapp\\pages\\csv\\Lista-Candidatos-Governador.csv";
		String str = "";
		try (BufferedReader read = new BufferedReader(new FileReader(path))) {
			while ((str = read.readLine()) != null) {
				String[] list = str.split("[;]");
				String filds = list[0].toString();
				List<String> governadores = nomes.get(filds);
				if (governadores == null) {
					governadores = new ArrayList<>();
				}

				governadores.add(filds);
				nomes.put(filds, governadores);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return nomes;
	}

	public Map<String, List<String>> listaCandidatosDistrital() {

		Map<String, List<String>> nomes = new TreeMap<>();

		String path = "E:\\Dev\\Projetos\\sgp-2018\\src\\main\\webapp\\pages\\csv\\Lista-Candidatos-Deputado Distrital.csv";
		String str = "";

		try (BufferedReader read = new BufferedReader(new FileReader(path));) {
			while ((str = read.readLine()) != null) {
				String[] list = str.split("[;]");
				String filds = list[0].toString();
				List<String> distritais = nomes.get(filds);
				if (distritais == null) {
					distritais = new ArrayList<>();
				}
				distritais.add(filds);
				nomes.put(filds, distritais);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nomes;

	}

	public Map<String, List<String>> listaCandidatosFederal() {
		Map<String, List<String>> nomes = new TreeMap<>();

		String path = "E:\\Dev\\Projetos\\sgp-2018\\src\\main\\webapp\\pages\\csv\\Lista-Candidatos-Deputado Federal.csv";
		String str = "";

		try (BufferedReader read = new BufferedReader(new FileReader(path))) {
			while ((str = read.readLine()) != null) {
				String[] list = str.split("[;]");
				String filds = list[0].toString();
				List<String> federais = nomes.get(filds);
				if (federais == null) {
					federais = new ArrayList<>();
				}

				federais.add(filds);
				nomes.put(filds, federais);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return nomes;
	}

	public Map<String, List<String>> listaCandidatosSenador() {
		Map<String, List<String>> nomes = new TreeMap<>();

		String path = "E:\\Dev\\Projetos\\sgp-2018\\src\\main\\webapp\\pages\\csv\\Lista-Candidatos-Senador.csv";
		String str = "";

		try (BufferedReader read = new BufferedReader(new FileReader(path))) {
			while ((str = read.readLine()) != null) {
				String[] list = str.split("[;]");
				String filds = list[0].toString();
				List<String> senadores = nomes.get(filds);
				if (senadores == null) {
					senadores = new ArrayList<>();
				}

				senadores.add(filds);
				nomes.put(filds, senadores);
			}
		} catch (Exception e) {
			
		}
		return nomes;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public List<Candidato> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<Candidato> candidatos) {
		this.candidatos = candidatos;
	}
	

}