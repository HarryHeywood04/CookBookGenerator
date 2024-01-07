import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Printer {
    public static void Print(Recipe[] recipes){
        CookBook cookBook = new CookBook();
        List<Category> categoriesList = new ArrayList<>();

        for (Recipe r:recipes) {
            int catID = FindCategory(r.getCategory(), categoriesList);
            if (catID == -1){
                categoriesList.add(new Category(r.getCategory()));
                categoriesList.get(categoriesList.size()-1).AddRecipe(r);
            } else {
                categoriesList.get(catID).AddRecipe(r);
            }
        }

        for (Category c:categoriesList) {
            cookBook.AddCategory(c.name);
            for (Recipe r:c.getRecipes()) {
                cookBook.AddRecipe(r);
            }
        }

        cookBook.Finish();
    }

    private static int FindCategory(String name, List<Category> categoryList){
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).name.equalsIgnoreCase(name))
                return i;
        }
        return -1;
    }

}
