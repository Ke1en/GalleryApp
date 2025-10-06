CREATE TABLE IF NOT EXISTS images
(
    id          UUID PRIMARY KEY,
    name        TEXT  NOT NULL,
    description TEXT,
    image       oid NOT NULL,
    create_Date TIMESTAMP
);
