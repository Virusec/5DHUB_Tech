-- liquibase formatted sql

-- changeset ashikin:V001_create-companies-table
CREATE TABLE companies
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    budget DOUBLE PRECISION
);