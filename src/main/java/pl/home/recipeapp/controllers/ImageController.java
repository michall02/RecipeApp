package pl.home.recipeapp.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.home.recipeapp.commands.RecipeCommand;
import pl.home.recipeapp.services.ImageService;
import pl.home.recipeapp.services.RecipeService;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
public class ImageController {
    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/image")
    public String showUpForm(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/imageform";
    }

    @PostMapping("recipe/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {
        imageService.saveImageFile(Long.valueOf(id), file);
        return "redirect:/recipe/" + id + "/show";
    }

    @GetMapping("recipe/{id}/recipeimage")
    public void renderImageFromDb(@PathVariable String id, HttpServletResponse response) throws IOException {
        RecipeCommand command = recipeService.findCommandById(Long.valueOf(id));
        byte[] byteArray = new byte[command.getImage().length];

        int i = 0;
        for (Byte b : command.getImage()) {
            byteArray[i++] = b;
        }
        response.setContentType("image/jpg");
        ByteArrayInputStream input = new ByteArrayInputStream(byteArray);
        IOUtils.copy(input, response.getOutputStream());
    }
}
