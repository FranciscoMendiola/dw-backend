DROP TABLE IF EXISTS region;

CREATE TABLE region(
    region_id INT NOT NULL AUTO_INCREMENT,
    region VARCHAR(100) NOT NULL,
    tag VARCHAR(100) NOT NULL,
    status TINYINT NOT NULL,
    
    PRIMARY KEY (region_id)
);

CREATE UNIQUE INDEX ux_region ON region(region);
CREATE UNIQUE INDEX ux_tag ON region(tag);

