CREATE TABLE IF NOT EXISTS status (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

INSERT INTO status (NAME, DESCRIPTION) VALUES
('Active', 'Active medical record'),
('Inactive', 'Inactive medical record');

CREATE TABLE IF NOT EXISTS medical_record_type (
    medical_record_type_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);


INSERT INTO MEDICAL_RECORD_TYPE (NAME, DESCRIPTION) VALUES
('Regular', 'Regular medical record'),
('Special', 'Special medical record');