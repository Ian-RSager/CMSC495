package charactercreator;

import java.io.Serializable;

/*
 * This class defines Skill objects, which allow the character to interact with the game world in a wide variety of ways.  They represent the
 * crystallization of some type of knowledge and/or training the character has practiced (or is naturally gifted at.)
 */

public class Skill extends Thing implements Serializable {
    protected String[] classes;                         // a list of classes that could use each feature
    protected String[] tags;                            // a list of tag words assoiated with this feature -- included for future versions.
    protected String relevantAbilityScore = "Strength"; // Which ability score will be used when calculating ability bonus values.
    protected boolean isProficient = false;             // Whether or not the character is proficient with this skill, and will receive their Proficiency Bonus when using it.
    protected int miscBonus = 0;                        // Any additional bonuses the character has to this skill.


    //Constructor to be used when reading in data from skills.json
    public Skill(String idIn, String nameIn, String descriptionIn, String[] classesIn,
                 String relevantAbilityScoreIn, String[] tagsIn) {
        super(idIn, nameIn, descriptionIn);
        this.classes = classesIn;
        this.relevantAbilityScore = relevantAbilityScoreIn;
        this.tags = tagsIn;
        this.isProficient = false;
        this.miscBonus = 0;
    } // end constructor method

    // Setters and getters.
    // For associated tags
    public String[] getTags() {
        return this.tags;
    }

    public void setTags(String[] newTagsIn) {
        this.tags = newTagsIn;
    }

    // For associated classTypes
    public String[] getClasses() {
        return this.classes;
    }

    public void setClasses(String[] newClassesIn) {
        this.classes = newClassesIn;
    }

    // For relevant ability score.
    public void setRelevantAbilityScore(String relevantAbilityScoreIn) {
        this.relevantAbilityScore = relevantAbilityScoreIn;
    }

    public String getRelevantAbilityScore() {
        return this.relevantAbilityScore;
    }

    // For isProficient.
    public void setIsProficient(boolean isProficientIn) {
        this.isProficient = isProficientIn;
    } // shouldn't this be in character.java?

    public boolean getIsProficient() {
        return this.isProficient;
    }                              // shouldn't this be in character.java?

    // For miscellaneous bonus.
    public void setMiscBonus(int miscBonusIn) {
        this.miscBonus = miscBonusIn;
    }

    public int getMiscBonus() {
        return this.miscBonus;
    }
}
