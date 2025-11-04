package cruz.zoo.com;

public class Bear extends Animal {
    private static int numOfBears = 0;
    private String roar;

    public Bear(String sex, int age, int weight, String name,
                String animalID, String birthDate, String color,
                String origin, String arrivalDate) {
        super(sex, age, weight, name, animalID, birthDate, color, origin, arrivalDate);
        this.roar = "Grrrrr"; // default bear sound
        numOfBears++;
    }

    public static int getNumOfBears() {
        return numOfBears;
    }

    public String getRoar() {
        return roar;
    }
}
