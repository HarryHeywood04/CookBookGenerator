import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Loader {
    public static Recipe[] Load(){
        ArrayList<Recipe> recipes = new ArrayList<>();
        File contentFolder = new File("Content");
        //Delete old images from output folder
        File oldImages = new File("output/images");
        File[] oldImageItems = oldImages.listFiles();
        for (File f:oldImageItems) {
            f.delete();
        }

        String[] fileNames = getFileNames(contentFolder); //Gets all recipes from content folder
        for (String name:fileNames) {
            if (name.contains(".png") || name.contains(".jpg") || name.contains(".jpeg")){
                try {
                    String[] splitFilePath = name.split(Pattern.quote("\\"));
                    String fileName = splitFilePath[splitFilePath.length-1];
                    File file = new File("output/images/" + fileName);
                    file.createNewFile();
                    Files.copy(
                            new File(name).toPath(),
                            new File("output/images/" + fileName).toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                recipes.add(readFile(name));
            }
        }
        Recipe[] result = new Recipe[recipes.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = recipes.get(i);
        }
        return result; //Returns list of recipes
    }

    public static String[] getFileNames(File file){
        File[] files = file.listFiles();
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile())
                result.add(files[i].getPath());
            else {
                String[] t = getFileNames(files[i]);
                result.addAll(Arrays.asList(t));
            }
        }
        String[] finalResult = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            finalResult[i] = result.get(i);
        }
        return finalResult;
    }

    public static Recipe readFile(String filePath) {
        try {
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(filePath));
            doc.getDocumentElement().normalize();
            Recipe recipe = new Recipe();
            recipe.setName(doc.getElementsByTagName("TITLE").item(0).getTextContent());
            recipe.setCategory(doc.getElementsByTagName("CATEGORY").item(0).getTextContent().toUpperCase());
            recipe.setServings(doc.getElementsByTagName("SERVINGS").item(0).getTextContent());
            recipe.setTime(doc.getElementsByTagName("TIME").item(0).getTextContent());
            NodeList ingredients = doc.getElementsByTagName("INGREDIENT");
            NodeList extras = doc.getElementsByTagName("EXTRA");
            NodeList steps = doc.getElementsByTagName("STEP");

            for (int i = 0; i < ingredients.getLength(); i++){
                Element element = (Element) ingredients.item(i);
                String q = element.getElementsByTagName("QUANTITY").item(0).getTextContent();
                String n = element.getElementsByTagName("NAME").item(0).getTextContent();
                recipe.addIngredient(new Ingredient(n, q));
            }
            for (int i = 0; i < extras.getLength(); i++){
                recipe.addIngredient(new Ingredient(extras.item(i).getTextContent()));
            }
            for (int i = 0; i < steps.getLength(); i++){
                recipe.addStep(steps.item(i).getTextContent());
            }

            if (doc.getElementsByTagName("AUTHOR").getLength() >= 1) {
                recipe.setAuthor(doc.getElementsByTagName("AUTHOR").item(0).getTextContent());
            } else {
                recipe.setAuthor(null);
            }

            //Set image
            if (doc.getElementsByTagName("IMAGE").getLength() >= 1) {
                recipe.setImage(doc.getElementsByTagName("IMAGE").item(0).getTextContent());
            } else {
                recipe.setImage(null);
            }

            return recipe;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
