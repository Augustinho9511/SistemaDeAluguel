package SistemadeAluguel.repository;

import SistemadeAluguel.model.entity.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface AluguelRepository extends JpaRepository <Aluguel, Long> {

    @Query("SELECT SUM(a.valorTotal) FROM Aluguel a WHERE UPPER(a.status) = 'FINALIZADO'")
    BigDecimal calcularFaturamentoTotal();
}
