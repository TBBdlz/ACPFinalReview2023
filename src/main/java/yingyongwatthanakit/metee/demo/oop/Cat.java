package yingyongwatthanakit.metee.demo.oop;

public class Cat extends Animal implements UseMagic {

    protected boolean isLikeCatnip;

    public Cat() {
        super();
        isLikeCatnip = false;
    }

    public Cat(String name, int age, String color, String specie, boolean isLikeCatnip) {
        super(name, age, color, specie);
        this.isLikeCatnip = isLikeCatnip;
    }

    public static void printAllCat() {
        System.out.println(String.format("Number of cat: %d\n", numberOfAnimal));
    }

    @Override // @ refers to annotation
    protected void eat() {
        System.out.println("Nom nom nom");
    }

    @Override
    protected void speak() {
        System.out.println("meow");
    }

    @Override
    protected void sleep() {
        System.out.println("ZZZzzzzz");
    }

    public boolean isLikeCatnip() {
        return isLikeCatnip;
    }

    public void setLikeCatnip(boolean likeCatnip) {
        isLikeCatnip = likeCatnip;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "isLikeCatnip=" + isLikeCatnip +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", specie='" + specie + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Cat rainy = new Cat("rainy", 3, "grey", "cat", false);
        System.out.println(rainy);
        rainy.speak();
        rainy.eat();
        printAllCat();
    }

    @Override
    public void attackByMagic() {
        System.out.println("Fire ball!!!!");
    }

}
