--SET FOREIGN_KEY_CHECKS=0;
--DELETE FROM report;
--DELETE FROM request;
--DELETE FROM fuel;
--DELETE FROM request_rentacars;
--DELETE FROM rentacar;

INSERT INTO report (adid, additional_info, kms) VALUES ( 1, 'info', 150);
INSERT INTO report (adid, additional_info, kms) VALUES ( 1, 'info2', 170);
INSERT INTO report (adid, additional_info, kms) VALUES ( 1, 'info3', 110);

INSERT INTO request (end_paid, reserved_date, end_date, start_date, status, userid) VALUES ('2020-06-10T17:00', '2020-06-10T07:00', '2020-06-11T05:00', '2020-06-10T05:00', 3, 1); --status kao trojka je PAID iz enuma
INSERT INTO request (end_paid, reserved_date, end_date, start_date, status, userid) VALUES ('2020-08-11T23:00', '2020-08-10T11:00','2020-08-11T05:00', '2020-08-10T05:00', 3, 1);
INSERT INTO request (end_paid, reserved_date, end_date, start_date, status, userid) VALUES ('2020-11-10T17:01', '2020-11-10T05:01', '2020-11-11T05:00','2020-11-10T05:00', 1, 1);
INSERT INTO request (end_paid, reserved_date, end_date, start_date, status, userid) VALUES ('2020-07-06T22:30', '2020-07-06T10:30', '2020-07-06T10:00', '2020-07-05T11:00', 1, 1);


INSERT INTO rentacar (ad, start_date, end_date, request_id) VALUES (1, '2020-06-10T05:00', '2020-06-20T05:00', 1);
INSERT INTO rentacar (ad, start_date, end_date, request_id) VALUES (2, '2020-07-10T05:00', '2020-09-10T05:00', 2);
INSERT INTO rentacar (ad, start_date, end_date, request_id) VALUES (3, '2020-10-10T05:00', '2020-11-10T05:00', 3);
INSERT INTO rentacar (ad, start_date, end_date, request_id) VALUES (4, '2020-07-10T10:00', '2020-07-15T11:00', 4);

INSERT INTO request_rentacars (request_id, rentacars_id) VALUES (1, 1);
INSERT INTO request_rentacars (request_id, rentacars_id) VALUES (2, 2);
INSERT INTO request_rentacars (request_id, rentacars_id) VALUES (3, 3);
INSERT INTO request_rentacars (request_id, rentacars_id) VALUES (4, 4);
--SET FOREIGN_KEY_CHECKS=1;
