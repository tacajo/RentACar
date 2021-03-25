--DELETE FROM user_roles;
--DELETE FROM user;
--DELETE FROM role;


INSERT INTO user (dtype, email, enabled, first_name, last_name, last_password_reset_date, password, username, num_created_ads,num_canceled_ads, disable_reservation, obligation, enabled_account) VALUES ('simpleuser', 'tacajovicic@gmail.com', true, 'Tamara', 'Jovicic', '2017-10-01', '$2y$12$uwJwfIj/KcknIy/SKwYTOeiZ/BvHFnO3strZeiP2jYJYkpektrGgS', 'taca',0,0, false, 0, true);
INSERT INTO user (dtype, email, enabled, first_name, last_name, last_password_reset_date, password, username,num_created_ads,num_canceled_ads, disable_reservation, obligation, enabled_account) VALUES ('agent', 'urb.saska@gmail.com', true, 'Aleksandra', 'Urban', '2018-03-11', '$2y$12$25FMVTOX8/Bevf0mM/2g4etuTXO/8jTYvwbiineL7mkpuWVvUKT2C', 'urb',0,0, false, 0, true);
INSERT INTO user (dtype, email, enabled, first_name, last_name, last_password_reset_date, password, username,num_created_ads,num_canceled_ads, disable_reservation, obligation, enabled_account) VALUES ('admin', 'sipovac.zeljana@gmail.com', true, 'Zeljana', 'Sipovac', '2020-03-01', '$2y$12$b5uZW60afCjvlmLZHCpdk.eFI/aOYwSoe3p696PAR2ZSIJ/lUCS6.', 'zex',0,0, false, 0, true);
INSERT INTO user (dtype, email, enabled, first_name, last_name, last_password_reset_date, password, username, num_created_ads,num_canceled_ads, disable_reservation, obligation, enabled_account) VALUES ('simpleuser', 'jovichicdajana@gmail.com', true, 'Dajana', 'Jovicic', '2020-10-01', '$2y$12$YEV4cglJBmM65.YDgRL.ieMhtUeGyBWcLpwNyc0NH2jDbp7LnSOi6', 'daca',0,0, false, 0, true);
INSERT INTO user (dtype, email, enabled, first_name, last_name, last_password_reset_date, password, username, num_created_ads,num_canceled_ads, disable_reservation, obligation, enabled_account) VALUES ('simpleuser', 'isapsw52@gmail.com', true, 'Pera', 'Peric', '2020-03-03', '$2y$12$fSkZ/P.Z5uXIdFg086lHROfl6Nw96muJY17gqvqR2N3.3XjUSsSeq', 'pera',0,0, false, 0, true);

INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_AGENT');

INSERT INTO user_roles (user_id, role_id) VALUES ( 1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES ( 2, 3);
INSERT INTO user_roles (user_id, role_id) VALUES ( 3, 2);
INSERT INTO user_roles (user_id, role_id) VALUES ( 4, 1);
INSERT INTO user_roles (user_id, role_id) VALUES ( 5, 1);