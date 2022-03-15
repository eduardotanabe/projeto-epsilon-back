package br.edu.ifms.projetoepsilon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifms.projetoepsilon.entities.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

	
}
