<h1 align="center">
  Desafio Muxi
</h1>

# Indice
- [Sobre](#-Sobre)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Como baixar o projeto](#-como-baixar-o-projeto-e-executar-a-aplicação)
- [API](#-api)
- [Rotas](#-rotas)
- [Documentação](#-documentação)


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
- [JSON Schema](https://json-schema.org/')

## 📦 Como baixar o projeto e executar a aplicação

* Java version 11.0.10

```bash

  #Clonar o repositório
  $git clone https://github.com/limaantonio/testeTecnico
  
  # Entrar no repositório
  $ cd testeTecnico

  # Inciar o projeto
  Abra o projeto na IDE de sua preferência e execute a aplicação
  
  ##Executando os testes: 
  - Vá até a pasta: test\repository\TerminalRepository para executar os testes unitários
  -                 test\service\TerminalService para executar os testes unitários
  -                 test\resource\TerminalResource para executar os testes de integração

```

## 
![favicon-16x16](https://github.com/heroku/favicon/raw/master/favicon.iconset/icon_16x16.png) API

A aplicação encontra-se hospeadado no **Heroku**, portanto é possivel utilizar a **API em produção**, acesse: https://desafio-tec-muxi.herokuapp.com/api/v1


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
