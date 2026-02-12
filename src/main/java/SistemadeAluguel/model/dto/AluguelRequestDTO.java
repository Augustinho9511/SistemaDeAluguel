package SistemadeAluguel.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AluguelRequestDTO {

    Long clienteId;
    Long equipamentoId;
    Integer quantidadeDias;
}
