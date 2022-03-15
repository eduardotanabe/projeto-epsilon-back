package br.edu.ifms.projetoepsilon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.ifms.projetoepsilon.entities.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
	
	@Query(value = "SELECT * FROM Veiculo v WHERE v.marca_id = :idMarca", nativeQuery = true)
	List<Veiculo> findVehiclesByMarca(@Param("idMarca") Integer idMarca);

	@Query(value = "SELECT * FROM Veiculo v WHERE v.placa_licenca = :placa", nativeQuery = true)
	List<Veiculo> findVehiclesByPlaca(@Param("placa") String placa);
	
	@Query(value = "SELECT * FROM Veiculo v WHERE v.marca_id = :idMarca AND v.modelo LIKE %:modelo%", nativeQuery = true)
	List<Veiculo> findVehiclesByMarcaModelo(@Param("idMarca") Integer idMarca, @Param("modelo") String modelo);

	@Query(value = "SELECT * FROM Veiculo v WHERE v.marca_id = :idMarca AND v.ano_modelo = :ano", nativeQuery = true)
	List<Veiculo> findVehiclesByMarcaAno(@Param("idMarca") Integer idMarca, @Param("ano") Integer anoModelo);

	@Query(value = "SELECT * FROM Veiculo v WHERE v.marca_id = :idMarca AND v.ano_modelo = :ano AND v.modelo LIKE %:modelo%", nativeQuery = true)
	List<Veiculo> findVehiclesByMarcaModeloAno(@Param("idMarca") Integer idMarca, @Param("modelo") String modelo, @Param("ano") Integer anoModelo);
	

}
