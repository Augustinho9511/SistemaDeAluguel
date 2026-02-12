package SistemadeAluguel.model.entity;

import SistemadeAluguel.model.enums.CategoriaEquipamento;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Data

public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipamentoId;

    private String nome;

    private boolean disponivel;

    private BigDecimal valorDiaria;

    @Enumerated(EnumType.STRING)
    private CategoriaEquipamento categoria;
}
