package br.com.caelum.ingresso.core.domain;

import java.util.List;

public interface SalaRepository {

	Sala findOne(Integer id);

	void save(Sala sala);

	List<Sala> findAll();

	void delete(Integer id);

}