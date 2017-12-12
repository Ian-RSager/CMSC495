package charactercreator;

import java.io.Serializable;

/*
 * This class is used to define Race objects.  A Race refers to the well, race, of the character.
 */

public class Race extends Thing implements Serializable{
    protected int[] abilityScore;                   // A list of the which ability scores receive a bonus.  In order these represent: Strength, Dexterity, Constitution, Intelligence, Wisdom, Charisma.
    protected int age;                              // The default age of the race.
    protected int heightInches;                     // The default height, in inches, of the race.
    protected boolean isMedium;                     // If true, the race is Medium sized, otherwise it is Small.
    protected int speed;                            // The race's movement speed, in feet per round.
    protected String[][] traits;                    // A list of all the traits the race possesses, to be displayed to the user.

    // Constructor to be used when reading in data from races.json
    public Race(String idIn, String nameIn, String descriptionIn, int[] abilityScoreIn, int ageIn, int heightInchesIn, boolean isMediumIn, int speedIn, String[][] traitsIn) {
        super(idIn, nameIn, descriptionIn);
        this.abilityScore = abilityScoreIn;
        this.age = ageIn;
        this.heightInches = heightInchesIn;
        this.isMedium = isMediumIn;
        this.speed = speedIn;
        this.traits = traitsIn;
    } // End constructor for use with JSON files.

    // Getter methods.  No setter methods are provided, as Race objects should never change while the programming is running.
    public int[] getAbilityScore() {return this.abilityScore;}
    public int getAge() {return this.age;}
    public int getHeightInches() {return this.heightInches;}
    public boolean getIsMedium() {return this.isMedium;}
    public int getSpeed() {return this.speed;}
    public String[][] getString() {return this.traits;}
} // End public class Race.
