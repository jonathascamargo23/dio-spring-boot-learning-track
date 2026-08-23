# API Inteligente de Orçamento com Spring AI

Projeto desenvolvido como desafio final da trilha de Spring Boot, utilizando Spring AI para integrar inteligência artificial a uma API de controle financeiro.

## Objetivo

O projeto permite registrar e consultar transações financeiras e utilizar inteligência artificial para interpretar comandos enviados por áudio.

O fluxo principal da aplicação é:

Áudio → Transcrição → Inteligência Artificial → Tool Calling → Operação financeira → Resposta em áudio

## Tecnologias utilizadas

- Java
- Spring Boot
- Spring AI
- OpenAI
- JPA / Hibernate
- H2
- Lombok
- Gradle
- Git
- GitHub

## Funcionalidades

A API permite:

- Registrar transações financeiras;
- Consultar transações por categoria;
- Processar comandos enviados por áudio;
- Transcrever áudio utilizando OpenAI Whisper;
- Utilizar IA para interpretar comandos;
- Executar funções da aplicação através de Tool Calling;
- Gerar respostas em áudio.

## Melhoria implementada

Como evolução do projeto apresentado durante o curso, foi adicionada uma nova ferramenta de Tool Calling chamada:

`get-total-by-category`

Essa ferramenta permite que a inteligência artificial consulte o valor total das transações de uma determinada categoria.

Por exemplo, ao enviar um comando como:

> "Quanto eu gastei com mercado?"

a IA identifica a categoria `GROCERIES` e utiliza a ferramenta `get-total-by-category` para calcular o total das transações dessa categoria.

### Funcionamento

```text
Usuário
   ↓
Comando de voz
   ↓
Whisper
   ↓
Texto
   ↓
Spring AI
   ↓
Tool Calling
   ↓
GetTotalByCategoryUseCase
   ↓
TransactionRepository
   ↓
Banco de dados
   ↓
Total da categoria
   ↓
Resposta da IA
   ↓
Áudio
Implementação
```

Foi criado o:

`GetTotalByCategoryUseCase.java`

O Use Case utiliza o padrão de Tool Calling do Spring AI através da anotação:
```
@Tool(
    name = "get-total-by-category",
    description = "Calcula o valor total das transações de uma categoria"
)
```
A ferramenta consulta as transações da categoria informada e realiza a soma dos valores.

Também foi atualizado o TransactionRepository para permitir a consulta das transações e o JpaTransactionRepository para implementar essa operação.

Por fim, o TransactionController foi atualizado para disponibilizar a nova ferramenta ao ChatClient:
```
.defaultTools(
    persistTransactionUseCase,
    listTransactionsByCategoryUseCase,
    getTotalByCategoryUseCase
)
```
## Teste realizado

Foram cadastradas transações de teste na categoria GROCERIES.

Exemplo:
```
{
  "description": "Compra no mercado",
  "amount": 15000,
  "category": "GROCERIES"
}
```
Também foi adicionada outra transação:
```
{
  "description": "Compra de alimentos",
  "amount": 25000,
  "category": "GROCERIES"
}
```
Depois foi enviado um comando de voz solicitando o total gasto com mercado.

A aplicação realizou corretamente o fluxo:
```
Áudio
↓
Transcrição
↓
Interpretação pela IA
↓
Tool Calling
↓
Consulta das transações
↓
Soma dos valores
↓
Resposta
```
## Como executar
Clone ou faça o download do projeto.
Abra a pasta 05-spring-ai em uma IDE compatível com Java.
Configure a variável de ambiente:
OPENAI_API_KEY
Execute a classe:
BudgetingApplication.java
Utilize um cliente HTTP, como Postman ou Insomnia, para testar os endpoints.
Criar transação
POST /transactions
Consultar transações por categoria
GET /transactions/GROCERIES
Enviar áudio para a IA
POST /transactions/ai

O endpoint deve receber um arquivo de áudio através do campo:

file
Aprendizados

Durante o desenvolvimento foram praticados conceitos de:

Spring Boot;
Spring AI;
ChatClient;
Tool Calling;
integração com modelos de IA;
transcrição de áudio;
geração de áudio;
persistência de dados;
arquitetura em camadas;
criação de novos casos de uso;
integração entre IA e regras reais da aplicação.

## Autor

Jonathas Camargo Oliveira Barboza

## Notes

- Educational final project focused on AI plus architectural discipline.
- External provider integration tests may require active credentials.
