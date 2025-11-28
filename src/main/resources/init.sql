--
-- Skapar databasen och tabellerna för Laboration 1.
--
DROP DATABASE IF EXISTS testdb;
CREATE DATABASE testdb;
-- Drop existing tables
DROP TABLE IF EXISTS account CASCADE;
DROP TABLE IF EXISTS moon_mission CASCADE;

-- Create tables using PostgreSQL syntax
CREATE TABLE account
(
    user_id     SMALLSERIAL PRIMARY KEY,
    name        VARCHAR(255),
    password    VARCHAR(255),
    first_name  VARCHAR(255),
    last_name   VARCHAR(255),
    ssn         VARCHAR(255)
);

CREATE TABLE moon_mission
(
    mission_id     SMALLSERIAL PRIMARY KEY,
    spacecraft     VARCHAR(255),
    launch_date    DATE,
    carrier_rocket VARCHAR(255),
    operator       VARCHAR(255),
    mission_type   VARCHAR(255),
    outcome        VARCHAR(255)
);

-- Insert initial data into account
INSERT INTO account (password, first_name, last_name, ssn)
VALUES
    ('8j_]xrCfh#t5,vne', 'Alexandra', 'Truby', '930213-1480'),
    ('g`:+W{\%H9UXqGU4', 'Adna', 'Sandberg', '760723-6085'),
    ('4D3ss?-;MY)9S!y{', 'Daniela', 'Petterson', '810809-3405'),
    ('MB=V4cbAqPz4vqmQ', 'Angela', 'Fransson', '371108-9221');

-- Update name column with generated username (first 3 letters of first_name + 3 letters of last_name)
UPDATE account
SET name = CONCAT(SUBSTRING(first_name, 1, 3), SUBSTRING(last_name, 1, 3));

-- Insert moon missions (Postgres compatible)
INSERT INTO moon_mission (spacecraft, launch_date, carrier_rocket, operator, mission_type, outcome)
VALUES
    ('Pioneer 0', '1958-08-17', 'Thor DM-18 Able I', 'USAF', 'Orbiter', 'Launch failure'),
    ('Luna E-1 No.1', '1958-08-23', 'Luna', 'OKB-1', 'Impactor', 'Launch failure'),
    ('Pioneer 4', '1959-03-03', 'Juno II', 'NASA', 'Flyby', 'Partial failure'),
    ('Luna 2', '1959-08-17', 'Thor DM-18 Able I', 'USAF', 'Orbiter', 'Successful'),
    ('Luna 3', '1959-10-04', 'Luna', 'OKB-1', 'Flyby', 'Successful'),
    ('Pioneer P-31', '1960-12-15', 'Atles-D Able', 'NASA', 'Orbiter', 'Launch failure'),
    ('Ranger 7',    '1964-07-28', 'Atlas LV-3 Agena-B', 'USAF', 'Orbiter', 'Successful'),
    ('Lunar Orbiter 4', '1967-05-04', 'Atlas SLVC-3 Agena-D', 'NASA', 'Orbiter', 'Successful'),
    ('SELENE', '2007-09-14', 'H-IIA 2022', 'JAXA', 'Orbiter', 'Successful'),
    ('Hiten', '1990-01-24', 'Mu-3S-II', 'ISAS', 'Flyby/Orbiter', 'Successful'),
    ('Beresheet', '2019-02-22', 'Falcon 9', 'SpaceIL', 'Lander', 'Spacecraft landing failure'),
    ('Chandrayaan-2', '2019-07-22', 'GSLV Mk III', 'ISRO', 'Orbiter', 'Operational'),
    ('TESS', '2019-04-18', 'Falcon 9 Full Thrust', 'NASA', 'Gravity assist', 'Successful'),
    ('Chang''es 5-T1', '2014-10-23', 'Long March 3C', 'CNSA', 'Flyby', 'Successful'),
    ('Manfred Memorial Moon Mission', '2014-10-23', 'Long March 3C', 'LuxSpace', 'Flyby', 'Successful'),
    ('Flow', '2011-09-10', 'Delta II 7920H', 'NASA', 'Orbiter', 'Successful'),
    ('LADEE', '2013-09-07', 'Minotaur V', 'NASA', 'Orbiter', 'Successful'),
    ('Luna E-1 No.2', '1958-10-11', 'Luna', 'OKB-1', 'Impactor', 'Launch failure');