package br.com.caelum.ingresso.core.infra.dao;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.core.domain.Sala;
import br.com.caelum.ingresso.core.domain.SalaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by nando on 03/03/17.
 */
@Repository
public class SalaDao implements SalaRepository {

    @PersistenceContext
    private EntityManager manager;

    public Sala findOne(Integer id) {

        return manager.find(Sala.class, id);
    }

    public void save(Sala sala) {
        manager.merge(sala);
    }

    public List<Sala> findAll() {
        return manager.createQuery("select s from Sala s", Sala.class).getResultList();
    }

    public void delete(Integer id) {
        manager.remove(findOne(id));
    }
}
