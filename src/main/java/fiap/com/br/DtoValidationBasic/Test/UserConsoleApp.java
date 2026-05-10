package fiap.com.br.DtoValidationBasic.Test;

import fiap.com.br.DtoValidationBasic.DTO.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class UserConsoleApp implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        // Criar tabela se não existir
        jdbcTemplate.execute("""
            CREATE TABLE IF NOT EXISTS users (
                id IDENTITY PRIMARY KEY,
                nome VARCHAR(50) NOT NULL
            )
        """);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Adicionar novo usuário");
            System.out.println("2. Listar usuários");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            if (opcao == 1) {
                System.out.print("Digite o nome do usuário: ");
                String nome = scanner.nextLine();
                UserDto userDto = new UserDto(nome);
                jdbcTemplate.update("INSERT INTO users (nome) VALUES (?)", userDto.getNome());
                System.out.println("Usuário salvo com sucesso!");
            } else if (opcao == 2) {
                List<UserDto> usuarios = jdbcTemplate.query(
                        "SELECT nome FROM users",
                        (rs, rowNum) -> new UserDto(rs.getString("nome"))
                );
                System.out.println("\n--- Lista de Usuários ---");
                usuarios.forEach(System.out::println);
            } else if (opcao == 0) {
                System.out.println("Encerrando...");
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }
}
