CREATE TABLE users (
    id UUID PRIMARY KEY,
    version BIGINT NOT NULL DEFAULT 0,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    posts_created BIGINT NOT NULL DEFAULT 0,
    topics_created BIGINT NOT NULL DEFAULT 0,
    registered_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE UNIQUE INDEX users_username_lower_idx ON users (lower(username));
CREATE UNIQUE INDEX users_email_lower_idx ON users (lower(email));

ALTER TABLE topics ADD COLUMN created_by UUID NOT NULL,
ADD CONSTRAINT fk_topics_user FOREIGN KEY (created_by) REFERENCES users(id);