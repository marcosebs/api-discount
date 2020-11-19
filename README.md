# Challenge

# Serviço de Pedidos/Descontos

Micro-serviço criado para atender requisições CRUD para usuário e para categorias de desconto.

Serviço também dispõe de um endpoint criado para calcular qual o desconto que determinado usuário tem em seus pedidos.

### Instalação

Requisitos
[Java 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
[Docker](https://docs.docker.com/docker-for-windows/install/)

Instalar as dependências do projeto e rodar os seguintes comandos:

```sh
$ mvnw spring-boot:build-image
$ docker-compose build
$ docker-compose up
```

Após executar a ação, teremos um container com a aplicação na porta 8080.
A documentaço da aplicação encontra-se na seguinte url (http://localhost:8080/swagger-ui.html#/)
Caso necessário, pode-se testar localmente utilizando a collection do postman 
(ApiDiscountCollection.postman_collection) na raiz do projeto.