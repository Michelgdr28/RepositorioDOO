package co.edu.uco.nose.dto;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import java.util.UUID;

public class CityDTO {
    private UUID id;
    private DepartmentDTO department;
    private String name;

    public CityDTO() {
        this.id = UUIDHelper.getUUIDHelper().getDefault();
        this.department = new DepartmentDTO();
        this.name = TextHelper.getDefault();
    }

    public CityDTO(final UUID id, final DepartmentDTO department, final String name) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
        this.department = (department == null) ? new DepartmentDTO() : department;
        this.name = TextHelper.getDefaultWithTrim(name);
    }

    public UUID getId() { 
    	return id;
    	}
    public void setId(UUID id) { 
    	this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    	}

    public DepartmentDTO getDepartment() { 
    	return department; 
    	}
    public void setDepartment(DepartmentDTO department) { 
    	this.department = (department == null) ? new DepartmentDTO() : department; 
    	}

    public String getName() { 
    	return name;
    	}
    public void setName(String name) { 
    	this.name = TextHelper.getDefaultWithTrim(name);
    	}
}

