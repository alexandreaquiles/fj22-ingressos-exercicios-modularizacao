package br.com.caelum.ingresso.compra.infra.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.compra.domain.Compra;
import br.com.caelum.ingresso.compra.domain.CompraRepository;

@Repository
public class CompraDao implements CompraRepository {

	@PersistenceContext
	private EntityManager manager;

	public void save(Compra compra) {
		manager.persist(compra);
	}
}