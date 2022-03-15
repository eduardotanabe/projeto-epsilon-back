package br.edu.ifms.projetoepsilon.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.edu.ifms.projetoepsilon.entities.Marca;

public class MarcaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotNull(message = "O campo montadora é obrigatório.")
	private String nome;
	
	public MarcaDTO(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public MarcaDTO(Marca obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
	}
	
	public MarcaDTO() {
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
