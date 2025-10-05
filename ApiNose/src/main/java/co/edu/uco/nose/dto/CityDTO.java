package co.edu.uco.nose.dto;

import java.util.UUID;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class CityDTO {
	private UUID id;
	private String name;
	private DepartmentDTO department;
	
	public CityDTO() {
		setId(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
		setDepartment(new DepartmentDTO());
	}
	
	public CityDTO(final UUID id) {
		setId(id);
		setName(TextHelper.getDefault());
		setDepartment(new DepartmentDTO());
	}
	
	public CityDTO(final UUID id, final String name, final DepartmentDTO department) {
		setId(id);
		setName(name);
		setDepartment(department);
	}
	
	public UUID getId() {
		return id;
	}
	
	public void setId(final UUID id) {
		this.id = UUIDHelper.getUUIDHelper() .getDefault(id);;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}
	
	public DepartmentDTO getState() {
		return department;
	}
	
	public void setDepartment(final DepartmentDTO department) {
		this.department = department == null ? new DepartmentDTO() : department;
	}

}
