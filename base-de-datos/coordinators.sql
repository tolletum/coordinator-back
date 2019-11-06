CREATE SCHEMA coordinator;

CREATE TABLE coordinator.profiles (
  id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
  description TEXT NOT NULL,
  rate DECIMAL NOT NULL,
);

CREATE TABLE coordinator.employees (
  id TEXT NOT NULL,
  name TEXT NOT NULL,
  lastname TEXT NOT NULL,
  chargeability INTEGER,
  iscoordinator DEFAULT false BOOLEAN,
  profile_id UUID NOT NULL REFERENCES profiles on id
);

CREATE TABLE coordinator.teams (
  id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
  coordinator TEXT REFERENCES coordinator.employees on id,
  employee TEXT REFERENCES coordinator.employees on id
);

CREATE TABLE coordinator.projects (
    id text,
    second_column integer
);