import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CookBook {
    FileWriter book;

    public CookBook() {
        try {
            book = new FileWriter("output/cookbook.html");
            book.write("<link rel=\"stylesheet\" href=\"cookbook.css\">");
            book.write("<body>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Finish(){
        try {
            book.write("</body>");
            book.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void AddRecipe(Recipe r){
        try {
            //Start section
            book.write("<recipe>");

            //Create recipe content
            book.write("<recipeName>" + r.getName() + "</recipeName><br>");
            if (r.getImage() != null)
                book.write("<img src=\"images/" + r.getImage() + "\"></img><br>");
            book.write("<servings>Servings: " + r.getServings() + "</servings><br>");
            book.write("<time>Time: " + r.getTime() + "</time><br>");

            //Ingredients
            book.write("<ingredients>Ingredients:<br>");
            ArrayList<Ingredient> e = new ArrayList<>();
            for (Ingredient i:r.getIngredients()) {
                if (i.extra)
                    e.add(i);
                else
                    book.write("<ingredient>" + i.toString() + "</ingredient><br>");
            }

            book.write("<extras>");
            book.write("Extras:<br>");
            for (Ingredient i:e){
                book.write("<extra>" + i.name + "</extra><br>");
            }
            book.write("</extras>");

            book.write("</ingredients>");

            //Steps
            book.write("<steps>Steps:<br>");
            for (int i = 0; i < r.getSteps().size(); i++) {
                book.write("<step>" + (i+1) + ". " + r.getSteps().get(i) + "</step><br>");
            }
            book.write("</steps>");

            //End section
            book.write("</recipe>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
