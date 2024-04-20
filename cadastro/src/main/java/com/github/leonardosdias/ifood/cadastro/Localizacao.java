package com.github.leonardosdias.ifood.cadastro;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "localizacao")
public class Localizacao extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	public Double latitude;

	public Double longitude;
}
