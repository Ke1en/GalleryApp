CREATE TABLE IF NOT EXISTS tags
(
    id   UUID PRIMARY KEY,
    name TEXT UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS image_tags
(
    image_id UUID NOT NULL,
    tag_id   UUID NOT NULL,
    PRIMARY KEY (image_id, tag_id),
    CONSTRAINT fk_images FOREIGN KEY (image_id) REFERENCES images(id) ON DELETE CASCADE,
    CONSTRAINT fk_tags FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE
);
