package br.edu.ifms.projetoepsilon.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.edu.ifms.projetoepsilon.DTO.VeiculoDTO;

@Entity(name = "veiculo")
@Table(name = "veiculo")
public class Veiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "marca_id")
	private Marca marca;
	
	@ManyToOne
	@JoinColumn(name = "tipo_veiculo_id")
	private TipoVeiculo tipoVeiculo;
	private String modelo;
	
	@Column(name = "placa_licenca")
	private String placaLicenca;
	
	@Column(name = "ano_fabricacao")
	private int anoFabricacao;
	
	@Column(name = "ano_modelo")
	private int anoModelo;
	
	@Column(name = "sequencial_chassi")
	private String sequencialChassi;
	
	@Column(name = "sequencial_motor")
	private String sequencialMotor;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "veiculo_id")
	private List<Imagem> imagens;
	
	public Veiculo(Long id, Marca marca, TipoVeiculo tipoVeiculo, String modelo, String placaLicenca, int anoFabricacao,
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
	
	public Veiculo(VeiculoDTO obj) {
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

	public Veiculo() {
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
	
	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
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
		Veiculo other = (Veiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
