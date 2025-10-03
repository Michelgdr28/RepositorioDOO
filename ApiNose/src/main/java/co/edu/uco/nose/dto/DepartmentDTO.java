package co.edu.uco.nose.dto;

import java.util.UUID;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class DepartmentDTO {
    private UUID id;
    private UUID countryId;
    private String name;

    public DepartmentDTO() {
        this.id = UUIDHelper.getUUIDHelper().getDefault();
        this.countryId = UUIDHelper.getUUIDHelper().getDefault();
        this.name = TextHelper.getDefault();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
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

    public void setName(String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }
}

