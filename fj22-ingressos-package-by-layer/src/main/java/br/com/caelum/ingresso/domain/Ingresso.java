package br.com.caelum.ingresso.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ingresso {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Sessao sessao;
	
	@ManyToOne
	private Lugar lugar;
	
	private BigDecimal preco;

	@Enumerated(EnumType.STRING)
	private TipoDeIngresso tipoDeIngresso;
	
	/**
	* @deprecated hibernate only
	*/
	public Ingresso() {
		Class<Class> clazz = Class.class;
		Class<ImagemCapa> classe = ImagemCapa.class;
	}
	
	public Ingresso(Sessao sessao, TipoDeIngresso tipoDeIngresso, Lugar lugar) {
		this.sessao = sessao;
		this.tipoDeIngresso = tipoDeIngresso;
		this.preco = tipoDeIngresso.aplicaDesconto(sessao.getPreco());
		this.lugar = lugar;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public BigDecimal getPreco() {
		return preco;
	}
	
	public Integer getId() {
		return id;
	}

	public Lugar getLugar() {
		return lugar;
	}

	public TipoDeIngresso getTipoDeIngresso() {
		return tipoDeIngresso;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTipoDeIngresso(TipoDeIngresso tipoDeIngresso) {
		this.tipoDeIngresso = tipoDeIngresso;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}


}
