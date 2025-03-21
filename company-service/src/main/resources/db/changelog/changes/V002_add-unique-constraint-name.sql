-- liquibase formatted sql

-- changeset ashikin:V002_add-unique-constraint-name
ALTER TABLE companies
ADD CONSTRAINT unique_company_name UNIQUE(name);