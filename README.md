<h1>Picpay Simplificado</h1>

 ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
 ![Spring](https://img.shields.io/badge/Spring-6DB33F.svg?style=for-the-badge&logo=Spring&logoColor=white)
 [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
 
 <P>Para desenvolver esse projeto, foi usado Java, Spring Boot e H2 como database</P>
 <p>Este é um projeto de desafio Backend do <a href="https://picpay.com/">PicPay</a>. O objetivo do projeto é realizar transferências entre duas entidades, 
   comuns e lojistas.</p>
   <p>Acesso para a proposta do <a href="https://github.com/PicPay/picpay-desafio-backend">Desafio Back-end PicPay</a></p>
  
   <h2>Sumário</h2>
<ul>
  <li><a href="#func">Funcionalidades</li>
  <li><a href="#pratic">Práticas adotadas</li>
    <li><a href="#instalacao">Instalação</li>
      <li><a href="#endpoints">API Endpoints</li>
</ul>
   
   <h2 id="func">Funcionalidades</h2>
   <ul>
     <li>Para ambos tipos de usuário, o cadastro é realizado com primeiro e último nome, documento(CPF/CNPJ), e-mail, senha, tipo de usuário e saldo. O e-mail e documento são 
       únicos no sistema.</li>
     <li>Usuários podem enviar dinheiro (efetuar transferência) para lojistas e entre usuários.</li>
     <li>Lojistas só recebem transferências, não enviam dinheiro para ninguém.</li>
     <li>Usuários só podem enviar dinheiro se houver saldo.</li>
     <li>No recebimento de pagamento, o usuário recebe uma notificação de envio de transferência</li>
   </ul>

   <h2 id="pratic">Práticas adotadas</h2>
<ul>
<li>Design Patterns, SOLID
<li>API REST
<li>Consultas com Spring Data JPA
<li>Injeção de Dependências
<li>Tratamento de respostas de erro
</ul>

<h2 id="instalacao">Instalação</h2>
<ol>
  <li>Clonar o repositório Git</li>
  
      https://github.com/sthevenalves/backend-picpay-simplified.git

  <li>Navegar para o diretório do projeto:</li>
  
      cd picpay-simplified

  <li>Compilar o projeto com Maven</li>

    mvn clean install

  <li>Executar o projeto:</li>

    java -jar target/picpay-simplified.jar
</ol>

<h2 id="endpoints">API Endpoints</h2>
<p>Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta <a href="https://www.postman.com/">Postman</a></p>

http POST :8080/users</li>
  
    {
  	  "firsName": "Julio",
      "lastName": "Alves",
      "password": "senha",
      "document": "12345641",
      "email": "julioalves@example.com",
      "userType": "COMMOM",
      "balance": 80
    }
  
  http POST :8080/users</li>

     {
     "firsName": "Stheven",
      "lastName": "Soares",
      "password": "senha",
      "document": "12345610",
      "email": "sthevensoares@example.com",
      "userType": "MERCHANT",
      "balance": 165
    }
  http GET :8080/users</li>
  
    [
      {
          "id": 1,
          "firsName": "Julio",
          "lastName": "Alves",
          "document": "12345641",
          "email": "julioalves@example.com",
          "password": "senha",
          "balance": 80.00,
          "userType": "COMMOM"
      },
      {
          "id": 2,
          "firsName": "Stheven",
          "lastName": "Soares",
          "document": "12345610",
          "email": "sthevensoares@example.com",
          "password": "senha",
          "balance": 165.00,
          "userType": "MERCHANT"
      }
    ]
  http POST :8080/transactions</li>
  <p>Request:</p>
   
    
    {
  	   "senderId": 1,
       "receiverId": 2,
       "value": 53 
    }
  <p>Reponse:</p>
  
      {
        "id": 1,
        "amount": 53,
        "sender": {
            "id": 1,
            "firsName": "Julio",
            "lastName": "Alves",
            "document": "12345641",
            "email": "julioalves@example.com",
            "password": "senha",
            "balance": 27.00,
            "userType": "COMMOM"
        },
        "receiver": {
           "id": 2,
           "firsName": "Stheven",
           "lastName": "Soares",
           "document": "12345610",
           "email": "sthevensoares@example.com",
           "password": "senha",
           "balance": 213.00,
           "userType": "MERCHANT"
        },
        "timestamp": "2024-01-27T11:13:55.2775202"
    }
    
