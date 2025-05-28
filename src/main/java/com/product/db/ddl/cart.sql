DROP TABLE IF EXISTS cart;

CREATE TABLE cart (
    cart_id INT NOT NULL AUTO_INCREMENT,
    customer_id INT NOT NULL,
    status TINYINT NOT NULL,

    PRIMARY KEY (cart_id)
);

CREATE UNIQUE INDEX ux_cart_customer ON cart (customer_id);
