package com.github.leonardosdias.ifood.cadastro;

import java.math.BigDecimal;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "prato")
public class Prato extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String nome;
	
	public String descricao;
	
	@ManyToOne
	public Restaurante restaurante;
	
	public BigDecimal preco;

}
