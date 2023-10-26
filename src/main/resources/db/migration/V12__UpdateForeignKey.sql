ALTER TABLE `orders`
    DROP FOREIGN KEY orders_ibfk_1; -- Primeiro, remova a chave estrangeira existente

ALTER TABLE `orders`
    ADD CONSTRAINT orders_ibfk_1
        FOREIGN KEY (`customer_id`)
            REFERENCES `customers` (`id`)
            ON DELETE CASCADE;
