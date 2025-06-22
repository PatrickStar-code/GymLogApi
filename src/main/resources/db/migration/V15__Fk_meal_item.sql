ALTER TABLE meal_item DROP CONSTRAINT IF EXISTS fk_mealitem_meal;
ALTER TABLE meal_item DROP CONSTRAINT IF EXISTS fk_mealitem_food;

ALTER TABLE meal_item
ADD CONSTRAINT fk_mealitem_meal
FOREIGN KEY (meal_id) REFERENCES meals(meal_id)
ON DELETE CASCADE;

ALTER TABLE meal_item
ADD CONSTRAINT fk_mealitem_food
FOREIGN KEY (food_id) REFERENCES food(food_id)
ON DELETE CASCADE;
