package SistemadeAluguel.model.entity;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cliente {

    private Long id;

    @NotBlank(message = "O nome do cliente é obrigatório")
    private String nome;

    private Double cpf;

    @ValiedEmail(message = "O email deve ser válido")
    private String email;
}
