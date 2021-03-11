package br.gov.prefeitura.msc.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gov.prefeitura.msc.service.ImovelDto;
import br.gov.prefeitura.msc.service.PesquisarIptuService;

@Component
@SessionScoped
public class BeanIptu implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PesquisarIptuService pesquisarIptuService;

	private String inscricaoImovel;
	private ImovelDto dto;
	
	public void pesquisar()
	{
		List<ImovelDto> dtos = pesquisarIptuService.pesquisar(inscricaoImovel);
		if (dtos != null && !dtos.isEmpty())
		{
			dto = dtos.get(0);
			if (!dto.getMensagem().equals("Sucesso"))
			{
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Falhou",  dto.getMensagem()) );
			} 
		}
		else
		{
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Falhou",  "Nenhum registro encontrato") );
		}
	}
	
	public void limpar()
	{
		inscricaoImovel = "";
		dto = null;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public ImovelDto getDto() {
		return dto;
	}
}