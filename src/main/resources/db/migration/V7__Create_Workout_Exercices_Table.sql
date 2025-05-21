CREATE TABLE workout_exercices (
    workout_exercices_id SERIAL  PRIMARY KEY,
    exercice_id VARCHAR(255) NOT NULL,
    weight NUMERIC NOT NULL,
    workout_plan_id BIGINT,
    CONSTRAINT fk_workout_plan
        FOREIGN KEY (workout_plan_id)
        REFERENCES workout_plan(workout_plan_id)
        ON DELETE CASCADE
);
