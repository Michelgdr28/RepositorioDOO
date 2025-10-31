package co.edu.uco.nose.business.facade.impl;

import co.edu.uco.nose.business.assembler.dto.impl.IdentificationTypeDTOAssembler;
import co.edu.uco.nose.business.business.impl.IdentificationTypeBusinessImpl;
import co.edu.uco.nose.business.domain.IdentificationTypeDomain;
import co.edu.uco.nose.business.facade.IdentificationTypeFacade;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.dto.IdentificationTypeDTO;

import java.util.List;
import java.util.UUID;

public final class IdentificationTypeFacadeImpl implements IdentificationTypeFacade {

    @Override
    public List<IdentificationTypeDTO> findAllIdentificationTypes() {
        var daoFactory = DAOFactory.getFactory();
        var business = new IdentificationTypeBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();
            List<IdentificationTypeDomain> domainList = business.findAllIdentificationTypes();
            return IdentificationTypeDTOAssembler.getIdentificationTypeDTOAssembler().toDTO(domainList);

        } catch (NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnum.FIND_ALL_IDENTIFICATION_TYPES_UNEXPECTED_ERROR.getContent();
            var technicalMessage = MessagesEnum.FIND_ALL_IDENTIFICATION_TYPES_UNEXPECTED_ERROR.getContent();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<IdentificationTypeDTO> findIdentificationTypesByFilter(final IdentificationTypeDTO identificationTypeFilters) {
        var daoFactory = DAOFactory.getFactory();
        var business = new IdentificationTypeBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();
            var filterDomain = IdentificationTypeDTOAssembler.getIdentificationTypeDTOAssembler().toDomain(identificationTypeFilters);
            List<IdentificationTypeDomain> domainList = business.findIdentificationTypesByFilter(filterDomain);
            return IdentificationTypeDTOAssembler.getIdentificationTypeDTOAssembler().toDTO(domainList);

        } catch (NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnum.FIND_IDENTIFICATION_TYPES_BY_FILTER_UNEXPECTED_ERROR.getContent();
            var technicalMessage = MessagesEnum.FIND_IDENTIFICATION_TYPES_BY_FILTER_UNEXPECTED_ERROR.getContent()
                    + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public IdentificationTypeDTO findSpecificIdentificationType(final UUID id) {
        var daoFactory = DAOFactory.getFactory();
        var business = new IdentificationTypeBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();
            IdentificationTypeDomain domain = business.findSpecificIdentificationType(id);
            return IdentificationTypeDTOAssembler.getIdentificationTypeDTOAssembler().toDTO(domain);

        } catch (NoseException exception) {
            daoFactory.rollbackTransaction();
            throw exception;

        } catch (final Exception exception) {
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnum.FIND_SPECIFIC_IDENTIFICATION_TYPE_UNEXPECTED_ERROR.getContent();
            var technicalMessage = MessagesEnum.FIND_SPECIFIC_IDENTIFICATION_TYPE_UNEXPECTED_ERROR.getContent()
                    + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }
}

