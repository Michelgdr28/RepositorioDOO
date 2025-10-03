package co.edu.uco.nose.dto;

import java.util.UUID;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class IdentificationTypeDTO {
    private UUID id;
    private String name;

    public IdentificationTypeDTO() {
        this.id = UUIDHelper.getUUIDHelper().getDefault();
        this.name = TextHelper.getDefault();
    }

    public IdentificationTypeDTO(final UUID id, final String name) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
        this.name = TextHelper.getDefaultWithTrim(name);
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
