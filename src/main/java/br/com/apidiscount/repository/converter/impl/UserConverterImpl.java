package br.com.apidiscount.repository.converter.impl;

import br.com.apidiscount.http.resource.UserResource;
import br.com.apidiscount.repository.converter.UserConverter;
import br.com.apidiscount.repository.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverterImpl implements UserConverter {

    private final ModelMapper mapper;

    @Override
    public UserResource entityToResource(UserEntity entity) {
        UserResource resource = new UserResource();
        if(entity != null)
            resource = mapper.map(entity, UserResource.class);
        return resource;
    }

    @Override
    public UserEntity resourceToEntity(UserResource resource) {
        UserEntity entity = new UserEntity();
        if(resource != null)
            entity = mapper.map(resource, UserEntity.class);
        return entity;
    }

}
