package co.edu.uco.nose.entity;

import java.util.UUID;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public final class DepartmentEntity extends Entity {
    
    private CountryEntity country;
    private String name;

    public DepartmentEntity() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setCountry(new CountryEntity());
        setName(TextHelper.getDefault());
    }

    public DepartmentEntity(final UUID id) {
        super(id);
        setCountry(new CountryEntity());
        setName(TextHelper.getDefault());
    }

    public DepartmentEntity(final UUID id, final CountryEntity country,final String name) {
        super(id);
        setCountry(country);
        setName(name);
    }

    public CountryEntity getCountry() { 
        return country;
    }

    public void setCountry(final CountryEntity country) {
        this.country = (country == null) ? new CountryEntity() : country;
    }

    public String getName() { 
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }
}

