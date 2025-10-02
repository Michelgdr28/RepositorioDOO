package co.edu.uco.nose.entity;

//Me falta jakarta con @entity y @Table

import java.util.UUID;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class CountryEntity {

    private UUID id;
    private String name;

    public CountryEntity() {
        this.id = UUIDHelper.getUUIDHelper().getDefault();
        this.name = TextHelper.getDefault();
    }

    public UUID getId() { 
    	return id;
    	}
    public void setId(UUID id) {
    	this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    	}

    public String getName() { 
    	return name;
    	}
    public void setName(String name) {
    	this.name = TextHelper.getDefaultWithTrim(name);
    	}
}

