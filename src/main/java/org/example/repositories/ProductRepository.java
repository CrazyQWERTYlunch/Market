package org.example.repositories;

import org.example.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    @Query("select p from ProductEntity p where p.category.id = :categoryId")
    public List<ProductEntity> findAllByCategoryId(int categoryId);
}
