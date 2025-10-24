package co.edu.uco.nose.dto;

import java.util.UUID;
import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public class UserDTO {
	
	private UUID id;
	private String identificationNumber;
	private String firstName;
	private String secondName;
	private String firstLastName;
	private String secondLastName;
	private String email;
	private String mobilePhone;
	private IdentificationTypeDTO identificationType;
	private CityDTO city;
	private boolean emailConfirmed;
	private boolean mobilePhoneConfirmed;
	
	public UserDTO() {
		setId(UUIDHelper.getUUIDHelper().getDefault());
		setIdentificationNumber(TextHelper.getDefault());
		setFirstName(TextHelper.getDefault());
		setSecondName(TextHelper.getDefault());
		setFirstLastName(TextHelper.getDefault());
		setSecondLastName(TextHelper.getDefault());
		setEmail(TextHelper.getDefault());
		setMobilePhone(TextHelper.getDefault());
		setIdentificationType(new IdentificationTypeDTO());
		setCity(new CityDTO());
		setEmailConfirmed(false);
		setMobilePhoneConfirmed(false);
		
	}

	public UserDTO(final UUID id) {
		setId(id);
		setIdentificationNumber(TextHelper.getDefault());
		setFirstName(TextHelper.getDefault());
		setSecondName(TextHelper.getDefault());
		setFirstLastName(TextHelper.getDefault());
		setSecondLastName(TextHelper.getDefault());
		setEmail(TextHelper.getDefault());
		setMobilePhone(TextHelper.getDefault());
		setIdentificationType(new IdentificationTypeDTO());
		setCity(new CityDTO());
		setEmailConfirmed(false);
		setMobilePhoneConfirmed(false);
	}
	
	public UserDTO(final UUID id, final String identificationNumber, final String firstName, final String secondName, final String firstLastName, final String secondLastName, final String email, final String mobilePhone,
			final IdentificationTypeDTO identificationType, final CityDTO city) {
		setId(id);
		setIdentificationNumber(identificationNumber);
		setFirstName(firstName);
		setSecondName(secondName);
		setFirstLastName(firstLastName);
		setSecondLastName(secondLastName);
		setEmail(email);
		setMobilePhone(mobilePhone);
		setIdentificationType(identificationType);
		setCity(city);
		setEmailConfirmed(false);
		setMobilePhoneConfirmed(false);

	}
	
	public UUID getId() {
		return id;
	}
	
	public void setId(final UUID id) {
		this.id = UUIDHelper.getUUIDHelper().getDefault(id);
	}
		
	public String getIdentificationNumber() {
		return identificationNumber;
	}
	
	public void setIdentificationNumber(final String identifificationNumber) {
		this.identificationNumber = TextHelper.getDefaultWithTrim(identificationNumber);
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
	
	public String getFirstLastName() {
		return firstLastName;
	}
	
	public void setFirstLastName(final String firstLastName) {
		this.firstLastName = TextHelper.getDefaultWithTrim(firstLastName);
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
	
	public String getMobilePhone() {
		return mobilePhone;
	}
	
	public void setMobilePhone(final String mobilePhone) {
		this.mobilePhone = TextHelper.getDefaultWithTrim(mobilePhone);
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
	
	public boolean isMobilePhoneConfirmed() {
		return mobilePhoneConfirmed;
	}
	
	public void setMobilePhoneConfirmed(boolean mobilePhoneConfirmed) {
		this.mobilePhoneConfirmed = mobilePhoneConfirmed;
	}
	
}