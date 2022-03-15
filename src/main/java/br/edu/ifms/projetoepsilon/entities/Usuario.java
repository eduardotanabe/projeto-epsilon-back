package br.edu.ifms.projetoepsilon.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.edu.ifms.projetoepsilon.DTO.UsuarioDTO;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	private String cpf;
	private String email;
	
	@Column(name = "contato_particular")
	private String contatoParticular;
	
	@Column(name = "contato_profissional")
	private String contatoProfissional;
	private String orgao;
	private String lotacao;
	private String cargo;
	private String uf;
	private String cidade;
	
	public Usuario(Long id, String nome, String cpf, String email, String contatoParticular, String contatoProfissional,
			String orgao, String lotacao, String cargo, String uf, String cidade) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.contatoParticular = contatoParticular;
		this.contatoProfissional = contatoProfissional;
		this.orgao = orgao;
		this.lotacao = lotacao;
		this.cargo = cargo;
		this.uf = uf;
		this.cidade = cidade;
	}
	
	public Usuario(UsuarioDTO obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.contatoParticular = obj.getContatoParticular();
		this.contatoProfissional = obj.getContatoProfissional();
		this.orgao = obj.getOrgao();
		this.lotacao = obj.getLotacao();
		this.cargo = obj.getCargo();
		this.uf = obj.getUf();
		this.cidade = obj.getCidade();
	}

	public Usuario() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContatoProfissional() {
		return contatoProfissional;
	}
	
	public void setContatoProfissional(String contatoProfissional) {
		this.contatoProfissional = contatoProfissional;
	}
	
	public String getContatoParticular() {
		return contatoParticular;
	}
	
	public void setContatoParticular(String contatoParticular) {
		this.contatoParticular = contatoParticular;
	}
	
	public String getOrgao() {
		return orgao;
	}
	
	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}
	
	public String getLotacao() {
		return lotacao;
	}
	
	public void setLotacao(String lotacao) {
		this.lotacao = lotacao;
	}
	
	public String getCargo() {
		return cargo;
	}
	
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public String getUf() {
		return uf;
	}
	
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
