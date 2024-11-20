# Projeto-BD---MarketPet

  MarketPet é um marketplace inovador especializado em produtos pet de segunda mão, oferecendo uma plataforma sustentável para compra e venda de itens para animais de estimação. Este projeto foi desenvolvido como parte de um trabalho prático para a disciplina de Banco de Dados, no curso de Ciência da Computação da CESAR School.

## Estrutura do Projeto
  O projeto está organizado de forma modular, seguindo as práticas de desenvolvimento com Java e Spring Boot:

src/main/java/com/MarketPet/MarketPet/
├── config/                     # Configurações do banco de dados e web
├── controller/                 # Controladores REST para endpoints
├── model/                      # Modelos de dados representando entidades
├── repository/                 # Repositórios para acesso aos dados
└── service/                    # Serviços para lógica de negócios

## Tecnologias Utilizadas
- Java 11
- Spring Boot
- JDBC (Java Database Connectivity)
- brModelo para modelagem de dados
- MySQL como banco de dados
- HTML/CSS/JavaScript para a interface web
  
## Instalação
  Para instalar e executar o projeto localmente, siga os passos abaixo:

### 1) Clone o repositório:

      git clone https://github.com/usuario/MarketPet.git
      cd MarketPet

### 2) Configure o banco de dados:

Crie um banco de dados MySQL e importe o script de criação e povoamento disponível no repositório.

### 3) Configure as dependências do Maven:

Execute o seguinte comando para baixar as dependências necessárias:

       mvn clean install

### 4) Execute a aplicação:

Use o seguinte comando para iniciar a aplicação:

      mvn spring-boot:run
      cd Frontend
      npm install
      npm start

## Configuração
  A configuração do banco de dados pode ser ajustada no arquivo src/main/java/com/MarketPet/MarketPet/config/DatabaseConfig.java. Certifique-se de atualizar o URL do banco de dados, nome de usuário e senha conforme a sua configuração local.
