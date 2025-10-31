package co.edu.uco.nose.business.facade.impl;

import co.edu.uco.nose.business.assembler.dto.impl.DepartmentDTOAssembler;
import co.edu.uco.nose.business.business.impl.DepartmentBusinessImpl;
import co.edu.uco.nose.business.domain.DepartmentDomain;
import co.edu.uco.nose.business.facade.DepartmentFacade;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.dto.DepartmentDTO;

import java.util.List;
import java.util.UUID;

public final class DepartmentFacadeImpl implements DepartmentFacade {

    @Override
    public List<DepartmentDTO> findAllDepartments() {
        var daoFactory = DAOFactory.getFactory();
        var business = new DepartmentBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();
            List<DepartmentDomain> domainList = business.findAllDepartments();
            return DepartmentDTOAssembler.getDepartmentDTOAssembler().toDTO(domainList);

        } catch (NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnum.FIND_ALL_DEPARTMENTS_UNEXPECTED_ERROR.getContent();
            var technicalMessage = MessagesEnum.FIND_ALL_DEPARTMENTS_UNEXPECTED_ERROR.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<DepartmentDTO> findDepartmentsByFilter(final DepartmentDTO departmentFilters) {
        var daoFactory = DAOFactory.getFactory();
        var business = new DepartmentBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();
            var filterDomain = DepartmentDTOAssembler.getDepartmentDTOAssembler().toDomain(departmentFilters);
            List<DepartmentDomain> domainList = business.findDepartmentsByFilter(filterDomain);
            return DepartmentDTOAssembler.getDepartmentDTOAssembler().toDTO(domainList);

        } catch (NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnum.FIND_DEPARTMENTS_BY_FILTER_UNEXPECTED_ERROR.getContent();
            var technicalMessage = MessagesEnum.FIND_DEPARTMENTS_BY_FILTER_UNEXPECTED_ERROR.getContent()
                    + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public DepartmentDTO findSpecificDepartment(final UUID id) {
        var daoFactory = DAOFactory.getFactory();
        var business = new DepartmentBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();
            DepartmentDomain domain = business.findSpecificDepartment(id);
            return DepartmentDTOAssembler.getDepartmentDTOAssembler().toDTO(domain);

        } catch (NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnum.FIND_SPECIFIC_DEPARTMENT_UNEXPECTED_ERROR.getContent();
            var technicalMessage = MessagesEnum.FIND_SPECIFIC_DEPARTMENT_UNEXPECTED_ERROR.getContent()
                    + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }
}
