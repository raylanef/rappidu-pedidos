# Rappidu Pedidos
Bem-vindo ao repositório da aplicação rappidu-pedidos. Este projeto é um sistema desenvolvido em Spring Boot, focado no gerenciamento de pedidoss.

## Requisitos
Certifique-se de que os seguintes requisitos estão instalados no seu ambiente:

- Java 17 ou superior
- Maven 3.8 ou superior
- Banco de dados configurado conforme o application.properties
- Configuração do Projeto


## Clone este repositório:
```
git clone https://github.com/seu-usuario/rappidu-pedidos.git
cd rappidu-pedidos
```
Configure o arquivo application.properties localizado em src/main/resources para apontar para seu ambiente de banco de dados.

## Instale as dependências do projeto:
```
mvn clean install
```
## Executando a Aplicação
Para rodar a aplicação localmente, use o seguinte comando:
```
mvn spring-boot:run
```
Alternativamente, execute o jar gerado:
```
java -jar target/rappidu-pedidos-0.0.1-SNAPSHOT.jar
```
## Acessando a Aplicação
Após iniciar o projeto, a aplicação estará disponível em:
http://localhost:8080

Documentação Swagger
A documentação da API está disponível em:
http://localhost:8080/swagger-ui.html

Executando Testes
Para rodar os testes unitários e de integração, execute:
```
mvn test
```
## Contribuição
Sinta-se à vontade para contribuir com melhorias ou correções para este projeto. Faça um fork do repositório, aplique suas mudanças em uma branch e envie um pull request.

Licença
Este projeto é licenciado sob a licença MIT. Veja o arquivo LICENSE para mais informações.
