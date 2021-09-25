DROP SCHEMA IF EXISTS midterm;
CREATE SCHEMA midterm;
USE midterm;
/*
DROP SCHEMA IF EXISTS midterm_test;
CREATE SCHEMA midterm_test;
USE midterm_test;
*/

INSERT INTO user(id, name, password, username) VALUES
('1', 'Jose', '$2a$10$BE3p4yAl6JDeYhRuXmPUsOwFPn/2ZA8U7loYUoQ/.bk.OOgH10njW', 'username1'), -- password: password
('2', 'Eduardo','$2a$10$BE3p4yAl6JDeYhRuXmPUsOwFPn/2ZA8U7loYUoQ/.bk.OOgH10njW', 'username2'), -- password: password
('3', 'Maria','$2a$10$NcfKcfldbLAEojIUdYgzSujzMgWH6hbCnw7y92FDgSsof/Mg/3MhW', 'admin1'), -- password: admin1
('4', 'Luis','$2a$10$BE3p4yAl6JDeYhRuXmPUsOwFPn/2ZA8U7loYUoQ/.bk.OOgH10njW', 'username3'); -- password: password

INSERT INTO roles(id, name, user_id) VALUES
('1','ACCOUNTHOLDER', '1'),
('2','ACCOUNTHOLDER', '2'),
('3','ADMIN', '3'),
('4','ACCOUNTHOLDER', '4');

INSERT INTO account_holder(date_of_birth, mailing_city, mailing_country, mailing_street, mailing_zip, city, country,street, zip_code, id) VALUES
('2010-06-13', 'Madrid', 'España', 'Avenida de América', '28123', 'Madrid', 'España', 'Avenida de América', '28123', '1'),
('1990-02-20', 'Ciudad Real', 'España', 'Calle Cruz del Sur', '13001', 'Ciudad Real', 'España', 'Calle Cruz del Sur', '13001', '2'),
('1980-03-03', 'Barcelona', 'España', 'Ramblas', '08002', 'Barcelona', 'España', 'Ramblas', '08002', '4');

INSERT INTO account(id, amount_balance, currency_balance, amount_penalty_fee, currency_penalty_fee, primary_owner, secondary_owner,date_of_creation) VALUES
(1, '900', 'EUR', '40', 'EUR', '1', '2','2010-06-13'),
(2, '700', 'EUR', '40', 'EUR', '2', '4','2010-07-04'),
(3, '6000', 'EUR', '40', 'EUR', '4', '1','2005-10-12'),
(4, '4230', 'EUR', '40', 'EUR', '2', '1','2006-05-24');

INSERT INTO third_party(id, hashed_key, name) VALUES
(1, 246810, 'Zara'),
(2, 246458, 'Bershka');

INSERT INTO savings(date_of_last_access, interest_rate, amount_minimum_balance, currency_minimum_balance, secret_key, status, id) VALUES
('2020-12-30', '0.1', '1000', 'EUR', '12345', 'ACTIVE', 1);

INSERT INTO checking(amount_minimum_balance, currency_minimum_balance, amount_monthly_maintenance_fee, currency_monthly_maintenance_fee, secret_key, status, id) VALUES
('250', 'EUR', '12', 'EUR', '12345', 'ACTIVE', 3);

INSERT INTO student_checking(secret_key, status, id) VALUES
('12345', 'ACTIVE', 2);

INSERT INTO credit_card(amount_credit_limit, currency_credit_limit, date_of_last_access, interest_rate, id) VALUES
('2800', 'EUR', '2020-12-19', '0.1', 4);

SELECT * FROM user;