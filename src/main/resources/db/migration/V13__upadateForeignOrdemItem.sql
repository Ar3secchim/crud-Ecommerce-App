ALTER TABLE `order_item`
    DROP FOREIGN KEY order_item_ibfk_1;

ALTER TABLE order_item
    ADD CONSTRAINT order_item_ibfk_1
        FOREIGN KEY (order_id)
            REFERENCES orders (id)
            ON DELETE CASCADE; -- Isso define a ação de cascata na exclusão
