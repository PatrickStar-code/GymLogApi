CREATE TABLE food (
    food_id SERIAL  PRIMARY KEY,
    food_name VARCHAR(255) NOT NULL,
    calories NUMERIC NOT NULL,
    proteins NUMERIC NOT NULL,
    carbs NUMERIC NOT NULL,
    fibers NUMERIC NOT NULL,
    fats NUMERIC NOT NULL
);
