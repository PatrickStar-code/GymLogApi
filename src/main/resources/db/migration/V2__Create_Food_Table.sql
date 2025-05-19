CREATE TABLE food (
    food_id SERIAL  PRIMARY KEY,
    food_name VARCHAR(255) NOT NULL,
    calories INT NOT NULL,
    proteins INT NOT NULL,
    carbs INT NOT NULL,
    fibers INT NOT NULL,
    fats INT NOT NULL
);
