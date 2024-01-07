import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CookBook {
    FileWriter book;

    public CookBook() {
        try {
            book = new FileWriter("output/cookbook.html");
            book.write("<!DOCTYPE HTML>\n");
            book.write("<html>\n");
            book.write("<link rel=\"stylesheet\" href=\"cookbook.css\">\n");
            book.write("<body>\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Finish(){
        try {
            book.write("</body>\n");
            book.write("</html>\n");
            book.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void AddRecipe(Recipe r){
        try {
            //Start section
            //book.write("<recipe>\n");

            //Create recipe content
            book.write("<h1>" + r.getName() + "</h1><br>\n");
            if (r.getImage() != null)
                book.write("<img src=\"images/" + r.getImage() + "\"></img>\n");
            book.write("<servings>Servings: " + r.getServings() + "</servings><br>\n");
            book.write("<time>Time: " + r.getTime() + "</time><br>\n");

            //Ingredients
            book.write("<ingredients>Ingredients:<br>\n");
            ArrayList<Ingredient> e = new ArrayList<>();
            for (Ingredient i:r.getIngredients()) {
                if (i.extra)
                    e.add(i);
                else
                    book.write("<ingredient>\n" + i.toString() + "</ingredient><br>\n");
            }

            book.write("<extras>Extras:<br>\n");
            for (Ingredient i:e){
                book.write("<extra>" + i.name + "</extra><br>\n");
            }
            book.write("</extras>\n");

            book.write("</ingredients>\n");

            //Steps
            book.write("<steps>Steps:<br>\n");
            for (int i = 0; i < r.getSteps().size(); i++) {
                book.write("<step>" + (i+1) + ". " + r.getSteps().get(i) + "</step><br>\n");
            }
            book.write("</steps>\n");

            //End section
            //book.write("</recipe>\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
