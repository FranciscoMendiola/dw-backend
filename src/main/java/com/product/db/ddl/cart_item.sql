DROP TABLE IF EXISTS cart_item;


CREATE TABLE cart_item (
    cart_item_id INT NOT NULL AUTO_INCREMENT,
    customer_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1 CHECK (quantity > 0),

    PRIMARY KEY (cart_item_id),
    CONSTRAINT fk_item_cart FOREIGN KEY (cart_id) REFERENCES cart (cart_id),
    -- Restricción única para evitar duplicados de producto por carrito
    CONSTRAINT ux_cart_product UNIQUE (cart_id, product_id) 
);