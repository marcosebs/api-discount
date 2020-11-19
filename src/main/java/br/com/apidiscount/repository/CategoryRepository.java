package br.com.apidiscount.repository;

import br.com.apidiscount.http.resource.CategoryResource;

import java.util.List;

public interface CategoryRepository {

    List<CategoryResource> findAll();

    CategoryResource create(CategoryResource request);

    void delete(Long id);

}
