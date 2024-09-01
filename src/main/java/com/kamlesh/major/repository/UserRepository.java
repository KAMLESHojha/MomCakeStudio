package com.kamlesh.major.repository;

import com.kamlesh.major.model.Product;

import java.util.List;

public interface UserRepository JpaRepository<User,Long> {
    List<Product> findAllUser(Long id);
}
