package br.edu.ifms.projetoepsilon.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifms.projetoepsilon.DTO.MarcaDTO;
import br.edu.ifms.projetoepsilon.services.MarcaService;

@RestController
@RequestMapping(value = "/marcas")
public class MarcaController {

	@Autowired
	private MarcaService service;

	@GetMapping
	public Page<MarcaDTO> list(@PageableDefault(sort = "nome", direction = Direction.ASC) Pageable paginacao) {
    return service.findAllPage(paginacao);
}

	@GetMapping("/list")
    public List<MarcaDTO> list() {
        return service.findAll();
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<MarcaDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));

    }

	@PostMapping
	public ResponseEntity<MarcaDTO> insert(@Valid @RequestBody MarcaDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<MarcaDTO> update(@PathVariable Long id, @Valid @RequestBody MarcaDTO dto) { 
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<MarcaDTO> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
