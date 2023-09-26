DROP TABLE crud.orders;

CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customers_id VARCHAR(36),
    `orderedAt` TIMESTAMP,
    `status` VARCHAR(255),
    `shippingAddress` VARCHAR(255),
    FOREIGN KEY (customers_id) REFERENCES customers(id)
);

DROP TABLE crud.OrderItem;

CREATE TABLE orderItem (
   id INT AUTO_INCREMENT PRIMARY KEY,
   products_id INT,
    `saleValue` DECIMAL(10, 2),
    amount INT,
    FOREIGN KEY (products_id) REFERENCES products(id)
 );

DELETE FROM customers;
