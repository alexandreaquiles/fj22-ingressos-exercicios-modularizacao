package br.com.caelum.ingresso.administracao.ui.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.administracao.domain.validacao.GerenciadorDeSessao;
import br.com.caelum.ingresso.administracao.ui.form.SessaoForm;
import br.com.caelum.ingresso.core.domain.FilmeRepository;
import br.com.caelum.ingresso.core.domain.SalaRepository;
import br.com.caelum.ingresso.core.domain.Sessao;
import br.com.caelum.ingresso.core.domain.SessaoRepository;

@Controller
public class SessaoController {

	@Autowired
	private SalaRepository salas;
	
	@Autowired
	private FilmeRepository filmes;
	
	@Autowired
	private SessaoRepository sessoes;

	@GetMapping("/admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer salaId, SessaoForm form) {
		ModelAndView modelAndView = new ModelAndView("sessao/sessao");

		modelAndView.addObject("sala", salas.findOne(salaId));
		modelAndView.addObject("filmes", filmes.findAll());
		modelAndView.addObject("form", form);

		return modelAndView;
	}

	@PostMapping("/admin/sessao")
	@Transactional
	public ModelAndView salva(@Valid SessaoForm form, BindingResult result) {
		if (result.hasErrors()) {
			return form(form.getSalaId(), form);
		}
		Sessao sessao = form.toSessao();
		List<Sessao> sessoesDaSala = sessoes.buscaSessoesDaSala(sessao.getSala());

		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoesDaSala);
		if (gerenciador.cabe(sessao)) {
			ModelAndView modelAndView =  new ModelAndView("redirect:/admin/sala/" + form.getSalaId() + "/sessoes");
			sessoes.save(sessao);
			return modelAndView;
		} else {
			result.addError(new FieldError("sessaoForm", "horario", "Já existe uma sessão nesse horário."));
		}
		return form(form.getSalaId(), form);

	}

}
