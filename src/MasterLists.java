/**
 * MasterLists.java  -- Created by cwilbur on 12/3/17.
 * CMSC495 -- Class project
 * Class responsible for reading in multiple JSON data files
 * and creating applicable objects from data.
 */

import java.io.BufferedReader;
        import java.io.FileNotFoundException;
        import java.io.FileReader;
        import java.io.IOException;
        import java.util.HashMap;

        import com.google.gson.Gson;

public class MasterLists {
    private static HashMap<String, Spell> masterSpellList;                  // Master spell list.
    private static HashMap<String, Skill> masterSkillList;                  // Master skill list.
    private static HashMap<String, Item> masterItemList;                    // Master item list.
    private static HashMap<String, Weapon> masterWeaponList;                // Master weapon list.
    private static HashMap<String, Armor> masterArmorList;                  // Master armor list.
    private static HashMap<String, ClassType> masterClassTypeList;          // Master character class list.
    private static HashMap<String, ClassFeature> masterClassFeatureList;    // Master class feature list.
    private static HashMap<String, Race> masterRaceList;                    // Master character race list.

    private static ClassType[] classTypeArray;
    private static Spell[] spellArray;
    private static Skill[] skillArray;
    private static Race[] raceArray;
    private static ClassFeature[] classFeatureArray;
    private static Weapon[] weaponArray;
    private static Armor[] armorArray;
    private static Item[] itemArray;


    protected FileReader fileReader;
    protected BufferedReader bufferedReader;
    protected String jsonString = "";
    protected Thing[] thingArray;

    protected Gson gson;
    protected HashMap<String, Thing> masterThingMap;


    // array of file names
    private static String fileNames[] = {"classTypes.json", "race.json", "TestFeaturesList.json",
            "items.json", "armor.json", "weapons.json", "skill.json", "spells.json"};

    private static Class[] classList= { ClassType.class, Race.class, ClassFeature.class,
    Item.class, Armor.class, Weapon.class, Skill.class, Spell.class};

    private static HashMap[] mapList = {masterClassTypeList, masterRaceList, masterClassFeatureList,
    masterItemList, masterArmorList, masterWeaponList, masterSpellList, masterSkillList};
    private static Object[] arrayList ={classTypeArray, raceArray, classFeatureArray, itemArray,
    armorArray, weaponArray, skillArray, spellArray};

    //QC tool to indicate
    private boolean[] flags = {false, false,false, false,false, false,false, false };

    //Constructor
    public MasterLists() {

        for (int i =0; i<flags.length ; i++ ){
            fileReader(fileNames[i]);
            buildMasterHashMap( i );
        }

        // attempt to loop through creation of hashmap which will replace buildMasterHashMap
        // Currenlty:  (line 71) classList[i] is not recognized as a class
        // currently:  (line 72) foreach is not applicable to arrrayList
//        for(int i =0 ; i <flags.length ; i++){
//            fileReader(fileNames[i]);
//            mapList[i] = new HashMap<String, classList[i]>();
//            for (ClassType thing : arrayList[i])
//                mapList[i].put(thing.name, thing);
//        }

        //QC to verify that each file was read.
        for (int k = 0 ; k < 8 ; k++){
            if (flags[k])
                System.out.println(fileNames[k]+ " is complete: ");
            else
                System.out.println("false" +k);
        }
    }

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

    }

    public void buildMasterHashMap(int num){
        gson = new Gson();

        switch(num){
            case 0:
                classTypeArray = gson.fromJson(jsonString, ClassType[].class);
                masterClassTypeList = new HashMap<String, ClassType>();
                for (ClassType thing : classTypeArray) {
                    masterClassTypeList.put(thing.name, thing);
                    thing.printData();
                }
                break;
            case 1:
                raceArray = gson.fromJson(jsonString, Race[].class);
                masterRaceList = new HashMap<String, Race>();
                for (Race thing : raceArray){
                    masterRaceList.put(thing.name, thing);
                thing.printData();
                }
                break;
            case 2:
                classFeatureArray = gson.fromJson(jsonString, ClassFeature[].class);
                masterClassFeatureList = new HashMap<String, ClassFeature>();
                for (ClassFeature thing : classFeatureArray){
                    masterClassFeatureList.put(thing.name, thing);
                    thing.printData();
                }
                break;
            case 3:
                itemArray = gson.fromJson(jsonString, Item[].class);
                masterItemList = new HashMap<String, Item>();
                for (Item thing : itemArray){
                    masterItemList.put(thing.name, thing);
                    thing.printData();
                }
                break;
            case 4:
                armorArray = gson.fromJson(jsonString, Armor[].class);
                masterArmorList = new HashMap<String, Armor>();
                for (Armor thing : armorArray){
                    masterArmorList.put(thing.name, thing);
                    thing.printData();
                }
                break;
            case 5:
                weaponArray = gson.fromJson(jsonString, Weapon[].class);
                masterWeaponList = new HashMap<String, Weapon>();
                for (Weapon thing : weaponArray){
                    masterWeaponList.put(thing.name, thing);
                    thing.printData();
                }
                break;
            case 6:
                skillArray = gson.fromJson(jsonString, Skill[].class);
                masterSkillList = new HashMap<String, Skill>();
                for (Skill thing : skillArray){
                    masterSkillList.put(thing.name, thing);
                    thing.printData();
                }
                break;
            case 7:
                spellArray = gson.fromJson(jsonString, Spell[].class);
                masterSpellList = new HashMap<String, Spell>();
                for (Spell thing : spellArray){
                    masterSpellList.put(thing.name, thing);
                    thing.printData();
                }
                break;
        }

        flags[num]=true;
    }

    // Master method to create all master lists.
    public static void createMasterLists() {

    } // End public void createMasterLists.

    // Helper method to create spell master list.
    private void createMasterSpellList() {

    } // End private void createMasterSpellList.


    // Getter method using id
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

}
