package br.com.apidiscount.repository.converter.impl;

import br.com.apidiscount.http.resource.CategoryResource;
import br.com.apidiscount.repository.converter.CategoryConverter;
import br.com.apidiscount.repository.entity.CategoryEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryConverterImpl implements CategoryConverter {

    private final ModelMapper mapper;

    @Override
    public CategoryResource entityToResource(CategoryEntity entity) {
        CategoryResource resource = new CategoryResource();
        if(entity != null)
           resource = mapper.map(entity, CategoryResource.class);
        return resource;
    }

    @Override
    public CategoryEntity resouceToEntity(CategoryResource resource) {
        CategoryEntity entity = new CategoryEntity();
        if(resource != null)
            entity = mapper.map(resource, CategoryEntity.class);
        return entity;
    }
}
