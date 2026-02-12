package SistemadeAluguel.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Data
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Equipamento equipamento;

    private LocalDate dataInicio;

    private LocalDate dataPrevista;

    private LocalDate dataDevolucaoReal;

    private BigDecimal valorTotal;
}
