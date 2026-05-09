-- =============================================
-- Sample data for FitnessTracker
-- Insert order respects foreign key constraints
-- =============================================

-- 1. Users (no dependencies)
INSERT INTO users (first_name, last_name, birthdate, email)
VALUES ('Jan', 'Kowalski', '1995-06-15', 'jan.kowalski@example.com');

INSERT INTO users (first_name, last_name, birthdate, email)
VALUES ('Anna', 'Nowak', '1988-03-22', 'anna.nowak@example.com');

INSERT INTO users (first_name, last_name, birthdate, email)
VALUES ('Piotr', 'Wisniewski', '2000-11-08', 'piotr.wisniewski@example.com');

INSERT INTO users (first_name, last_name, birthdate, email)
VALUES ('Maria', 'Zielinska', '1992-09-30', 'maria.zielinska@example.com');

INSERT INTO users (first_name, last_name, birthdate, email)
VALUES ('Tomasz', 'Lewandowski', '1985-01-17', 'tomasz.lewandowski@example.com');

-- 2. Trainings (depends on users)
INSERT INTO trainings (user_id, start_time, end_time, activity_type, distance, average_speed)
VALUES (1, '2024-03-01 07:00:00', '2024-03-01 08:15:00', 'RUNNING', 10.5, 8.4);

INSERT INTO trainings (user_id, start_time, end_time, activity_type, distance, average_speed)
VALUES (1, '2024-03-03 17:00:00', '2024-03-03 18:30:00', 'CYCLING', 25.0, 16.7);

INSERT INTO trainings (user_id, start_time, end_time, activity_type, distance, average_speed)
VALUES (2, '2024-03-02 06:30:00', '2024-03-02 07:45:00', 'SWIMMING', 2.0, 1.6);

INSERT INTO trainings (user_id, start_time, end_time, activity_type, distance, average_speed)
VALUES (3, '2024-03-04 09:00:00', '2024-03-04 10:00:00', 'WALKING', 5.2, 5.2);

INSERT INTO trainings (user_id, start_time, end_time, activity_type, distance, average_speed)
VALUES (4, '2024-03-05 18:00:00', '2024-03-05 19:30:00', 'TENNIS', 0.0, 0.0);

INSERT INTO trainings (user_id, start_time, end_time, activity_type, distance, average_speed)
VALUES (5, '2024-03-06 08:00:00', '2024-03-06 09:00:00', 'RUNNING', 8.3, 8.3);

INSERT INTO trainings (user_id, start_time, end_time, activity_type, distance, average_speed)
VALUES (2, '2024-03-07 16:00:00', '2024-03-07 17:30:00', 'CYCLING', 30.0, 20.0);

-- 3. Statistics (depends on users, OneToOne)
INSERT INTO statistics (user_id, total_trainings, total_distance, total_calories_burned)
VALUES (1, 15, 120.5, 8500);

INSERT INTO statistics (user_id, total_trainings, total_distance, total_calories_burned)
VALUES (2, 22, 85.3, 6200);

INSERT INTO statistics (user_id, total_trainings, total_distance, total_calories_burned)
VALUES (3, 8, 42.0, 2800);

INSERT INTO statistics (user_id, total_trainings, total_distance, total_calories_burned)
VALUES (4, 10, 0.0, 4500);

INSERT INTO statistics (user_id, total_trainings, total_distance, total_calories_burned)
VALUES (5, 12, 95.7, 7100);

-- 4. Health Metrics (depends on users, ManyToOne)
INSERT INTO health_metrics (user_id, date, weight, height, heart_rate)
VALUES (1, '2024-03-01', 78.5, 180.0, 65);

INSERT INTO health_metrics (user_id, date, weight, height, heart_rate)
VALUES (1, '2024-03-15', 77.8, 180.0, 63);

INSERT INTO health_metrics (user_id, date, weight, height, heart_rate)
VALUES (2, '2024-03-01', 62.0, 165.0, 70);

INSERT INTO health_metrics (user_id, date, weight, height, heart_rate)
VALUES (3, '2024-03-01', 85.2, 175.0, 72);

INSERT INTO health_metrics (user_id, date, weight, height, heart_rate)
VALUES (4, '2024-03-01', 58.0, 168.0, 68);

INSERT INTO health_metrics (user_id, date, weight, height, heart_rate)
VALUES (5, '2024-03-01', 90.0, 185.0, 60);
