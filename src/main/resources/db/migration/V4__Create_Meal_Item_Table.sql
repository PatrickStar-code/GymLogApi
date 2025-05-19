CREATE TABLE meal_item (
    meal_item_id SERIAL  PRIMARY KEY,
    quantity INT NOT NULL,
    food_id BIGINT,
    meal_id BIGINT,
    CONSTRAINT fk_mealitem_food FOREIGN KEY (food_id) REFERENCES food(food_id),
    CONSTRAINT fk_mealitem_meal FOREIGN KEY (meal_id) REFERENCES meals(meal_id)
);
