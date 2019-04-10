package pl.home.recipeapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.home.recipeapp.model.Recipe;
import pl.home.recipeapp.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository  recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() {
        //given
        Recipe recipe = new Recipe();
        HashSet recipeSet = new HashSet();
        recipeSet.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeSet);

        //when
        Set<Recipe> recipes = recipeService.getRecipes();

        //then
        assertEquals(1, recipes.size());
        assertNotEquals(0, recipes.size());
        assertNotEquals(2, recipes.size());
        verify(recipeRepository,times(1)).findAll();
    }
}