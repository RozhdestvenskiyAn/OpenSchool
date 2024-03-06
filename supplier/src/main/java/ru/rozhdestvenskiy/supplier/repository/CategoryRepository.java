package ru.rozhdestvenskiy.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rozhdestvenskiy.supplier.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
