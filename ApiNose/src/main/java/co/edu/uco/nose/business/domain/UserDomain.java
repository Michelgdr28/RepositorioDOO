package co.edu.uco.nose.business.domain;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helper.TextHelper;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;

public final class UserDomain extends Domain {

    private IdentificationTypeDomain identificationType;
    private String identificationNumber;
    private String firstName;
    private String secondName;
    private String firstLastName;
    private String secondLastName;
    private CityDomain residenceCity;
    private String email;
    private String mobilePhone;
    private boolean emailConfirmed;
    private boolean mobilePhoneConfirmed;

    public UserDomain() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setIdentificationType(new IdentificationTypeDomain());
        setIdentificationNumber(TextHelper.getDefault());
        setFirstName(TextHelper.getDefault());
        setSecondName(TextHelper.getDefault());
        setFirstLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setResidenceCity(new CityDomain());
        setEmail(TextHelper.getDefault());
        setMobilePhone(TextHelper.getDefault());
        setEmailConfirmed(false);
        setMobilePhoneConfirmed(false);
    }

    public UserDomain(final UUID id) {
        super(id);
        setIdentificationType(new IdentificationTypeDomain());
        setIdentificationNumber(TextHelper.getDefault());
        setFirstName(TextHelper.getDefault());
        setSecondName(TextHelper.getDefault());
        setFirstLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setResidenceCity(new CityDomain());
        setEmail(TextHelper.getDefault());
        setMobilePhone(TextHelper.getDefault());
        setEmailConfirmed(false);
        setMobilePhoneConfirmed(false);
    }

    public UserDomain(final UUID id,
                      final IdentificationTypeDomain identificationType,
                      final String identificationNumber,
                      final String firstName,
                      final String secondName,
                      final String firstLastName,
                      final String secondLastName,
                      final CityDomain residenceCity,
                      final String email,
                      final String mobilePhone,
                      final boolean emailConfirmed,
                      final boolean mobilePhoneConfirmed) {
        super(id);
        setIdentificationType(identificationType);
        setIdentificationNumber(identificationNumber);
        setFirstName(firstName);
        setSecondName(secondName);
        setFirstLastName(firstLastName);
        setSecondLastName(secondLastName);
        setResidenceCity(residenceCity);
        setEmail(email);
        setMobilePhone(mobilePhone);
        setEmailConfirmed(emailConfirmed);
        setMobilePhoneConfirmed(mobilePhoneConfirmed);
    }

    public IdentificationTypeDomain getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(final IdentificationTypeDomain identificationType) {
        this.identificationType = (identificationType == null) ? new IdentificationTypeDomain() : identificationType;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(final String identificationNumber) {
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

    public CityDomain getResidenceCity() {
        return residenceCity;
    }

    public void setResidenceCity(final CityDomain residenceCity) {
        this.residenceCity = (residenceCity == null) ? new CityDomain() : residenceCity;
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

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(final boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public boolean isMobilePhoneConfirmed() {
        return mobilePhoneConfirmed;
    }

    public void setMobilePhoneConfirmed(final boolean mobilePhoneConfirmed) {
        this.mobilePhoneConfirmed = mobilePhoneConfirmed;
    }
}

