package br.com.caelum.ingresso.core.infra.dao;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.core.domain.Lugar;
import br.com.caelum.ingresso.core.domain.LugarRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by nando on 03/03/17.
 */
@Repository
public class LugarDao implements LugarRepository {

	@PersistenceContext
	private EntityManager manager;

	public void save(Lugar lugar) {
		manager.persist(lugar);
	}

	public Lugar findOne(Integer id) {
		return manager.find(Lugar.class, id);
	}
}
