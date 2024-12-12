public class Rock {
    Long rock;
    long multiplier;
    
    public Rock(Long rock, Long multiplier) {
        this.rock = rock;
        this.multiplier = multiplier;
    }

    public boolean equals(Rock newRock) {
        return this.rock.equals(newRock.rock);
    }

    public String toString() {
        return "Number: " + rock + " Mult: " + multiplier;
    }
}
