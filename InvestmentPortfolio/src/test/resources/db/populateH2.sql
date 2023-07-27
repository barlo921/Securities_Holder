DROP TABLE IF EXISTS portfolio cascade;
DROP TABLE IF EXISTS security cascade;
DROP TABLE IF EXISTS deal cascade;
DROP SEQUENCE IF EXISTS securities_id_sequence;

CREATE SEQUENCE securities_id_sequence START WITH 1;

CREATE TABLE portfolio
(
    id              BIGINT DEFAULT securities_id_sequence.nextval PRIMARY KEY,
    user_id         BIGINT                      NOT NULL
);

CREATE TABLE security
(
    id              BIGINT DEFAULT securities_id_sequence.nextval PRIMARY KEY,
    name            VARCHAR(255)                NOT NULL,
    average_price   INTEGER                     NOT NULL,
    quantity        INTEGER                     NOT NULL,
    type            VARCHAR(255)                NOT NULL,
    portfolio_id    BIGINT                      NOT NULL,
    FOREIGN KEY (portfolio_id) REFERENCES portfolio (id) ON DELETE CASCADE
);

CREATE TABLE deal
(
    id              BIGINT DEFAULT securities_id_sequence.nextval PRIMARY KEY,
    date_time       TIMESTAMP                   NOT NULL,
    price           INTEGER                     NOT NULL,
    quantity        INTEGER                     NOT NULL,
    security_id     BIGINT                      NOT NULL,
    FOREIGN KEY (security_id) REFERENCES security (id) ON DELETE CASCADE
);

INSERT INTO portfolio (user_id) VALUES (1);
INSERT INTO security (name, average_price, quantity, type, portfolio_id) VALUES ('YNDX', 2400, 1, 'SHARE', 1);
INSERT INTO deal (date_time, price, quantity, security_id) VALUES ('2023-01-01 00:01:01', 2400, 1, 2);