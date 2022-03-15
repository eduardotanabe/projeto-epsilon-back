package br.edu.ifms.projetoepsilon.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.edu.ifms.projetoepsilon.entities.Marca;
import br.edu.ifms.projetoepsilon.entities.TipoVeiculo;
import br.edu.ifms.projetoepsilon.entities.Veiculo;

public class VeiculoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@NotNull(message = "O campo montadora é obrigatório.")
	private Marca marca;
	@NotNull(message = "O campo tipo do veículo é obrigatório.")
	private TipoVeiculo tipoVeiculo;
	@NotBlank(message = "O campo modelo do veículo é obrigatório.")
	private String modelo;
	@NotBlank(message = "O campo placa de licença do veículo é obrigatório.")
	private String placaLicenca;
	@NotNull(message = "O campo ano de fabricação do veículo é obrigatório.")
	private int anoFabricacao;
	@NotNull(message = "O campo ano do modelo do veículo é obrigatório.")
	private int anoModelo;
	@NotBlank(message = "O campo sequencial do chassi do veículo é obrigatório.")
	private String sequencialChassi;
	@NotBlank(message = "O campo sequencial do bloco do motor do veículo é obrigatório.")
	private String sequencialMotor;
	
	public VeiculoDTO(Long id, Marca marca, TipoVeiculo tipoVeiculo, String modelo, String placaLicenca, int anoFabricacao,
			int anoModelo, String sequencialChassi, String sequencialMotor) {
		this.id = id;
		this.marca = marca;
		this.tipoVeiculo = tipoVeiculo;
		this.modelo = modelo;
		this.placaLicenca = placaLicenca;
		this.anoFabricacao = anoFabricacao;
		this.anoModelo = anoModelo;
		this.sequencialChassi = sequencialChassi;
		this.sequencialMotor = sequencialMotor;
	}

	public VeiculoDTO(Veiculo obj) {
		this.id = obj.getId();
		this.marca = obj.getMarca();
		this.tipoVeiculo = obj.getTipoVeiculo();
		this.modelo = obj.getModelo();
		this.placaLicenca = obj.getPlacaLicenca();
		this.anoFabricacao = obj.getAnoFabricacao();
		this.anoModelo = obj.getAnoModelo();
		this.sequencialChassi = obj.getSequencialChassi();
		this.sequencialMotor = obj.getSequencialMotor();
	}
	
	public VeiculoDTO() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Marca getMarca() {
		return marca;
	}
	
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}
	
	public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getPlacaLicenca() {
		return placaLicenca;
	}
	
	public void setPlacaLicenca(String placaLicenca) {
		this.placaLicenca = placaLicenca;
	}
	
	public int getAnoFabricacao() {
		return anoFabricacao;
	}
	
	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	
	public int getAnoModelo() {
		return anoModelo;
	}
	
	public void setAnoModelo(int anoModelo) {
		this.anoModelo = anoModelo;
	}
	
	public String getSequencialChassi() {
		return sequencialChassi;
	}
	
	public void setSequencialChassi(String sequencialChassi) {
		this.sequencialChassi = sequencialChassi;
	}
	
	public String getSequencialMotor() {
		return sequencialMotor;
	}
	
	public void setSequencialMotor(String sequencialMotor) {
		this.sequencialMotor = sequencialMotor;
	}
	
}
