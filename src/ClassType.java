package charactercreator;

import java.io.Serializable;

/*
 * This class is used to define ClassType objects, more commonly referred to simply as classes, or character classes.  These objects contain the necessary data and references
 * to generate a character class.
 */

public class ClassType extends Thing implements Serializable {

    protected int[] hitDice;                        // An array determining which hit die the character uses.  There are 4 possible values, signifying: d6, d8, d10, d12.
    protected String[] armorProficiencies;          // A list of the armor proficiencies the character gets.
    protected String[] weaponProficiencies;         // A list of the weapon proficiencies the character gets.
    protected String[] savingThrows;                // A list of the saving throws the character gains proficiency in.
    protected int numberOfSkillChoices;             // A number signifying the quantity of choices the player is able to make from the skillChoicesArray, to gain proficiency in those skills.
    protected String[] skillChoicesArray;           // A list of the skills the player may choose from, to gain proficiency in when creating a character of this class.
    protected String[] tools;                       // A list of the tools the character gains proficiency in.
    protected String equipment;                     // A description of the equipment the player may choose when creating a new character of this class.  For front-end use only.
    protected String[][] features;                  // A list of the features the class gains at each level.  Each feature is listed via its unique ID, per the ClassFeature class.
    protected String[] subClassChoices;             // A list of the possible subclasses the player may choose from, when creating a character with levels in this class.
    protected String[][] subClassFeaturesList;      // A list of all the class features each of the possible subclasses receives.  21 entries in total, the first to be used as an identifier, and indices 1-20 signifying which feature is received at levels 1 through 20 for this particular subclass.

    // Constructor to be used when reading in data from classTypes.json
    public ClassType(String idIn, String nameIn, String descriptionIn, int[] hitDiceIn,
                     String[] armorProficienciesIn, String[] weaponsProficienciesIn, String[] savingThrowsIn,
                     int numberOfSkillChoicesIn, String[] skillChoicesArrayIn, String[] toolsIn, String equipmentIn,
                     String[][] featuresIn, String[][] subClassFeaturesListIn){
        super(idIn, nameIn, descriptionIn);
        this.hitDice = hitDiceIn;
        this.armorProficiencies = armorProficienciesIn;
        this.weaponProficiencies = weaponsProficienciesIn;
        this.savingThrows = savingThrowsIn;
        this.numberOfSkillChoices = numberOfSkillChoicesIn;
        this.skillChoicesArray = skillChoicesArrayIn;
        this.tools = toolsIn;
        this.equipment = equipmentIn;
        this.features = featuresIn;
        this.subClassFeaturesList = subClassFeaturesListIn;
    }

    /**
     * printData serves as a temporary QC. It will not necessarily be used during operation.
     */
    @Override
    public void printData(){
        super.printData();
        try{
        int count = 6;
        for(int num: this.hitDice) {
            if (num > 0)
                System.out.println("HitDice : "+ num +"d" + count);
            count+=2;
        }
        System.out.println("Armor Proficiencies:");
        for (String ap : this.armorProficiencies) {
            System.out.println("\t:" + ap);
        }
        System.out.println("Weapon Proficiencies:");
        for (String wp : this.weaponProficiencies) {
            System.out.println("\t:" + wp);
        }
        System.out.println("Saving throws for:");
        for (String svt : this.savingThrows) {
            System.out.println("\t:" + svt);
        }

        System.out.println("Choose "+this.numberOfSkillChoices+" of the following possible skills:");
        for (String sca : this.skillChoicesArray) {
            System.out.println("\t:" + sca);
        }

        System.out.println("Available tools:");
        for (String eachTool : this.tools) {
            System.out.println("\t:" + eachTool);
        }
        System.out.println("Equipment: "+this.equipment);
        System.out.println("Features: ");
        for(String[] eachFeature: this.features)
            for(String eaFeature: eachFeature)
                System.out.println("\t:"+eaFeature);
        }catch (Exception e){
            System.out.println("\n\n\t ************** ClassType - OOPS ************** \n\n");
        }
    }

    // Only getter methods will be used, as these values should not change during the operation of the program.
    public int[] getHitDice() {return this.hitDice;}
    public String[] getArmorProficiencies() {return this.armorProficiencies;}
    public String[] getWeaponProficiencies() {return this.weaponProficiencies;}
    public String[] getSavingThrows() {return this.savingThrows;}
    public int getNumberOfSkillChoices() {return this.numberOfSkillChoices;}
    public String[] getSkillChoicesArray() {return this.skillChoicesArray;}
    public String[] getTools() {return this.tools;}
    public String getEquipment() {return this.equipment;}
    public String[][] getFeatures() {return this.features;}
    public String[] getSubClassChoices() {return this.subClassChoices;}
    public String[][] getSubClassFeaturesList() {return this.subClassFeaturesList;}

} // End public class ClassType.
