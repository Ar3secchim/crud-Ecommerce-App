ALTER TABLE order_item
DROP FOREIGN KEY order_item_ibfk_2,
ADD FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE;
