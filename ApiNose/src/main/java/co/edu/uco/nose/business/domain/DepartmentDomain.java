package co.edu.uco.nose.business.domain;

import java.util.UUID;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class DepartmentDomain extends Domain {
    private UUID countryId;
    private String name;

    public DepartmentDomain() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setCountryId(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
    }

    public DepartmentDomain(UUID id) {
        super(id);
        setCountryId(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
    }

    public DepartmentDomain(UUID id, UUID countryId, String name) {
        super(id);
        setCountryId(countryId);
        setName(name);
    }

    public UUID getCountryId() { 
    	return countryId;
    	}
    public void setCountryId(UUID countryId) {
    	this.countryId = UUIDHelper.getUUIDHelper().getDefault(countryId);
    	}

    public String getName() { 
    	return name;
    	}
    public void setName(final String name) {
    	this.name = TextHelper.getDefaultWithTrim(name);
    	}
}

