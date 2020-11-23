# Restful API desenvolvida com Spring Boot.
Operações CRUD para gerencimento de pets.

### Passos para rodar o projeto: 

##### 1 - Instalar Docker de acordo com o seu sistema operacional:
[Download Docker](https://docs.docker.com/get-docker/)

##### 2 - Após instalado o Docker, execute o seguinte comando para criar o servidor de banco de dados Postgres:
docker run --name postgres-docker -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres

##### 3 - Faça pull deste projeto e execute a classe principal:

```java
@SpringBootApplication
public class OriginalApplication {

	public static void main(String[] args) {
		SpringApplication.run(OriginalApplication.class, args);
	}

}
```
##### 4 - Assim que a aplicação estiver rodando, é possível consultar a documentação da API feita em Swagger no link abaixo:
[Documentação](http://localhost:8080/swagger-ui.html#/) 

##### 5 - Faça pull do projeto original-web disponível no link abaixo para rodar o front end:
[Front end app](https://github.com/fndribeiro/original-web)
