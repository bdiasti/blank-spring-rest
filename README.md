
Este projeto em branco spring rest possui
-------------

1) Tratamento de erros

2) Versionamento

3) Paginação e ordenação de resources

4) Segurança com JWT

6) Teste unitário

7) Hateoas

8) Logger

9) Cache

10) Docker


Para utilizar a Blank project é necessário
-------------

1) Autenticar em: localhost:8080/auth passando o json abaixo via post
{
	
	"username":"admin",
	"password":"admin"
}

2) O retorno deste post será um token que poderá ser usado e passado no header como:

Authorization: token-retornado


Rodando o Blank project com Docker
-------------

1) Rode maven install para gerar o pacote final em /target

2) No diretorio home da app, rode docker build -t blank-project-image .

3) Verifique se a imagem foi gerada: docker images

4) Rode seu container com Blank project: docker run -d --name blank-project -p 8080:8080 blank-project-image


Caso queira o Mysql rodando com o blank project, faça:

1) Colocar o ip da maquina docker na String de conexão em application.properties

2) Ir na home do app e digitar: docker-compose up


Para subir o mysql de testes via docker
-------------

docker run --name container-teste-mysql  -p 3307:3306 -e MYSQL_ROOT_PASSWORD=senha-mysql -d mysql/mysql-server:latest

docker exec -it container-teste-mysql bash

docker start container-teste-mysql
