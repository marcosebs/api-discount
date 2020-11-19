package br.com.apidiscount.repository;

import br.com.apidiscount.http.resource.UserResource;

public interface UserRepository {

    UserResource findById(Long id);

    UserResource create(UserResource request);

    UserResource edit(UserResource request);

    void delete(Long id);

}
