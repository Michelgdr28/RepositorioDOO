package co.edu.uco.nose.entity;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;


public final class CityEntity {
	private UUID id;
	private String name;
	private DepartmentEntity department;
	
	public CityEntity() {
		setId(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
		setDepartment(new DepartmentEntity());
	}
	
	public CityEntity(final UUID id) {
		setId(id);
		setName(TextHelper.getDefault());
		setDepartment(new DepartmentEntity());
	}

	
	public CityEntity(final UUID id, final String name, final DepartmentEntity country) {
		setId(id);
		setName(name);
		setDepartment(country);
	} 
	
	public UUID getId() {
		return id;
	}
	
	public void setId(final UUID id) {
		this.id = UUIDHelper.getUUIDHelper().getDefault(id);
	}
	
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(final DepartmentEntity department) {
		this.department = (department == null) ? new DepartmentEntity() : department;
	}
	
}
