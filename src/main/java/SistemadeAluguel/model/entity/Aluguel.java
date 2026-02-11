package SistemadeAluguel.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Aluguel {

    private Long id;

    private Cliente cliente;

    private Equipamento equipamento;

    private String dataInicio;

    private String dataPrevista;

    private String dataDevolucaoReal;

    private Double valorTotal;
}
