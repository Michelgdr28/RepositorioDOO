package co.edu.uco.nose.business.business.impl;

import co.edu.uco.nose.business.assembler.entity.impl.IdentificationTypeEntityAssembler;
import co.edu.uco.nose.business.business.IdentificationTypeBusiness;
import co.edu.uco.nose.business.domain.IdentificationTypeDomain;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.IdentificationTypeEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class IdentificationTypeBusinessImpl implements IdentificationTypeBusiness {

    private final DAOFactory daoFactory;

    public IdentificationTypeBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<IdentificationTypeDomain> findAllIdentificationTypes() {
        try {

            List<IdentificationTypeEntity> entityList = daoFactory.getIdentificationTypeDAO().findAll();

            List<IdentificationTypeDomain> domainList = new ArrayList<>();

            for (IdentificationTypeEntity entity : entityList) {
                domainList.add(IdentificationTypeEntityAssembler.getIdTypeEntityAssembler().toDomain(entity));
            }

            return domainList;

        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw NoseException.create(
                exception,
                "Error inesperado al consultar todos los tipos de identificación.",
                "Capa de Negocio"
            );
        }
    }

    @Override
    public List<IdentificationTypeDomain> findIdentificationTypesByFilter(final IdentificationTypeDomain identificationTypeFilters) {
        try {

            IdentificationTypeEntity filterEntity = IdentificationTypeEntityAssembler
                    .getIdTypeEntityAssembler()
                    .toEntity(identificationTypeFilters);

            List<IdentificationTypeEntity> entityList = daoFactory.getIdentificationTypeDAO().findByfilter(filterEntity);

            List<IdentificationTypeDomain> domainList = new ArrayList<>();

            for (IdentificationTypeEntity entity : entityList) {
                domainList.add(IdentificationTypeEntityAssembler.getIdTypeEntityAssembler().toDomain(entity));
            }

            return domainList;

        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw NoseException.create(
                exception,
                "Error inesperado al consultar los tipos de identificación por filtro.",
                "Capa de Negocio"
            );
        }
    }

    @Override
    public IdentificationTypeDomain findSpecificIdentificationType(final UUID id) {
        try {

            IdentificationTypeEntity entity = daoFactory.getIdentificationTypeDAO().findById(id);

            if (entity == null) {
                throw NoseException.create(
                    "El tipo de identificación con el ID " + id + " no fue encontrado.",
                    "Capa de Negocio"
                );
            }

            return IdentificationTypeEntityAssembler.getIdTypeEntityAssembler().toDomain(entity);

        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw NoseException.create(
                exception,
                "Error inesperado al buscar un tipo de identificación específico.",
                "Capa de Negocio"
            );
        }
    }
}

