package co.edu.uco.nose.dto;

import java.util.UUID;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class IdentificationTypeDTO {
	private UUID id;
	private String name;
	
	public IdentificationTypeDTO() {
		setId(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
	}
	
	public IdentificationTypeDTO(final UUID id) {
		setId(id);
		setName(TextHelper.getDefault());
	}
	
	public IdentificationTypeDTO(final UUID id, final String name) {
		setId(id);
		setName(name);
	}
	
	public UUID getId() {
		return id;
	}
	
	public void setId(final UUID id) {
		this.id = UUIDHelper.getUUIDHelper() .getDefault(id);;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = TextHelper.getDefaultWithTrim(name);
	}

}