# Crud Ecommerce App

Este é um projeto de exemplo de um aplicativo de comércio eletrônico que demonstra operações CRUD (Create, Read, Update, Delete) em relação a produtos, pedidos e clientes. O aplicativo oferece funcionalidades básicas de gerenciamento de um comércio eletrônico, permitindo adicionar, visualizar, atualizar e excluir produtos, realizar pedidos e gerenciar informações de clientes.

## Funcionalidades Principais

- 🔒 Cadastro, leitura, atualização e exclusão de produtos.
- 🚀 Realização de pedidos.
- 📦 Gerenciamento de clientes.

#### 1. Create (Criar)
   O CRUD começa com a operação de criação, que envolve adicionar novos registros ou objetos a uma fonte de dados, como um banco de dados. No contexto de um sistema de comércio eletrônico, isso pode significar adicionar novos produtos ao catálogo.

Exemplo de Criação (Create) - Adicionar um Produto:
``` java
// Criando um novo produto
Product newProduct = new Product();
newProduct.setName("Camiseta");
newProduct.setPrice(BigDecimal.valueOf(19.99));
newProduct.setDescription("Camiseta de algodão");

// Salvando o produto no banco de dados
productRepository.save(newProduct);
```

#### 2. Read (Ler)
   A operação de leitura envolve a recuperação de registros ou objetos da fonte de dados. Isso é usado para visualizar ou listar informações existentes.

Exemplo de Leitura (Read) - Listar Produtos:

``` java
// Recuperando todos os produtos do banco de dados
List<Product> products = productRepository.findAll();

// Exibindo os produtos
for (Product product : products) {
    System.out.println("Nome: " + product.getName());
    System.out.println("Preço: " + product.getPrice());
    System.out.println("Descrição: " + product.getDescription());
}
```

#### 3. Update (Atualizar)
A operação de atualização envolve modificar registros ou objetos existentes na fonte de dados. Isso permite fazer alterações nos dados.

Exemplo de Atualização (Update) - Alterar o Preço de um Produto:

``` java
// Recuperando um produto específico por ID
Product productToUpdate = productRepository.findById(1L);

// Alterando o preço do produto
productToUpdate.setPrice(BigDecimal.valueOf(24.99));

// Salvando as alterações no banco de dados
productRepository.save(productToUpdate);

```

#### 4. Delete (Excluir)
A operação de exclusão envolve a remoção de registros ou objetos da fonte de dados.

Exemplo de Exclusão (Delete) - Remover um Produto:

``` java
// Recuperando um produto específico por ID
Product productToDelete = productRepository.findById(2L);

if (productToDelete != null) {
    // Removendo o produto do banco de dados
    productRepository.delete(productToDelete);
} else {
    System.out.println("Produto não encontrado.");
}
```

## Tecnologias Utilizadas
- 💻 Linguagem de Programação: Java

## Funcionalidades em produção
- 💻 Adicionar Framework de Desenvolvimento: Spring Boot
- 📦 Banco de Dados: Banco de Dados Relacional (por exemplo, MySQL)
- 🌈 Frontend: interface a ser desenvolvida com React


## Maiores Desafios

- Sair de linguagens que não são tipada e começar ligar com JAVA que é fortemente tipado
- POO (programação orientada objeto), não ultilizava esse paradgnima para programar.
