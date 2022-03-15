package br.edu.ifms.projetoepsilon.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ifms.projetoepsilon.DTO.ImagemDTO;
import br.edu.ifms.projetoepsilon.entities.Imagem;
import br.edu.ifms.projetoepsilon.helpers.NomeArquivoHelper;
import br.edu.ifms.projetoepsilon.repositories.ImagemRepository;
import br.edu.ifms.projetoepsilon.services.exceptions.ControllerNotFoundException;

@Service
public class ImagemService {
	
	@Autowired
	private ImagemRepository repository;
	
	private NomeArquivoHelper helper = new NomeArquivoHelper();
	
	@Transactional
	public Imagem salvar(Imagem imagem) {
		if(imagem == null) {
			throw new NullPointerException("Imagem Data Null");
		}
		
		return repository.save(imagem);
	}
	
	@Transactional(readOnly = true)
	public List<ImagemDTO> findAll() {
		List<Imagem> list = repository.findAll();
		return list.stream().map(imagem -> new ImagemDTO(imagem)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public ImagemDTO findById(Long id) {
		Optional<Imagem> obj = repository.findById(id);
		Imagem imagem = obj.orElseThrow(() -> new ControllerNotFoundException("A imagem solicitada não foi localizada."));
		return new ImagemDTO(imagem);
	}
	
	@Transactional(readOnly = true)
	public List<ImagemDTO> findByIdVeiculo(Long idVeiculo) {
		List<Imagem> lista = repository.findByIdVeiculo(idVeiculo);
		return lista.stream().map(imagem -> new ImagemDTO(imagem)).collect(Collectors.toList());
	}
	
	public Imagem uploadSingleFile(MultipartFile file) {
		Imagem imagem = Imagem.buildImage(file, helper);
		return imagem;
	}
	
	public List<Imagem> uploadMultiFiles(MultipartFile[] files) {
		return Arrays.asList(files).stream().map(file -> uploadSingleFile(file)).collect(Collectors.toList());
	}

}
