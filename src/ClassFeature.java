package charactercreator;

/*
 * This class is used to define ClassFeature objects.  A ClassFeature is a particular mechanic that a character has access to due to
 * one or more of the character classes that they have gained levels in.
 */

import java.io.Serializable;
import java.util.Map;


public class ClassFeature extends Thing implements Serializable {

    protected String[] classes;                     // An array of the classes, based on the class id tags, which have access to this feature.  Includes subclasses.
    protected String[] tags;                        // Plain text tags of the relevant classes or other descriptors which may be used to search for or describe this feature.
    protected String[][] levelingInfo;              // An array of the elements of the feature that change based upon the character's level in the relevant class.
    protected Map<String, Object> content;           // An Array of applicable features


    //Constructor to be used when reading in data from features.json
    public ClassFeature(String idIn, String nameIn, Map<String, Object>  contentIn,
                        String descriptionIn, String[] classesIn,
                        String[] tagsIn, String[][] levelingInfoIn){

        super(idIn, nameIn, descriptionIn);
        this.classes = classesIn;
        this.tags = tagsIn;
        this.levelingInfo = levelingInfoIn;
        this.content=contentIn;

    }// end constructor 

    // Only getter methods will be used, as these values should not change during the operation of the program.
    public String[] getClasses() {return this.classes;}
    public String[] getTags() {return this.tags;}
    public String[][] getLevelingInfo() {return this.levelingInfo;}

    // Method to determine whether or not this feature has a particular entry in its class array.
    public boolean getHasClass(String classToSearchForIn) {
        for (String classString : this.classes) {
            if (classString.equalsIgnoreCase(classToSearchForIn)) {
                return true;
            } // End if.
        } // End for.
        return false;
    } // End getHasClass.

    // Method to determine whether or not this feature has a particular entry in its tag array.
    public boolean getHasTag(String tagToSearchForIn) {
        for (String tagString : this.tags) {
            if (tagString.equalsIgnoreCase(tagToSearchForIn)) {
                return true;
            } // End if.
        } // End for.
        return false;
    } // End getHasTag.

}
