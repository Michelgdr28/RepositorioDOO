package co.edu.uco.nose.controller;

import co.edu.uco.nose.business.facade.impl.IdentificationTypeFacadeImpl;
import co.edu.uco.nose.controller.dto.Response;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.dto.IdentificationTypeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/identification-types")
public class IdentificationTypeController {

    @GetMapping
    public ResponseEntity<Response<IdentificationTypeDTO>> findAllIdentificationTypes() {

        Response<IdentificationTypeDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.OK;

        try {
            var facade = new IdentificationTypeFacadeImpl();

            responseObjectData.setData(facade.findAllIdentificationTypes());
            responseObjectData.addMessages(MessagesEnum.FIND_ALL_IDENTIFICATION_TYPES_SUCCESS.getContent());

        } catch (final NoseException exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessages(exception.getUserMessage());
            responseStatusCode = HttpStatus.BAD_REQUEST;
            exception.printStackTrace();

        } catch (final Exception exception) {
            var userMessage = MessagesEnum.FIND_ALL_IDENTIFICATION_TYPES_UNEXPECTED_ERROR.getContent();
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessages(userMessage);
            responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            exception.printStackTrace();
        }

        return new ResponseEntity<Response<IdentificationTypeDTO>>(responseObjectData, responseStatusCode);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<IdentificationTypeDTO>> findSpecificIdentificationType(@PathVariable UUID id) {

        Response<IdentificationTypeDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.OK;

        try {
            var facade = new IdentificationTypeFacadeImpl();

            responseObjectData.setData(List.of(facade.findSpecificIdentificationType(id)));
            responseObjectData.addMessages(MessagesEnum.FIND_SPECIFIC_IDENTIFICATION_TYPE_SUCCESS.getContent());

        } catch (final NoseException exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessages(exception.getUserMessage());
            responseStatusCode = HttpStatus.NOT_FOUND;
            exception.printStackTrace();

        } catch (final Exception exception) {
            var userMessage = MessagesEnum.FIND_SPECIFIC_IDENTIFICATION_TYPE_UNEXPECTED_ERROR.getContent();
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessages(userMessage);
            responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            exception.printStackTrace();
        }

        return new ResponseEntity<Response<IdentificationTypeDTO>>(responseObjectData, responseStatusCode);
    }

    @GetMapping("/filter")
    public ResponseEntity<Response<IdentificationTypeDTO>> findIdentificationTypesByFilter(
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String name
    ) {
        Response<IdentificationTypeDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.OK;

        try {
            var facade = new IdentificationTypeFacadeImpl();

            IdentificationTypeDTO filter = new IdentificationTypeDTO();
            filter.setId(id);
            filter.setName(name);

            responseObjectData.setData(facade.findIdentificationTypesByFilter(filter));
            responseObjectData.addMessages(MessagesEnum.FIND_IDENTIFICATION_TYPES_BY_FILTER_SUCCESS.getContent());

        } catch (final NoseException exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessages(exception.getUserMessage());
            responseStatusCode = HttpStatus.BAD_REQUEST;
            exception.printStackTrace();

        } catch (final Exception exception) {
            var userMessage = MessagesEnum.FIND_IDENTIFICATION_TYPES_BY_FILTER_UNEXPECTED_ERROR.getContent();
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessages(userMessage);
            responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            exception.printStackTrace();
        }

        return new ResponseEntity<Response<IdentificationTypeDTO>>(responseObjectData, responseStatusCode);
    }
}

