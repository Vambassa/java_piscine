SET TIME ZONE 'Europe/Moscow';

INSERT INTO users (login, password)
VALUES ('Linda', '111'),
       ('Bill', '222'),
       ('Jim', 'aaa'),
       ('John', 'ggg'),
       ('Lizzy', 'p1l2k3');

INSERT INTO chatrooms (name, owner_id)
VALUES ('Random', 1),
       ('ADM', 2),
       ('Help', 3),
       ('Report', 4),
       ('Ping Pong', 1);

INSERT INTO messages (author_id, room_id, text)
VALUES (1, 1, 'MEME'),
       (1, 2, 'TIJ'),
       (2, 1, 'Hi there'),
       (4, 4, 'No space left on device'),
       (2, 1, 'Lets play'),
       (1, 1, 'MEME'),
       (1, 2, 'TIJ'),
       (2, 1, 'Hi there'),
       (4, 4, 'No space left on device'),
       (2, 1, 'Lets play');