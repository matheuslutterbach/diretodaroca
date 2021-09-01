CREATE TABLE IF NOT EXISTS customer (
    id              BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(256) NOT NULL,
    cpf             VARCHAR(256) NOT NULL,
    email           VARCHAR(256) NOT NULL,
    phone           VARCHAR(20)  NOT NULL,
    created_at      DATETIME     NOT NULL,
    updated_at      DATETIME     NOT NULL,

    UNIQUE(cpf)
);