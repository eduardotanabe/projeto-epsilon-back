package br.edu.ifms.projetoepsilon.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ifms.projetoepsilon.DTO.VeiculoDTO;
import br.edu.ifms.projetoepsilon.entities.Imagem;
import br.edu.ifms.projetoepsilon.entities.Veiculo;
import br.edu.ifms.projetoepsilon.repositories.VeiculoRepository;
import br.edu.ifms.projetoepsilon.services.exceptions.ControllerNotFoundException;
import br.edu.ifms.projetoepsilon.services.exceptions.DataBaseException;
import br.edu.ifms.projetoepsilon.services.funcionaties.ExtractionDataFromPDF;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository repository;
	
	@Transactional(readOnly = true)
	public List<VeiculoDTO> findAll() {
		List<Veiculo> list = repository.findAll();
		return list.stream().map(veiculo -> new VeiculoDTO(veiculo)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<VeiculoDTO> findVehiclesByPlaca(String placa) {
		List<Veiculo> list = repository.findVehiclesByPlaca(placa);
		System.out.println(list);
		return list.stream().map(veiculo -> new VeiculoDTO(veiculo)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<VeiculoDTO> findVehiclesByMarca(Integer idMarca) {
		List<Veiculo> list = repository.findVehiclesByMarca(idMarca);
		System.out.println(list);
		return list.stream().map(veiculo -> new VeiculoDTO(veiculo)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<VeiculoDTO> findVehiclesByMarcaModelo(Integer idMarca, String modelo) {
		List<Veiculo> list = repository.findVehiclesByMarcaModelo(idMarca, modelo);
		System.out.println(list);
		return list.stream().map(veiculo -> new VeiculoDTO(veiculo)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<VeiculoDTO> findVehiclesByMarcaAno(Integer idMarca, Integer anoModelo) {
		List<Veiculo> list = repository.findVehiclesByMarcaAno(idMarca, anoModelo);
		return list.stream().map(veiculo -> new VeiculoDTO(veiculo)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<VeiculoDTO> findVehiclesByMarcaModeloAno(Integer idMarca, String modelo, Integer anoModelo) {
		List<Veiculo> list = repository.findVehiclesByMarcaModeloAno(idMarca, modelo, anoModelo);
		return list.stream().map(veiculo -> new VeiculoDTO(veiculo)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public VeiculoDTO findById(Long id) {
		Optional<Veiculo> obj = repository.findById(id);
		Veiculo veiculo = obj.orElseThrow(() -> new ControllerNotFoundException("O veículo solicitado não foi localizado."));
		return new VeiculoDTO(veiculo);
	}
	
	@Transactional
	public VeiculoDTO getInfoFromPDF(MultipartFile pdfFile) {
		File file = new File("src/main/resources/targetFile.tmp");

		try (OutputStream os = new FileOutputStream(file)) {
		    os.write(pdfFile.getBytes());
		    VeiculoDTO veiculoDto = ExtractionDataFromPDF.getDataFromDPF(new File("src/main/resources/targetFile.tmp"));
		    
		    return veiculoDto;
		    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Transactional
	public VeiculoDTO insert(VeiculoDTO dto, List<Imagem> imagens) {
		Veiculo veiculo = new Veiculo();
		copyDtoToEntity(dto, veiculo);
		veiculo.setImagens(imagens);
		veiculo = repository.save(veiculo);
		return new VeiculoDTO(veiculo);
	}
	
	@Transactional
	public VeiculoDTO update(Long id, VeiculoDTO dto) {
		try {
			Veiculo veiculo = repository.getById(id);
			copyDtoToEntity(dto, veiculo);
			veiculo = repository.save(veiculo);
			return new VeiculoDTO(veiculo);
			
		} catch (EntityNotFoundException e) {
			throw new ControllerNotFoundException("O id do veiculo não foi localizado.");
		}
	}
	

	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException exception) {
			throw new ControllerNotFoundException("Não foi possível excluir, pois o id do veiculo não foi localizado.");
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Não foi possível excluir o veículo, pois o mesmo está em uso");
		}
	}
	
		private void copyDtoToEntity(VeiculoDTO dto, Veiculo veiculo) {
			veiculo.setMarca(dto.getMarca());
			veiculo.setModelo(dto.getModelo());
			veiculo.setTipoVeiculo(dto.getTipoVeiculo());
			veiculo.setPlacaLicenca(dto.getPlacaLicenca());
			veiculo.setAnoFabricacao(dto.getAnoFabricacao());
			veiculo.setAnoModelo(dto.getAnoModelo());
			veiculo.setSequencialChassi(dto.getSequencialChassi());
			veiculo.setSequencialMotor(dto.getSequencialMotor());
			
		}
		
	
}
