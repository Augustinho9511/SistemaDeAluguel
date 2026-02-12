package SistemadeAluguel.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class AluguelResponseDTO {

    Long id;
    String nomeCliente;
    String nomeEquipamento;
    LocalDate datapPrevista;
    BigDecimal valorTotal;
}
