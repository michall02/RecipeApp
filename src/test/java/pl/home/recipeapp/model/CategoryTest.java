package pl.home.recipeapp.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() throws Exception{
        category = new Category();
    }

    @Test
    public void getIdTest(){
        Long id = 10L;

        category.setId(id);

        assertEquals(id, category.getId());

    }

}