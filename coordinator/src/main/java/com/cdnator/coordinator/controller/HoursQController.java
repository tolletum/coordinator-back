package com.cdnator.coordinator.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import com.cdnator.coordinator.dao.HoursQDao;
import com.cdnator.coordinator.dao.entity.Employee;
import com.cdnator.coordinator.dao.entity.HoursQ;
import com.cdnator.coordinator.dao.entity.HoursQId;
import com.cdnator.coordinator.dto.EmployeeDTO;
import com.cdnator.coordinator.dto.HoursQDTO;
import com.cdnator.coordinator.dto.HoursQIdDTO;
import com.cdnator.coordinator.dto.enumtypes.MonthEnum;
import com.cdnator.coordinator.dto.enumtypes.QuarterEnum;
import com.cdnator.coordinator.exception.BadRequestException;
import com.cdnator.coordinator.mapper.MapperDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/coordinators")
public class HoursQController {

  private static final Logger logger = LoggerFactory.getLogger(HoursQController.class);

  @Autowired
  private HoursQDao dao;

  @Autowired
  private MapperDTO mapper;

  @PostMapping("/hours-quarter")
  public ResponseEntity<HoursQDTO> insertHoursQ(@Valid @RequestBody HoursQDTO hoursQ) {

    final HoursQ savedHoursQ = dao.insertHoursQ(mapper.hoursQDTOToHoursQ(hoursQ));

    // Formato '/hours-quarter/{year}-{quarter}-{month}-{employeeId}''
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
      .path("/{year}-{quarter}-{month}-{employeeId}")
      .buildAndExpand(
        savedHoursQ.getId().getYear(), 
        savedHoursQ.getId().getQuarter(), 
        savedHoursQ.getId().getMonth(), 
        savedHoursQ.getId().getEmployee().getId())
      .toUri();

    return ResponseEntity.created(location).body(mapper.hoursQToHoursQDTO(savedHoursQ));
  }

  @GetMapping("/hours-quarter")
  public List<HoursQDTO> listHoursQByTeamAndQ(@RequestParam(value = "teamId", required = false) UUID teamdId, @RequestParam(value = "year", required = false) String year, @RequestParam(value = "quarter", required = false) String quarter) {

    List<HoursQ> listOfHoursQ = new ArrayList<HoursQ>();
    if(teamdId != null && !StringUtils.isEmpty(year) && !StringUtils.isEmpty(quarter)) {
      listOfHoursQ = dao.listHoursQByTeamAndQ(teamdId, year, quarter);
    } else {
      listOfHoursQ = dao.listHoursQ();
    }

    return mapper.listHoursQToHoursQDTO(listOfHoursQ);
  }

  @GetMapping("/hours-quarter/{year}-{quarter}-{month}-{employeeId}")
  public ResponseEntity<HoursQDTO> getHoursQ(@PathVariable("year") String year, @PathVariable("quarter") String quarter, @PathVariable("month") String month, @PathVariable("employeeId") String employeeId) {

    EmployeeDTO employee = new EmployeeDTO();
    employee.setId(employeeId);
    HoursQIdDTO id = new HoursQIdDTO(
      year, 
      QuarterEnum.valueOf(quarter), 
      MonthEnum.valueOf(month), 
      employee);

    final HoursQ hoursQ = dao.getHoursQ(mapper.hoursQIdDTOToHoursQId(id));

    return ResponseEntity.ok().body(mapper.hoursQToHoursQDTO(hoursQ));
  }

  // TODO: Falta terminar esta parte de abajo: update y delete
  
  @PatchMapping("/hours-quarter/{id}")
  public ResponseEntity<HoursQDTO> updateHoursQ(@PathVariable HoursQId id, @RequestBody HoursQDTO updatedHoursQ) {

    final HoursQ savedHoursQ = dao.updateHoursQ(id, mapper.hoursQDTOToHoursQ(updatedHoursQ));

    return ResponseEntity.ok().body(mapper.hoursQToHoursQDTO(savedHoursQ));
  }

  @DeleteMapping("/hours-quarter/{id}")
  public void deleteHoursQ(@PathVariable HoursQId id) {

    try {
      dao.deleteHoursQ(id);
    } catch(Exception ex) {
      logger.error("Error deleting hours quarter: " + ex.toString());
      throw new BadRequestException("Error deleting hours quarter with id: " + id + ". " + ex.toString());
    }
  }

}