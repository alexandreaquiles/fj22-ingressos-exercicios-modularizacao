package br.com.caelum.ingresso.validacao;

import java.math.BigDecimal;

public class Main {
	
	public static void main(String[] args) {
		BigDecimal x = new BigDecimal("0.3");
		BigDecimal soma = x.add(x).add(x);
		
		BigDecimal zero = BigDecimal.ZERO;
		BigDecimal um = BigDecimal.ONE;
		BigDecimal dez = BigDecimal.TEN;
		
		// double x = 0.3;
		// double soma = x + x + x;
		System.out.println(soma);
		
		
	}

}
