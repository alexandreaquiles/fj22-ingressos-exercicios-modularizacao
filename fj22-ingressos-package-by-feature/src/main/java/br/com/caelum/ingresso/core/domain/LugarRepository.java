package br.com.caelum.ingresso.core.domain;

public interface LugarRepository {

	void save(Lugar lugar);

	Lugar findOne(Integer id);

}