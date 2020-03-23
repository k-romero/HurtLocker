public class Item {
    public String name;
    public Double price;

    public Item(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public static Item itemFactory(String name,Double price){
        return new Item(name, price);
    }
}
