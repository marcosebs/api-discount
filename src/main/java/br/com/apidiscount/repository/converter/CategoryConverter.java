package br.com.apidiscount.repository.converter;

import br.com.apidiscount.http.resource.CategoryResource;
import br.com.apidiscount.repository.entity.CategoryEntity;

public interface CategoryConverter {

    CategoryResource entityToResource(CategoryEntity entity);

    CategoryEntity resouceToEntity(CategoryResource resource);

}
