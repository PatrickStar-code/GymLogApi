CREATE TABLE training_history
(
    id serial NOT NULL,
    workout_plan_id integer NOT NULL,
    user_id integer NOT NULL,
    date timestamp without time zone NOT NULL,
    CONSTRAINT workout_plan_id PRIMARY KEY (id),
    CONSTRAINT training_history_workout_plan_id_fkey FOREIGN KEY (workout_plan_id)
        REFERENCES workout_plan (workout_plan_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT training_history_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES "users" (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);