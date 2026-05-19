# Projeto de Testes de API com RestAssured

Este projeto demonstra a implementação de testes automatizados para APIs RESTful utilizando **RestAssured** em **Java 21**, com gerenciamento de dependências via **Maven**. Além disso, inclui um script de teste de carga com **Apache JMeter** e uma configuração de pipeline de Integração Contínua (CI) usando **GitHub Actions**.

## 🚀 Tecnologias Utilizadas

*   **Java 21**: Linguagem de utilizada.
*   **Maven**: Ferramenta de automação de build e gerenciamento de dependências.
*   **RestAssured**: Biblioteca Java para testar e validar serviços REST.
*   **JUnit 5**: Framework de testes para Java.
*   **Jackson**: Biblioteca para manipulação de JSON.
*   **Lombok**: Biblioteca para reduzir código boilerplate.
*   **Apache JMeter**: Ferramenta para testes de carga e desempenho.
*   **GitHub Actions**: Plataforma de CI/CD para automação de workflows.

## 📦 Estrutura do Projeto

```
trabalho-restassured/
├── .github/
│   └── workflows/
│       └── maven.yml         # Configuração do GitHub Actions
├── src/
│   ├── main/
│   │   └── java/
│   │       └── br/trabalho/ap3/
│   │           ├── App.java
│   │           └── model/
│   │               └── Endereco.java
│   └── test/
│       └── java/
│           └── br/trabalho/ap3/
│               └── ViaCepApiTest.java  # Testes automatizados com RestAssured
│       └── resources/
│           └── viacep-schema.json    # Schema JSON para validação
├── pom.xml                   # Arquivo de configuração do Maven
├── load_test.jmx             # Script de teste de carga do JMeter
└── README.md                 # Este arquivo
```

## ⚙️ Configuração e Execução

### Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

*   **Java Development Kit (JDK) 21**
*   **Apache Maven**
*   **Apache JMeter** (opcional, para executar o teste de carga localmente)

### Configuração do Projeto

1.  **Clone o repositório:**
    ```bash
    git clone <link_deste_repositorio>
    cd trabalho-restassured
    ```

2.  **Compile o projeto:**
    ```bash
    mvn clean install
    ```

### Execução dos Testes Automatizados (RestAssured)

Para executar os testes funcionais localmente, utilize o Maven:

```bash
mvn test
```

Os resultados dos testes serão exibidos no console e um relatório detalhado será gerado no diretório `target/surefire-reports/`.

### Execução do Teste de Carga (JMeter)

1.  **Abra o JMeter:**
    ```bash
    jmeter
    ```

2.  **Carregue o script:** No JMeter, vá em `File > Open` e selecione o arquivo `load_test.jmx` localizado na raiz do projeto.

3.  **Execute o teste:** Clique no botão "Start" (seta verde) na barra de ferramentas do JMeter para iniciar o teste de carga.

4.  **Visualize os resultados:** Adicione "View Results Tree" ou "Summary Report" aos seus Listeners no JMeter para analisar os resultados do teste.

## 🚀 Pipeline de CI/CD com GitHub Actions

Este projeto está configurado com um pipeline de CI/CD utilizando GitHub Actions. O arquivo de workflow (`.github/workflows/maven.yml`) define os seguintes passos:

*   **Trigger**: O workflow é acionado em cada `push` (envio de código) e `pull_request` (solicitação de pull) para o repositório.
*   **Configuração do Ambiente**: Configura um ambiente com **JDK 21** em uma máquina `ubuntu-latest`.
*   **Build**: Compila o projeto Maven.
*   **Testes**: Executa os testes automatizados com `mvn test`.

Qualquer falha nos testes ou no build resultará em uma falha no pipeline, indicando que o código não está pronto para ser integrado ou implantado.

## 📝 Relatório Técnico

Um relatório técnico detalhado (`relatorio_tecnico.md`) foi elaborado, descrevendo a API testada (ViaCEP), a metodologia de testes, as ferramentas utilizadas, os cenários de teste e uma análise crítica dos resultados esperados. Este relatório complementa a documentação do projeto, fornecendo insights sobre a qualidade e o desempenho da API.
