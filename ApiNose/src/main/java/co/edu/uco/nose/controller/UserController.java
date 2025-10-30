package co.edu.uco.nose.controller;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
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
		responseObjectData.addMessages(MessagesEnum.SUCCESS_USER_LISTED.getContent());
	
		}catch(final NoseException exception) {
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessages(exception.getUserMessage());
			responseStatusCode = HttpStatus.BAD_REQUEST;
			exception.printStackTrace();
		}catch (Exception exception) {
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessages(MessagesEnum.GENERAL_UNEXPECTED_ERROR.getContent());
			responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			exception.printStackTrace();
		}	
		return new ResponseEntity<>(responseObjectData,responseStatusCode);
	}
	@GetMapping("/{id}")
    public ResponseEntity<Response<UserDTO>> findUserByID(@PathVariable UUID id) {

        Response<UserDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.OK;

        try {
            var facade = new UserFacadeImpl();
            var user = facade.findSpecificUser(id);

            responseObjectData.setData(new ArrayList<>());
            responseObjectData.getData().add(user);
            responseObjectData.addMessages(MessagesEnum.SUCCESS_USER_FOUND.getContent());

        } catch (final NoseException exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessages(exception.getUserMessage());
            responseStatusCode = HttpStatus.BAD_REQUEST;
            exception.printStackTrace();
        } catch (final Exception exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessages(MessagesEnum.GENERAL_UNEXPECTED_ERROR.getContent());
            responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            exception.printStackTrace();
        }

        return new ResponseEntity<>(responseObjectData, responseStatusCode);
    }
	@GetMapping("/filter")
	public ResponseEntity<Response<UserDTO>> findUsersByFilters(
			@RequestParam(required = false) UUID id,
	        @RequestParam(required = false) String identificationNumber,
	        @RequestParam(required = false) String firstName,
	        @RequestParam(required = false) String secondName,
	        @RequestParam(required = false) String firstLastName,
	        @RequestParam(required = false) String secondLastName,
	        @RequestParam(required = false) String email,
	        @RequestParam(required = false) String mobilePhone,
	        @RequestParam(required = false) UUID identificationTypeId,
	        @RequestParam(required = false) UUID cityId,
	        @RequestParam(required = false, defaultValue = "false") boolean emailConfirmed,
	        @RequestParam(required = false, defaultValue = "false") boolean mobilePhoneConfirmed
	) {
	    Response<UserDTO> responseObjectData = Response.createSuccededResponse();
	    HttpStatusCode responseStatusCode = HttpStatus.OK;

	    try {
	        var facade = new UserFacadeImpl();
	
	        var identificationTypeDTO = new IdentificationTypeDTO();
	        identificationTypeDTO.setId(identificationTypeId);

	        var cityDTO = new CityDTO();
	        cityDTO.setId(cityId);

	        var userDto = new UserDTO(
	                id == null ? UUID.fromString("00000000-0000-0000-0000-000000000000") : id,
	                identificationNumber == null ? "" : identificationNumber,
	                firstName == null ? "" : firstName,
	                secondName == null ? "" : secondName,
	                firstLastName == null ? "" : firstLastName,
	                secondLastName == null ? "" : secondLastName,
	                email == null ? "" : email,
	                mobilePhone == null ? "" : mobilePhone,
	                identificationTypeDTO,
	                cityDTO,
	                emailConfirmed,
	                mobilePhoneConfirmed
	        );

	        var result = facade.findUsersByFilter(userDto);
	        responseObjectData.setData(result);
	        responseObjectData.addMessages(MessagesEnum.SUCCESS_USER_LISTED.getContent());

	    } catch (NoseException exception) {
	        responseObjectData = Response.createFailedResponse();
	        responseObjectData.addMessages(exception.getUserMessage());
	        responseStatusCode = HttpStatus.BAD_REQUEST;
	        exception.printStackTrace();
	    } catch (Exception exception) {
	        var userMessage = MessagesEnum.GENERAL_UNEXPECTED_ERROR.getContent();
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

            responseObjectData.addMessages(MessagesEnum.SUCCESS_USER_CREATED.getContent());
        } catch (final NoseException exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessages(exception.getUserMessage());
            responseStatusCode = HttpStatus.BAD_REQUEST;
            exception.printStackTrace();
        } catch (final Exception exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessages(MessagesEnum.GENERAL_UNEXPECTED_ERROR.getContent());
            responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            exception.printStackTrace();
        }

        return new ResponseEntity<>(responseObjectData, responseStatusCode);
    }

	@PutMapping ("/{id}")
	public ResponseEntity<Response<UserDTO>> updateUserInformation(@PathVariable final UUID id, @RequestBody final UserDTO user) {
        Response<UserDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.OK;

        try {
            var facade = new UserFacadeImpl();
            facade.updateUserInformation(id, user);

            responseObjectData.addMessages(MessagesEnum.SUCCESS_USER_UPDATED.getContent());
        } catch (final NoseException exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessages(exception.getUserMessage());
            responseStatusCode = HttpStatus.BAD_REQUEST;
            exception.printStackTrace();
        } catch (final Exception exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessages(MessagesEnum.GENERAL_UNEXPECTED_ERROR.getContent());
            responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            exception.printStackTrace();
        }

        return new ResponseEntity<>(responseObjectData, responseStatusCode);
    }
	@DeleteMapping("/{id}")
    public ResponseEntity<Response<UserDTO>> dropUserInformation(@PathVariable final UUID id) {
        Response<UserDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.NO_CONTENT;

        try {
            var facade = new UserFacadeImpl();
            facade.dropUserInformation(id);

            // no content — keep body empty but return a Response for consistency
            responseObjectData.addMessages(MessagesEnum.SUCCESS_USER_DELETED.getContent());
        } catch (final NoseException exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessages(exception.getUserMessage());
            responseStatusCode = HttpStatus.BAD_REQUEST;
            exception.printStackTrace();
        } catch (final Exception exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessages(MessagesEnum.GENERAL_UNEXPECTED_ERROR.getContent());
            responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            exception.printStackTrace();
        }

        return new ResponseEntity<>(responseObjectData, responseStatusCode);
    }
}
