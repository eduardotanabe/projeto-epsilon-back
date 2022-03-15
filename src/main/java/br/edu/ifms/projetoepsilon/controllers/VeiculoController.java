package br.edu.ifms.projetoepsilon.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifms.projetoepsilon.DTO.VeiculoDTO;
import br.edu.ifms.projetoepsilon.entities.Imagem;
import br.edu.ifms.projetoepsilon.services.ImagemService;
import br.edu.ifms.projetoepsilon.services.VeiculoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoService service;
	
	@Autowired
	private ImagemService imagemService;

	@GetMapping
	public ResponseEntity<List<VeiculoDTO>> findAll() {
		List<VeiculoDTO> lista = service.findAll();
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "/veiculos-marca/{idMarca}")
	public ResponseEntity<List<VeiculoDTO>> findVehiclesByMarca(@PathVariable Integer idMarca) {
		List<VeiculoDTO> lista = service.findVehiclesByMarca(idMarca);
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping(value = "/veiculos-placa/{placa}")
	public ResponseEntity<List<VeiculoDTO>> findVehiclesByPlaca(@PathVariable String placa) {
		List<VeiculoDTO> lista = service.findVehiclesByPlaca(placa);
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping(value = "/veiculos-marca-modelo/{idMarca}/{modelo}")
	public ResponseEntity<List<VeiculoDTO>> findVehiclesByMarcaModelo(@PathVariable Integer idMarca, @PathVariable String modelo) {
		List<VeiculoDTO> lista = service.findVehiclesByMarcaModelo(idMarca, modelo);
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping(value = "/veiculos-marca-ano/{idMarca}/{anoModelo}")
	public ResponseEntity<List<VeiculoDTO>> findVehiclesByMarcaAno(@PathVariable Integer idMarca, @PathVariable Integer anoModelo) {
		List<VeiculoDTO> lista = service.findVehiclesByMarcaAno(idMarca, anoModelo);
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping(value = "/veiculos-marca-modelo-ano/{idMarca}/{modelo}/{anoModelo}")
	public ResponseEntity<List<VeiculoDTO>> findVehiclesByMarcaModeloAno(
			@PathVariable Integer idMarca, @PathVariable String modelo, @PathVariable Integer anoModelo) {
		List<VeiculoDTO> lista = service.findVehiclesByMarcaModeloAno(idMarca, modelo, anoModelo);
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<VeiculoDTO> findById(@PathVariable Long id) {
		VeiculoDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping("/uploads")
	public ResponseEntity<VeiculoDTO> getInfoFromPDF(@RequestParam("file")  MultipartFile file) {
		VeiculoDTO veiculoDto = service.getInfoFromPDF(file);
		return ResponseEntity.ok().body(veiculoDto);
	}
	
	@PostMapping(consumes = { "multipart/form-data" })
	public ResponseEntity<VeiculoDTO> insert(@RequestPart("form") VeiculoDTO dto, @RequestParam("files") MultipartFile[] files) {
		List<Imagem> imagens = imagemService.uploadMultiFiles(files);
		dto = service.insert(dto, imagens);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<VeiculoDTO> update(@PathVariable Long id, @Valid @RequestBody VeiculoDTO dto) { 
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<VeiculoDTO> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
