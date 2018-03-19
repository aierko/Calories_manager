DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@mail.ua', 'password' ),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);
INSERT INTO meals (date_time, description, calories,user_id) VALUES
  ('2017-10-29 10:00:00', 'Завтрак', 500, 100000),
  ('2017-10-30 10:00:00', 'Обед', 500, 100000),
  ('2017-10-24 10:00:00', 'Ужин', 500, 100000),
  ('2017-10-25 10:00:00', 'Завтрак', 500, 100000),
  ('2017-10-21 10:00:00', 'Обед', 500, 100001),
  ('2017-10-22 10:00:00', 'Завтрак', 500, 100001)
