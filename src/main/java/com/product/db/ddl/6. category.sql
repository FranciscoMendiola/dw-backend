DROP TABLE IF EXISTS category;

CREATE TABLE category(
    category_id INT NOT NULL AUTO_INCREMENT,
    category VARCHAR(100) NOT NULL,
    tag VARCHAR(100) NOT NULL,
    status TINYINT NOT NULL,
    
    PRIMARY KEY (category_id)
);

CREATE UNIQUE INDEX ux_category_category ON region(region);
CREATE UNIQUE INDEX ux_category_tag ON region(tag);
