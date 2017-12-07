package charactercreator;

import java.util.HashMap;

/*
 * This class allows for the creation of master lists of all the JSON Objects needed for this program.  These lists should not be edited after
 * the program is done loading.
 */

public class MasterLists {
    private static HashMap<String, Spell> masterSpellList;                  // Master spell list.
    private static HashMap<String, Skill> masterSkillList;                  // Master skill list.
    private static HashMap<String, Item> masterItemList;                    // Master item list.
    private static HashMap<String, Weapon> masterWeaponList;                // Master weapon list.
    private static HashMap<String, Armor> masterArmorList;                  // Master armor list.
    private static HashMap<String, ClassType> masterClassTypeList;          // Master character class list.
    private static HashMap<String, ClassFeature> masterClassFeatureList;    // Master class feature list.
    private static HashMap<String, Race> masterRaceList;                    // Master character race list.
    
    private static String spellsFileName = "spells.json";                   // The directory for spells.json.
    private static String skillsFileName = "skills.json";                   // The directory for skills.json.
    private static String itemsFileName = "items.json";                     // The directory for items.json.
    private static String weaponsFileName = "weapons.json";                 // The directory for weapons.json.
    private static String armorFileName = "armor.json";                     // The directory for armor.json.
    private static String classTypeFileName = "classTypes.json";            // The directory for classType.json.
    private static String featuresFileName = "features.json";               // The directory for features.json.
    private static String raceFileName = "race.json";                       // The directory for race.json.
    
    // Master method to create all master lists.
    public static void createMasterLists() {
       
    } // End public void createMasterLists.
    
    // Helper method to create spell master list.
    private void createMasterSpellList() {
        
    } // End private void createMasterSpellList.
    
    // Getters.  No setters are provided as no master list should change during the course of the program's operation.
    // There is a getter for the entirety of each master list, as well as a getter for the relevant Object type via ID (which acts as the HashMap key.)
    public static HashMap<String, Spell> getMasterSpellList() {return masterSpellList;}
    public static Spell getSpellByID(String IDIn) {return masterSpellList.get(IDIn);}
    
    public static HashMap<String, Skill> getMasterSkillList() {return masterSkillList;}
    public static Skill getSkillByID(String IDIn) {return masterSkillList.get(IDIn);}
    
    public static HashMap<String, Item> getMasterItemList() {return masterItemList;}
    public static Item getItemByID(String IDIn) {return masterItemList.get(IDIn);}
    
    public static HashMap<String, Weapon> getMasterWeaponList() {return masterWeaponList;}
    public static Weapon getWeaponByID(String IDIn) {return masterWeaponList.get(IDIn);}
    
    public static HashMap<String, Armor> getMasterArmorList() {return masterArmorList;}
    public static Armor getArmorByID(String IDIn) {return masterArmorList.get(IDIn);}
    
    public static HashMap<String, ClassType> getMasterClassTypeList() {return masterClassTypeList;}
    public static ClassType getClassTypeByID(String IDIn) {return masterClassTypeList.get(IDIn);}
    
    public static HashMap<String, ClassFeature> getMasterClassFeatureList() {return masterClassFeatureList;}
    public static ClassFeature getClassFeatureByID(String IDIn) {return masterClassFeatureList.get(IDIn);}
    
    public static HashMap<String, Race> getMasterRaceList() {return masterRaceList;}
    public static Race getRaceByID(String IDIn) {return masterRaceList.get(IDIn);}
    
} // End public class MasterLists.
