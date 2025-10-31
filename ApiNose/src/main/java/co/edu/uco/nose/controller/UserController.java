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
			var userMessage = "Unexpected error";
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessages(userMessage);
			responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			exception.printStackTrace();
		}

		return new ResponseEntity<Response<UserDTO>>(responseObjectData, responseStatusCode);
	}
	@GetMapping("/filter")
	public ResponseEntity<Response<UserDTO>> findUsersByFilter(
			@RequestParam(required = false) UUID id,
            @RequestParam(required = false) String identificationNumber,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String mobilePhone,
            @RequestParam(required = false) UUID identificationTypeId,
            @RequestParam(required = false) UUID CityId
       ){

		Response<UserDTO> responseObjectData = Response.createSuccededResponse();
		HttpStatusCode responseStatusCode = HttpStatus.OK;

		try {
			var facade = new UserFacadeImpl();
			UserDTO filter = new UserDTO();
            filter.setId(id);
            filter.setIdentificationNumber(identificationNumber);
            filter.setEmail(email);
            filter.setFirstName(firstName);
            filter.setMobilePhone(mobilePhone);

            if (identificationTypeId != null) {
                IdentificationTypeDTO idTypeFilter = new IdentificationTypeDTO();
                idTypeFilter.setId(identificationTypeId);
                filter.setIdentificationType(idTypeFilter);
            }

            if (CityId != null) {
                CityDTO cityFilter = new CityDTO();
                cityFilter.setId(CityId);
                filter.setCity(cityFilter);
            }

            responseObjectData.setData(facade.findUsersByFilter(filter));
            responseObjectData.addMessages("Users filtered succesfully");

		} catch (final NoseException exception) {
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessages(exception.getUserMessage());
			responseStatusCode = HttpStatus.BAD_REQUEST;
			exception.printStackTrace();
		} catch (final Exception exception) {
			var userMessage = "Unexpected error";
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessages(userMessage);
			responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			exception.printStackTrace();
		}

		return new ResponseEntity<>(responseObjectData, responseStatusCode);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Response<UserDTO>> findSpecificUser(@PathVariable UUID id) {
		Response<UserDTO> responseObjectData = Response.createSuccededResponse();
		HttpStatusCode responseStatusCode = HttpStatus.OK;

		try {
			var facade = new UserFacadeImpl();
			
			responseObjectData.setData(List.of(facade.findSpecificUser(id)));
            responseObjectData.addMessages("User found successfully!");

		} catch (final NoseException exception) {
			responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessages(exception.getUserMessage());
            responseStatusCode = HttpStatus.NOT_FOUND;
            exception.printStackTrace();
		} catch (final Exception exception) {
			var userMessage = "Unexpected error While searching for User";
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessages(userMessage);
            responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            exception.printStackTrace();
		}

		return new ResponseEntity<>(responseObjectData, responseStatusCode);
	}

	@PostMapping
	public ResponseEntity<Response<UserDTO>> registerNewUserInformation(@RequestBody UserDTO user) {
		Response<UserDTO> responseObjectData = Response.createSuccededResponse();
		HttpStatusCode responseStatusCode = HttpStatus.CREATED;

		try {
			var facade = new UserFacadeImpl();
			facade.registerNewUserInformation(user);
			responseObjectData.addMessages("User successfully registered!");
		} catch (final NoseException exception) {
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessages(exception.getUserMessage());
			responseStatusCode = HttpStatus.BAD_REQUEST;
			exception.printStackTrace();
		} catch (final Exception exception) {
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessages("Unexpected error while registering user");
			responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			exception.printStackTrace();
		}

		return new ResponseEntity<>(responseObjectData, responseStatusCode);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Response<UserDTO>> updateUserInformation(@PathVariable UUID id, @RequestBody UserDTO user) {
		Response<UserDTO> responseObjectData = Response.createSuccededResponse();
		HttpStatusCode responseStatusCode = HttpStatus.OK;

		try {
			var facade = new UserFacadeImpl();
			facade.updateUserInformation(id, user);
			responseObjectData.addMessages("User updated successfully!");
		} catch (final NoseException exception) {
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessages(exception.getUserMessage());
			responseStatusCode = HttpStatus.BAD_REQUEST;
			exception.printStackTrace();
		} catch (final Exception exception) {
			var userMessage =("Unexpected error while updating user");
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessages(userMessage);
			responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			exception.printStackTrace();
		}

		return new ResponseEntity<>(responseObjectData, responseStatusCode);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<UserDTO>> dropUserInformation(@PathVariable UUID id) {
		Response<UserDTO> responseObjectData = Response.createSuccededResponse();
		HttpStatusCode responseStatusCode = HttpStatus.OK;

		try {
			var facade = new UserFacadeImpl();
			facade.dropUserInformation(id);
			responseObjectData.addMessages("User deleted successfully!");
		} catch (final NoseException exception) {
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessages(exception.getUserMessage());
			responseStatusCode = HttpStatus.BAD_REQUEST;
			exception.printStackTrace();
		} catch (final Exception exception) {
            var userMessage = ("Unexpected error while deleting user");
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessages(userMessage);
			responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			exception.printStackTrace();
		}

		return new ResponseEntity<>(responseObjectData, responseStatusCode);
	}
}


