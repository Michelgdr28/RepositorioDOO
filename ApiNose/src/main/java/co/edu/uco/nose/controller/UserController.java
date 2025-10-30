package co.edu.uco.nose.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.nose.business.facade.impl.UserFacadeImpl;
import co.edu.uco.nose.controller.dto.Response;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.dto.CityDTO;
import co.edu.uco.nose.dto.IdentificationTypeDTO;
import co.edu.uco.nose.dto.UserDTO;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@GetMapping("/dummy")
	public UserDTO getUserDTODummy() {
		return new UserDTO();
	}

	@GetMapping
	public ResponseEntity<Response<UserDTO>> findAllUsers() {

		Response<UserDTO> responseObjectData = Response.createSuccededResponse();
		HttpStatusCode responseStatusCode = HttpStatus.OK;

		try {
			var facade = new UserFacadeImpl();
			responseObjectData.setData(facade.findAllUsers());
			responseObjectData.addMessages(MessagesEnum.SUCCESS_USER_LISTED.getContent());
		} catch (final NoseException exception) {
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessages(exception.getUserMessage());
			responseStatusCode = HttpStatus.BAD_REQUEST;
			exception.printStackTrace();
		} catch (final Exception exception) {
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessages("Unexpected error");
			responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			exception.printStackTrace();
		}

		return new ResponseEntity<>(responseObjectData, responseStatusCode);
	}
	@GetMapping("/filter")
	public ResponseEntity<Response<UserDTO>> findByFilter(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String lastname,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String identificationNumber,
			@RequestParam(required = false) UUID identificationTypeId,
			@RequestParam(required = false) UUID cityId) {

		Response<UserDTO> responseObjectData = Response.createSuccededResponse();
		HttpStatusCode responseStatusCode = HttpStatus.OK;

		try {
			var filter = new UserDTO();
			filter.setFirstName(name);
			filter.setFirstLastName(lastname);
			filter.setEmail(email);
			filter.setIdentificationNumber(identificationNumber);

			if (identificationTypeId != null) {
				var identificationType = new IdentificationTypeDTO();
				identificationType.setId(identificationTypeId);
				filter.setIdentificationType(identificationType);
			}

			if (cityId != null) {
				var city = new CityDTO();
				city.setId(cityId);
				filter.setCity(city);
			}

			var facade = new UserFacadeImpl();
			List<UserDTO> filteredUsers = facade.findUsersByFilter(filter);
			responseObjectData.setData(filteredUsers);
			responseObjectData.addMessages("Users filtered successfully!");

		} catch (final NoseException exception) {
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessages(exception.getUserMessage());
			responseStatusCode = HttpStatus.BAD_REQUEST;
			exception.printStackTrace();
		} catch (final Exception exception) {
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessages("Unexpected error while filtering users");
			responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			exception.printStackTrace();
		}

		return new ResponseEntity<>(responseObjectData, responseStatusCode);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Response<UserDTO>> findUserById(@PathVariable UUID id) {
		Response<UserDTO> response = Response.createSuccededResponse();
		HttpStatusCode statusCode = HttpStatus.OK;

		try {
			var facade = new UserFacadeImpl();
			var user = facade.findSpecificUser(id);
			response.getData().add(user);
			response.addMessages("User found successfully!");
		} catch (final NoseException exception) {
			response = Response.createFailedResponse();
			response.addMessages(exception.getUserMessage());
			statusCode = HttpStatus.BAD_REQUEST;
			exception.printStackTrace();
		} catch (final Exception exception) {
			response = Response.createFailedResponse();
			response.addMessages("Unexpected error while searching for user");
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			exception.printStackTrace();
		}

		return new ResponseEntity<>(response, statusCode);
	}

	@PostMapping
	public ResponseEntity<Response<UserDTO>> registerNewUserInformation(@RequestBody UserDTO user) {
		Response<UserDTO> response = Response.createSuccededResponse();
		HttpStatusCode statusCode = HttpStatus.CREATED;

		try {
			var facade = new UserFacadeImpl();
			facade.registerNewUserInformation(user);
			response.addMessages("User successfully registered!");
		} catch (final NoseException exception) {
			response = Response.createFailedResponse();
			response.addMessages(exception.getUserMessage());
			statusCode = HttpStatus.BAD_REQUEST;
			exception.printStackTrace();
		} catch (final Exception exception) {
			response = Response.createFailedResponse();
			response.addMessages("Unexpected error while registering user");
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			exception.printStackTrace();
		}

		return new ResponseEntity<>(response, statusCode);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Response<UserDTO>> updateUserInformation(@PathVariable UUID id, @RequestBody UserDTO user) {
		Response<UserDTO> response = Response.createSuccededResponse();
		HttpStatusCode statusCode = HttpStatus.OK;

		try {
			var facade = new UserFacadeImpl();
			facade.updateUserInformation(id, user);
			response.addMessages("User updated successfully!");
		} catch (final NoseException exception) {
			response = Response.createFailedResponse();
			response.addMessages(exception.getUserMessage());
			statusCode = HttpStatus.BAD_REQUEST;
			exception.printStackTrace();
		} catch (final Exception exception) {
			response = Response.createFailedResponse();
			response.addMessages("Unexpected error while updating user");
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			exception.printStackTrace();
		}

		return new ResponseEntity<>(response, statusCode);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<UserDTO>> dropUserInformation(@PathVariable UUID id) {
		Response<UserDTO> response = Response.createSuccededResponse();
		HttpStatusCode statusCode = HttpStatus.OK;

		try {
			var facade = new UserFacadeImpl();
			facade.dropUserInformation(id);
			response.addMessages("User deleted successfully!");
		} catch (final NoseException exception) {
			response = Response.createFailedResponse();
			response.addMessages(exception.getUserMessage());
			statusCode = HttpStatus.BAD_REQUEST;
			exception.printStackTrace();
		} catch (final Exception exception) {
			response = Response.createFailedResponse();
			response.addMessages("Unexpected error while deleting user");
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			exception.printStackTrace();
		}

		return new ResponseEntity<>(response, statusCode);
	}
}


