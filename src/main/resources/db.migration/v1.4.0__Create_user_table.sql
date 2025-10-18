CREATE TABLE IF NOT EXISTS users
(
    id       UUID PRIMARY KEY,
    username TEXT UNIQUE NOT NULL,
    password TEXT        NOT NULL,
    email    TEXT,
    role     TEXT        NOT NULL DEFAULT 'USER'
);
