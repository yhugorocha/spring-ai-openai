package io.github.yhugorocha.spring_open_ia.controller;

import io.github.yhugorocha.spring_open_ia.service.ChatService;
import io.github.yhugorocha.spring_open_ia.service.ImageService;
import io.github.yhugorocha.spring_open_ia.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatWebAIController {

    private final ChatService chatService;
    private final RecipeService recipeService;
    private final ImageService imageService;

    public ChatWebAIController(ChatService chatService, RecipeService recipeService, ImageService imageService) {
        this.chatService = chatService;
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping("ask-ai")
    public String chat(@RequestParam String prompt) {
        return chatService.chat(prompt);
    }

    @GetMapping("ask-ai-options")
    public String chatWithOptions(@RequestParam String prompt){
        return chatService.chatWithOptions(prompt);
    }

    @GetMapping("recipe-creator")
    public String chat(@RequestParam String ingredients,
                       @RequestParam(defaultValue = "todas") String cuisine,
                       @RequestParam(defaultValue = "nenhuma") String dietaryRestrictions){
        return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions);
    }

    @GetMapping("generate-image")
    public List<String> generateImages(@RequestParam String prompt,
                                       @RequestParam(defaultValue = "hd") String quality,
                                       @RequestParam(defaultValue = "1") Integer n,
                                       @RequestParam(defaultValue = "1024") Integer height,
                                       @RequestParam(defaultValue = "1024") Integer width) {

        return imageService.generateImage(prompt, quality, n, height, width)
                .getResults()
                .stream()
                .map(i -> i.getOutput().getUrl())
                .toList();
    }
}
