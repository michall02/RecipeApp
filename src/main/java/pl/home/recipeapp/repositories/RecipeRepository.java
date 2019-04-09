package pl.home.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.home.recipeapp.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
