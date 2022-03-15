package br.edu.ifms.projetoepsilon.controllers;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ifms.projetoepsilon.DTO.ImagemDTO;
import br.edu.ifms.projetoepsilon.DTO.MarcaDTO;
import br.edu.ifms.projetoepsilon.DTO.VeiculoDTO;
import br.edu.ifms.projetoepsilon.entities.Imagem;
import br.edu.ifms.projetoepsilon.helpers.NomeArquivoHelper;
import br.edu.ifms.projetoepsilon.services.ImagemService;

@RestController
@RequestMapping(value = "/imagens")
public class ImagemController {

	@Autowired
	ImagemService service;

	private NomeArquivoHelper helper = new NomeArquivoHelper();

	/**
	 * Get all images information without data.
	 * 
	 * @return return list of all images information.
	 */
	@GetMapping("/info")
	public ResponseEntity<List<ImagemDTO>> findAll() {
		List<ImagemDTO> lista = service.findAll();
		return ResponseEntity.ok().body(lista);
	}

	/**
	 * Upload single file to database.
	 * 
	 * @param file file data
	 * @return return saved image info with ImageResponse class.
	 */
	@PostMapping("/upload")
	public ImagemDTO uploadSingleFile(@RequestParam("file") MultipartFile file) {
		Imagem imagem = Imagem.buildImage(file, helper);
		service.salvar(imagem);
		return new ImagemDTO(imagem);
	}

	/**
	 * Upload multiple files to database.
	 * 
	 * @param files files data
	 * @return return saved images info list with ImageResponse class.
	 */
	@PostMapping("/uploads")
	public List<ImagemDTO> uploadMultiFiles(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files).stream().map(file -> uploadSingleFile(file)).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ImagemDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	/**
	 * Sends valid or default image bytes with given fileName pathVariable.
	 * 
	 * @param fileName
	 * @return return valid byte array
	 */
	@GetMapping(value = "/show/imagem/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable Long id) throws Exception {
		ImagemDTO dto = service.findById(id);
		byte[] encodedBase64 = Base64.getEncoder().encode(dto.getData());
		return ResponseEntity.ok().contentType(MediaType.valueOf(dto.getTipoArquivo())).body(encodedBase64);
	}
	
	/**
	 * Envia apenas arquivo tipo JPG/JPEG e com as imagens Base 64 em byte[]
	 * 
	 * 
	 */
	@GetMapping(value = "/show/{idVeiculo}")
	public ResponseEntity<List<ImagemDTO>> getImages(@PathVariable Long idVeiculo) throws Exception {
		List<ImagemDTO> lista = service.findByIdVeiculo(idVeiculo);
		List<ImagemDTO> listaImagem = lista.stream().map(imagem -> {
			ImagemDTO dto = new ImagemDTO(imagem);
			dto.setEncodeBase64();
			return dto;
			}).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaImagem);
	}

//	@GetMapping(value = "/zip-download", produces="application/zip")
//	public void zipDownload(@RequestParam List<String> name, HttpServletResponse response) throws IOException {
//		ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
//		for (String fileName : name) {
//			FileSystemResource resource = new FileSystemResource(fileBasePath + fileName);
//			ZipEntry zipEntry = new ZipEntry(resource.getFilename());
//			zipEntry.setSize(resource.contentLength());
//			zipOut.putNextEntry(zipEntry);
//			StreamUtils.copy(resource.getInputStream(), zipOut);
//			zipOut.closeEntry();
//		}
//		zipOut.finish();
//		zipOut.close();
//		response.setStatus(HttpServletResponse.SC_OK);
//		response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + zipFileName + "\"");
//	}

	/**
	 * Sends valid or default image bytes with given fileName or uuid request
	 * params.
	 * 
	 * @param name image name
	 * @param uuid image uuid
	 * @return return valid byte array
	 */
//	@GetMapping("/show")
//	public ResponseEntity<byte[]> getImageWithRequestParam(@RequestParam(required = false, value = "uuid") String uuid,
//			@RequestParam(required = false, value = "nome") String nome) throws Exception {
//
//		if (uuid != null) {
//			Imagem imagem = getImagemPorUuid(uuid);
//			return ResponseEntity.ok().contentType(MediaType.valueOf(imagem.getTipoArquivo())).body(imagem.getData());
//		}
//		if (nome != null) {
//			return getImage(nome);
//		}
//		Imagem defaultImage = Imagem.defaultImage();
//		return ResponseEntity.ok().contentType(MediaType.valueOf(defaultImage.getTipoArquivo()))
//				.body(defaultImage.getData());
//
//	}

	/**
	 * Sends valid or default scaled image bytes with given file name or uuid
	 * request params.
	 * 
	 * @param name   image name
	 * @param uuid   image uuid
	 * @param width  image width
	 * @param height image height
	 * @return return scaled valid byte array
	 */
//	@GetMapping("/show/{width}/{height}")
//	public ResponseEntity<byte[]> getScaledImageWithRequestParam(@PathVariable int width, @PathVariable int height,
//			@RequestParam(required = false, value = "uuid") String uuid,
//			@RequestParam(required = false, value = "nome") String nome) throws Exception {
//
//		if (uuid != null) {
//			Imagem imagem = getImagemPorUuid(uuid, width, height);
//			return ResponseEntity.ok().contentType(MediaType.valueOf(imagem.getTipoArquivo())).body(imagem.getData());
//		}
//		if (nome != null) {
//			Imagem imagem = getImagemPorNome(nome, width, height);
//			return ResponseEntity.ok().contentType(MediaType.valueOf(imagem.getTipoArquivo())).body(imagem.getData());
//		}
//		Imagem defImage = Imagem.defaultImage(width, height);
//		return ResponseEntity.ok().contentType(MediaType.valueOf(defImage.getTipoArquivo())).body(defImage.getData());
//	}

	/**
	 * Sends valid or default scaled image bytes with given fileName.
	 * 
	 * @param fileName image name
	 * @param width    image width
	 * @param height   image height
	 * @return return valid byte array
	 */
//	@GetMapping("/show/{width}/{height}/{fileName:.+}")
//	public ResponseEntity<byte[]> getScaledImage(@PathVariable int width, @PathVariable int height,
//			@PathVariable String fileName) throws Exception {
//		Imagem imagem = getImagemPorNome(fileName, width, height);
//		return ResponseEntity.ok().contentType(MediaType.valueOf(imagem.getTipoArquivo())).body(imagem.getData());
//	}

	/**
	 * get Image by name. If image is null return default image from asset.
	 * 
	 * @param name the name of image
	 * @return valid image or default image
	 */
	public ImagemDTO getImagemPorId(Long id) throws Exception {
		ImagemDTO dto = service.findById(id);

		return dto;
	}

	/**
	 * get scaled Image by name, width and height. If image is null return default
	 * image from asset.
	 * 
	 * @param name   the name of image
	 * @param width  width size of image
	 * @param height height size of image
	 * @return valid scaled image or default scaled image
	 */
//	public Imagem getImagemPorNome(String nome, int width, int height) throws Exception {
//		Imagem imagem = service.findByNomeArquivo(nome);
//		if (imagem == null) {
//			Imagem defImage = Imagem.defaultImage();
//			defImage.scale(width, height);
//			return defImage;
//		}
//		imagem.scale(width, height);
//		return imagem;
//	}

	/**
	 * get Image by uuid. If image is null return default image from asset.
	 * 
	 * @param uuid the uuid of image
	 * @return valid image or default image
	 */
//	public Imagem getImagemPorUuid(String uuid) throws Exception {
//		Imagem imagem = service.findByUuid(uuid);
//		if (imagem == null) {
//			return Imagem.defaultImage();
//		}
//		return imagem;
//	}

	/**
	 * get scaled Image by uuid, width and height. If image is null return default
	 * image from asset.
	 * 
	 * @param name   the uuid of image
	 * @param width  width size of image
	 * @param height height size of image
	 * @return valid scaled image or default scaled image
	 */
//	public Imagem getImagemPorUuid(String uuid, int width, int height) throws Exception {
//		Imagem imagem = service.findByUuid(uuid);
//		if (imagem == null) {
//			Imagem defImage = Imagem.defaultImage();
//			defImage.scale(width, height);
//			return defImage;
//		}
//		imagem.scale(width, height);
//		return imagem;
//	}

}
