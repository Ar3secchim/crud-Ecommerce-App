CREATE TABLE OrderItem (
   id INT AUTO_INCREMENT PRIMARY KEY,
   products_id INT,
   saleValue DECIMAL(10, 2),
   amount INT,
   FOREIGN KEY (products_id) REFERENCES products(id)
);
