package co.edu.uco.nose.test;

import co.edu.uco.nose.business.facade.impl.UserFacadeImpl;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helper.UUIDHelper;
import co.edu.uco.nose.dto.CityDTO;
import co.edu.uco.nose.dto.CountryDTO;
import co.edu.uco.nose.dto.IdentificationTypeDTO;
import co.edu.uco.nose.dto.DepartmentDTO;
import co.edu.uco.nose.dto.UserDTO;

public class TestUserRegistration {
	public static void main(String[] args) {
		
		try {
			var identificationType = new IdentificationTypeDTO();
			identificationType.setId(UUIDHelper.getUUIDHelper().getFromString("86ef14dd-4160-4cd5-9339-56366ecfb14a"));
			identificationType.setName("Cédula de ciudadanía");
			
			var country = new CountryDTO();
			country.setId(UUIDHelper.getUUIDHelper().getFromString("86ef14dd-4160-4cd5-9339-56366ecfb1bd"));
			country.setName("Colombia");
			
			var department = new DepartmentDTO();
			department.setId(UUIDHelper.getUUIDHelper().getFromString("86ef14dd-4160-4cd5-9339-56366ecfb1bc"));
			department.setName("Antioquia");
			department.setCountry(country);
			
			var city = new CityDTO();
			city.setId(UUIDHelper.getUUIDHelper().getFromString("86ef14dd-4160-4cd5-9339-56366ecfb1b5"));
			city.setName("Rionegro");
			city.setDepartment(department);
			
			var user = new UserDTO();
			user.setIdentificationNumber("1000442701");
			user.setFirstName("Matias");
			user.setSecondName("Alejandro");
			user.setFirstLastName("Mora");
			user.setSecondLastName("De los Rios");
			user.setEmail("mati.2509");
			user.setMobilePhone("3226271355");
			user.setIdentificationType(identificationType);
			user.setCity(city);
			user.setEmailConfirmed(false);
			user.setMobilePhoneConfirmed(false);
						
			// Colocar parametros, menos el id
			
			var facade = new UserFacadeImpl();
			facade.registerNewUserInformation(user);
			
			
			System.out.println("Gane el semestre!!");
		} catch (NoseException e) {
			System.err.println(e.getUserMessage());
			System.err.println(e.getTechnicalMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
