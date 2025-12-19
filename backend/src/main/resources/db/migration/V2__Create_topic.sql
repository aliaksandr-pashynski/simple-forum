CREATE TABLE topics (
    id UUID PRIMARY KEY,
    version BIGINT NOT NULL DEFAULT 0,
    name VARCHAR(255) NOT NULL UNIQUE,
    posts_count BIGINT NOT NULL DEFAULT 0,
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    category_id UUID NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);