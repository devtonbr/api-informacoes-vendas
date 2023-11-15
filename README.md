````# API DE INFORMAÇÕES DE LOJAS - TRABALHO INFNET

Projeto conceito para utilização de Serviço Rest com Spring Boot

## Iniciando o Projeto

O projeto contém alguns exemplos de serviço REST. O projeto já está configurado em modo standalone

### Pré requisitos

```
Java 17 : ([https://www.oracle.com/br/java/technologies/javase/jdk17-archive-downloads.html]);

Spring Boot na versão 3.1.4:  (https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-dependencies/3.1.4);

Maven 3.9.5: (https://dlcdn.apache.org/maven/maven-3/3.9.5/binaries/apache-maven-3.9.5-bin.tar.gz)

Eclipse ou Intellij

Postman para Testes : (https://www.postman.com/downloads/)
```

## Rodando os Testes

Utilize o postman Para rodar os testes.

Collection estão na raiz do repositório.


```
InformacoesApi.postman_collection.json
```

## Rodando local

Inicie com a classe Application.java

## Deploy

Basta executar o comando maven install

Para rodar, vá na pasta target onde tem o artefato gerado e execute o comando:

```
java -jar api-informacoes-vendas-0.0.1-SNAPSHOT.jar
```

## Rodando via Docker (Precisa ter o Docker Instalado)

Execute o comando para gerar a imagem via Docker

```
docker build -t api-informacoes-vendas .
```

Para executar o container, rode o comando abaixo:

```
docker run --name api-informacoes-vendas -p 8081:8081 \
    -e SPRING_DATASOURCE_PASSWORD=<SENHA DO BANCO> \
    -e SPRING_DATASOURCE_USERNAME=<USUARIO DE BANCO> \
    -e MYSQL_HOST= <IP DO BANCO> \ 
    api-informacoes-vendas:latest
```

## Autores

* **Clayton Morais de Oliveira** 
````