CREATE TABLE IF NOT EXISTS thumbnails
(
    id          UUID PRIMARY KEY,
    thumbnail_url   TEXT,
    create_Date TIMESTAMP,
    image_id UUID REFERENCES images(id)
);
