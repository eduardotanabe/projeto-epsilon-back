package br.edu.ifms.projetoepsilon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.ifms.projetoepsilon.DTO.ImagemDTO;
import br.edu.ifms.projetoepsilon.entities.Imagem;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Long>{
	
	@Query(value = "SELECT i.nome, i.tipo_arquivo, i.tamanho FROM imagem i WHERE i.id = '1'", nativeQuery = true)
	List<ImagemDTO> findAllImagemDTO();

	@Query(value = "SELECT i.id, i.data, i.data_atualizacao, i.data_criacao, i.nome, i.tamanho, i.tipo_arquivo, i.veiculo_id FROM imagem i WHERE i.veiculo_id = :idVeiculo", nativeQuery = true)
	List<Imagem> findByIdVeiculo(@Param("idVeiculo") Long idVeiculo);
	
}
