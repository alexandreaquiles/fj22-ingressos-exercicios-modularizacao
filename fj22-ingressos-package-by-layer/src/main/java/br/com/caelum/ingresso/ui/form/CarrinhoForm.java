package br.com.caelum.ingresso.ui.form;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.caelum.ingresso.domain.Ingresso;
import br.com.caelum.ingresso.domain.Lugar;
import br.com.caelum.ingresso.domain.Sessao;
import br.com.caelum.ingresso.domain.TipoDeIngresso;
import br.com.caelum.ingresso.infra.dao.LugarDao;
import br.com.caelum.ingresso.infra.dao.SessaoDao;

public class CarrinhoForm {
	private List<Ingresso> ingressos = new ArrayList<>();

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	public List<Ingresso> toIngressos(SessaoDao sessaoDao, LugarDao lugarDao) {
		return this.ingressos.stream().map(ingresso -> {
			Sessao sessao = sessaoDao.findOne(ingresso.getSessao().getId());
			Lugar lugar = lugarDao.findOne(ingresso.getLugar().getId());
			TipoDeIngresso tipoDeIngresso = ingresso.getTipoDeIngresso();
			return new Ingresso(sessao, tipoDeIngresso, lugar);
		}).collect(Collectors.toList());
	}

}
