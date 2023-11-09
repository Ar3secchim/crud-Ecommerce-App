🚀 Get started here
===================

Este é um projeto de exemplo de um aplicativo de comércio eletrônico que demonstra operações CRUD (Create, Read, Update, Delete) em relação a produtos, pedidos e clientes. O aplicativo oferece funcionalidades básicas de gerenciamento de um comércio eletrônico, permitindo adicionar, visualizar, atualizar e excluir produtos, realizar pedidos e gerenciar informações de clientes.

### POST Login
```
http://localhost:8081/login/
```

Este é um pedido POST para realizar o login. O Response fornece um token de autorização.

**Body** (json)
```json
{
  "token": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJyc2Rhc2Rzc0BnbWFpbC5jb20iLCJpZCI6MSwibmFtZSI6IlJlIFNlY2NoaW0iLCJleHAiOjE2OTc3MjMyNTUsImlzcyI6ImNydWQifQ.Agz6MfCfvPqM6odXkb9Rt3InOMciiue9fOTnI3wG0LoQRph3Syat8kwy-KSaHfvotDO-w5CQ-LiWKr6\_u2GXqw"
}
```
Customer
--------

Esta seção descreve os métodos relacionados aos clientes.

### GET Get List Customer

```
http://localhost:8081/customer
```

Este é um pedido GET e é usado para "obter" dados de um ponto final. Não há corpo de pedido para um pedido GET, mas você pode usar parâmetros de consulta para especificar o recurso do qual deseja obter dados (por exemplo, neste pedido, temos `id=1`).

Uma resposta GET bem-sucedida terá um status de `200 OK` e deve incluir algum tipo de corpo de resposta - por exemplo, conteúdo da web HTML ou dados JSON.

### GET Get List Customer By Id

```
http://localhost:8081/customer
```

Este é um pedido GET e é usado para "obter" dados de um ponto final. Não há corpo de pedido para um pedido GET, mas você pode usar parâmetros de consulta para especificar o recurso do qual deseja obter dados (por exemplo, neste pedido, temos `id=1`).

Uma resposta GET bem-sucedida terá um status de `200 OK` e deve incluir algum tipo de corpo de resposta - por exemplo, conteúdo da web HTML ou dados JSON.

### GET Get List Customer By Name

```
http://localhost:8081/customer/name/renara
```
Este é um pedido GET e é usado para "obter" dados de um ponto final. Não há corpo de pedido para um pedido GET, mas você pode usar parâmetros de consulta para especificar o recurso do qual deseja obter dados (por exemplo, neste pedido, temos `id=1`).

Uma resposta GET bem-sucedida terá um status de `200 OK` e deve incluir algum tipo de corpo de resposta - por exemplo, conteúdo da web HTML ou dados JSON.

### POST Create Customer

```
http://localhost:8081/customer
```

Este é um pedido POST, que envia dados para uma API por meio do corpo do pedido. Este pedido envia dados em formato JSON, e os dados são refletidos na resposta.

Um pedido POST bem-sucedido normalmente retorna um código de resposta `200 OK` ou `201 Created`.

**Body** (json)

```json
{
	"name": "Re Secchim",
	"email": "renarasecchim@gmail.com",
	"address": "rua joão balbi, 917",
	"password":"@Ar3secchim"
}
```
PUT Update customer
---------------

```
http://localhost:8081/customer/10
```

Este é um pedido PUT e é usado para substituir uma peça de dados existente. Por exemplo, após criar uma entidade com um pedido POST, você pode querer modificá-la posteriormente. Isso pode ser feito usando um pedido PUT. Normalmente, você identifica a entidade sendo atualizada incluindo um identificador na URL (por exemplo, `id=1`).

Um pedido PUT bem-sucedido normalmente retorna um código de resposta `200 OK`.

**Body** (json)

```json
{
	"name": "Secchim",
	"email": "r@gmail.com",
	"address": "rua joão balbi, 917",
	"password":"@Ar3secchim"
}
```
DELETE Delete customer
----------------------

```
http://localhost:8081/customer/8
```
Este é um pedido DELETE e é usado para excluir dados que foram criados anteriormente por meio de um pedido POST. Normalmente, você identifica a entidade a ser excluída incluindo um identificador na URL (por exemplo, `id=1`).

Um pedido DELETE bem-sucedido normalmente retorna um código de resposta `200 OK`, `202 Accepted` ou `204 No Content`.

Product
-------

Esta seção descreve os métodos relacionados aos produtos.

### GET Get List Product

```
http://localhost:8081/product
```

**Request Headers**

Authorization

Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJyc2Rhc2Rzc0BnbWFpbC5jb20iLCJpZCI6MSwibmFtZSI6IlJlIFNlY2NoaW0iLCJleHAiOjE2OTc3MjMyNTUsImlzcyI6ImNydWQifQ.Agz6MfCfvPqM6odXkb9Rt3InOMciiue9fOTnI3wG0LoQRph3Syat8kwy-KSaHfvotDO-w5CQ-LiWKr6\_u2GXqw

### GET Get Product By Id
```
http://localhost:8081/product/1
```

Este é um pedido GET e é usado para "obter" dados de um ponto final. Não há corpo de pedido para um pedido GET, mas você pode usar parâmetros de consulta para especificar o recurso do qual deseja obter dados.

### POST Create Product

```
http://localhost:8081/product
```
Este é um pedido POST, que envia dados para uma API por meio do corpo do pedido. Este pedido envia dados em formato JSON, e os dados são refletidos na resposta.

**Request Headers**

| Authorization |
| --- |
| Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJyc2Rhc2Rzc0BnbWFpbC5jb20iLCJpZCI6MSwibmFtZSI6IlJlIFNlY2NoaW0iLCJleHAiOjE2OTc3MjMyNTUsImlzcyI6ImNydWQifQ.Agz6MfCfvPqM6odXkb9Rt3InOMciiue9fOTnI3wG0LoQRph3Syat8kwy-KSaHfvotDO-w5CQ-LiWKr6_u2GXqw |

**Body** (json)

```json
{ 	
    "name": "PS5", 	
    "description": "console game com ssd 1 terabyte", 	
    "price": 4586.57 
}
```


### DELETE Delete Product By Id

```
http://localhost:8081/product/1
```

Este é um pedido DELETE para excluir um produto com um ID específico.

**Body** (json)

```json
{ 	
    "name": "PS5", 	
    "description": "console game com ssd 1 terabyte", 	
    "price": 4586.57 
}
```
Order
-----

Esta seção descreve os métodos relacionados aos pedidos.

### POST Create Order

```
http://localhost:8081/order
```

Este é um pedido POST para criar um novo pedido. O pedido cria um pedido com base nos dados fornecidos no corpo da solicitação.

**Request Headers**

| Authorization |
| --- |
| Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJyc2Rhc2Rzc0BnbWFpbC5jb20iLCJpZCI6MSwibmFtZSI6IlJlIFNlY2NoaW0iLCJleHAiOjE2OTc3MjMyNTUsImlzcyI6ImNydWQifQ.Agz6MfCfvPqM6odXkb9Rt3InOMciiue9fOTnI3wG0LoQRph3Syat8kwy-KSaHfvotDO-w5CQ-LiWKr6_u2GXqw |

**Body** (json)

```json
{
  "customer": 1
}
```

### GET Get Order

```
http://localhost:8081/order
```

Este é um pedido GET para obter a lista de pedidos. Ele retorna a lista de todos os pedidos disponíveis.

**Request Headers**

| Authorization |
| --- |
| Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJyc2Rhc2Rzc0BnbWFpbC5jb20iLCJpZCI6MSwibmFtZSI6IlJlIFNlY2NoaW0iLCJleHAiOjE2OTc3MjMyNTUsImlzcyI6ImNydWQifQ.Agz6MfCfvPqM6odXkb9Rt3InOMciiue9fOTnI3wG0LoQRph3Syat8kwy-KSaHfvotDO-w5CQ-LiWKr6_u2GXqw |

### GET Get Order By Id

```
http://localhost:8081/order/1
```

Este é um pedido GET para obter detalhes de um pedido específico com base no ID fornecido.

### DELETE Delete Order

```
http://localhost:8081/order/1
```
Este é um pedido DELETE para excluir um pedido com um ID específico.


### OrderItem

Esta seção descreve os métodos relacionados aos itens de pedido.

### POST Add Item Order

```
http://localhost:8081/order/1
```
Este é um pedido POST para adicionar um item a um pedido específico com base no ID fornecido.

**Request Headers**

| Authorization |
| --- |
| Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJyc2Rhc2Rzc0BnbWFpbC5jb20iLCJpZCI6MSwibmFtZSI6IlJlIFNlY2NoaW0iLCJleHAiOjE2OTc3MjMyNTUsImlzcyI6ImNydWQifQ.Agz6MfCfvPqM6odXkb9Rt3InOMciiue9fOTnI3wG0LoQRph3Syat8kwy-KSaHfvotDO-w5CQ-LiWKr6_u2GXqw |

**Body** (json)

```json
{
  "product": 1, 	
  "amount": 1
}
```

### DELETE Delete OrderItem

```
http://localhost:8081/order/ordemItem/2
```
Este é um pedido DELETE para excluir um item de pedido com um ID específico.


### PUT Update Order

```
http://localhost:8081/order/ordemItem/2
```
Este é um pedido PUT e é usado para atualizar um item de pedido existente. Normalmente, você identifica o item sendo atualizado incluindo um identificador na URL.

**Request Headers**

| Authorization |
| --- |
| Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJyc2Rhc2Rzc0BnbWFpbC5jb20iLCJpZCI6MSwibmFtZSI6IlJlIFNlY2NoaW0iLCJleHAiOjE2OTc3MjMyNTUsImlzcyI6ImNydWQifQ.Agz6MfCfvPqM6odXkb9Rt3InOMciiue9fOTnI3wG0LoQRph3Syat8kwy-KSaHfvotDO-w5CQ-LiWKr6_u2GXqw |

**Body** (json)

```json
{
  "product": 1, 	
  "amount": 1
}
```
