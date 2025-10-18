package co.edu.uco.nose.test;

import co.edu.uco.nose.business.facade.impl.UserFacadeImpl;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.dto.UserDTO;

public class TestUserRegistration {
	public static void main(String[] args) {
		try {
			var user = new UserDTO();
			
			//colocar parametros menos el id
			
			var facade = new UserFacadeImpl();
			facade.registerNewUserInformation(user);
			
			System.out.println("Gane el semestre");
		}catch(NoseException exception) {
			System.err.println(exception.getUserMessage());
			System.err.println(exception.getTechnicalMessage());
			exception.printStackTrace();
	}catch(Exception exception) {
		exception.printStackTrace();
	}	
	}

}
