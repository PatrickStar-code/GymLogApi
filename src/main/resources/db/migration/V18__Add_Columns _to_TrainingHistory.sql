ALTER TABLE training_history
    ADD COLUMN status VARCHAR(255);

ALTER TABLE training_history
    ADD COLUMN comment VARCHAR(255);