CREATE TABLE workout_sets (
    workout_sets_id SERIAL  PRIMARY KEY,
    sets INT NOT NULL,
    reps INT NOT NULL,
    weight NUMERIC NOT NULL,
    workout_plan_id BIGINT,
    CONSTRAINT fk_workout_plan
        FOREIGN KEY (workout_plan_id)
        REFERENCES workout_plan(workout_plan_id)
        ON DELETE CASCADE
)