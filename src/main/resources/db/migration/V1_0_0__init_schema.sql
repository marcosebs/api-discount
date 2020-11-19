CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    orders_count INT
);

CREATE TABLE category (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    min_orders INT,
    max_orders INT,
    discount INT,
    description VARCHAR(255)
);