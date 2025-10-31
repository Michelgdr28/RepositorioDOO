package co.edu.uco.nose.business.business.impl;

import co.edu.uco.nose.business.assembler.entity.impl.DepartmentEntityAssembler;
import co.edu.uco.nose.business.business.DepartmentBusiness;
import co.edu.uco.nose.business.domain.DepartmentDomain;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.DepartmentEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class DepartmentBusinessImpl implements DepartmentBusiness {

    private final DAOFactory daoFactory;

    public DepartmentBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<DepartmentDomain> findAllDepartments() {
        try {

            List<DepartmentEntity> entityList = daoFactory.getDepartmentDAO().findAll();

            List<DepartmentDomain> domainList = new ArrayList<>();

            for (DepartmentEntity entity : entityList) {
                domainList.add(DepartmentEntityAssembler.getDepartmentEntityAssembler().toDomain(entity));
            }

            return domainList;

        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw NoseException.create(
                exception,
                "Error inesperado al consultar todos los departamentos.",
                "Capa de Negocio"
            );
        }
    }

    @Override
    public List<DepartmentDomain> findDepartmentsByFilter(final DepartmentDomain departmentFilters) {
        try {

            DepartmentEntity filterEntity = DepartmentEntityAssembler
                    .getDepartmentEntityAssembler()
                    .toEntity(departmentFilters);

            List<DepartmentEntity> entityList = daoFactory.getDepartmentDAO().findByfilter(filterEntity);

            List<DepartmentDomain> domainList = new ArrayList<>();

            for (DepartmentEntity entity : entityList) {
                domainList.add(DepartmentEntityAssembler.getDepartmentEntityAssembler().toDomain(entity));
            }

            return domainList;

        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw NoseException.create(
                exception,
                "Error inesperado al consultar los departamentos por filtro.",
                "Capa de Negocio"
            );
        }
    }

    @Override
    public DepartmentDomain findSpecificDepartment(final UUID id) {
        try {

            DepartmentEntity entity = daoFactory.getDepartmentDAO().findById(id);

            if (entity == null) {
                throw NoseException.create(
                    "El departamento con el ID " + id + " no fue encontrado.",
                    "Capa de Negocio"
                );
            }

            return DepartmentEntityAssembler.getDepartmentEntityAssembler().toDomain(entity);

        } catch (final NoseException exception) {
            throw exception;
        } catch (final Exception exception) {
            throw NoseException.create(
                exception,
                "Error inesperado al buscar un departamento específico.",
                "Capa de Negocio"
            );
        }
    }
}

