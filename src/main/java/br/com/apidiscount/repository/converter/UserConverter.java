package br.com.apidiscount.repository.converter;

import br.com.apidiscount.http.resource.UserResource;
import br.com.apidiscount.repository.entity.UserEntity;

public interface UserConverter {

    UserResource entityToResource(UserEntity entity);

    UserEntity resourceToEntity(UserResource resource);

}
