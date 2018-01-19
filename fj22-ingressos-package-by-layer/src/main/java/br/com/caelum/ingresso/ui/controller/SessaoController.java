package br.com.caelum.ingresso.ui.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.domain.Carrinho;
import br.com.caelum.ingresso.domain.ImagemCapa;
import br.com.caelum.ingresso.domain.Sessao;
import br.com.caelum.ingresso.domain.TipoDeIngresso;
import br.com.caelum.ingresso.domain.validacao.GerenciadorDeSessao;
import br.com.caelum.ingresso.infra.dao.FilmeDao;
import br.com.caelum.ingresso.infra.dao.SalaDao;
import br.com.caelum.ingresso.infra.dao.SessaoDao;
import br.com.caelum.ingresso.infra.rest.ImdbClient;
import br.com.caelum.ingresso.ui.form.SessaoForm;

@Controller
public class SessaoController {

	@Autowired
	private SalaDao salaDao;
	@Autowired
	private FilmeDao filmeDao;
	
	@Autowired
	private SessaoDao sessaoDao;

	@Autowired
	private ImdbClient client;

	@Autowired
	private Carrinho carrinho;
	
	@GetMapping("/admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer salaId, SessaoForm form) {
		ModelAndView modelAndView = new ModelAndView("sessao/sessao");

		modelAndView.addObject("sala", salaDao.findOne(salaId));
		modelAndView.addObject("filmes", filmeDao.findAll());
		modelAndView.addObject("form", form);

		return modelAndView;
	}

	@PostMapping("/admin/sessao")
	@Transactional
	public ModelAndView salva(@Valid SessaoForm form, BindingResult result) {
		if (result.hasErrors()) {
			return form(form.getSalaId(), form);
		}
		Sessao sessao = form.toSessao(salaDao, filmeDao);
		List<Sessao> sessoesDaSala = sessaoDao.buscaSessoesDaSala(sessao.getSala());

		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoesDaSala);
		if (gerenciador.cabe(sessao)) {
			ModelAndView modelAndView =  new ModelAndView("redirect:/admin/sala/" + form.getSalaId() + "/sessoes");
			sessaoDao.save(sessao);
			return modelAndView;
		} else {
			result.addError(new FieldError("sessaoForm", "horario", "Já existe uma sessão nesse horário."));
		}
		return form(form.getSalaId(), form);

	}

	@GetMapping("/sessao/{id}/lugares")
	public ModelAndView lugaresNaSessao(@PathVariable("id") Integer sessaoId) {
		ModelAndView modelAndView = new ModelAndView("sessao/lugares");
		Sessao sessao = sessaoDao.buscaSessaoComIngressos(sessaoId);
		Optional<ImagemCapa> imagemCapa = client.request(sessao.getFilme(), ImagemCapa.class);
		modelAndView.addObject("sessao", sessao);
		modelAndView.addObject("carrinho", carrinho);
		modelAndView.addObject("imagemCapa", imagemCapa.orElse(new ImagemCapa()));
		modelAndView.addObject("tiposDeIngressos", TipoDeIngresso.values());
		
		return modelAndView;
	}

}
