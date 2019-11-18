DROP SCHEMA IF EXISTS coordinator CASCADE;

CREATE SCHEMA coordinator;

CREATE TABLE coordinator.profiles (
  id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
  description TEXT NOT NULL,
  rate DECIMAL NOT NULL
);

CREATE TABLE coordinator.employees (
  id TEXT NOT NULL PRIMARY KEY,
  name TEXT NOT NULL,
  lastname TEXT NOT NULL,
  chargeability INTEGER,
  iscoordinator BOOLEAN DEFAULT FALSE,
  profile_id UUID NOT NULL REFERENCES coordinator.profiles (id)
);

CREATE TABLE coordinator.teams (
  id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
  coordinator TEXT REFERENCES coordinator.employees (id),
  employee TEXT REFERENCES coordinator.employees (id)
);

CREATE TABLE coordinator.projects (
    id TEXT NOT NULL PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT NOT NULL,
    budget DECIMAL NOT NULL,
    team_id UUID NOT NULL REFERENCES coordinator.teams (id),
    client TEXT NOT NULL,
    manager TEXT NOT NULL
);

CREATE TABLE coordinator.projects_assigns (
    project_id TEXT NOT NULL REFERENCES coordinator.projects (id),
    employee_id TEXT NOT NULL REFERENCES coordinator.employees (id),
    time_assigned INTEGER NOT NULL,
    PRIMARY KEY (project_id, employee_id)
);