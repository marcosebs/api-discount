package br.com.apidiscount.repository.jpa;

import br.com.apidiscount.repository.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositoryJpa extends JpaRepository<CategoryEntity, Long> {
}
