package pl.home.recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.home.recipeapp.repositories.CategoryRepository;
import pl.home.recipeapp.repositories.RecipeRepository;
import pl.home.recipeapp.repositories.UnitOfMeasureRepository;

@Controller
public class IndexController {

    private final RecipeRepository recipeRepository;

    public IndexController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model){
        model.addAttribute("recipes", recipeRepository.findAll());

        return "index";
    }


}
