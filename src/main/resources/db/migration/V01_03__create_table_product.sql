CREATE TABLE IF NOT EXISTS product (
    id              BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name            BIGINT       NOT NULL,
    description     VARCHAR(100)         ,
    unit            VARCHAR(100) NOT NULL,
    product_type    VARCHAR(100) NOT NULL,
    updated_at      DATETIME     NOT NULL,
    created_at      DATETIME     NOT NULL
);