public class Printer {
    public static void Print(Recipe[] recipes){
        CookBook cookBook = new CookBook();
        for (Recipe r:recipes) {
            System.out.println(r.getName());
            cookBook.AddRecipe(r);
        }
        cookBook.Finish();
    }

}
