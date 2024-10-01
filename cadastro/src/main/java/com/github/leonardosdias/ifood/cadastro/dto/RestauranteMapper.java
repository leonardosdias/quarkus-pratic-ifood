package com.github.leonardosdias.ifood.cadastro.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.github.leonardosdias.ifood.cadastro.Restaurante;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {
    // @Mapping(target = "nome", source = "nomeFantasia")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    @Mapping(target = "localizacao.id", ignore = true)
    public Restaurante toAdicionarRestaurante(AdicionarRestauranteDTO dto);

    // @Mapping(target = "nome", source = "nomeFantasia")
    public void toAtualizarRestaurante(AtualizarRestauranteDTO dto, @MappingTarget Restaurante restaurante);

    // @Mapping(target = "nome", source = "nomeFantasia")
    @Mapping(target = "dataCriacao", dateFormat = "dd/MM/yyyy HH:mm:ss")
    public RestauranteDTO toListarRestauranteDTO(Restaurante restaurante);

    @Mapping(target = "nome", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    @Mapping(target = "localizacao", ignore = true)
    public Restaurante toRemoverRestaurante(RemoverRestauranteDTO dto);

}
