DROP TABLE IF EXISTS customer_image;

CREATE TABLE customer_image(
    customer_image_id INT NOT NULL AUTO_INCREMENT,
    customer_id INT NOT NULL,
    image VARCHAR(255) NOT NULL,
    status TINYINT NOT NULL,
    
    PRIMARY KEY (customer_image_id),
    CONSTRAINT fk_customer_image_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);