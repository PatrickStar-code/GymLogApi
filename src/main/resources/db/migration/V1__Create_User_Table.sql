CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    gender VARCHAR(50) NOT NULL,
    height NUMERIC NOT NULL,
    weight NUMERIC NOT NULL,
    age INT NOT NULL,
    goal VARCHAR(50) NOT NULL,
    goal_weight NUMERIC NOT NULL,
    birth_date TIMESTAMP NOT NULL,
    activy_level VARCHAR(50) NOT NULL,
    avatar_url VARCHAR(255) NOT NULL,
    is_active BOOLEAN NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP NOT NULL
);
