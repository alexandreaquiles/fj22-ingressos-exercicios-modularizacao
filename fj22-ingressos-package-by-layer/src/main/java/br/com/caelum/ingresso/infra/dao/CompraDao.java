package br.com.caelum.ingresso.infra.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.domain.Compra;

@Repository
public class CompraDao {

	@PersistenceContext
	private EntityManager manager;

	public void save(Compra compra) {
		manager.persist(compra);
	}
}