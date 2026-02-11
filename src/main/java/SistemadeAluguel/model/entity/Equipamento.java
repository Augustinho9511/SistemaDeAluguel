package SistemadeAluguel.model.entity;

import SistemadeAluguel.model.enums.CategoriaEquipamento;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Equipamento {

    private Long id;

    private String nome;

    private boolean disponivel;

    private Double valorDiaria;
    
    private CategoriaEquipamento categoria;
}
