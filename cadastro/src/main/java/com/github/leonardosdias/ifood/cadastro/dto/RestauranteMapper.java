package com.github.leonardosdias.ifood.cadastro.dto;

import org.mapstruct.Mapper;

import com.github.leonardosdias.ifood.cadastro.Restaurante;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {

    public Restaurante toRestaurante(AdicionarRestauranteDTO dto);
}
