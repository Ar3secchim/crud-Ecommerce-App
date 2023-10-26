CREATE TABLE customers (
   id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(50) NOT NULL,
   email VARCHAR(100) NOT NULL,
   address VARCHAR(200) NOT NULL,
   UNIQUE (email),
   PRIMARY KEY (id)
);

CREATE TABLE orders (
    id INT NOT NULL AUTO_INCREMENT,
    customer_id int NOT NULL,
    status VARCHAR(100) NOT NULL,
    total DECIMAL(19, 2) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customers (id)
);

CREATE TABLE order_item (
    id INT NOT NULL AUTO_INCREMENT,
    order_id int NOT NULL,
    product_id int not null,
    amount int NOT NULL,
    price DECIMAL(19, 2) NOT NULL,
    total DECIMAL(19, 2) NOT NULL,
    PRIMARY KEY (id),

    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);

CREATE TABLE products (
  id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  description VARCHAR(4000) NULL,
  barcode VARCHAR(255) UNIQUE NOT NULL,
  price DECIMAL NOT NULL
)
