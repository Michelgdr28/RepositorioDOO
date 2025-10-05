package co.edu.uco.nose.dto;

import java.util.UUID;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class UserDTO {
	
	private UUID id;
	private String idNumber;
	private String firstName;
	private String secondName;
	private String lastName;
	private String secondLastName;
	private String email;
	private String phoneNumber;
	private IdentificationTypeDTO identificationType;
	private CityDTO city;
	private boolean emailConfirmed;
	private boolean phoneConfirmed;
	
	public UserDTO() {
		setId(UUIDHelper.getUUIDHelper().getDefault());
		setIdNumber(TextHelper.getDefault());
		setFirstName(TextHelper.getDefault());
		setSecondName(TextHelper.getDefault());
		setLastName(TextHelper.getDefault());
		setSecondLastName(TextHelper.getDefault());
		setEmail(TextHelper.getDefault());
		setPhoneNumber(TextHelper.getDefault());
		setIdentificationType(new IdentificationTypeDTO());
		setCity(new CityDTO());
		setEmailConfirmed(false);
		setPhoneConfirmed(false);
	}
	
	public UserDTO(final UUID id) {
		setId(id);
		setIdNumber(TextHelper.getDefault());
		setFirstName(TextHelper.getDefault());
		setSecondName(TextHelper.getDefault());
		setLastName(TextHelper.getDefault());
		setSecondLastName(TextHelper.getDefault());
		setEmail(TextHelper.getDefault());
		setPhoneNumber(TextHelper.getDefault());
		setIdentificationType(new IdentificationTypeDTO());
		setCity(new CityDTO());
		setEmailConfirmed(false);
		setPhoneConfirmed(false);
	}
	
	public UserDTO(final UUID id, final String idNumber, final String firstName, final String secondName, final String lastName, final String secondLastName, final String email, final String phoneNumber,
			final IdentificationTypeDTO identificationType, final CityDTO city) {
		setId(id);
		setIdNumber(idNumber);
		setFirstName(firstName);
		setSecondName(secondName);
		setLastName(lastName);
		setSecondLastName(secondLastName);
		setEmail(email);
		setPhoneNumber(phoneNumber);
		setIdentificationType(identificationType);
		setCity(city);
		setEmailConfirmed(false);
		setPhoneConfirmed(false);

	}
	
	public UUID getId() {
		return id;
	}
	
	public void setId(final UUID id) {
		this.id = UUIDHelper.getUUIDHelper().getDefault(id);
	}
		
	public String getIdNumber() {
		return idNumber;
	}
	
	public void setIdNumber(final String idNumber) {
		this.idNumber = TextHelper.getDefaultWithTrim(idNumber);
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(final String firstName) {
		this.firstName = TextHelper.getDefaultWithTrim(firstName);
	}
	
	public String getSecondName() {
		return secondName;
	}
	
	public void setSecondName(final String secondName) {
		this.secondName = TextHelper.getDefaultWithTrim(secondName);
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(final String lastName) {
		this.lastName = TextHelper.getDefaultWithTrim(lastName);
	}
	
	public String getSecondLastName() {
		return secondLastName;
	}
	
	public void setSecondLastName(final String secondLastName) {
		this.secondLastName = TextHelper.getDefaultWithTrim(secondLastName);
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(final String email) {
		this.email = TextHelper.getDefaultWithTrim(email);
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = TextHelper.getDefaultWithTrim(phoneNumber);
	}
	
	public IdentificationTypeDTO getIdentificationType() {
		return identificationType;
	}
	
	public void setIdentificationType(final IdentificationTypeDTO identificationType) {
		this.identificationType = (ObjectHelper.isNull(identificationType) ? new IdentificationTypeDTO() : identificationType);
	}
	
	public CityDTO getCity() {
		return city;
	}
	
	public void setCity(final CityDTO city) {
		this.city = (ObjectHelper.isNull(city) ? new CityDTO() : city);
	}
	
	public boolean isEmailConfirmed() {
		return emailConfirmed;
	}
	
	public void setEmailConfirmed(boolean emailConfirmed) {
		this.emailConfirmed = emailConfirmed;
	}
	
	public boolean isPhoneConfirmed() {
		return phoneConfirmed;
	}
	
	public void setPhoneConfirmed(boolean phoneConfirmed) {
		this.phoneConfirmed = phoneConfirmed;
	}
	
}