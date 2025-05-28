DROP TABLE IF EXISTS customer;

CREATE TABLE customer(
    customer_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    rfc VARCHAR(13) NOT NULL,
    mail VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    address VARCHAR(255),
    user_id INT NOT NULL,
    region_id INT NOT NULL,
    status TINYINT NOT NULL,
    
    PRIMARY KEY (customer_id),
    CONSTRAINT fk_customer_region FOREIGN KEY (region_id) REFERENCES region(region_id)
);

CREATE UNIQUE INDEX ux_customer_rfc ON customer(rfc);
CREATE UNIQUE INDEX ux_customer_mail ON customer(mail);
