package SistemadeAluguel.repository;

import SistemadeAluguel.model.entity.Aluguel;
import SistemadeAluguel.model.enums.StatusAluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface AluguelRepository extends JpaRepository <Aluguel, Long> {

    @Query("SELECT SUM(a.valorTotal) FROM Aluguel a WHERE UPPER(a.status) = 'FINALIZADO'")
    BigDecimal calcularFaturamentoTotal();

    @Query("SELECT a FROM Aluguel a WHERE UPPER (a.status) = 'ABERTO' AND a.dataPrevista < :hoje")
    List<Aluguel> buscarAlugueisAtrasados(@Param("hoje") LocalDate hoje);

    boolean existsByClienteClienteIdAndStatusAndDataPrevistaBefore(Long clienteId, StatusAluguel status, LocalDate data);
}
