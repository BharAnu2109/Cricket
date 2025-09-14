-- Initialize Cricket Management System Database

-- Create databases for different services
CREATE DATABASE IF NOT EXISTS playerdb;
CREATE DATABASE IF NOT EXISTS teamdb;
CREATE DATABASE IF NOT EXISTS matchdb;
CREATE DATABASE IF NOT EXISTS statsdb;

-- Sample data for Player Service
\c playerdb;

-- This will be handled by JPA/Hibernate, but here's the expected schema
-- Players table will be created automatically by Hibernate

-- Insert some sample players
-- Note: This is just for reference. Actual data will be inserted via REST API
/*
INSERT INTO players (first_name, last_name, date_of_birth, country, playing_role, batting_style, bowling_style, jersey_number, is_active, created_at, updated_at) VALUES
('Virat', 'Kohli', '1988-11-05', 'India', 'BATSMAN', 'RIGHT_HANDED', 'RIGHT_ARM_MEDIUM', 18, true, NOW(), NOW()),
('MS', 'Dhoni', '1981-07-07', 'India', 'WICKET_KEEPER_BATSMAN', 'RIGHT_HANDED', NULL, 7, false, NOW(), NOW()),
('Kane', 'Williamson', '1990-08-08', 'New Zealand', 'BATSMAN', 'RIGHT_HANDED', 'RIGHT_ARM_MEDIUM', 22, true, NOW(), NOW()),
('Joe', 'Root', '1990-12-30', 'England', 'BATSMAN', 'RIGHT_HANDED', 'RIGHT_ARM_SPIN', 66, true, NOW(), NOW()),
('Steve', 'Smith', '1989-06-02', 'Australia', 'BATSMAN', 'RIGHT_HANDED', 'LEG_SPIN', 49, true, NOW(), NOW());
*/