package br.com.caelum.ingresso.compra.infra.rest;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.compra.domain.ImdbClient;
import br.com.caelum.ingresso.core.domain.Filme;

@Component
public class RestImdbClient implements ImdbClient {

	private Logger logger = Logger.getLogger(RestImdbClient.class);

	public <T> Optional<T> request(Filme filme, Class<T> tClass) {
		RestTemplate client = new RestTemplate();

		String titulo = filme.getNome().replace(" ", "+");

		String url = String.format("https://imdb-fj22.herokuapp.com/imdb?title=%s", titulo);

		try {
			return Optional.of( client.getForObject(url, tClass));
		} catch (RestClientException e) {
			logger.error(e.getMessage(), e);
			return Optional.empty();
		}

	}

}
