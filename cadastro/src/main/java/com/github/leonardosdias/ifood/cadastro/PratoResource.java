package com.github.leonardosdias.ifood.cadastro;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/pratos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PratoResource {

	@GET
	@Path("{idRestaurante}/pratos")
	public List<Restaurante> buscarPratos(@PathParam("idRestaurante") Long idRestaurante) {
		Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
		if (restauranteOp.isEmpty()) {
			throw new NotFoundException("Restaurante não existe");
		}

		return Prato.list("restaurante", restauranteOp.get());
	}

	@POST
	@Path("{idRestaurante}/pratos")
	@Transactional
	public Response adicionarPrato(@PathParam("idRestaurante") Long idRestaurante, Prato dto) {
		Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional((idRestaurante));
		if (restauranteOp.isEmpty()) {
			throw new NotFoundException("Restaurante não existe");
		}

		Prato prato = new Prato();
		prato.nome = dto.nome;
		prato.descricao = dto.descricao;
		prato.preco = dto.preco;
		prato.restaurante = restauranteOp.get();
		prato.persist();
		return Response.status(Status.CREATED).build();
	}

	@PUT
	@Path("{idRestaurante}/pratos/{id}")
	@Transactional
	public void atualizarPrato(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id,
			Prato dto) {
		Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional((idRestaurante));

		if (restauranteOp.isEmpty()) {
			throw new NotFoundException("Restaurante não existe");
		}

		Optional<Prato> pratoOp = Prato.findByIdOptional(id);

		if (pratoOp.isEmpty()) {
			throw new NotFoundException("Prato não existe");
		}

		Prato prato = pratoOp.get();
		prato.preco = dto.preco;
		prato.persist();
	}

	@DELETE
	@Path("{idRestaurante}/pratos/{id}")
	@Transactional
	public void deletarPrato(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id) {
		Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional((idRestaurante));

		if (restauranteOp.isEmpty()) {
			throw new NotFoundException("Restaurante não existe");
		}

		Optional<Prato> pratoOp = Prato.findByIdOptional(id);

		if (pratoOp.isEmpty()) {
			throw new NotFoundException("Prato não existe");
		}

		pratoOp.ifPresentOrElse(Prato::delete, () -> {
			throw new NotFoundException();
		});
	}

}
