package br.com.caelum.ingresso.infra.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.domain.Filme;
import br.com.caelum.ingresso.domain.Ingresso;
import br.com.caelum.ingresso.domain.Sala;
import br.com.caelum.ingresso.domain.Sessao;

@Repository
public class SessaoDao {

	@PersistenceContext
	private EntityManager manager;

	public void save(Sessao sessao) {
		manager.persist(sessao);
	}
	
	public List<Sessao> buscaSessoesDaSala(Sala sala) {
		return manager.createQuery("select s from Sessao s where s.sala = :sala", Sessao.class)
				.setParameter("sala", sala)
				.getResultList();
	}
	
	public List<Sessao> buscaSessoesDoFilme(Filme filme) {
		return manager.createQuery("select s from Sessao s where s.filme = :filme", Sessao.class)
				.setParameter("filme", filme)
				.getResultList();
	}

	public Sessao findOne(Integer id) {
		return manager.find(Sessao.class, id);
	}
	
	public Sessao buscaSessaoComIngressos(Integer id) {
		return manager.createQuery("select s from Sessao s left join fetch s.ingressos where s.id = :id", Sessao.class)
				.setParameter("id", id)
				.getSingleResult();
	}

}
