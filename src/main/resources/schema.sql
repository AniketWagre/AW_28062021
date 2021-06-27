CREATE TABLE credit_card (
    id   NUMBER(12,0)      primary key auto_increment,
    bank VARCHAR(32)       NOT NULL,
    card_number NUMBER(16,0)   NOT NULL UNIQUE,
    expiry_date DATE       NOT NULL,
    PRIMARY KEY (id)
);