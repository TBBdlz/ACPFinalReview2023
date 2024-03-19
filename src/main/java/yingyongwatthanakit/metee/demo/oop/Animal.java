package yingyongwatthanakit.metee.demo.oop;

public abstract class Animal {

    protected String name;
    protected int age; // date of birth
    protected String color;
    protected String specie;
    protected static int numberOfAnimal = 0;

    public Animal() {
        numberOfAnimal++;
    }

    public Animal(String name, int age, String color, String specie) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.specie = specie;
        numberOfAnimal++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public static int getNumberOfAnimal() {
        return numberOfAnimal;
    }

    public static void setNumberOfAnimal(int numberOfAnimal) {
        Animal.numberOfAnimal = numberOfAnimal;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", specie='" + specie + '\'' +
                '}';
    }

    protected abstract void eat();
    protected abstract void speak();
    protected abstract void sleep();

}
