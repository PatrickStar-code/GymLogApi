ALTER TABLE progress_log DROP CONSTRAINT IF EXISTS fk_progresslog_user;

ALTER TABLE progress_log
ADD CONSTRAINT fk_progresslog_user
FOREIGN KEY (user_id) REFERENCES users(user_id)
ON DELETE CASCADE;
