package br.com.caelum.ingresso.compra.ui.form;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.caelum.ingresso.compra.domain.Ingresso;
import br.com.caelum.ingresso.compra.domain.TipoDeIngresso;
import br.com.caelum.ingresso.core.domain.Lugar;
import br.com.caelum.ingresso.core.domain.LugarRepository;
import br.com.caelum.ingresso.core.domain.Sessao;
import br.com.caelum.ingresso.core.domain.SessaoRepository;

public class CarrinhoForm {
	private List<Ingresso> ingressos = new ArrayList<>();

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	public List<Ingresso> toIngressos(SessaoRepository sessoes, LugarRepository lugares) {
		return this.ingressos.stream().map(ingresso -> {
			Sessao sessao = sessoes.findOne(ingresso.getSessao().getId());
			Lugar lugar = lugares.findOne(ingresso.getLugar().getId());
			TipoDeIngresso tipoDeIngresso = ingresso.getTipoDeIngresso();
			return new Ingresso(sessao, tipoDeIngresso, lugar);
		}).collect(Collectors.toList());
	}

}
