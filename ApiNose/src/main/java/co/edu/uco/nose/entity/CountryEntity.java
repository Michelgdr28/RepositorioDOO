package co.edu.uco.nose.entity;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public final class CountryEntity  {
	private UUID id;
	private String name;
	
	public CountryEntity() {
		setId(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
	}
	
	public CountryEntity(UUID id) {
		setId(id);
		setName(TextHelper.getDefault());
	}
	
	public CountryEntity(UUID id, final String name) {
		setId(id);
		setName(name);
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

