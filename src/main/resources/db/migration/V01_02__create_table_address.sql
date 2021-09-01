CREATE TABLE IF NOT EXISTS address (
    id              BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_customer     BIGINT       NOT NULL,
    street          VARCHAR(256) NOT NULL,
    number          VARCHAR(3)   NOT NULL,
    neighborhood    VARCHAR(256) NOT NULL,
    zip_code        VARCHAR(8)   NOT NULL,
    city            VARCHAR(256) NOT NULL,
    state           VARCHAR(256) NOT NULL,
    updated_at      DATETIME     NOT NULL,
    created_at      DATETIME     NOT NULL,

    FOREIGN KEY (id_customer) REFERENCES customer(id)
);