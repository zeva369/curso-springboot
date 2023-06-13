DROP TABLE IF EXISTS tbl_regions;

CREATE TABLE tbl_regions (
                                id BIGINT AUTO_INCREMENT  PRIMARY KEY,
                                name VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS tbl_customers;

CREATE TABLE tbl_customers (
                                id BIGINT AUTO_INCREMENT  PRIMARY KEY,
                                number_id BIGINT,
                                first_name VARCHAR(250) NOT NULL,
                                last_name VARCHAR(250) NOT NULL,
                                email VARCHAR(250) NOT NULL,
                                photo_url VARCHAR(250),
                                region_id BIGINT NOT NULL,
                                status VARCHAR(100) NOT NULL

);