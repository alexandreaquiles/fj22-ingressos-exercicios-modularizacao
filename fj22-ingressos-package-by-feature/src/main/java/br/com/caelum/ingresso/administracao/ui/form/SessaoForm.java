package br.com.caelum.ingresso.administracao.ui.form;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.caelum.ingresso.core.domain.Filme;
import br.com.caelum.ingresso.core.domain.Sala;
import br.com.caelum.ingresso.core.domain.Sessao;

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
	
	public Sessao toSessao() {
		Filme filme = new Filme(filmeId);
		Sala sala = new Sala(salaId);
		
		Sessao sessao = new Sessao(horario, filme, sala);
		sessao.setId(id);

		return sessao;
	}
	
}
