CREATE TYPE popularity AS ENUM ('POPULAR', 'REGULAR');

CREATE TABLE IF NOT EXISTS area (
  id SERIAL PRIMARY KEY,
  hh_id BIGINT NOT NULL,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS fav_employers (
    id SERIAL PRIMARY KEY,
    hh_id BIGINT NOT NULL,
    name VARCHAR(300) NOT NULL,
   	description TEXT,
	area BIGINT NOT NULL,

	comment TEXT,
	date_create TIMESTAMP NOT NULL,
	popularity VARCHAR(10),
	views_count INTEGER DEFAULT 0,
	foreign key (area) references area(id)
);

CREATE TABLE IF NOT EXISTS salary (
    id serial PRIMARY KEY,
    sal_to INTEGER,
    sal_from INTEGER,
    currency VARCHAR(5),
    gross BOOLEAN
);

CREATE TABLE IF NOT EXISTS fav_vacancies (
    id SERIAL PRIMARY KEY,
    hh_id BIGINT NOT NULL,
    name VARCHAR(300) NOT NULL,
	area BIGINT NOT NULL,
	salary INTEGER NOT NULL,
	comment TEXT,
	date_create TIMESTAMP NOT NULL,
	popularity VARCHAR(10),
	views_count INTEGER DEFAULT 0,
	employer BIGINT NOT NULL,

	foreign key (employer) references fav_employers(id),
	foreign key (area) references area(id),
	foreign key (salary) references salary(id)
);



insert into area (id, hh_id, name) values
(1, 3565989, 'Ekat'),
(2, 454, 'Moscow'),
(3, 12345678, 'Piter'),
(4, 2452, 'Mo1'),
(5, 12345678, 'Mo3'),
(6, 78678, 'Mo4'),
(7, 786073, 'Mo5'),
(8, 2220, 'Mo6'),
(9, 75783, 'Mo7'),
(10, 4268, 'Mo8');

INSERT INTO fav_employers(
   id, hh_id,  name, description, area, comment, date_create)
   VALUES
   (1, 54679, 'sber', 'descr', 1, 'comm', '07-03-2021'),
   (2, 147, 'vtb', 'descr', 2, 'comm', '07-03-2021'),
   (3, 28, 'qa', 'descr', 3, 'comm', '07-03-2021'),
   (4, 368, 'ws', 'descr', 4, 'comm', '07-03-2021'),
   (5, 587, 'ed', 'descr', 5, 'comm', '07-03-2021'),
   (6, 456, 'rf', 'descr', 6, 'comm', '07-03-2021'),
   (7, 3421, 'tg', 'descr', 7, 'comm', '07-03-2021');




