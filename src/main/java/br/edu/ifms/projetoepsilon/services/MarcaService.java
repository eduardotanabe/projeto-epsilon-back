package br.edu.ifms.projetoepsilon.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifms.projetoepsilon.DTO.MarcaDTO;
import br.edu.ifms.projetoepsilon.entities.Marca;
import br.edu.ifms.projetoepsilon.repositories.MarcaRepository;
import br.edu.ifms.projetoepsilon.services.exceptions.ControllerNotFoundException;
import br.edu.ifms.projetoepsilon.services.exceptions.DataBaseException;

@Service
public class MarcaService {

	@Autowired
	private MarcaRepository repository;
	
	@Transactional(readOnly = true)
	public Page<MarcaDTO> findAllPage(@PageableDefault(sort = "nome", direction = Direction.ASC) Pageable paginacao) {
		Page<Marca> items = repository.findAll(paginacao);
		return items.map(MarcaDTO::new);
	}
	
	@Transactional(readOnly = true)
	public List<MarcaDTO> findAll() {
		List<Marca> list = repository.findAll();
		return list.stream().map(marca-> new MarcaDTO(marca)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public MarcaDTO findById(Long id) {
		Optional<Marca> obj = repository.findById(id);
		Marca marca = obj.orElseThrow(() -> new ControllerNotFoundException("O usuário solicitado não foi localizado."));
		return new MarcaDTO(marca);
	}
	
	@Transactional
	public MarcaDTO insert(MarcaDTO dto) {
		Marca marca = new Marca(dto);
		marca = repository.save(marca);
		return new MarcaDTO(marca);
	}
	
	@Transactional
	public MarcaDTO update(Long id, MarcaDTO dto) {
		try {
			Marca marca = repository.getById(id);
			marca.setNome(dto.getNome());
			marca = repository.save(marca);
			return new MarcaDTO(marca);
			
		} catch (EntityNotFoundException e) {
			throw new ControllerNotFoundException("O id do usuário não foi localizado.");
		}
	}

	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException exception) {
			throw new ControllerNotFoundException("Não foi possível excluir, pois o id do usuário não foi localizado.");
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Não foi possível excluir o usuário, pois o mesmo está em uso");
		}
	}
	
}
