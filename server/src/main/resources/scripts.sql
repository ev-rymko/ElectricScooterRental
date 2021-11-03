CREATE TABLE rental_point_seq (
    next_val BIGINT DEFAULT 0
);

CREATE TABLE rental_points (
    id BIGINT NOT NULL PRIMARY KEY,
    country VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    address VARCHAR(200) NOT NULL
);

CREATE TABLE scooter_price_seq (
    next_val BIGINT DEFAULT 0
);

CREATE TABLE scooter_prices(
    id BIGINT NOT NULL PRIMARY KEY,
    scooter_type VARCHAR(100) NOT NULL,
    price_per_hour DOUBLE NOT NULL,
    subscription_price_per_day DOUBLE NOT NULL
);

CREATE TABLE scooter_seq (
    next_val BIGINT DEFAULT 0
);

CREATE TABLE scooters(
    id BIGINT NOT NULL PRIMARY KEY,
    scooter_price_id BIGINT NOT NULL,
    model VARCHAR(200) NOT NULL,
    details VARCHAR(500) NOT NULL,
    scooter_condition INT NOT NULL DEFAULT 5,
    rental_point_id BIGINT NOT NULL,
    FOREIGN KEY (rental_point_id) REFERENCES rental_points (id),
    FOREIGN KEY (scooter_price_id) REFERENCES scooter_prices (id)
);

CREATE TABLE user_seq (
    next_val BIGINT DEFAULT 0
);

CREATE TABLE users (
    id BIGINT NOT NULL PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    second_name VARCHAR(250) NOT NULL,
    email VARCHAR(250) NOT NULL
);

CREATE TABLE account_seq (
    next_val BIGINT DEFAULT 0
);

CREATE TABLE accounts (
    id BIGINT NOT NULL PRIMARY KEY,
    login VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE role_seq (
    next_val BIGINT DEFAULT 0
);

CREATE TABLE roles (
    id BIGINT NOT NULL PRIMARY KEY,
    role_value VARCHAR(250) NOT  NULL
);

CREATE TABLE account_roles (
    account_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (account_id) REFERENCES accounts (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE rent_seq (
    next_val BIGINT DEFAULT 0
);

CREATE TABLE rental_history (
    id BIGINT NOT NULL PRIMARY KEY,
    rent_date DATETIME NOT NULL,
    account_id BIGINT NOT NULL,
    rental_point_id BIGINT NOT NULL,
    scooter_id BIGINT NOT NULL,
    subscription VARCHAR(50),
    hours INT,
    final_price DOUBLE NOT NULL,
    mileage DOUBLE NOT NULL DEFAULT 0,
    FOREIGN KEY (account_id) REFERENCES accounts (id),
    FOREIGN KEY (rental_point_id) REFERENCES rental_points (id),
    FOREIGN KEY (scooter_id) REFERENCES scooters (id)
);


INSERT INTO rental_point_seq (next_val) VALUES (1);
INSERT INTO scooter_seq (next_val) VALUES (1);
INSERT INTO scooter_price_seq (next_val) VALUES (1);
INSERT INTO user_seq (next_val) VALUES (1);
INSERT INTO account_seq (next_val) VALUES (1);
INSERT INTO role_seq (next_val) VALUES (3);
INSERT INTO rent_seq (next_val) VALUES (1);

INSERT INTO roles VALUES (1, 'USER');
INSERT INTO roles VALUES (2, 'ADMIN');





