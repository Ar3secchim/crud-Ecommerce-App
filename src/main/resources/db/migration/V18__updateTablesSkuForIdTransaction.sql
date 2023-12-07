ALTER TABLE products
    RENAME COLUMN sku TO sku_id;

ALTER TABLE customers
    RENAME COLUMN sku TO id_transaction;

ALTER TABLE order_item
    RENAME COLUMN sku TO id_transaction;

ALTER TABLE orders
    RENAME COLUMN sku TO id_transaction;
