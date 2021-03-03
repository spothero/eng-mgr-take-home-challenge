DROP TABLE IF EXISTS worked_hours;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  manager_id INT,
  first_name TEXT NOT NULL,
  last_name TEXT NOT NULL,
  email TEXT UNIQUE NOT NULL,
  active BOOLEAN NOT NULL DEFAULT true,
  created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE worked_hours (
  user_id INT NOT NULL,
  date DATE NOT NULL,
  hours NUMERIC(4,2) NOT NULL CHECK (hours > 0 AND hours <= 24),
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (user_id, date),
  CONSTRAINT fk_users
    FOREIGN KEY(user_id)
    REFERENCES users(id)
);
