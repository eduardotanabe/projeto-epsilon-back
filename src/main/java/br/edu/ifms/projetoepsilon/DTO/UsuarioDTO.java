package br.edu.ifms.projetoepsilon.DTO;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.edu.ifms.projetoepsilon.entities.Usuario;

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank(message = "O campo nome completo é obrigatório.")
	private String nome;
	@NotBlank(message = "O campo CPF é obrigatório.")
	private String cpf;
	@Email(message = "O campo e-mail é obrigatório.")
	private String email;
	@NotBlank(message = "O campo contato particular é obrigatório.")
	private String contatoParticular;
	@NotBlank(message = "O campo contato profissional é obrigatório.")
	private String contatoProfissional;
	@NotBlank(message = "O campo orgão que trabalha é obrigatório.")
	private String orgao;
	@NotBlank(message = "O campo lotação que trabalha é obrigatório.")
	private String lotacao;
	@NotBlank(message = "O campo cargo é obrigatório.")
	private String cargo;
	@NotBlank(message = "O campo unidade federativa do trabalho é obrigatório.")
	private String uf;
	@NotBlank(message = "O campo cidade do trabalho é obrigatório.")
	private String cidade;

	public UsuarioDTO(Long id, String nome, String cpf, String email, String contatoParticular, String contatoProfissional, String orgao, String lotacao,
			String cargo, String uf, String cidade) {
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
	
	public UsuarioDTO(Usuario obj) {
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
	
	public UsuarioDTO() {
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

	
}
