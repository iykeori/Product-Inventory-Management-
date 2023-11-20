
CREATE DATABASE inventory;
#DROP DATABASE inventory;
Use inventory;

CREATE TABLE product (
    id              VARCHAR(55) PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    category        VARCHAR(100) NOT NUll,
    sellingPrice    DECIMAL(18, 2) NOT NULL,
    costPrice       DECIMAL(18, 2) NOT NULL,
    stockCount      BIGINT NOT NULL,
    manufacturer     VARCHAR(100) NOT NULL,
    created			DATETIME NOT NULL,
    updated			 DATETIME DEFAULT NULL
);

CREATE TABLE sales (
    id              VARCHAR(55) PRIMARY KEY,
    productId       VARCHAR(55) NOT NULL,
    quantity        INT NOT NULL,
    totalPrice      DECIMAL(18, 2) NOT NULL,
    created			DATETIME NOT NULL,
    updated			DATETIME DEFAULT NULL,

    FOREIGN KEY (productId) REFERENCES product (id) ON UPDATE CASCADE
);

CREATE TABLE returnsales (
    id              VARCHAR(55) PRIMARY KEY,
    productId       VARCHAR(55) NOT NULL,
    salesId         VARCHAR(55) NOT NULL,
    quantity        INT NOT NULL,
    totalPrice      DECIMAL(18, 2) NOT NULL,
    created			DATETIME NOT NULL,
    updated			 DATETIME DEFAULT NULL,

    FOREIGN KEY (productId) REFERENCES product (id) ON UPDATE CASCADE,
    FOREIGN KEY (salesId ) REFERENCES sales (id) ON UPDATE CASCADE
);

# Create TRIGGERS
CREATE TRIGGER productCreated
BEFORE INSERT ON product
FOR EACH ROW
    BEGIN
        SET NEW.created = NOW();
    END;

CREATE TRIGGER productUpdated
BEFORE UPDATE ON product
FOR EACH ROW
    BEGIN
        SET NEW.updated = NOW();
    END;

CREATE TRIGGER salesCreated
BEFORE INSERT ON sales
FOR EACH ROW
    BEGIN
        SET NEW.created = NOW();
    END;

CREATE TRIGGER salesUpdated
BEFORE UPDATE ON sales
FOR EACH ROW
    BEGIN
        SET NEW.updated = NOW();
    END;

CREATE TRIGGER returnsalesCreated
BEFORE INSERT ON returnsales
FOR EACH ROW
    BEGIN
        SET NEW.created = NOW();
    END;

CREATE TRIGGER returnsalesUpdated
BEFORE UPDATE ON returnsales
FOR EACH ROW
    BEGIN
        SET NEW.updated = NOW();
    END;