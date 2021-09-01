CREATE TABLE IF NOT EXISTS seller (
    id              BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_address      BIGINT       NOT NULL,
    name            VARCHAR(256) NOT NULL,
    cpf             VARCHAR(11)  NOT NULL,
    birthdate       DATE                 ,
    updated_at      DATETIME     NOT NULL,
    created_at      DATETIME     NOT NULL,
    UNIQUE(cpf),

    FOREIGN KEY (id_address) REFERENCES address(id)
);