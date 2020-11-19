package br.com.apidiscount.repository.impl;

import br.com.apidiscount.http.resource.UserResource;
import br.com.apidiscount.infra.exception.ApiDiscountException;
import br.com.apidiscount.infra.exception.enumerator.ErrorMappingEnum;
import br.com.apidiscount.repository.UserRepository;
import br.com.apidiscount.repository.converter.UserConverter;
import br.com.apidiscount.repository.entity.UserEntity;
import br.com.apidiscount.repository.jpa.UserRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserRepositoryJpa jpa;
    private final UserConverter converter;

    @Override
    public UserResource findById(Long id) {
        UserEntity entity = validateById(id);
        return converter.entityToResource(entity);
    }

    @Override
    public UserResource create(UserResource request) {
        UserEntity entity = converter.resourceToEntity(request);
        return converter.entityToResource(jpa.save(entity));
    }

    @Override
    public UserResource edit(UserResource request) {
        validateById(request.getId());
        return converter.entityToResource(jpa.save(converter.resourceToEntity(request)));
    }

    @Override
    public void delete(Long id) {
        validateById(id);
        jpa.deleteById(id);
    }

    private UserEntity validateById(Long id) {
        Optional<UserEntity> userEntity = jpa.findById(id);
        if (!userEntity.isPresent())
            throw new ApiDiscountException(ErrorMappingEnum.USER_NOT_FOUND, HttpStatus.BAD_REQUEST);
        return userEntity.get();
    }

}
