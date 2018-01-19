package br.com.caelum.ingresso.ui.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.domain.Carrinho;
import br.com.caelum.ingresso.domain.Cartao;
import br.com.caelum.ingresso.infra.dao.CompraDao;
import br.com.caelum.ingresso.infra.dao.LugarDao;
import br.com.caelum.ingresso.infra.dao.SessaoDao;
import br.com.caelum.ingresso.ui.form.CarrinhoForm;

@Controller
public class CompraController {

	@Autowired
	private SessaoDao sessaoDao;

	@Autowired
	private LugarDao lugarDao;

	@Autowired
	private	CompraDao	compraDao;
	
	@Autowired
	private Carrinho carrinho;

	@PostMapping("/compra/ingressos")
	public ModelAndView enviarParaPagamento(CarrinhoForm carrinhoForm) {
		ModelAndView modelAndView = new ModelAndView("redirect:/compra");
		carrinhoForm.toIngressos(sessaoDao, lugarDao).forEach(carrinho::add);
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
		
		compraDao.save(carrinho.toCompra());
		carrinho.limpa();
		return new ModelAndView("redirect:/");
	}
}
