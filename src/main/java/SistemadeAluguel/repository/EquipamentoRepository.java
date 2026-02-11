package SistemadeAluguel.repository;

import SistemadeAluguel.model.entity.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipamentoRepository extends JpaRepository <Equipamento, Long> {

    List<Equipamento> findByDisponivelTrue();
}
