# Crud Ecommerce App

Este é um projeto de exemplo de um aplicativo de comércio eletrônico que demonstra operações CRUD (Create, Read, Update, Delete) em relação a produtos, pedidos e clientes. O aplicativo oferece funcionalidades básicas de gerenciamento de um comércio eletrônico, permitindo adicionar, visualizar, atualizar e excluir produtos, realizar pedidos e gerenciar informações de clientes.

## Funcionalidades Principais

- 🔒 Cadastro, leitura, atualização e exclusão de produtos e usuários.
- 🚀 Realização de pedidos.
- 📦 Gerenciamento de clientes.
- 💻 Autenticação com Jwt

## 1. Create (Criar)
   O CRUD começa com a operação de criação, que envolve adicionar novos registros ou objetos a uma fonte de dados, como um banco de dados. No contexto de um sistema de comércio eletrônico, isso pode significar adicionar novos produtos ao catálogo.

Exemplo de Criação (Create) - Criando uma order:

###  POST addProduct

``http://localhost:8081/order/:idOrder``

#### Request Headers

| Authorization      |                                                                                                                                                                                                                                         |
| ----------- |-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Bearer      | eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJyc0BnbWFpbC5jb20iLCJpZCI6MTAsIm5hbWUiOiJSZSBTZWNjaGltIiwiZXhwIjoxNjk2NjA0MzM1LCJpc3MiOiJjcnVkIn0.bFuiN9q461ayOz5OLUqDhWyGj2SF0rt-1OmUiWnjqx95mTscVKD5L2wN1bfpHEZldSaKfsBe7ukmDDpyaJHbSw |

#### Body 
```json
  {
  "product": {
    "id": 1,
    "name": "camera canon",
    "price": 586.56
  },
  "amount": 2,
  "total": 1173.12,
  "orderId": 1
}
```

Exemplo de Criação (Create) - Adicionar um Produto:
###  POST createOrder

``http://localhost:8081/order``

#### Request Headers

| Authorization      |                                                                                                                                                                                                                                         |
| ----------- |-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Bearer      | eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJyc0BnbWFpbC5jb20iLCJpZCI6MTAsIm5hbWUiOiJSZSBTZWNjaGltIiwiZXhwIjoxNjk2NjA0MzM1LCJpc3MiOiJjcnVkIn0.bFuiN9q461ayOz5OLUqDhWyGj2SF0rt-1OmUiWnjqx95mTscVKD5L2wN1bfpHEZldSaKfsBe7ukmDDpyaJHbSw |

#### Body
```json
  {
    "customer": 10
  }
```

### 2. Read (Ler)
   A operação de leitura envolve a recuperação de registros ou objetos da fonte de dados. Isso é usado para visualizar ou listar informações existentes.

Exemplo de Leitura (Read) - Listar Produtos:

###  GET getOrderById

``http://localhost:8081/order/9``

#### Body reponse
```json
  {
    "product": 2,
    "amount": 2
  }
```


#### 3. Update (Atualizar)
A operação de atualização envolve modificar registros ou objetos existentes na fonte de dados. Isso permite fazer alterações nos dados.

Exemplo de Atualização (Update) - Alterar o Preço de um Produto:

###  PUT updateOrder

``http://localhost:8081/order/ordemItem/9``

#### Body
```json
  {
    "product": 1,
    "amount": 4
  }
```

#### 4. Delete (Excluir)
A operação de exclusão envolve a remoção de registros ou objetos da fonte de dados.

Exemplo de Exclusão (Delete) - Remover uma Order:

###  DELETE updateOrder

``http://localhost:8081/order/4``


## Tecnologias Utilizadas
- 💻 Linguagem de Programação: Java

## Funcionalidades em produção
- Implementação de Clean Architecture
- 🌈 Frontend: interface a ser desenvolvida com React

## Maiores Desafios

- Sair de linguagens que não são tipada e começar a lidar com JAVA que é 
  fortemente tipado
- POO (programação orientada objeto), não utilizava esse paradigma para 
  programar.
- Aplicação de design SOLID 
