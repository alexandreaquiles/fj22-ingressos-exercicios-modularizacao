package br.com.caelum.ingresso.ui.form;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.caelum.ingresso.domain.Filme;
import br.com.caelum.ingresso.domain.Sala;
import br.com.caelum.ingresso.domain.Sessao;
import br.com.caelum.ingresso.infra.dao.FilmeDao;
import br.com.caelum.ingresso.infra.dao.SalaDao;

public class SessaoForm {

	private Integer id;
	
	@NotNull
	private Integer salaId;

	@DateTimeFormat(pattern="HH:mm")
	@NotNull
	private LocalTime horario;
	
	@NotNull
	private Integer filmeId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSalaId() {
		return salaId;
	}

	public void setSalaId(Integer salaId) {
		this.salaId = salaId;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public Integer getFilmeId() {
		return filmeId;
	}

	public void setFilmeId(Integer filmeId) {
		this.filmeId = filmeId;
	}
	
	public Sessao toSessao(SalaDao salaDao, FilmeDao filmeDao) {
		Filme filme = filmeDao.findOne(filmeId);
		Sala sala = salaDao.findOne(salaId);
		
		Sessao sessao = new Sessao(horario, filme, sala);
		sessao.setId(id);

		return sessao;
	}
	
}
