package SistemadeAluguel.repository;

import SistemadeAluguel.model.entity.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AluguelRepository extends JpaRepository <Aluguel, Long> {
}
