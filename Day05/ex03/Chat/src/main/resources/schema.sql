CREATE SCHEMA IF NOT EXISTS chat;

DROP TABLE IF EXISTS users, chatrooms, messages;

CREATE TABLE IF NOT EXISTS users (
     id BIGSERIAL PRIMARY KEY,
     login VARCHAR(30),
     password VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS chatrooms (
     id BIGSERIAL PRIMARY KEY,
     name VARCHAR(30),
     owner_id BIGINT REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS messages (
    id BIGSERIAL PRIMARY KEY,
    author_id BIGINT REFERENCES users(id),
    room_id BIGINT REFERENCES chatrooms(id),
    text TEXT,
    time TIMESTAMP DEFAULT LOCALTIMESTAMP(0)
);