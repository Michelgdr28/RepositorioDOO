package co.edu.uco.nose.business.domain;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public final class CityDomain extends Domain {

    private DepartmentDomain department;
    private String name;

    public CityDomain() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setDepartment(new DepartmentDomain());
        setName(TextHelper.getDefault());
    }

    public CityDomain(final UUID id) {
        super(id);
        setDepartment(new DepartmentDomain());
        setName(TextHelper.getDefault());
    }

    public CityDomain(final UUID id, final DepartmentDomain department, final String name) {
        super(id);
        setDepartment(department);
        setName(name);
    }

    public DepartmentDomain getDepartment() {
        return department;
    }

    public void setDepartment(final DepartmentDomain department) {
        this.department = (department == null) ? new DepartmentDomain() : department;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }
}

