package co.edu.uco.nose.business.domain;

import java.util.UUID;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class CityDomain extends Domain {
    private UUID departmentId;
    private String name;

    public CityDomain() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setDepartmentId(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
    }

    public CityDomain(UUID id) {
        super(id);
        setDepartmentId(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
    }

    public CityDomain(UUID id, UUID departmentId, String name) {
        super(id);
        setDepartmentId(departmentId);
        setName(name);
    }

    public UUID getDepartmentId() { 
    	return departmentId; 
    	}
    public void setDepartmentId(UUID departmentId) { 
    	this.departmentId = UUIDHelper.getUUIDHelper().getDefault(departmentId);
    	}

    public String getName() { 
    	return name;
    	}
    public void setName(final String name) {
    	this.name = TextHelper.getDefaultWithTrim(name);
    	}
}

