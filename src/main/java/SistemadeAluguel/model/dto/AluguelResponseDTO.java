package SistemadeAluguel.model.dto;

import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Data
public class AluguelResponseDTO {

    Long id;
    String nomeCliente;
    String nomeEquipamento;
    LocalDate datapPrevista;
    BigDecimal valorTotal;
    String status;
}
