package br.gov.prefeitura.msc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import br.gov.prefeitura.msc.service.AutorizacaoService.LoginDTO;

public class Autorizador implements PhaseListener {
	private static final long serialVersionUID = 1L;

	@Override
    public void afterPhase(PhaseEvent event) {
		
		FacesContext context = event.getFacesContext();
		
        HttpSession httpSession = (HttpSession)context.getExternalContext().getSession(true);
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        String token = request.getParameter("token");
        if (token != null)
        	httpSession.setAttribute("token", token);

	    String t = (String) context.getExternalContext().getSessionMap().get("token");
	    String urlSgm = retornarSgm(request);
	    if(t != null) {
	    	LoginDTO dto = obter(urlSgm);
	    	if (dto != null && dto.getStatus())
	    		return;
	    }
	    
	    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    	try {
			ec.redirect(urlSgm + "index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	    context.renderResponse();
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
    private String retornarSgm(HttpServletRequest request) {
    	try {
    		URI uri = new URI(request.getRequestURL().toString());
			String url = uri.getScheme() + "://" + uri.getHost() +  ":8080/sgm/";
			return url;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
        return null;
	}
    
    private LoginDTO obter(String urlSgm) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String token = (String) context.getExternalContext().getSessionMap().get("token");
			String url = urlSgm + "autorizacao?token="+token;

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
}
