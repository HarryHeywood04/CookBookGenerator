import org.w3c.dom.*;

import javax.imageio.ImageIO;
import javax.xml.parsers.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;

public class Loader {
    public static Recipe[] Load(){
        ArrayList<Recipe> recipes = new ArrayList<>();
        File recipeFolder = new File("Content/Recipes");
        String[] fileNames = getFileNames(recipeFolder);
        for (String name:fileNames) {
            recipes.add(readFile(name));
        }
        Recipe[] result = new Recipe[recipes.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = recipes.get(i);
        }
        return result;
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
            recipe.setCategory(doc.getElementsByTagName("CATEGORY").item(0).getTextContent());
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
            if (doc.getElementsByTagName("IMAGE").getLength() >= 1) {
                File file = new File("output/images/" + doc.getElementsByTagName("IMAGE").item(0).getTextContent());
                file.createNewFile();
                Files.copy(
                        new File("Content/Images/" + doc.getElementsByTagName("IMAGE").item(0).getTextContent()).toPath(),
                        new File("output/images/" + doc.getElementsByTagName("IMAGE").item(0).getTextContent()).toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
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
