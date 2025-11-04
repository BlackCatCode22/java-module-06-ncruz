package cruz.zoo.com;

public class Animal {
    private String sex;
    private int age;
    private int weight;
    private String name;
    private String animalID;
    private String birthDate;
    private String color;
    private String origin;
    private String arrivalDate;

    private static int numOfAnimals = 0;

    // Constructor
    public Animal(String sex, int age, int weight, String name,
                  String animalID, String birthDate, String color,
                  String origin, String arrivalDate) {
        this.sex = sex;
        this.age = age;
        this.weight = weight;
        this.name = name;
        this.animalID = animalID;
        this.birthDate = birthDate;
        this.color = color;
        this.origin = origin;
        this.arrivalDate = arrivalDate;
        numOfAnimals++;
    }

    // --- Getters ---
    public String getSex() { return sex; }
    public int getAge() { return age; }
    public int getWeight() { return weight; }
    public String getName() { return name; }
    public String getAnimalID() { return animalID; }
    public String getBirthDate() { return birthDate; }
    public String getColor() { return color; }
    public String getOrigin() { return origin; }
    public String getArrivalDate() { return arrivalDate; }

    // --- Static Counter ---
    public static int getNumOfAnimals() {
        return numOfAnimals;
    }

    // --- Full info method, can be overridden by subclasses ---
    public String fullInfo() {
        return animalID + "; " +
                age + " years old; " +
                name + "; birthDate: " + birthDate + "; " +
                color + " color; " +
                sex + "; " +
                weight + " pounds; " +
                "from: " + origin + "; arrived: " + arrivalDate;
    }

    @Override
    public String toString() {
        return fullInfo();
    }
}
