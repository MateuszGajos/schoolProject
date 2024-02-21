-- Drop tables if they exist
DROP TABLE IF EXISTS Attendance;
DROP TABLE IF EXISTS Child;
DROP TABLE IF EXISTS Parent;
DROP TABLE IF EXISTS School;

-- Create School table
CREATE TABLE School
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(255)   NOT NULL,
    hour_price DECIMAL(10, 2) NOT NULL
);

-- Create Parent table
CREATE TABLE Parent
(
    id        SERIAL PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname  VARCHAR(255) NOT NULL
);

-- Create Child table
CREATE TABLE Child
(
    id        SERIAL PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname  VARCHAR(255) NOT NULL,
    parent_id INT          NOT NULL,
    school_id INT          NOT NULL,
    FOREIGN KEY (parent_id) REFERENCES Parent (id),
    FOREIGN KEY (school_id) REFERENCES School (id)
);

-- Create Attendance table
CREATE TABLE Attendance
(
    id         SERIAL PRIMARY KEY,
    child_id   INT       NOT NULL,
    entry_date TIMESTAMP NOT NULL,
    exit_date  TIMESTAMP NOT NULL,
    FOREIGN KEY (child_id) REFERENCES Child (id)
);
