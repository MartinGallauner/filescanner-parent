create schema if not exists scanner;

CREATE TABLE node
(
    id        SERIAL       NOT NULL,
    parent_id BIGINT       NULL,
    name      VARCHAR(100) NULL,
    path      VARCHAR(300) NULL,
    size      BIGINT       NULL,
    mod_date  TIMESTAMP    NULL,
    scan_date TIMESTAMP    NOT NULL,
    type      VARCHAR(100) NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_parent_node FOREIGN KEY (parent_id) REFERENCES node (id)
);
