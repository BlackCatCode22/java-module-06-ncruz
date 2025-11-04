package cruz.zoo.com;

public class Lion extends Animal {
    private static int numOfLions = 0;
    private String roar;

    public Lion(String sex, int age, int weight, String name,
                String animalID, String birthDate, String color,
                String origin, String arrivalDate) {
        super(sex, age, weight, name, animalID, birthDate, color, origin, arrivalDate);
        this.roar = "Roarrr"; // default lion sound
        numOfLions++;
    }

    public static int getNumOfLions() {
        return numOfLions;
    }

    public String getRoar() {
        return roar;
    }
}
