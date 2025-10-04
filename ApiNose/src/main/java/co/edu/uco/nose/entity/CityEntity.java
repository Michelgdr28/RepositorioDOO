package co.edu.uco.nose.entity;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public final class CityEntity extends Entity {

    private DepartmentEntity department;
    private String name;

    public CityEntity() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setDepartment(new DepartmentEntity());
        setName(TextHelper.getDefault());
    }

    public CityEntity(final UUID id) {
        super(id);
        setDepartment(new DepartmentEntity());
        setName(TextHelper.getDefault());
    }

    public CityEntity(final UUID id, final DepartmentEntity department, final String name) {
        super(id);
        setDepartment(department);
        setName(name);
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(final DepartmentEntity department) {
        this.department = (department == null) ? new DepartmentEntity() : department;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }
}


