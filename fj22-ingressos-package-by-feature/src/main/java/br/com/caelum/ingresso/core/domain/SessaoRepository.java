package br.com.caelum.ingresso.core.domain;

import java.util.List;

public interface SessaoRepository {

	void save(Sessao sessao);

	List<Sessao> buscaSessoesDaSala(Sala sala);

	List<Sessao> buscaSessoesDoFilme(Filme filme);

	Sessao findOne(Integer id);

	Sessao buscaSessaoComIngressos(Integer id);

}