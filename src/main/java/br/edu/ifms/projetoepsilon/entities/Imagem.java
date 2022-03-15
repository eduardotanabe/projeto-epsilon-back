package br.edu.ifms.projetoepsilon.entities;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.edu.ifms.projetoepsilon.helpers.NomeArquivoHelper;

@Entity
@Table(name = "imagem")
public class Imagem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@CreatedBy
//	private String criadoPor;
	
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT-3")
	private Date dataCriacao;
	
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT-3")
	private Date dataAtualizacao;
	
	private String nome;
	
	@Column(name = "tipo_arquivo")
	private String tipoArquivo;
	private long tamanho;
	
	@ManyToOne
	private Veiculo veiculo;
	
	@Lob
	@Column(name = "data")
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] data;
	
	public Imagem(Long id, Date dataCriacao, Date dataAtualizacao, String nome, String tipoArquivo, long tamanho,
			Veiculo veiculo, byte[] data) {
		this.id = id;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
		this.nome = nome;
		this.tipoArquivo = tipoArquivo;
		this.tamanho = tamanho;
		this.veiculo = veiculo;
		this.data = data;
	}

	public Imagem() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
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

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
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
		Imagem other = (Imagem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * Create new Image class.
	 * 
	 * @return new Image.
	 */
	@Transient
	public static Imagem build() {
		Imagem imagem = new Imagem();
		Date agora = new Date();
		imagem.setDataCriacao(agora);
		imagem.setDataAtualizacao(agora);
		return imagem;
	}

	@Transient
	public void setFiles(MultipartFile file) {
		setTipoArquivo(file.getContentType());
		setTamanho(file.getSize());
	}

	/**
	 * Scale image data with given width and height.
	 * 
	 * @param width  scale width
	 * @param height scale height
	 * @return scaled image byte array and change to class data.
	 */
	@Transient
	public byte[] scale(int width, int height) throws Exception {

		if (width == 0 || height == 0)
			return data;

		ByteArrayInputStream in = new ByteArrayInputStream(data);

		try {
			BufferedImage img = ImageIO.read(in);

			java.awt.Image scaledImage = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
			BufferedImage imgBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			imgBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			ImageIO.write(imgBuff, "jpg", buffer);
			setData(buffer.toByteArray());
			return buffer.toByteArray();

		} catch (Exception e) {
			throw new Exception("IOException in scale");
		}
	}

	/**
	 * @param fileName - filename of the resources.
	 * 
	 * @return inputstream for image
	 * */
	private static InputStream getResourceFileAsInputStream(String fileName) {
	    ClassLoader classLoader = Image.class.getClassLoader();
	    return classLoader.getResourceAsStream(fileName);
	}
	
	/**
	 * Generate no context image with `notfound.jpg` image in asset.
	 * 
	 * @return create default image.
	 */
	@Transient
	public static Imagem defaultImage() throws Exception {
	    InputStream is = getResourceFileAsInputStream("notfound.jpg");
		String fileType = "image/jpeg";
		byte[] bdata = FileCopyUtils.copyToByteArray(is);
		Imagem imagem = new Imagem(null, null, null, null, fileType, 0, null, bdata);
		return imagem;
	}

	/**
	 * Generate scaled no context image with `notfound.jpg` image in asset with
	 * given width and height.
	 * 
	 * @param width  scale width
	 * @param height scale height
	 * @return create scaled default image.
	 */
	@Transient
	public static Imagem defaultImage(int width, int height) throws Exception {
		Imagem defaultImage = defaultImage();
		defaultImage.scale(width, height);
		return defaultImage;
	}

	/**
	 * Generate scaled no context image with `notfound.jpg` image in asset with
	 * given width and height.
	 * 
	 * @param file   multipartfile data to build.
	 * @param helper filenamehelper class to generate name.
	 * @return return new Image class related with file.
	 */
	@Transient
	public static Imagem buildImage(MultipartFile file, NomeArquivoHelper helper) {
		String fileName = helper.generateDisplayName(file.getOriginalFilename());

		Imagem imagem = Imagem.build();
		imagem.setNome(fileName);
		imagem.setFiles(file);

		try {
			imagem.setData(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imagem;
	}
	

}
