ALTER TABLE users
ADD COLUMN verified BOOLEAN  NOT NULL DEFAULT false,
ADD COLUMN verification_token VARCHAR(255),
ADD COLUMN verification_token_expiration_date TIMESTAMP;