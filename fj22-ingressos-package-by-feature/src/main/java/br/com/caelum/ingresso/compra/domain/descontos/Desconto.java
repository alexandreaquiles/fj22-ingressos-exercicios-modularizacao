package br.com.caelum.ingresso.compra.domain.descontos;

import java.math.BigDecimal;

public interface Desconto {

	BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal);
	String getDescricao();
	
}
