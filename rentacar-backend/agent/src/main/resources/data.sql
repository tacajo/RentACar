SET FOREIGN_KEY_CHECKS=0;
INSERT INTO user (email, enabled, enabled_account, first_name, last_name, last_password_reset_date, password, username, disable_reservation) VALUES ('tacajovicic@gmail.com', true, true, 'Tamara', 'Jovicic', '2017-10-01', '$2y$12$uwJwfIj/KcknIy/SKwYTOeiZ/BvHFnO3strZeiP2jYJYkpektrGgS', 'taca', false);
INSERT INTO user (email, enabled, enabled_account, first_name, last_name, last_password_reset_date, password, username, disable_reservation) VALUES ('urb.saska@gmail.com', true, true,'Aleksandra', 'Urban', '2018-03-11', '$2y$12$25FMVTOX8/Bevf0mM/2g4etuTXO/8jTYvwbiineL7mkpuWVvUKT2C', 'urb', false);
INSERT INTO user (email, enabled, enabled_account, first_name, last_name, last_password_reset_date, password, username, disable_reservation) VALUES ('sipovac.zeljana@gmail.com', true, true, 'Zeljana', 'Sipovac', '2020-03-01', '$2y$12$b5uZW60afCjvlmLZHCpdk.eFI/aOYwSoe3p696PAR2ZSIJ/lUCS6.', 'zex', false);

INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_AGENT');

INSERT INTO user_roles (user_id, role_id) VALUES ( 1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES ( 2, 3);
INSERT INTO user_roles (user_id, role_id) VALUES ( 3, 2);

insert into permission (name) values ('user');
insert into permission (name) values ('newAd');
insert into permission (name) values ('codebook');
insert into permission (name) values ('orderedRequests');
insert into permission (name) values ('publishedRequests');

insert into role_permissions (role_id, permission_id) values (1, 1);
insert into role_permissions (role_id, permission_id) values (2, 1);
insert into role_permissions (role_id, permission_id) values (3, 1);
insert into role_permissions (role_id, permission_id) values (1, 2);
insert into role_permissions (role_id, permission_id) values (3, 2);
insert into role_permissions (role_id, permission_id) values (2, 3);
insert into role_permissions (role_id, permission_id) values (1, 4);
insert into role_permissions (role_id, permission_id) values (3, 5);

INSERT INTO fuel (name, code_book_id) VALUES ('BMB95', 1);
INSERT INTO fuel (name, code_book_id) VALUES ('Diesel', 1);
INSERT INTO fuel (name, code_book_id) VALUES ('Diesel100', 1);

INSERT INTO brand (name) VALUES ('Opel');
INSERT INTO brand (name) VALUES ('Fiat');
INSERT INTO brand (name) VALUES ('BMW');
INSERT INTO brand (name) VALUES ('Peugeot');

INSERT INTO model (name) VALUES ('Insignia');
INSERT INTO model (name) VALUES ('500L');
INSERT INTO model (name) VALUES ('X6');
INSERT INTO model (name) VALUES ('206');

INSERT INTO gear_shift (name, code_book_id) VALUES ('Manual', 1);
INSERT INTO gear_shift (name, code_book_id) VALUES ('Automatic', 1);

INSERT INTO car_class (name) VALUES ('Caravan');
INSERT INTO car_class (name) VALUES ('Hatch_Back');

INSERT INTO code_book (name) VALUES ('sifrarnik1');

INSERT INTO price_list (start_date, end_date, price, collision_damage_waiver, price_per_km) VALUES ('2020-06-04', '2020-09-16', 5000.0, 1000.0, 100.0);

INSERT INTO car (child_seats, limit_kms, traveled_kms, ad_id, brand_id, car_class_id, fuel_id, gear_shift_id, model_id) VALUES (2, 200, 40000, 1, 1, 1, 1, 1, 1);
INSERT INTO car (child_seats, limit_kms, traveled_kms, ad_id, brand_id, car_class_id, fuel_id, gear_shift_id, model_id) VALUES (0, 100, 10000, 2, 2, 2, 2, 2, 2);
INSERT INTO car (child_seats, limit_kms, traveled_kms, ad_id, brand_id, car_class_id, fuel_id, gear_shift_id, model_id) VALUES (2, 200, 40000, 3, 3, 1, 1, 1, 3);
INSERT INTO car (child_seats, limit_kms, traveled_kms, ad_id, brand_id, car_class_id, fuel_id, gear_shift_id, model_id) VALUES (0, 100, 10000, 4, 4, 2, 2, 2, 4);
INSERT INTO car (child_seats, limit_kms, traveled_kms, ad_id, brand_id, car_class_id, fuel_id, gear_shift_id, model_id) VALUES (2, 200, 40000, 5, 1, 1, 1, 1, 1);
INSERT INTO car (child_seats, limit_kms, traveled_kms, ad_id, brand_id, car_class_id, fuel_id, gear_shift_id, model_id) VALUES (0, 100, 10000, 6, 2, 2, 2, 2, 2);


INSERT INTO ad (city, collision_damage_waiver, rating, userid, car_id, price_list_id,discount) VALUES ('Beograd', true, 4.0, 1, 1, 1,20);
INSERT INTO ad (city, collision_damage_waiver, rating, userid, car_id, price_list_id,discount) VALUES ('Beograd', true, 5.0, 1, 2, 1,20);
INSERT INTO ad (city, collision_damage_waiver, rating, userid, car_id, price_list_id,discount) VALUES ('Cacak', true, 4.0, 2, 3, 1,20);
INSERT INTO ad (city, collision_damage_waiver, rating, userid, car_id, price_list_id,discount) VALUES ('Novi Sad', true, 4.0, 2, 4, 1,20);
INSERT INTO ad (city, collision_damage_waiver, rating, userid, car_id, price_list_id,discount) VALUES ('Novi Sad', true, 4.0, 1, 5, 1,20);
INSERT INTO ad (city, collision_damage_waiver, rating, userid, car_id, price_list_id,discount) VALUES ('Beograd', true, 4.0, 2, 6, 1,20);

INSERT INTO request (end_date, start_date, status, userid) VALUES ('2020-06-20T05:00', '2020-06-10T05:00', 3, 1); --status kao trojka je PAID iz enuma
INSERT INTO request (end_date, start_date, status, userid) VALUES ('2020-10-10T05:00', '2020-08-10T05:00', 3, 1);
INSERT INTO request (end_date, start_date, status, userid) VALUES ('2020-11-10T05:00', '2020-10-10T05:00', 1, 1);
INSERT INTO request (end_date, start_date, status, userid) VALUES ('2020-07-10T10:00', '2020-07-05T11:00', 1, 1);

INSERT INTO rentacar (ad, start_date, end_date, request_id) VALUES (1, '2020-06-10T05:00', '2020-06-20T05:00', 1);
INSERT INTO rentacar (ad, start_date, end_date, request_id) VALUES (2, '2020-07-10T05:00', '2020-09-10T05:00', 2);
INSERT INTO rentacar (ad, start_date, end_date, request_id) VALUES (3, '2020-10-10T05:00', '2020-11-10T05:00', 3);
INSERT INTO rentacar (ad, start_date, end_date, request_id) VALUES (4, '2020-07-10T10:00', '2020-07-15T11:00', 4);

INSERT INTO request_rentacars (request_id, rentacars_id) VALUES (1, 1);
INSERT INTO request_rentacars (request_id, rentacars_id) VALUES (2, 2);
INSERT INTO request_rentacars (request_id, rentacars_id) VALUES (3, 3);
INSERT INTO request_rentacars (request_id, rentacars_id) VALUES (4, 4);

INSERT INTO message (content, date, senderid, receiverid, adid) VALUES ('Hi Aleksandra test message', '2020-06-17T10:00', 1, 2, 3);
INSERT INTO message (content, date, senderid, receiverid, adid) VALUES ('Hi Tamara', '2020-06-17T10:00', 2, 1, 3);

--INSERT INTO gear_shift_cars (gear_shift_id, cars_id) VALUES (1, 1);
--INSERT INTO gear_shift_cars (gear_shift_id, cars_id) VALUES (2, 2);


SET FOREIGN_KEY_CHECKS=1;