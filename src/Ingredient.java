public class Ingredient {
    String name;
    String quantity;
    boolean extra;

    public Ingredient(String nameIn, String quantityIn){
        name = nameIn;
        quantity = quantityIn;
        extra = false;
    }

    public Ingredient(String nameIn){
        name = nameIn;
        extra = true;
    }

    @Override
    public String toString() {
        return "<quantity>" + this.quantity + "</quantity> - \n<name>" + this.name + "</name>\n";
    }
}
