package pl.home.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.home.recipeapp.model.Category;
import pl.home.recipeapp.model.Recipe;
import pl.home.recipeapp.model.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    Optional<Category> findByDescription(String description);
}
