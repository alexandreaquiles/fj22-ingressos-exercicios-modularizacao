package br.com.caelum.ingresso.compra.ui.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.compra.domain.Carrinho;
import br.com.caelum.ingresso.compra.domain.Cartao;
import br.com.caelum.ingresso.compra.domain.CompraRepository;
import br.com.caelum.ingresso.compra.ui.form.CarrinhoForm;
import br.com.caelum.ingresso.core.domain.LugarRepository;
import br.com.caelum.ingresso.core.domain.SessaoRepository;

@Controller
public class CompraController {

	@Autowired
	private SessaoRepository sessoes;

	@Autowired
	private LugarRepository lugares;

	@Autowired
	private	CompraRepository compras;
	
	@Autowired
	private Carrinho carrinho;

	@PostMapping("/compra/ingressos")
	public ModelAndView enviarParaPagamento(CarrinhoForm carrinhoForm) {
		ModelAndView modelAndView = new ModelAndView("redirect:/compra");
		carrinhoForm.toIngressos(sessoes, lugares).forEach(carrinho::add);
		return modelAndView;
	}

	@GetMapping("/compra")
	public ModelAndView checkout(Cartao cartao) {
		ModelAndView modelAndView = new ModelAndView("compra/pagamento");
		modelAndView.addObject("carrinho", carrinho);
		return modelAndView;
	}

	@PostMapping("/compra/comprar")
	@Transactional
	public ModelAndView comprar(@Valid Cartao cartao, BindingResult result) {
		if (result.hasErrors()) {
			return checkout(cartao);
		}
		
		if (!cartao.isValido()) {
			result.addError(new FieldError("cartao", "vencimento", "Vencimento inválido."));
			return checkout(cartao);
		}
		
		compras.save(carrinho.toCompra());
		carrinho.limpa();
		return new ModelAndView("redirect:/");
	}
}
