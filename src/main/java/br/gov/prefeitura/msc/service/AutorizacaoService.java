package br.gov.prefeitura.msc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class AutorizacaoService {
	
	@Value("${app.url.sgm}")
	private String urlSgm;
	
	public LoginDTO obter() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String token = (String) context.getExternalContext().getSessionMap().get("token");
			String url = urlSgm + "/autorizacao?token="+token;

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

			LoginDTO dto = gson.fromJson(new String(output.getBytes()), LoginDTO.class);
			return dto;
		} catch (IOException ex) {
			ex.getMessage();
		}
		return null;
	}
	
	public static class LoginDTO {
		private Boolean status;
		private String mensagem;
		private String login;
		
		public Boolean getStatus() {
			return status;
		}
		public void setStatus(Boolean status) {
			this.status = status;
		}
		public String getMensagem() {
			return mensagem;
		}
		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}
		public String getLogin() {
			return login;
		}
		public void setLogin(String login) {
			this.login = login;
		}
	}
}
