CREATE TABLE workout_plan (
    workout_plan_id SERIAL  PRIMARY KEY,
    name VARCHAR(255),
    image_url VARCHAR(255),
    user_id BIGINT,
    CONSTRAINT fk_workout_plan_user
        FOREIGN KEY (user_id)
        REFERENCES users(user_id)
        ON DELETE SET NULL

);
