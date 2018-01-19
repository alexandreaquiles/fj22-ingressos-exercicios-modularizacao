package br.com.caelum.ingresso.compra.domain;

import java.math.BigDecimal;

import br.com.caelum.ingresso.compra.domain.descontos.Desconto;
import br.com.caelum.ingresso.compra.domain.descontos.DescontoDeTrintaPorCentoParaBancos;
import br.com.caelum.ingresso.compra.domain.descontos.DescontoEstudante;
import br.com.caelum.ingresso.compra.domain.descontos.SemDesconto;

public enum TipoDeIngresso {

	INTEIRO(new SemDesconto()),
	ESTUDANTE(new DescontoEstudante()),
	BANCO(new DescontoDeTrintaPorCentoParaBancos());
	
	private final Desconto desconto;
	
	private TipoDeIngresso(Desconto desconto) {
		this.desconto = desconto;
	}

	public BigDecimal aplicaDesconto(BigDecimal valor) {
		return desconto.aplicarDescontoSobre(valor);
	}

	public String getDescricao() {
		return desconto.getDescricao();
	}
	
}
