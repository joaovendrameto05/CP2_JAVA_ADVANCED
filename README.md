Projeto Spring Boot API - Gestão de Brinquedos (Checkpoint 2)

## Descrição do Projeto

Este documento apresenta o detalhamento do projeto desenvolvido para o Checkpoint 2 da disciplina de Programação Spring Boot com Persistência. O sistema consiste em uma API RESTful construída em linguagem Java utilizando o framework Spring Boot e o gerenciador de dependências Maven. O objetivo principal do trabalho foi criar uma aplicação robusta para gerenciar o catálogo de uma empresa de brinquedos voltada para o público de até catorze anos. Para isso, implementamos todas as operações fundamentais de persistência de dados, que incluem a criação, leitura, atualização e exclusão de registros, conectando a aplicação de forma direta ao banco de dados Oracle fornecido pela instituição.

Tecnologias e Ferramentas Utilizadas
* Java 
* Spring Boot (Spring Web, Spring Data JPA)
* Maven
* Banco de Dados Oracle (SQL Developer)
* Insomnia / Postman (Testes de Endpoints HTTP) 

A arquitetura do projeto foi construída de maneira sequencial e dividida em camadas lógicas. O processo começou com a geração da estrutura base da aplicação, onde definimos as dependências essenciais para rotas web e persistência de dados. Logo após, configuramos as credenciais de acesso e o dialeto do banco de dados no arquivo de propriedades do sistema, garantindo que o servidor local funcionasse corretamente na porta 8080. Na camada de modelo de dados, elaboramos a entidade responsável por espelhar a tabela de brinquedos no banco, configurando de forma rigorosa as colunas de identificador, nome, tipo, classificação, tamanho e preço. A camada de repositório foi configurada para automatizar as transações com o banco de dados, e a camada controladora foi desenvolvida para mapear os endereços da aplicação e gerenciar as respostas das requisições externas.

Durante o alinhamento das estruturas do projeto e a integração das pastas, enfrentamos alguns obstáculos técnicos e bloqueios de permissão do sistema operacional. Para manter a produtividade e resolver esses problemas de forma analítica, recorremos ao auxílio de ferramentas de inteligência artificial. O uso da IA foi extremamente pontual e necessário para nos ajudar a solucionar conflitos de versionamento no repositório, limpar arquivos corrompidos que travavam o ambiente de desenvolvimento e, principalmente, diagnosticar uma falha de comunicação com o banco de dados. Graças a esse suporte, conseguimos ajustar os scripts de criação da tabela e alinhar o comportamento das chaves primárias do Java para que fossem perfeitamente compatíveis com as exigências do padrão Oracle.


Passo a Passo do Desenvolvimento e Arquitetura

A construção da aplicação seguiu uma arquitetura em camadas e foi executada através das seguintes etapas sequenciais:

1. **Estruturação Base**: Geração do projeto através do Spring Initializr, definindo a estrutura de diretórios Maven e as dependências essenciais (Spring Web, Spring Data JPA e Oracle Driver);

2. **Configuração de Persistência**: Mapeamento das credenciais corporativas da instituição e das diretrizes do Hibernate (Dialeto Oracle) no arquivo `application.properties`, estabelecendo a comunicação na porta local 8080 do servidor Tomcat;
 
3. **Mapeamento Objeto-Relacional (Model)**: Criação da entidade `Brinquedo`, espelhando a tabela `TDS_TB_Brinquedos` no banco de dados[cite: 23]. [cite_start]Foram definidas e tipadas rigorosamente as colunas obrigatórias: `Id`, `Nome`, `Tipo`, `Classificacao`, `Tamanho` e `Preco`;

4. **Camada de Acesso a Dados (Repository)**: Implementação da interface estendendo `JpaRepository` para automatizar as transações SQL sem a necessidade de comandos manuais no código;

5. **Controlador REST (Controller)**: Desenvolvimento da classe `BrinquedoController` mapeando o endpoint primário `/brinquedos` para receber as requisições externas e coordenar as respostas HTTP;

6. **Resolução de Integrações (Uso de Inteligência Artificial)**: Durante a unificação das estruturas de repositório, o projeto enfrentou divergências técnicas. Ferramentas de Inteligência Artificial foram acionadas de forma analítica para:
    * Corrigir conflitos de versionamento do Git decorrentes de diretórios e pacotes locais desalinhados.
    * Refatorar caminhos absolutos e realizar limpeza do workspace (`EPERM block errors`).
    * Diagnosticar o Erro 500 originado por falhas no mapeamento do banco de dados (ORA-00955).
    * Estruturar os scripts DDL corretos e adequar o controle de chaves primárias (`IDENTITY`) para compatibilidade perfeita com o padrão Oracle.
  
## Documentação de Endpoints e Testes de CRUD

As operações foram validadas localmente utilizando o cliente HTTP Insomnia[cite: 18]. [cite_start]As capturas de tela das requisições bem-sucedidas estão anexadas a este repositório, conforme exigência descrita no documento base.

### 
1. Criar Registro (POST /brinquedos)
Realiza a inserção de dados através do envio de uma estrutura JSON, persistindo a informação no banco de dados.

**Exemplo de Payload:**
```json
{
  "nome": "Lego Star Wars Millennium Falcon",
  "tipo": "Blocos de Montar",
  "classificacao": "+9 anos",
  "tamanho": "Grande",
  "preco": 850.50
}

2. Consultar Registros (GET /brinquedos)
Retorna a lista em formato JSON contendo todos os brinquedos armazenados.

3. Atualizar Registro (PUT /brinquedos/{id})
Substitui os dados de um brinquedo existente especificado pelo ID na URL, enviando uma nova estrutura JSON.  

Exemplo de Payload:

JSON
{
  "nome": "Lego Star Wars Millennium Falcon",
  "tipo": "Blocos de Montar",
  "classificacao": "+9 anos",
  "tamanho": "Grande",
  "preco": 999.99
}

4. Excluir Registro (DELETE /brinquedos/{id})
Realiza a remoção definitiva do brinquedo no banco de dados informando o ID específico via URL.

Todas as funcionalidades da aplicação foram rigorosamente testadas localmente utilizando o software Insomnia, e as evidências visuais de sucesso acompanham a entrega final. No teste de criação de registro, enviamos uma requisição contendo os dados de um brinquedo, especificando o nome como Lego Star Wars Millennium Falcon, o tipo como blocos de montar, classificação para maiores de nove anos, tamanho grande e com o preço definido em oitocentos e cinquenta reais e cinquenta centavos. A operação de leitura também foi validada, retornando perfeitamente a lista dos itens persistidos no banco. Para validar a atualização, enviamos uma nova requisição direcionada ao identificador do brinquedo recém-criado, ajustando o seu preço para novecentos e noventa e nove reais e noventa e nove centavos, o que foi processado com sucesso. Por fim, o teste de exclusão foi realizado apontando o identificador do item na rota, confirmando a remoção definitiva do registro no banco de dados.


Integrantes-
João Victor Vendrameto - RM563665
Gabriel Saraiva Ambrosio - RM566552
