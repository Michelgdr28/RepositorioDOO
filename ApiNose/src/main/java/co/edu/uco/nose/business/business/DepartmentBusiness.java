package co.edu.uco.nose.business.business;

import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.business.domain.DepartmentDomain;

public interface DepartmentBusiness {

	List<DepartmentDomain> findAllDepartments();

	List<DepartmentDomain> findDepartmentsByFilter(DepartmentDomain departmentFilters);

	DepartmentDomain findSpecificDepartment(UUID id);

}
