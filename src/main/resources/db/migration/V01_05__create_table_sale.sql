CREATE TABLE IF NOT EXISTS sale (
    id              BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_customer     BIGINT       NOT NULL,
    id_seller       BIGINT       NOT NULL,
    id_address      BIGINT       NOT NULL,
    delivery_date   DATE         NOT NULL,
    payment_method  VARCHAR(100) NOT NULL,
    updated_at      DATETIME     NOT NULL,
    created_at      DATETIME     NOT NULL,

    FOREIGN KEY (id_customer) REFERENCES customer(id),
    FOREIGN KEY (id_seller)   REFERENCES seller(id),
    FOREIGN KEY (id_address)  REFERENCES address(id)
);