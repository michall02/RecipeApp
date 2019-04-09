package pl.home.recipeapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.home.recipeapp.model.Category;
import pl.home.recipeapp.model.Difficulty;
import pl.home.recipeapp.model.Ingredient;
import pl.home.recipeapp.model.Notes;
import pl.home.recipeapp.model.Recipe;

import pl.home.recipeapp.model.UnitOfMeasure;
import pl.home.recipeapp.repositories.CategoryRepository;
import pl.home.recipeapp.repositories.RecipeRepository;
import pl.home.recipeapp.repositories.UnitOfMeasureRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeLoader implements ApplicationListener<ContextRefreshedEvent> {

    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeLoader(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());


    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupsUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> stalkUomOptional = unitOfMeasureRepository.findByDescription("Stalk");

        if(!stalkUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> canUomOptional = unitOfMeasureRepository.findByDescription("Can");

        if(!canUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> sliceUomOptional = unitOfMeasureRepository.findByDescription("Slice");

        if(!sliceUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }
        Optional<UnitOfMeasure> wholeUomOptional = unitOfMeasureRepository.findByDescription("Whole");

        if(!wholeUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        //get optionals
        UnitOfMeasure stalkUom = stalkUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaspoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure canUom = canUomOptional.get();
        UnitOfMeasure wholeUom = wholeUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();
        UnitOfMeasure sliceUom = sliceUomOptional.get();

        //get Categories
        Optional<Category> greekCategoryOptional = categoryRepository.findByDescription("Greek");

        if(!greekCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> englishCategoryOptional = categoryRepository.findByDescription("English");

        if(!englishCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Category greekCategory = greekCategoryOptional.get();
        Category englishCategory = englishCategoryOptional.get();

        //Classic Carrot Salad
        Recipe clCarSalRecipe = new Recipe();
        clCarSalRecipe.setDescription("Classic Carrot Salad");
        clCarSalRecipe.setPrepTime(15);
        clCarSalRecipe.setCookTime(0);
        clCarSalRecipe.setDifficulty(Difficulty.EASE);
        clCarSalRecipe.setDirections("The proportions of this recipe are flexible, depending on what you have on hand. Many traditional carrot salad recipes also call for crushed pineapple, but I think with the apples, raisins, and carrots, it’s sweet enough.\n" +
                "By the way, you can often find carrots of different colors at the market — deep red, purple, bright orange, and bold yellow.\n" +
                "The taste differences are subtle (they’re all delicious!); the main difference is in their mix of nutrients. For example while orange carrots have lots of beta-carotene, red carrots have lycopene (what you find in tomatoes).\n"+
                "\nRead more: https://www.simplyrecipes.com/recipes/classic_carrot_salad/");

        Notes clCarSalNotes = new Notes();
        clCarSalNotes.setRecipeNotes("This salad — a cousin to coleslaw — can be served right away, but also keeps well in the fridge for an hour or two if you want to get your dinner side dish prepped and crossed off the list.\n" +
                "If you want to make it further ahead, grate the carrots and prep the apples, then toss them with a bit of lemon juice or apple cider vinegar to prevent browning. Wait to combine with the mayo until closer to serving.\n"+
                "\nRead more: https://www.simplyrecipes.com/recipes/classic_carrot_salad/");
        clCarSalNotes.setRecipe(clCarSalRecipe);
        clCarSalRecipe.setNotes(clCarSalNotes);

        clCarSalRecipe.getIngredients().add(new Ingredient("freshly grated carrots", new BigDecimal(3), clCarSalRecipe, cupsUom ));
        clCarSalRecipe.getIngredients().add(new Ingredient("raisins", new BigDecimal(1), clCarSalRecipe, cupsUom ));
        clCarSalRecipe.getIngredients().add(new Ingredient("large apple cored and chopped", new BigDecimal(1), clCarSalRecipe, wholeUom));
        clCarSalRecipe.getIngredients().add(new Ingredient("mayonnaise", new BigDecimal(0.25), clCarSalRecipe, cupsUom));


        clCarSalRecipe.getCategories().add(greekCategory);

        //add to return list
        recipes.add(clCarSalRecipe);

        //English Muffin Tuna Melts Recipe
        Recipe englishMuffinRecipe = new Recipe();
        englishMuffinRecipe.setDescription("English Muffin Tuna Melts Recipe");
        englishMuffinRecipe.setCookTime(15);
        englishMuffinRecipe.setPrepTime(15);
        englishMuffinRecipe.setDifficulty(Difficulty.MODERATE);

        englishMuffinRecipe.setDirections("There are a few things to keep in mind to make these tuna muffin melts really delicious.\n" +
                "For starters, don’t overstuff the muffins. I like to use about two tablespoons of tuna salad per English muffin half. You should be able to make about eight muffin halves out of one batch of tuna salad.\n" +
                "\n" +
                "Next, let’s talk soggy muffins. Generally, you might be inclined to toast the muffins to prevent them from getting too soggy. The problem, though, is that if you toast the muffins, they will burn after you bake the melts.\n" +
                "\n" +
                "So don’t toast them. Instead, put half a slice of Swiss cheese between the muffin and the tuna salad. That will keep the moisture away from the muffin and prevent it from getting too soggy.\n" +
                "\n" +
                "Do those few things, and you’ll have some really delicious tuna muffin melts!\n"+
                "\nRead more: https://www.simplyrecipes.com/recipes/english_muffin_tuna_melts/");

        Notes englishMuffinNotes = new Notes();
        englishMuffinNotes.setRecipeNotes("These muffins are great on their own or with a side of steamed vegetables, baked fries, or even a small side salad.\n" +
                "\n" +
                "For a light dinner, though, I can eat two or three of these muffins with some of the pickled veggies and be full!\n"+
                "\nRead more: https://www.simplyrecipes.com/recipes/english_muffin_tuna_melts/");
        englishMuffinNotes.setRecipe(englishMuffinRecipe);
        englishMuffinRecipe.setNotes(englishMuffinNotes);


        englishMuffinRecipe.getIngredients().add(new Ingredient("celery, minced", new BigDecimal(1), englishMuffinRecipe, stalkUom));
        englishMuffinRecipe.getIngredients().add(new Ingredient("minced red onion", new BigDecimal(0.25), englishMuffinRecipe, cupsUom));
        englishMuffinRecipe.getIngredients().add(new Ingredient("minced red pepper", new BigDecimal(2), englishMuffinRecipe, tableSpoonUom));
        englishMuffinRecipe.getIngredients().add(new Ingredient("albacore tuna, oil or water packed", new BigDecimal(2), englishMuffinRecipe, canUom));
        englishMuffinRecipe.getIngredients().add(new Ingredient("salt", new BigDecimal(0.125), englishMuffinRecipe, teaspoonUom));
        englishMuffinRecipe.getIngredients().add(new Ingredient("freshly ground black pepper", new BigDecimal(0.125), englishMuffinRecipe, teaspoonUom));
        englishMuffinRecipe.getIngredients().add(new Ingredient("mayonnaise", new BigDecimal(0.33), englishMuffinRecipe, cupsUom));
        englishMuffinRecipe.getIngredients().add(new Ingredient("English muffins", new BigDecimal(4), englishMuffinRecipe, wholeUom));
        englishMuffinRecipe.getIngredients().add(new Ingredient("Swiss cheese", new BigDecimal(8), englishMuffinRecipe, sliceUom));


        englishMuffinRecipe.getCategories().add(englishCategory);


        recipes.add(englishMuffinRecipe);

        return recipes;
    }
}
