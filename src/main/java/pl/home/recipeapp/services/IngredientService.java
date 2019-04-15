package pl.home.recipeapp.services;

import pl.home.recipeapp.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeAndIngredientId(Long recipeId, Long ingredientId);
}
