CREATE TABLE workout_sets (
    workout_sets_id SERIAL PRIMARY KEY,
    sets INT NOT NULL,
    reps INT NOT NULL,
    weight NUMERIC NOT NULL,
    workout_exercices_id BIGINT,
    CONSTRAINT fk_workout_exercices_id
        FOREIGN KEY (workout_exercices_id)
        REFERENCES workout_exercices(workout_exercices_id)
        ON DELETE CASCADE
);
