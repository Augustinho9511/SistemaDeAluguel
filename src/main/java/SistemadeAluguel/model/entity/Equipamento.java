package SistemadeAluguel.model.entity;

import SistemadeAluguel.model.enums.CategoriaEquipamento;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Equipamento {


    private Long equipamentoId;

    private String nome;

    private boolean disponivel;

    private BigDecimal valorDiaria;

    private CategoriaEquipamento categoria;
}
