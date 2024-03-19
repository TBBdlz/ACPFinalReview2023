package yingyongwatthanakit.metee.demo.oop;

public class Dog extends Animal {

    protected String favoriteHuman;

    public Dog(String favoriteHuman) {
        this.favoriteHuman = favoriteHuman;
    }

    public Dog(String name, int age, String color, String specie, String favoriteHuman) {
        super(name, age, color, specie);
        this.favoriteHuman = favoriteHuman;
    }


    @Override
    protected void eat() {
        System.out.println("adkngkladngfkladnflk");
    }

    @Override
    protected void speak() {
        System.out.println("Bark");
    }

    @Override
    protected void sleep() {
        System.out.println("dakngjadnfgklnadklfn");
    }

    public String getFavoriteHuman() {
        return favoriteHuman;
    }

    public void setFavoriteHuman(String favoriteHuman) {
        this.favoriteHuman = favoriteHuman;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "favoriteHuman='" + favoriteHuman + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", specie='" + specie + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Dog orange = new Dog("หมาส้ม", 4, "orange", "orange dog", "Jojo jo star");
        System.out.println(orange);
        orange.speak();
        orange.eat();
    }

}
