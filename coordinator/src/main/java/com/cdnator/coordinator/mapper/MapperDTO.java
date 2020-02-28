package com.cdnator.coordinator.mapper;

import java.util.List;

import com.cdnator.coordinator.dao.entity.Employee;
import com.cdnator.coordinator.dao.entity.HoursQ;
import com.cdnator.coordinator.dao.entity.Profile;
import com.cdnator.coordinator.dao.entity.Project;
import com.cdnator.coordinator.dao.entity.Team;
import com.cdnator.coordinator.dto.EmployeeDTO;
import com.cdnator.coordinator.dto.HoursQDTO;
import com.cdnator.coordinator.dto.ProfileDTO;
import com.cdnator.coordinator.dto.ProjectDTO;
import com.cdnator.coordinator.dto.TeamDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MapperDTO {

  @Mapping(ignore = true, target = "employees")
  ProfileDTO profileToProfileDTO(Profile profile);

  EmployeeDTO employeeToEmployeeDTO(Employee employee);

  ProjectDTO projectToProjectDTO(Project project);

  @Mapping(ignore = true, target = "employees")
  @Mapping(ignore = true, target = "projects")
  TeamDTO teamToTeamDTO(Team team);

  Profile profileDTOToProfile(ProfileDTO profile);

  Employee employeeDTOToEmployee(EmployeeDTO employee);

  Project projectDTOToProject(ProjectDTO project);

  Team teamDTOToTeam(TeamDTO team);

  List<ProfileDTO> listProfileToProfileDTO(List<Profile> profiles);

  List<EmployeeDTO> listEmployeeToEmployeeDTO(List<Employee> employees);

  List<ProjectDTO> listProjectToProjectDTO(List<Project> project);

  List<TeamDTO> listTeamToTeamDTO(List<Team> teams);

  HoursQDTO hoursQToHoursQDTO(HoursQ savedHoursQ);

  List<HoursQDTO> listHoursQToHoursQDTO(List<HoursQ> listOfHoursQ);

  HoursQ hoursQDTOToHoursQ(HoursQDTO updatedHoursQ);

}