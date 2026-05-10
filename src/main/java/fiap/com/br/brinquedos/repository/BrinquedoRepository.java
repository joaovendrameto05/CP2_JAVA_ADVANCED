package fiap.com.br.brinquedos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiap.com.br.brinquedos.model.Brinquedo;

@Repository
public interface BrinquedoRepository extends JpaRepository<Brinquedo, Long> {
}