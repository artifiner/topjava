DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES (TO_TIMESTAMP('2020-01-30 10:00', 'YYYY-MM-DD HH24:MI'), 'Завтрак', 500, 100000),
       (TO_TIMESTAMP('2020-01-30 13:00', 'YYYY-MM-DD HH24:MI'), 'Обед', 1000, 100000),
       (TO_TIMESTAMP('2020-01-30 20:00', 'YYYY-MM-DD HH24:MI'), 'Ужин', 500, 100000),
       (TO_TIMESTAMP('2020-01-31 00:00', 'YYYY-MM-DD HH24:MI'), 'Еда на граничное значение', 100, 100000),
       (TO_TIMESTAMP('2020-01-31 10:00', 'YYYY-MM-DD HH24:MI'), 'Завтрак', 1000, 100000),
       (TO_TIMESTAMP('2020-01-31 13:00', 'YYYY-MM-DD HH24:MI'), 'Обед', 500, 100000),
       (TO_TIMESTAMP('2020-01-31 20:00', 'YYYY-MM-DD HH24:MI'), 'Ужин', 410, 100000),
       (TO_TIMESTAMP('2020-02-1 20:00', 'YYYY-MM-DD HH24:MI'), 'Ужин', 700, 100000),
       (TO_TIMESTAMP('2020-01-31 18:00', 'YYYY-MM-DD HH24:MI'), 'Ужин админа', 1999, 100001);