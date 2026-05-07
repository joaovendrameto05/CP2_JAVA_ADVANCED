package fiap.com.br.DtoValidationBasic.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String nome;

    // Construtor padrão (obrigatório para frameworks como Spring)
    public UserDto() {
    }

    // Construtor com argumento
    public UserDto(String nome) {
        this.nome = nome;
    }

    // Getter
    public String getNome() {
        return nome;
    }

    // Setter
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Opcional: para debug
    @Override
    public String toString() {
        return "UserDto{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
