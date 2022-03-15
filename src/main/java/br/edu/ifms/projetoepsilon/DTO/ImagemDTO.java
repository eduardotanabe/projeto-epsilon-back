package br.edu.ifms.projetoepsilon.DTO;

import java.io.Serializable;
import java.util.Base64;

import br.edu.ifms.projetoepsilon.entities.Imagem;

public class ImagemDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String tipoArquivo;
	private long tamanho;
	private byte[] data;
	
	public ImagemDTO(String nome, String tipoArquivo, long tamanho, byte[] data) {
		this.nome = nome;
		this.tipoArquivo = tipoArquivo;
		this.tamanho = tamanho;
		this.data = data;
	}
	
	public ImagemDTO(Imagem imagem) {
		setNome(imagem.getNome());
		setTipoArquivo(imagem.getTipoArquivo());
		setTamanho(imagem.getTamanho());
		setData(imagem.getData());
	}
	
	public ImagemDTO(ImagemDTO dto) {
		setNome(dto.getNome());
		setTipoArquivo(dto.getTipoArquivo());
		setTamanho(dto.getTamanho());
		setData(dto.getData());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public long getTamanho() {
		return tamanho;
	}

	public void setTamanho(long tamanho) {
		this.tamanho = tamanho;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	public void setEncodeBase64() {
		this.data = Base64.getEncoder().encode(this.data);
	}
	
	
	
	
}
