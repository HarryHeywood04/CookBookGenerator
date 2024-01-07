import java.util.ArrayList;
import java.util.List;

public class Category {
    String name;
    List<Recipe> recipes;

    public Category(String name){
        this.name = name;
        recipes = new ArrayList<>();
    }

    public void AddRecipe(Recipe r){
        recipes.add(r);
    }

    public Recipe[] getRecipes(){
        Recipe[] out = new Recipe[recipes.size()];
        for (int i = 0; i < recipes.size(); i++) {
            out[i] = recipes.get(i);
        }
        return out;
    }
}
