-- liquibase formatted sql

-- changeset ashikin:V002_add-company-id-to-users
ALTER TABLE users
ADD COLUMN company_id BIGINT;