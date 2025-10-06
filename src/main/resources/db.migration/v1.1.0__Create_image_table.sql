CREATE TABLE IF NOT EXISTS images
(
    id          UUID PRIMARY KEY,
    image       oid NOT NULL,
    create_Date TIMESTAMP
);
