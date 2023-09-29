DROP TABLE products;

CREATE TABLE products (
     id INT NOT NULL AUTO_INCREMENT,
     name VARCHAR(100) NOT NULL,
     description VARCHAR(300) NOT NULL,
     price DECIMAL(19, 2) NOT NULL,

     PRIMARY KEY (id)
);
