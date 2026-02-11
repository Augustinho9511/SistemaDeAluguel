package SistemadeAluguel.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Aluguel {

    @Id
    @GeneratedValue
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
