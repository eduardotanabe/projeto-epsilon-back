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

import br.edu.ifms.projetoepsilon.DTO.TipoVeiculoDTO;
import br.edu.ifms.projetoepsilon.entities.TipoVeiculo;
import br.edu.ifms.projetoepsilon.repositories.TipoVeiculoRepository;
import br.edu.ifms.projetoepsilon.services.exceptions.ControllerNotFoundException;
import br.edu.ifms.projetoepsilon.services.exceptions.DataBaseException;

@Service
public class TipoVeiculoService {

	@Autowired
	private TipoVeiculoRepository repository;
	
	@Transactional(readOnly = true)
	public Page<TipoVeiculoDTO> findAllPage(@PageableDefault(sort = "nome", direction = Direction.ASC) Pageable paginacao) {
		Page<TipoVeiculo> items = repository.findAll(paginacao);
		return items.map(TipoVeiculoDTO::new);
	}
	
	@Transactional(readOnly = true)
	public List<TipoVeiculoDTO> findAll() {
		List<TipoVeiculo> list = repository.findAll();
		return list.stream().map(tipoVeiculo-> new TipoVeiculoDTO(tipoVeiculo)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public TipoVeiculoDTO findById(Long id) {
		Optional<TipoVeiculo> obj = repository.findById(id);
		TipoVeiculo tipoVeiculo = obj.orElseThrow(() -> new ControllerNotFoundException("O usuário solicitado não foi localizado."));
		return new TipoVeiculoDTO(tipoVeiculo);
	}
	
	@Transactional
	public TipoVeiculoDTO insert(TipoVeiculoDTO dto) {
		TipoVeiculo tipoVeiculo = new TipoVeiculo(dto);
		tipoVeiculo = repository.save(tipoVeiculo);
		return new TipoVeiculoDTO(tipoVeiculo);
	}
	
	@Transactional
	public TipoVeiculoDTO update(Long id, TipoVeiculoDTO dto) {
		try {
			TipoVeiculo tipoVeiculo = repository.getById(id);
			tipoVeiculo.setNome(dto.getNome());
			tipoVeiculo = repository.save(tipoVeiculo);
			return new TipoVeiculoDTO(tipoVeiculo);
			
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
