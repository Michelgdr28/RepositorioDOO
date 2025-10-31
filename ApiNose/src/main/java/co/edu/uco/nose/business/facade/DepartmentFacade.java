package co.edu.uco.nose.business.facade;

import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.dto.DepartmentDTO;

public interface DepartmentFacade {

	List<DepartmentDTO> findAllDepartments();

	List<DepartmentDTO> findDepartmentsByFilter(DepartmentDTO departmentFilters);

	DepartmentDTO findSpecificDepartment(UUID id);

}
