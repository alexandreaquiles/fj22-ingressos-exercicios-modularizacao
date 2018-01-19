package br.com.caelum.ingresso.compra.domain;

import java.util.Optional;

import br.com.caelum.ingresso.core.domain.Filme;

public interface ImdbClient {

	<T> Optional<T> request(Filme filme, Class<T> tClass);

}