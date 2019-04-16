package pl.home.recipeapp.services;

import pl.home.recipeapp.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(Long recipeId, Long ingredientId);
}
