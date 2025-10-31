package co.edu.uco.nose.controller;

import co.edu.uco.nose.business.facade.impl.DepartmentFacadeImpl;
import co.edu.uco.nose.controller.dto.Response;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.dto.DepartmentDTO;
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
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    @GetMapping
    public ResponseEntity<Response<DepartmentDTO>> findAllDepartments() {

        Response<DepartmentDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.OK;

        try {

            var facade = new DepartmentFacadeImpl();

            responseObjectData.setData(facade.findAllDepartments());
            responseObjectData.addMessages("All Departments filtered successfully");

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

        return new ResponseEntity<Response<DepartmentDTO>>(responseObjectData, responseStatusCode);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response<DepartmentDTO>> findSpecificDepartment (@PathVariable UUID id){

        Response<DepartmentDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.OK;

        try {

            var facade = new DepartmentFacadeImpl();

            responseObjectData.setData(List.of(facade.findSpecificDepartment(id)));
            responseObjectData.addMessages("Find Specific Department successfully");

        } catch (final NoseException exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessages(exception.getUserMessage());
            responseStatusCode = HttpStatus.NOT_FOUND;
            exception.printStackTrace();

        } catch (final Exception exception) {
            var userMessage = "Unexpected error";
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessages(userMessage);
            responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            exception.printStackTrace();
        }
        return new ResponseEntity<Response<DepartmentDTO>>(responseObjectData, responseStatusCode);
    }

    @GetMapping("/filter")
    public ResponseEntity<Response<DepartmentDTO>> findDepartmentsByFilter (
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String name
    ) {
        Response<DepartmentDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.OK;

        try {
            var facade = new DepartmentFacadeImpl();

            DepartmentDTO filter = new DepartmentDTO();
            filter.setId(id);
            filter.setName(name);

            responseObjectData.setData(facade.findDepartmentsByFilter(filter));
            responseObjectData.addMessages("Departments filtered succesfully");
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
        return new ResponseEntity<Response<DepartmentDTO>>(responseObjectData, responseStatusCode);
    }
}

