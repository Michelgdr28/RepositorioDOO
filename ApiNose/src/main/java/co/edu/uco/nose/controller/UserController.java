package co.edu.uco.nose.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.nose.business.facade.impl.UserFacadeImpl;
import co.edu.uco.nose.controller.dto.Response;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.dto.UserDTO;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@GetMapping("/dummy")
	public UserDTO getUserDtoDummy() {
		return new UserDTO();
	}
	
	@GetMapping
	public ResponseEntity<Response<UserDTO>> findAllUsers() {
		Response<UserDTO>responseObjectData=Response.createSuccededResponse();
		HttpStatusCode responseStatusCode = HttpStatus.OK;
		
		try {
		var facade = new UserFacadeImpl();
		responseObjectData.setData(facade.findAllUsers());
		responseObjectData.addMessages("All users filtered succesfully");
	
		}catch(final NoseException exception) {
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessages(exception.getUserMessage());
			responseStatusCode = HttpStatus.BAD_REQUEST;
			exception.printStackTrace();
		}catch (Exception exception) {
			var userMessage = "Unexpected Error";
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessages(userMessage);
			responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			exception.printStackTrace();
		}	
		return new ResponseEntity<>(responseObjectData,responseStatusCode);
	}
	
	@PostMapping	
	public String registerNewUserInformation(@RequestBody UserDTO user) {
		return "POST : User registered";
	}
	@PutMapping ("/{id}")
	public String updateUserInformation(@PathVariable UUID id,@RequestBody UserDTO user) {
		return "PUT : User updated";
	
	}
	@DeleteMapping("/{id}")
	public String dropUserInformation(@PathVariable UUID id) {
		return "DELETE : User deleted";
	}
}
