import java.util.ArrayList;

public class Recipe {
    private String name;
    private String author;
    private String category;
    private String servings;
    private String time;
    private String image;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> steps;

    public Recipe() {
        ingredients = new ArrayList<>();
        steps = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    public void addStep(String step){
        this.steps.add(step);
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getServings() {
        return servings;
    }

    public String getTime() {
        return time;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public String getImage() {
        return image;
    }
}
