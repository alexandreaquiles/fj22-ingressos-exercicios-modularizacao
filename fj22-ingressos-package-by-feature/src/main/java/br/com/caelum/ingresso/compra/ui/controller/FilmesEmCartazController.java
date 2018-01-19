package br.com.caelum.ingresso.compra.ui.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.compra.domain.DetalhesDoFilme;
import br.com.caelum.ingresso.compra.domain.ImdbClient;
import br.com.caelum.ingresso.core.domain.Filme;
import br.com.caelum.ingresso.core.domain.FilmeRepository;
import br.com.caelum.ingresso.core.domain.Sessao;
import br.com.caelum.ingresso.core.domain.SessaoRepository;

@Controller
public class FilmesEmCartazController {

	@Autowired
	private FilmeRepository filmes;

	@Autowired
	private SessaoRepository sessoes;

	@Autowired
	private ImdbClient client;

	@GetMapping("/filme/em-cartaz")
	public ModelAndView emCartaz() {
		ModelAndView modelAndView = new ModelAndView("filme/em-cartaz");
		modelAndView.addObject("filmes", filmes.findAll());
		return modelAndView;
	}

	@GetMapping("/filme/{id}/detalhe")
	public ModelAndView detalhe(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("/filme/detalhe");
		Filme filme = filmes.findOne(id);
		List<Sessao> sessoesDoFilme = sessoes.buscaSessoesDoFilme(filme);

		Optional<DetalhesDoFilme> detalhesDoFilme = client.request(filme, DetalhesDoFilme.class);

		modelAndView.addObject("sessoes", sessoesDoFilme);
		modelAndView.addObject("detalhes", detalhesDoFilme.orElse(new DetalhesDoFilme()));

		return modelAndView;
	}
}
