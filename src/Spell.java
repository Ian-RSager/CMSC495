package charactercreator;

/*
 * This class defines Spell objects, magical incantations that allow the character to manipulate reality in fantastical ways.
 */

import java.io.Serializable;
import java.util.Map;

public class Spell extends Thing implements Serializable {
    protected String castingTime;                   // How long the spell takes to cast.
    protected String[] classes;                     // An array of the names of all classes capable of casting this spell.
    protected Map<String, Object> components;       // The components needed to cast the spell.
    protected String duration;                      // How long the effects of the spell last.
    protected String level;                         // The level of the spell, from cantrip through 9.
    protected String range;                         // The effective distance the spell maybe cast from.
    protected Boolean canBeRitual;                  // Whether or not the spell can be cast as a ritual.
    protected String school;                        // A one-word description of which school of magic the spell falls under.
    protected String[] tags;
    protected String type;

    // Constructor to be used when reading in data from spells.json
    public Spell(String idIn, String castingTime, String[] classes, Map<String, Object> components, String descriptionIn,
                 String duration, String level, String nameIn, String range, Boolean canBeRitual, String school,
                 String[] tags, String type) {
        super(idIn, nameIn, descriptionIn);
        this.castingTime = castingTime;
        this.classes = classes;
        this.components = components;
        this.duration = duration;
        this.level = level;
        this.range = range;
        this.canBeRitual = canBeRitual;
        this.school = school;
        this.tags = tags;
        this.type = type;
    }

    // Setters and getters.
    // For classes.
    public void setClasses(String[] classesIn) {this.classes = classesIn;}
    public String[] getClasses() {return this.classes;}
    // For level.
    public void setLevel(String levelIn) {this.level = levelIn;}
    public String getLevel() {return this.level;}

    // For components.
    // Currently read in as objects...
    public void setComponents(Map<String, Object> componentsIn) {this.components = componentsIn;}
    public Map<String, Object> getComponents() {return this.components;}

    // For description.
    public void setDescription(String descriptionIn) {this.description = descriptionIn;}
    // For school.
    public void setSchool(String schoolIn) {this.school = schoolIn;}
    public String getSchool() {return this.school;}
    // For casting time.
    public void setCastingTime(String castingTimeIn) {this.castingTime = castingTimeIn;}
    public String getCastingTime() {return this.castingTime;}
    // For range.
    public void setRange(String rangeIn) {this.range = rangeIn;}
    public String getRange() {return this.range;}
    // For duration.
    public void setDuration(String durationIn) {this.duration = durationIn;}
    public String getDuration() {return this.duration;}
    // For Ritual flag.
    public void setCanBeRitual(boolean canBeRitualIn) {this.canBeRitual = canBeRitualIn;}
    public boolean getCanBeRitual() {return this.canBeRitual;}

}


