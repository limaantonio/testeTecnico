<h1 align="center">
  Desafio Muxi
</h1>

# Indice
- [Sobre](#-Sobre)
- [Tecnologias Utilizadas](#-tecnologias-Utilizadas)
- [Como baixar o projeto](#-como-baixar-o-projeto)
- [Rotas](#-rotas)


## 🗒 Sobre


O **Desafio Muxi** consiste em uma API
simples que gerencia determinados Dados, aqui denominados (Terminal). Atráves dos end-points fornecidos pela API é possivel Cadastrar, Listar, Listar por um determinado atributo (LOGIC) e Atualizar. Esta API foi desenvolvida focada nas melhores práticas de programação, aplicando conceitos de SOLID e TDD.

## 🔗  Tecnologias utilizadas

- [Java](https://www.java.com/pt-BR/')
- [Spring Boot](https://spring.io/projects/spring-boot')
- [HSQLDB](http://hsqldb.org/')
- [JUnit-5](https://junit.org/junit5/')
- [REST Assured](https://rest-assured.io/')
- [Swagger](https://swagger.io/specification/')


## 📦 Como baixar o projeto

* Java version 11.0.10

```bash

  #Clonar o repositório
  $git clone https://github.com/limaantonio/testeTecnico
  
  # Entrar no repositório
  $ cd testeTecnico

  # Inciar o projeto
  Executar

```

## 🚀 Rotas

```
  Todas as requisições do POST para está API devem ter o cabeçalho Content-Type: application/json.A API contém as seguintes rotas:
  
  Rotas TERMINAL:
  
  * GET api/v1/terminais : lista todas os TERMINAIS cadastradas
  * GET api/v1/terminal/[logic] : lista um TERMINAL de acordo com o atributos (LOGIC) informado
  * POST api/v1/terminal/[logic] : cria um novo TERMINAL
  * PUT api/v1/terminal/[logic] : atualiza um TERMINAL de acordo com o atributos (LOGIC) informado

  Documentação:
  * GET /swagger-ui.html: rota para visualizar a documentação da API
  
```

## 🗒 Documentação

Teste a aplicação através da documentação: https://desafio-tec-muxi.herokuapp.com/swagger-ui.html#/terminal-resource



Desenvolvido por Antonio Carlos
