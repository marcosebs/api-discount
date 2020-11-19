package br.com.apidiscount.repository.jpa;

import br.com.apidiscount.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJpa extends JpaRepository<UserEntity, Long> {
}
