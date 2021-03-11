package br.gov.prefeitura.msc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class PesquisarIptuService {
	
	@Value("${app.url.stur}")
	private String urlStur;

	public List<ImovelDto> pesquisar(String inscricaoImovel) {
		try {
			String url = retornarUrl(inscricaoImovel);

			System.out.println(url);

			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");

			if (conn.getResponseCode() != 200) {
				System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()), "UTF-8"));

			String output = "";
			String line;
			while ((line = br.readLine()) != null) {
				output += line;
			}

			conn.disconnect();

			Gson gson = new Gson();

			ImovelDto[] dto = gson.fromJson(new String(output.getBytes()), ImovelDto[].class);
			List<ImovelDto> dtos = new ArrayList<ImovelDto>();
			for (ImovelDto a : dto) {
				dtos.add(a);
			}
			return dtos;

		} catch (IOException ex) {
			ex.getMessage();
		}
		return null;
	}

	public String retornarUrl(String inscricaoImovel) {
		return urlStur + "?inscricaoImovel=" + inscricaoImovel;
	}
}
