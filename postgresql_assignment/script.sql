-- Database: owners_pets

-- DROP DATABASE owners_pets;

CREATE DATABASE owners_pets
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_IN'
    LC_CTYPE = 'en_IN'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

SELECT DATE('1500-10-12') + (DATE('0001-01-02') - DATE('0001-01-01')) as col into a_var;

-- Deleting the table
DROP TABLE IF EXISTS data_table;

-- Creating the table
CREATE TABLE data_table(
	id SERIAL PRIMARY KEY,
	opt INT NOT NULL,
	date1 DATE,
	date2 DATE,
	n INT,
	phrase TEXT,
	operation CHAR(1)
);

-- Creating the table for 
-- tracking answers
drop table if exists answers;
CREATE TABLE answers(
	id SERIAL PRIMARY KEY,
	answer TEXT
);

DROP TABLE IF EXISTS days_to_string;

-- Creating table days_to_string
CREATE TABLE days_to_string(
	id SERIAL PRIMARY KEY,
	string_day varchar(20)
);

drop table if exists phrases1;

-- Creating table to track phrases1
CREATE TABLE phrases1(
	id SERIAL PRIMARY KEY,
	phrase VARCHAR(30),
	n int,
	field VARCHAR(20)
);

drop table if exists phrases2;

-- Creating the table phrases 2
CREATE TABLE phrases2(
	id SERIAL PRIMARY KEY,
	phrase VARCHAR(30),
	sign int,
	field VARCHAR(20)
);

-- Inserting values into the table
INSERT INTO data_table(opt, date) VALUES
(3, DATE('1500-12-10'));

INSERT INTO data_table(opt, date1, date2, operation) VALUES
(1, DATE('1500-12-10'), DATE('1500-01-02'), '+');

INSERT INTO data_table(opt, phrase) VALUES
(5, 'today');

INSERT INTO phrases2(phrase, sign, field) VALUES
('weeks from now', 1, 'weeks'),
('days from now', 1, 'days'),
('months from now', 1, 'months'),
('years from now', 1, 'years'),
('days earlier', -1, 'days'),
('weeks earlier', -1, 'weeks'),
('months earlier', -1, 'months'),
('years earlier', -1, 'years');


-- Selecting from the table
SELECT * FROM data_table;

-- Inserting into phrases1
INSERT INTO phrases1(phrase, n, field) VALUES
('today', 0, 'days'),
('tomorrow', 1, 'days'),
('day-after-tomorrow', 2, 'days'),
('yesterday', -1, 'days'),
('day-before-yesterday', -2, 'days'),
('last week', -7, 'days'),
('previous week', -7, 'days'),
('nextweek', 7, 'days'),
('next month', 1, 'months'),
('next year', 1, 'years'),
('last month', -1, 'months'),
('last year', -1, 'years'),
('month after', 1, 'months'),
('monthbefore', 1, 'months');

select * from phrases1;
select * from phrases2;

-- Inserting into days_to_string table
INSERT INTO days_to_string(string_day) VALUES
('Sunday'),
('Monday'),
('Tuesday'),
('Wednesday'),
('Thrusday'),
('Friday'),
('Saturday');

select * from days_to_string;

-- Creating the add_dates function
create or replace function add_dates(
   IN date1 DATE,
   IN date2 DATE,
   OUT final_date DATE
)
language plpgsql    
as $$

begin
	final_date := date1 + (date2 - DATE('0001-01-01'));

end;$$

-- Creating the substract_dates function
create or replace function substract_dates(
   IN date1 DATE,
   IN date2 DATE,
   OUT final_date int
)
language plpgsql    
as $$

begin
	final_date := abs(date1 - date2);

end;$$

-- Creating a function that transforms the 
-- number of days into date
create or replace function days_to_date(
   IN days INT,
   OUT final_date DATE
)
language plpgsql    
as $$

begin
	final_date := days + DATE('0001-01-01');

end;$$

-- Creating a function that transforms the 
-- day index to day name
create or replace function days_to_name(
   IN day_index INT,
   OUT day_name VARCHAR(20)
)
language plpgsql    
as $$

begin
	if day_index > 6 then
		day_index = 6;
	end if;
	select string_day into day_name from days_to_string where id=day_index;

end;$$

-- Create function to operate on option 1
create or replace function option1(
   IN date1 DATE,
   IN date2 DATE,
   IN operation CHAR(1),
   OUT final_date date
)
language plpgsql    
as $$

begin
	IF operation = '+' then
		final_date := add_dates(date1, date2);
	else
		final_date := days_to_date(substract_dates(date1, date2));
	end if;

end;$$

-- Create function to operate on option 2
create or replace function option2(
   IN n int,
   IN date1 DATE,
   IN phrase text,
   OUT final_date date
)
language plpgsql    
as $$

begin
	IF phrase = 'days' then
		final_date := add_dates(date1, DATE('0001-01-01') + n);
	elsif phrase = 'weeks' then
		final_date := add_dates(date1, DATE('0001-01-01') + 7*n);
	else
		final_date := add_dates(date1, DATE(DATE('0001-01-01') + n * INTERVAL '1 MONTH'));
	end if;

end;$$

-- Create function to operate on option 3
create or replace function option3(
   IN date1 DATE,
   OUT final_str VARCHAR(20)
)
language plpgsql    
as $$

begin
	final_str := days_to_name(cast(extract(dow from date1) as int));
end;$$

-- Create function to operate on option 4
create or replace function option4(
   IN date1 DATE,
   OUT final_str VARCHAR(20)
)
language plpgsql    
as $$

begin
	final_str := cast(extract('week' from date1) as int);
end;$$

-- Create function to operate on option 5
create or replace function option5(
   IN phrase1 VARCHAR(30),
   OUT final_str VARCHAR(20)
)
language plpgsql    
as $$
declare
	n int;
	field VARCHAR(20);
	len_results int;
	sign int;

begin

	select count(*) into len_results from phrases1 p1 where p1.phrase = phrase1;
	if len_results = 1 then
		select p1.n, p1.field into n, field from phrases1 p1 where p1.phrase = phrase1;
	else
		select p2.field, p2.sign into field, sign from phrases2 p2 where phrase like concat('%', p2.phrase);
		n := cast(split_part(phrase1, ' ', 1) as int) * sign;
	end if;
	
	final_str := DATE(CURRENT_DATE + (n || field)::interval);

end;$$

create or replace function option_processor(
	IN opt INT,
	IN date1 DATE,
	IN date2 DATE,
	IN n INT,
	IN phrase TEXT,
	IN operation CHAR(1),
   	OUT final_str TEXT
)
language plpgsql    
as $$
declare

begin
	if opt = 1 then
		final_str := option1(date1, date2, operation);
	elsif opt = 2 then
		final_str := option2(n, date1, phrase);
	elsif opt = 3 then
		final_str := option3(date1);
	elsif opt = 4 then
		final_str := option4(date1);
	else
		final_str := option5(phrase);
	end if;
	
	final_str := cast(final_str as text);

end;$$

-- Create procedure for building 
-- This procedure reads the data
-- and stores it and finally processes
-- it and stores the answers into a 
-- answers table
create or replace procedure load_data(
	file_name text
)
language plpgsql
as $$
declare
-- variable declaration
begin

	drop table if exists string_data;
	CREATE TABLE string_data(
		id SERIAL PRIMARY KEY,
		data_point TEXT
	);
	COPY string_data(data_point) FROM '/home/mijo1/Desktop/Sts/Sapient_Courses/PJP2_projects/postgresql/data.csv' WITH (FORMAT csv);
	
	insert into data_table(opt, date1, date2, operation)
		select cast(split_part(data_point, ' ', 1) as int), cast(split_part(data_point, ' ', 2) as date), 
			cast(split_part(data_point, ' ', 3) as date), split_part(data_point, ' ', 4)
		from string_data
		where cast(split_part(data_point, ' ', 1) as int) = 1;
		
	insert into data_table(opt, phrase, n, date1)
		select cast(split_part(data_point, ' ', 1) as int), split_part(data_point, ' ', 2), 
			cast(split_part(data_point, ' ', 3) as int), cast(split_part(data_point, ' ', 4) as date)
		from string_data
		where cast(split_part(data_point, ' ', 1) as int) = 2;
		
	insert into data_table(opt, date1)
		select cast(split_part(data_point, ' ', 1) as int), cast(split_part(data_point, ' ', 2) as date) 
		from string_data
		where cast(split_part(data_point, ' ', 1) as int) in (3, 4);
		
	insert into data_table(opt, phrase)
		select cast(split_part(data_point, ' ', 1) as int), split_part(data_point, ' ', 2)
		from string_data
		where cast(split_part(data_point, ' ', 1) as int) = 5;
	
	insert into answers(answer)
		select option_processor(opt, date1, date2, n, phrase, operation) from data_table;

end; $$

-- Using the add_dates function
SELECT date1 + (date2 - DATE('0001-01-01')) from data_table
where opt = 1;

-- Using the substract_dates function
SELECT substract_dates(date1, date2) from data_table
where opt = 1;

SELECT option1(date1, date2, operation) from data_table
where opt = 1;

select option2(2, Date('0001-01-01'), 'months');

select days_to_date(cast(extract(dow from date '1500-12-10') as int));

select option3(date1) from data_table;

select array_length(option4(date '0001-01-28'));

select opt into a_ from data_table where opt = 4;

select concat('1', ' 2');

select CURRENT_DATE + (interval '1 day');

select * from phrases2 where '2 weeks from now' like concat('%', phrase);

select CURRENT_DATE + interval concat('1', ' month');

select current_timestamp + 2 * interval '1 day';

select option5('5 days from now');

drop table if exists string_data;
CREATE TABLE string_data(
	id SERIAL PRIMARY KEY,
	data_point TEXT
);
COPY string_data(data_point) FROM '/home/mijo1/Desktop/Sts/Sapient_Courses/PJP2_projects/postgresql/data.csv' WITH (FORMAT csv);

select * from string_data;

call load_data('/home/mijo1/Desktop/Sts/Sapient_Courses/PJP2_projects/postgresql/data.csv');

select * from answers;

COPY answers TO '/home/mijo1/Desktop/Sts/Sapient_Courses/PJP2_projects/postgresql/answers.csv' DELIMITER ',' CSV HEADER;