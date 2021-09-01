CREATE TABLE IF NOT EXISTS seller_product (
    id              BIGINT   NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_seller       BIGINT   NOT NULL,
    id_product      BIGINT   NOT NULL,
    price           DECIMAL  NOT NULL,
    updated_at      DATETIME NOT NULL,
    created_at      DATETIME NOT NULL,

    FOREIGN KEY (id_seller)  REFERENCES seller(id),
    FOREIGN KEY (id_product) REFERENCES product(id)
);