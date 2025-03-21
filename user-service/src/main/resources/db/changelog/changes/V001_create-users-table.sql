-- liquibase formatted sql

-- changeset ashikin:V001_create-users-table
CREATE TABLE users
(
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    phone_number VARCHAR(255)
);