package br.com.apidiscount.repository.impl;

import br.com.apidiscount.http.resource.CategoryResource;
import br.com.apidiscount.infra.exception.ApiDiscountException;
import br.com.apidiscount.infra.exception.enumerator.ErrorMappingEnum;
import br.com.apidiscount.repository.CategoryRepository;
import br.com.apidiscount.repository.converter.CategoryConverter;
import br.com.apidiscount.repository.entity.CategoryEntity;
import br.com.apidiscount.repository.jpa.CategoryRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryRepositoryJpa jpa;
    private final CategoryConverter converter;

    @Override
    public List<CategoryResource> findAll() {
        return jpa.findAll()
                .stream()
                .map(converter::entityToResource)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResource create(CategoryResource request) {
        return converter.entityToResource(jpa.save(converter.resouceToEntity(request)));
    }

    @Override
    public void delete(Long id) {
        Optional<CategoryEntity> entity = jpa.findById(id);
        if(!entity.isPresent())
            throw new ApiDiscountException(ErrorMappingEnum.CATEGORY_NOT_FOUND, HttpStatus.BAD_REQUEST);
        jpa.deleteById(id);
    }

}
