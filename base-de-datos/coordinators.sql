DROP SCHEMA IF EXISTS coordinator CASCADE;

CREATE SCHEMA coordinator;

CREATE TABLE coordinator.profile (
  id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
  description TEXT NOT NULL,
  rate DECIMAL NOT NULL
);

CREATE TABLE coordinator.team (
  id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
  description TEXT NOT NULL,
  area TEXT NOT NULL
);

CREATE TABLE coordinator.employee (
  id TEXT NOT NULL PRIMARY KEY,
  name TEXT NOT NULL,
  lastname TEXT NOT NULL,
  chargeability INTEGER,
  iscoordinator BOOLEAN DEFAULT FALSE,
  profileid UUID NOT NULL,
  teamid UUID,
  FOREIGN KEY (profileid) REFERENCES coordinator.profile(id) ON UPDATE CASCADE,
  FOREIGN KEY (teamid) REFERENCES coordinator.team(id) ON UPDATE CASCADE
);

CREATE TABLE coordinator.project (
    id TEXT NOT NULL PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT NOT NULL,
    budget DECIMAL NOT NULL,
    teamid UUID NOT NULL,
    client TEXT NOT NULL,
    manager TEXT NOT NULL,
    FOREIGN KEY (teamid) REFERENCES coordinator.team(id) ON UPDATE CASCADE
);

CREATE TABLE coordinator.profile_project (
  projectid TEXT NOT NULL,
  profileid UUID NOT NULL,
  hours INTEGER NOT NULL,
  PRIMARY KEY (projectid, profileid),
  FOREIGN KEY (projectid) REFERENCES coordinator.project(id) ON UPDATE CASCADE,
  FOREIGN KEY (profileid) REFERENCES coordinator.profile(id) ON UPDATE CASCADE
);

CREATE TABLE coordinator.projects_assign (
    projectid TEXT NOT NULL,
    employeeid TEXT NOT NULL,
    timeassigned INTEGER NOT NULL,
    PRIMARY KEY (projectid, employeeid),
    FOREIGN KEY (projectid) REFERENCES coordinator.project(id) ON UPDATE CASCADE,
    FOREIGN KEY (employeeid) REFERENCES coordinator.employee(id) ON UPDATE CASCADE
);

-- Hours by employee in a Q
CREATE TABLE coordinator.hours_q (
    year CHARACTER VARYING(4) NOT NULL,
    month CHARACTER VARYING(10) NOT NULL,
	  quarter CHARACTER VARYING(2) NOT NULL,
    employeeid TEXT NOT NULL,
    hours INTEGER NOT NULL,
    PRIMARY KEY (year, month, quarter, employeeid),
    FOREIGN KEY (employeeid) REFERENCES coordinator.employee(id) ON UPDATE CASCADE
);
