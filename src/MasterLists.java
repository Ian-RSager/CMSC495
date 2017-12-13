package charactercreator;

/**
 * MasterLists.java
 * CMSC495 -- Class project
 * Class responsible for reading in multiple JSON data files
 * and creating applicable objects from data.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.io.Serializable;
import com.google.gson.Gson;

public class MasterLists implements Serializable {
    private static HashMap<String, Spell> masterSpellList;                  // Master spell list.
    private static HashMap<String, Skill> masterSkillList;                  // Master skill list.
    private static HashMap<String, Item> masterItemList;                    // Master item list.
    private static HashMap<String, Weapon> masterWeaponList;                // Master weapon list.
    private static HashMap<String, Armor> masterArmorList;                  // Master armor list.
    private static HashMap<String, ClassType> masterClassTypeList;          // Master character class list.
    private static HashMap<String, ClassFeature> masterClassFeatureList;    // Master class feature list.
    private static HashMap<String, Race> masterRaceList;                    // Master character race list.

    private static ClassType[] classTypeArray;              // Temp object array used in creation of master list
    private static Spell[] spellArray;                      // Temp object array used in creation of master list
    private static Skill[] skillArray;                      // Temp object array used in creation of master list
    private static Race[] raceArray;                        // Temp object array used in creation of master list
    private static ClassFeature[] classFeatureArray;        // Temp object array used in creation of master list
    private static Weapon[] weaponArray;                    // Temp object array used in creation of master list
    private static Armor[] armorArray;                      // Temp object array used in creation of master list
    private static Item[] itemArray;                        // Temp object array used in creation of master list

    protected FileReader fileReader;
    protected BufferedReader bufferedReader;
    protected String jsonString = "";           // temp string to hold file data prior to creation of master list
    protected Gson gson;                        // JSON file parser

    // array of file names
    private static String fileNames[] = {"classType.json","race.json", "features.json",
            "items.json","armor.json","weapons.json","skill.json","spells.json"};
  
    //Constructor
    public MasterLists() {

        // Loops through all files
        // For each file, data is read in and made into a string
        // which is then used to create multiple objects which are loaded into applicable hashMaps.
        for (int i =0; i<fileNames.length ; i++ ){
            fileReader(fileNames[i]);
            buildMasterHashMap( i );
        }// end loop
    }// end constructor method

    // File reader
    public void fileReader(String fileName){

        jsonString = "";
        String line = null;

        try {
            fileReader = new FileReader(fileName);
            System.out.println("....................opening "+fileName);
            bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                jsonString += line;
            }
            bufferedReader.close();
        }

        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }

        catch(IOException ex) {
            System.out.println("Error reading file '"+ fileName + "'");
        }

    }// end fileReader method

    public void buildMasterHashMap(int num){
        gson = new Gson();

        switch(num){
            case 0:
                classTypeArray = gson.fromJson(jsonString, ClassType[].class);
                masterClassTypeList = new HashMap<String, ClassType>();
                for (ClassType thing : classTypeArray) {
                    masterClassTypeList.put(thing.id, thing);
                }
                break;
            case 1:
                raceArray = gson.fromJson(jsonString, Race[].class);
                masterRaceList = new HashMap<String, Race>();
                for (Race thing : raceArray){
                    masterRaceList.put(thing.id, thing);
                }
                break;
            case 2:
                classFeatureArray = gson.fromJson(jsonString, ClassFeature[].class);
                masterClassFeatureList = new HashMap<String, ClassFeature>();
                for (ClassFeature thing : classFeatureArray){
                    masterClassFeatureList.put(thing.id, thing);
                }
                break;
            case 3:
                itemArray = gson.fromJson(jsonString, Item[].class);
                masterItemList = new HashMap<String, Item>();
                for (Item thing : itemArray){
                    masterItemList.put(thing.id, thing);
                }
                break;
            case 4:
                armorArray = gson.fromJson(jsonString, Armor[].class);
                masterArmorList = new HashMap<String, Armor>();
                for (Armor thing : armorArray){
                    masterArmorList.put(thing.id, thing);
                }
                break;
            case 5:
                weaponArray = gson.fromJson(jsonString, Weapon[].class);
                masterWeaponList = new HashMap<String, Weapon>();
                for (Weapon thing : weaponArray){
                    masterWeaponList.put(thing.id, thing);
                }
                break;
            case 6:
                skillArray = gson.fromJson(jsonString, Skill[].class);
                masterSkillList = new HashMap<String, Skill>();
                for (Skill thing : skillArray){
                    masterSkillList.put(thing.id, thing);
                }
                break;
            case 7:
                spellArray = gson.fromJson(jsonString, Spell[].class);
                masterSpellList = new HashMap<String, Spell>();
                for (Spell thing : spellArray){
                    masterSpellList.put(thing.name, thing);
                }
                break;
        }//end switch
    }// end public void buildMasterHashmap()

    // Master method to create all master lists.
    public static void createMasterLists() {

    } // End public void createMasterLists.

    // Helper method to create spell master list.
    private void createMasterSpellList() {

    } // End private void createMasterSpellList.

    // Getters.  No setters are provided as no master list should change during the course of the program's operation.
    // There is a getter for the entirety of each master list, 
    // as well as a getter for the relevant Object type via ID (which acts as the HashMap key.)
    public static HashMap<String, Spell> getMasterSpellList() {
            return masterSpellList;
    }
        
    public static Spell getSpellByID(String IDIn) {
            return masterSpellList.get(IDIn);
    }

    public static HashMap<String, Skill> getMasterSkillList() {
            return masterSkillList;
    }
        
    public static Skill getSkillByID(String IDIn) {
            return masterSkillList.get(IDIn);
    }

    public static HashMap<String, Item> getMasterItemList() {
            return masterItemList;
    }
        
    public static Item getItemByID(String IDIn) {
            return masterItemList.get(IDIn);
    }

    public static HashMap<String, Weapon> getMasterWeaponList() {
            return masterWeaponList;
    }
        
    public static Weapon getWeaponByID(String IDIn) {
            return masterWeaponList.get(IDIn);
    }

    public static HashMap<String, Armor> getMasterArmorList() {
            return masterArmorList;
    }
        
    public static Armor getArmorByID(String IDIn) {
            return masterArmorList.get(IDIn);
    }

    public static HashMap<String, ClassType> getMasterClassTypeList() {
            return masterClassTypeList;
    }
        
    public static ClassType getClassTypeByID(String IDIn) {
            return masterClassTypeList.get(IDIn);
    }

    public static HashMap<String, ClassFeature> getMasterClassFeatureList() {
            return masterClassFeatureList;
    }
        
    public static ClassFeature getClassFeatureByID(String IDIn) {
            return masterClassFeatureList.get(IDIn);
    }

    public static HashMap<String, Race> getMasterRaceList() {
            return masterRaceList;
    }
        
    public static Race getRaceByID(String IDIn) {
            return masterRaceList.get(IDIn);
    }

}
