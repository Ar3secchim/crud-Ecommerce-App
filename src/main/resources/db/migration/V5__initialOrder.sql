CREATE TABLE orders (
     id INT AUTO_INCREMENT PRIMARY KEY,
     customers_id VARCHAR(36),
     orderedAt TIMESTAMP,
     status VARCHAR(255),
     shippingAddress VARCHAR(255),
     FOREIGN KEY (customers_id) REFERENCES customers(id)
);
