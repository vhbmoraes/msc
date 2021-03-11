package br.gov.prefeitura.msc.service;

import java.math.BigDecimal;

public class ImovelDto {
	private String mensagem;
	private Integer inscricaoImovel;
	private BigDecimal valorMetro;
	private BigDecimal valorVenal;
	
	private BigDecimal aliquota;
	private BigDecimal valorIptu;
	private BigDecimal valorAVista;
	private BigDecimal valorParcela1;
	private BigDecimal valorParcela2;
	private BigDecimal valorParcela3;
	private BigDecimal valorParcela4;
	private BigDecimal valorParcela5;
	private BigDecimal valorParcela6;
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Integer getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(Integer inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public BigDecimal getValorMetro() {
		return valorMetro;
	}
	public void setValorMetro(BigDecimal valorMetro) {
		this.valorMetro = valorMetro;
	}
	public BigDecimal getValorVenal() {
		return valorVenal;
	}
	public void setValorVenal(BigDecimal valorVenal) {
		this.valorVenal = valorVenal;
	}
	public BigDecimal getAliquota() {
		return aliquota;
	}
	public void setAliquota(BigDecimal aliquota) {
		this.aliquota = aliquota;
	}
	public BigDecimal getValorAVista() {
		return valorAVista;
	}
	public void setValorAVista(BigDecimal valorAVista) {
		this.valorAVista = valorAVista;
	}
	public BigDecimal getValorIptu() {
		return valorIptu;
	}
	public void setValorIptu(BigDecimal valorIptu) {
		this.valorIptu = valorIptu;
	}
	public BigDecimal getValorParcela1() {
		return valorParcela1;
	}
	public void setValorParcela1(BigDecimal valorParcela1) {
		this.valorParcela1 = valorParcela1;
	}
	public BigDecimal getValorParcela2() {
		return valorParcela2;
	}
	public void setValorParcela2(BigDecimal valorParcela2) {
		this.valorParcela2 = valorParcela2;
	}
	public BigDecimal getValorParcela3() {
		return valorParcela3;
	}
	public void setValorParcela3(BigDecimal valorParcela3) {
		this.valorParcela3 = valorParcela3;
	}
	public BigDecimal getValorParcela4() {
		return valorParcela4;
	}
	public void setValorParcela4(BigDecimal valorParcela4) {
		this.valorParcela4 = valorParcela4;
	}
	public BigDecimal getValorParcela5() {
		return valorParcela5;
	}
	public void setValorParcela5(BigDecimal valorParcela5) {
		this.valorParcela5 = valorParcela5;
	}
	public BigDecimal getValorParcela6() {
		return valorParcela6;
	}
	public void setValorParcela6(BigDecimal valorParcela6) {
		this.valorParcela6 = valorParcela6;
	}
}
