CREATE TABLE progress_log (
    progress_log_id SERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    weight NUMERIC NOT NULL,
    body_fat NUMERIC NOT NULL,
    hip NUMERIC NOT NULL,
    chest NUMERIC NOT NULL,
    arms_Left NUMERIC NOT NULL,
    arms_right NUMERIC NOT NULL,
    thigh_left NUMERIC NOT NULL,
    thigh_right NUMERIC NOT NULL,
    left_calf NUMERIC NOT NULL,
    right_calf NUMERIC NOT NULL,
    waist VARCHAR(255) NOT NULL,
    comment VARCHAR(255) NOT NULL,
    responsible VARCHAR(255) NOT NULL,
    user_id BIGINT,
    CONSTRAINT fk_progresslog_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);
