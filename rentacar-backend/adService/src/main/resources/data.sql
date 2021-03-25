SET FOREIGN_KEY_CHECKS=0;
--DELETE FROM model;
--DELETE FROM brand;
--DELETE FROM fuel;
--DELETE FROM gear_shift;
--DELETE FROM car_class;
--DELETE FROM car_class;
--DELETE FROM code_book;
--DELETE FROM price_list;
--DELETE FROM ad;

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

INSERT INTO code_book_models(code_book_id, models_id) VALUE(1,1);
INSERT INTO code_book_models(code_book_id, models_id) VALUE(1,2);
INSERT INTO code_book_models(code_book_id, models_id) VALUE(1,3);
INSERT INTO code_book_models(code_book_id, models_id) VALUE(1,4);

INSERT INTO code_book_brands(code_book_id, brands_id) VALUE(1,1);
INSERT INTO code_book_brands(code_book_id, brands_id) VALUE(1,2);
INSERT INTO code_book_brands(code_book_id, brands_id) VALUE(1,3);
INSERT INTO code_book_brands(code_book_id, brands_id) VALUE(1,4);

INSERT INTO code_book_fuels(code_book_id, fuels_id) VALUE(1,1);
INSERT INTO code_book_fuels(code_book_id, fuels_id) VALUE(1,2);
INSERT INTO code_book_fuels(code_book_id, fuels_id) VALUE(1,3);

INSERT INTO code_book_gear_shift(code_book_id, gear_shift_id) VALUE(1,1);
INSERT INTO code_book_gear_shift(code_book_id, gear_shift_id) VALUE(1,2);

INSERT INTO code_book_classes(code_book_id, classes_id) VALUE(1,1);
INSERT INTO code_book_classes(code_book_id, classes_id) VALUE(1,2);

INSERT INTO price_list (start_date, end_date, price, collision_damage_waiver, price_per_km) VALUES ('2020-06-04', '2020-09-16', 5000.0, 1000.0, 100.0);

INSERT INTO car (child_seats, limit_kms, traveled_kms, ad_id, brand_id, car_class_id, fuel_id, gear_shift_id, model_id) VALUES (2, 200, 40000, 1, 1, 1, 1, 1, 1);
INSERT INTO car (child_seats, limit_kms, traveled_kms, ad_id, brand_id, car_class_id, fuel_id, gear_shift_id, model_id) VALUES (0, 100, 10000, 2, 2, 2, 2, 2, 2);
INSERT INTO car (child_seats, limit_kms, traveled_kms, ad_id, brand_id, car_class_id, fuel_id, gear_shift_id, model_id) VALUES (2, 200, 40000, 3, 3, 1, 1, 1, 3);
INSERT INTO car (child_seats, limit_kms, traveled_kms, ad_id, brand_id, car_class_id, fuel_id, gear_shift_id, model_id) VALUES (0, 100, 10000, 4, 4, 2, 2, 2, 4);
INSERT INTO car (child_seats, limit_kms, traveled_kms, ad_id, brand_id, car_class_id, fuel_id, gear_shift_id, model_id) VALUES (2, 200, 40000, 5, 1, 1, 1, 1, 1);
INSERT INTO car (child_seats, limit_kms, traveled_kms, ad_id, brand_id, car_class_id, fuel_id, gear_shift_id, model_id) VALUES (0, 100, 10000, 6, 2, 2, 2, 2, 2);


INSERT INTO ad (city, collision_damage_waiver, rating, userid, car_id, price_list_id,discount) VALUES ('Beograd', true, 4.0, 1, 1, 1,0);
INSERT INTO ad (city, collision_damage_waiver, rating, userid, car_id, price_list_id,discount) VALUES ('Beograd', true, 5.0, 1, 2, 1,0);
INSERT INTO ad (city, collision_damage_waiver, rating, userid, car_id, price_list_id,discount) VALUES ('Cacak', true, 4.0, 2, 3, 1,0);
INSERT INTO ad (city, collision_damage_waiver, rating, userid, car_id, price_list_id,discount) VALUES ('Novi Sad', true, 4.0, 2, 4, 1,0);
INSERT INTO ad (city, collision_damage_waiver, rating, userid, car_id, price_list_id,discount) VALUES ('Novi Sad', true, 4.0, 1, 5, 1,0);
INSERT INTO ad (city, collision_damage_waiver, rating, userid, car_id, price_list_id,discount) VALUES ('Beograd', true, 4.0, 2, 6, 1,0);

--INSERT INTO gear_shift_cars (gear_shift_id, cars_id) VALUES (1, 1);
--INSERT INTO gear_shift_cars (gear_shift_id, cars_id) VALUES (2, 2);

SET FOREIGN_KEY_CHECKS=1;