package br.com.apidiscount.service.impl;

import br.com.apidiscount.http.resource.CategoryResource;
import br.com.apidiscount.infra.exception.ApiDiscountException;
import br.com.apidiscount.infra.exception.enumerator.ErrorMappingEnum;
import br.com.apidiscount.repository.CategoryRepository;
import br.com.apidiscount.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResource create(CategoryResource request) {
        request.setId(null);
        if(request.getMaxOrders() == null || request.getMinOrders() == null || request.getDiscount() == null)
            throw new ApiDiscountException(ErrorMappingEnum.REQUIRED_FIELD, HttpStatus.BAD_REQUEST);
        if(request.getMaxOrders() < request.getMinOrders())
            throw new ApiDiscountException(ErrorMappingEnum.INVALID_CATEGORY_PARAMETERS, HttpStatus.BAD_REQUEST);
        List<CategoryResource> categories = categoryRepository.findAll();
        categories.forEach(category -> {
            if(request.getMaxOrders() >= category.getMinOrders() && request.getMinOrders() <= category.getMaxOrders())
                throw new ApiDiscountException(ErrorMappingEnum.CATEGORY_EXISTING, HttpStatus.BAD_REQUEST);
        });
        return categoryRepository.create(request);
    }

    @Override
    public List<CategoryResource> list() {
        return categoryRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(id);
    }

}
