<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="ingresso"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ingresso:template>
	<jsp:body>
		<div class=" col-md-6 col-md-offset-3">
	        <h1>${detalhes.titulo}</h1>
	        <img src="${detalhes.imagem}" />
	
	        <div>
	            <label for="ano">Ano</label>
	            <span id="ano">${detalhes.ano}</span>
	        </div>
	
	        <div>
	            <label for="diretores">Diretores</label>
	            <span id="diretores">${detalhes.diretores}</span>
	        </div>
	
	        <div>
	            <label for="escritores">Escritores</label>
	            <span id="escritores">${detalhes.escritores}</span>
	        </div>
	
	        <div>
	            <label for="atores">Atores</label>
	            <span id="atores">${detalhes.atores}</span>
	        </div>
	
	        <div>
	            <label for="descricao">Descrição</label>
	            <span id="descricao">${detalhes.descricao}</span>
	        </div>
	
	        <div>
	            <label for="avaliacao">Avaliação</label>
	            <span id="avaliacao">${detalhes.avaliacao}</span>
	        </div>

				<table class="table table-hover">
					<thead>
						<tr>
							<th>Sala</th>
							<th>Horario</th>
							<th>Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${sessoes}" var="sessao">
							<tr>
								<td>${sessao.sala.nome}</td>
								<td>${sessao.horario}</td>
								<td>
									<a href="/sessao/${sessao.id}/lugares" class="btn">
										Comprar
										<span class="glyphicon glyphicon-blackboard"
									aria-hidden="true"></span>
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		</div>
    </jsp:body>
</ingresso:template>
