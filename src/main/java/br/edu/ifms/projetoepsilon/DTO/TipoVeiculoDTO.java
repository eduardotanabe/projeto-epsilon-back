package br.edu.ifms.projetoepsilon.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.edu.ifms.projetoepsilon.entities.TipoVeiculo;

public class TipoVeiculoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotNull(message = "O campo tipo de veículo é obrigatório.")
	private String nome;
	
	public TipoVeiculoDTO(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public TipoVeiculoDTO(TipoVeiculo obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
	}
	
	public TipoVeiculoDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
