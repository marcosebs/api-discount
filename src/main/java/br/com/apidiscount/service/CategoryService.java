package br.com.apidiscount.service;

import br.com.apidiscount.http.resource.CategoryResource;

import java.util.List;

public interface CategoryService {

    CategoryResource create(CategoryResource request);

    List<CategoryResource> list();

    void delete(Long id);

}
