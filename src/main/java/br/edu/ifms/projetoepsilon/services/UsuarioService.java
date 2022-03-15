package br.edu.ifms.projetoepsilon.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifms.projetoepsilon.DTO.UsuarioDTO;
import br.edu.ifms.projetoepsilon.entities.Usuario;
import br.edu.ifms.projetoepsilon.repositories.UsuarioRepository;
import br.edu.ifms.projetoepsilon.services.exceptions.ControllerNotFoundException;
import br.edu.ifms.projetoepsilon.services.exceptions.DataBaseException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Transactional(readOnly = true)
	public List<UsuarioDTO> findAll() {
		List<Usuario> list = repository.findAll();
		return list.stream().map(usuario-> new UsuarioDTO(usuario)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public UsuarioDTO findById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		Usuario usuario = obj.orElseThrow(() -> new ControllerNotFoundException("O usuário solicitado não foi localizado."));
		return new UsuarioDTO(usuario);
	}
	
	@Transactional
	public UsuarioDTO insert(UsuarioDTO dto) {
		Usuario usuario = new Usuario(dto);
		usuario = repository.save(usuario);
		return new UsuarioDTO(usuario);
	}
	
	@Transactional
	public UsuarioDTO update(Long id, UsuarioDTO dto) {
		try {
			Usuario usuario = repository.getById(id);
			copyDtoToEntity(dto, usuario);
			usuario = repository.save(usuario);
			return new UsuarioDTO(usuario);
			
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
	
	private void copyDtoToEntity(UsuarioDTO dto, Usuario usuario) {
		usuario.setNome(dto.getNome());
		usuario.setCpf(dto.getCpf());
		usuario.setEmail(dto.getEmail());
		usuario.setContatoParticular(dto.getContatoParticular());
		usuario.setContatoProfissional(dto.getContatoProfissional());
		usuario.setOrgao(dto.getOrgao());
		usuario.setLotacao(dto.getLotacao());
		usuario.setUf(dto.getUf());
		usuario.setCidade(dto.getCidade());
	}
}
