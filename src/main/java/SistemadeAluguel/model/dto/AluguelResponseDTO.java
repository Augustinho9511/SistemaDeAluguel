package SistemadeAluguel.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AluguelResponseDTO {

    Long id;
    String nomeCliente;
    String nomeEquipamento;
    LocalDate datapPrevista;
    BigDecimal valorTotal;
}
