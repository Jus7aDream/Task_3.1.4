ALTER TABLE `users`
    MODIFY `first_name` varchar(255) AFTER `id`;

ALTER TABLE `users`
    MODIFY `last_name` varchar(255) AFTER `first_name`;

ALTER TABLE `users`
    MODIFY `age` BIGINT AFTER `last_name`;

ALTER TABLE `users`
    MODIFY `email` varchar(255) AFTER `age`;

ALTER TABLE `users`
    MODIFY `password` varchar(255) AFTER `email`;


INSERT INTO schema_hiber.users (id, first_name, last_name, age, email, password)
VALUES (1, 'firstAdmin', 'lastAdmin',33, 'admin@gmail.com', '$2a$12$5yrf8l3IuWuZQlooGVVvT.oeUUkMmXRowU4LDrEdOkGaGXa.igxdG');

INSERT INTO schema_hiber.users (id, first_name, last_name, age, email, password)
VALUES (2, 'firstUser', 'lastUser', 33, 'user@gmail.com', '$2a$12$U0QkgLP.T1DgMoaATnDPy.2nDD5Di4rbzhH5yX2GYgtvbi.sdEqy.');


INSERT INTO schema_hiber.roles (id, name)
VALUES (1, 'ROLE_ADMIN');

INSERT INTO schema_hiber.roles (id, name)
VALUES (2, 'ROLE_USER');


INSERT INTO schema_hiber.users_roles (user_id, role_id)
VALUES (1, 1);

INSERT INTO schema_hiber.users_roles (user_id, role_id)
VALUES (1, 2);

INSERT INTO schema_hiber.users_roles (user_id, role_id)
VALUES (2, 2);

