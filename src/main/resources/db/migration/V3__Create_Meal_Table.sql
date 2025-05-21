CREATE TABLE meals (
    meal_id SERIAL  PRIMARY KEY,
    date_time TIMESTAMP NOT NULL,
    meal_type VARCHAR(50) NOT NULL,
    calories NUMERIC NOT NULL,
    proteins NUMERIC NOT NULL,
    carbs  NUMERIC NULL,
    fats  NUMERIC NULL,
    user_id BIGINT,
    CONSTRAINT fk_meals_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);
