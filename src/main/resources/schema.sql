-- Account table (unchanged)
CREATE TABLE IF NOT EXISTS account (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Role table (unchanged)
CREATE TABLE IF NOT EXISTS role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

-- Account_roles table (unchanged)
CREATE TABLE IF NOT EXISTS account_roles (
    account_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (account_id, role_id),
    FOREIGN KEY (account_id) REFERENCES account(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);

-- Wallet table (unchanged)
CREATE TABLE IF NOT EXISTS wallet (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT UNIQUE,
    rubies DECIMAL(10,2) DEFAULT 0,
    emeralds DECIMAL(10,2) DEFAULT 0,
    sapphires DECIMAL(10,2) DEFAULT 0,
    diamonds DECIMAL(10,2) DEFAULT 0,
    FOREIGN KEY (account_id) REFERENCES account(id)
);

-- Inventory table (unchanged)
CREATE TABLE IF NOT EXISTS inventory (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT UNIQUE,
    FOREIGN KEY (account_id) REFERENCES account(id)
);

CREATE TABLE IF NOT EXISTS item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    inventory_id BIGINT,
    title VARCHAR(255) NOT NULL,
    explanation TEXT,
    url VARCHAR(255),
    price INTEGER,
    FOREIGN KEY (inventory_id) REFERENCES inventory(id)
);

-- Updated Apod table
CREATE TABLE IF NOT EXISTS apod (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    copyright VARCHAR(255),
    date DATE,
    explanation TEXT,
    hdurl VARCHAR(255),
    media_type VARCHAR(50),
    service_version VARCHAR(10),
    title VARCHAR(255),
    url VARCHAR(255)
);