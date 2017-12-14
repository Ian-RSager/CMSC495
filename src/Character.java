//package charactercreator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.*;

/*
 * This class serves as a way to store the data that defines a given Character object.  That is to say, all the fields and information that one would reasonably
 * expect to need to know about an adventurer in Dungeons and Dragons - name, race, inventory, spells, alignment, and so on, that is NOT directly tied to the character's
 * class, race, level, or other constant values.
 *
 * To Do List:
 * spell save DCs for all stats
 * spell attack modifier for all stats
 * 
 * Change Log:
 * --12/7/2017--
 * Altered setAbilityScoreConstitution method to properly account for changes to hit point values.
 * Fully implemented recalculate() method.
 * --12/10/2017--
 * Altered removeKnownSpell method, hopefully resolving a null pointer error.
 * --12/11/2017
 * Altered removeKnownSpell AGAIN, hopefully the issue is really resolved this time.
 * Initialized all fields to deal with null pointer exceptions.
 * Added method to return a formatted String of all the Character's levels.
 * --12/12/2017--
 * Added getCharacterSkillByID method.
 * Fully created initializeCharacter method.
 * --12/13/2017--
 * Hopefully bugfixes to initializeCharacter method.
 */

public class Character implements Serializable {
    private int id = 0;                                             // Unique number ID for the character, for potential future use if more than one character can be opened at the same time.
    private String name = "Name";                                   // The character's name.
    private String race = "Race";                                   // The character's race, subraces are considered their own race.
    private int level = 0;                                          // The character's total level.
    private int[] levelArray = new int[]{0,0,0,0,0,0,0,0,0,0,0,0};  
    // The total levels the character has in each class.
    // In order, these represent levels of: (0)Barbarian, (1)Bard, (2)Cleric, (3)Druid, (4)Fighter, (5)Monk, (6)Paladin, (7)Ranger, (8)Rogue, (9)Sorcerer, (10)Warlock, (11)Wizard.
    private String[] subClassArray = new String[]{"","","","","","","","","","","",""};
    // The subclass the character has chosen for a given class.
    // In order, these represent levels of: (0)Barbarian, (1)Bard, (2)Cleric, (3)Druid, (4)Fighter, (5)Monk, (6)Paladin, (7)Ranger, (8)Rogue, (9)Sorcerer, (10)Warlock, (11)Wizard.
    private String faction = "Faction";                             // The faction the character currently belongs to.
    private int proficiencyBonus = 0;                               // The character's current proficency bonus, based on their character level.
    private int hitPointsMaximum = 0;                               // The character's maximum hit points.
    private int hitPointsCurrent = 0;                               // The character's current hit points.
    private int armorClass = 10;                                    // The character's current armor class.
    private int initiative = 0;                                     // The character's initiative modifier.
    private int passivePerception = 0;                              // The character's passive perception (which is 10 + the Perception skill value).
    private String size = "Size";                                   // The character's size (small, medium, large, etc.)
    private int speed = 30;                                         // The character's speed, in feet per round.
    private String background = "Background";                       // The character's background.
    private int experiencePoints = 0;                               // The current value of experience the character has earned.
    private String alignment = "Alignment";                         // The character's alignment.
    private ArrayList<String> languages = new ArrayList<>();        // The languages the character can speak, in plain text.
    private int weight = 0;                                         // The character's weight.
    private int height = 0;                                         // The character's height.
    private String characterImg = "characterImg";                   // The path to the file used for the character's image.
    private String sex = "Sex";                                     // The character's sex.
    private int abilityScoreStrength = 1;                           // The character's Strength value.
    private int abilityScoreDexterity = 1;                          // The character's Dexterity value.
    private int abilityScoreConstitution = 1;                       // The character's Constitution value.
    private int abilityScoreIntelligence = 1;                       // The character's Intelligence value.
    private int abilityScoreWisdom = 1;                             // The character's Wisdom value.
    private int abilityScoreCharisma = 1;                           // The character's Charisma value.
    private int[] totalSkillModifierArray = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    // The total modifier for each of the character's skills.
    // In order, these represent the modifiers for: (0)Acrobatics, (1)Animal Handling, (2)Arcana, (3)Athletics, (4)Deception, (5)History, (6)Insight, (7)Intimidation, 
    // (8)Investigation, (9)Medicine, (10)Nature, (11)Perception, (12)Performance, (13)Persuasion, (14)Religion, (15)Sleight of Hand, (16)Stealth, (17)Survival 
    private boolean[] savingThrowProficiencyArray = new boolean[]{false,false,false,false,false,false};
    // An array saving the flag of whether or not the character has proficiency in a given saving throw.
    // In order, these represent the saving throws for: (0)Strength, (1)Dexterity, (2)Constitution, (3)Intelligence, (4)Wisdom, (5)Charisma
    private int[] savingThrowFinalValueArray = new int[]{0,0,0,0,0,0};
    // An array saving the final values of each of the character's saving throws.
    // In order, these represent the saving throws for: (0)Strength, (1)Dexterity, (2)Constitution, (3)Intelligence, (4)Wisdom, (5)Charisma
    private ArrayList<String> weaponProficiencyList = new ArrayList<>();    // A list of all weapons the character has proficiency in.
    private ArrayList<String> armorProficiencyList = new ArrayList<>();     // A list of all types of armor the character has proficiency in.
    private ArrayList<String> equippedWeaponsList = new ArrayList<>();      // A list of all currently equipped weapons, via their unique IDs.
    private String equippedArmor = "Equipped Armor";                        // The unique ID of the armor currently worn by the character.
    private String equippedShield = "Equipped Shield";                      // The unique ID of the shield currently worn by the character.
    private ArrayList<Item> itemsList = new ArrayList<>();                  // A list of all items in the character's inventory.
    private ArrayList<Skill> skillsList = new ArrayList<>();                // A list of all skills, intialized as a copy from the masterSkillList.
    private ArrayList<Spell> knownSpells = new ArrayList<>();               // A list of ALL spells the character currently knows, via spellbook or innate memorization.
    private ArrayList<Spell> memorizedSpells = new ArrayList<>();           // A list of the CURRENTLY MEMORIZED spells for the character.
    private ArrayList<ClassFeature> featuresList = new ArrayList<>();       // A list of all class features that the character possesses.  These are merely listed via their IDs.
    
    // Blank constructor.
    public Character() {
        initializeSkillsList();
    } // End blank constructor.
    
    // Setters and getters.  Not all fields have a setter, due to the fact that values for these fields will be calculated internally.
    // For ID.
    public void setID(int idIn) {this.id = idIn;}
    public int getID() {return this.id;}
    
    // For name.
    public void setName(String nameIn) {this.name = nameIn;}
    public String getName() {return this.name;}
    
    // For race.
    public void setRace(String raceIn) {this.race = raceIn;}
    public String getRace() {return this.race;}
    
    // For level.  This will be automatically calculated based on the values in levelArray.
    public int getLevel() {return this.level;} // End getLevel.
    
    // For level array.  This setter method requires the programmer to enter the index to be altered, as well as the new value for that index in the level array.  Recalculates total character level.
    public void setClassLevel(int indexIn, int newLevelValueIn) {
        this.levelArray[indexIn] = newLevelValueIn;
        this.level = IntStream.of(levelArray).sum();
        // recalculate();
    } // End set class level.
    
    // Allows the programmer to get the entire level array.
    public int[] getLevelArray() {return this.levelArray;}
    
    // Allows the programmer to get a fully formatted String, displaying the character's levels in each class.  For front end use only.
    public String getLevelString() {
        String returnString = "";
        boolean firstLevelWritten = false;
        
        for (int i = 0; i < this.levelArray.length; i++) {
            if (this.levelArray[i] > 0) {    // Only display the levels a character has in a class, if they have one or more levels in that class.
                switch (i) {
                    case 0:
                        if (firstLevelWritten) {
                            returnString += " , Barbarian " + this.levelArray[i];
                        } else {
                            returnString += "Barbarian " + this.levelArray[i];
                            firstLevelWritten = true;
                        } // End if/else.
                        break;
                    case 1:
                        if (firstLevelWritten) {
                            returnString += " , Bard " + this.levelArray[i];
                        } else {
                            returnString += "Bard " + this.levelArray[i];
                            firstLevelWritten = true;
                        } // End if/else.
                        break;
                    case 2:
                        if (firstLevelWritten) {
                            returnString += " , Cleric " + this.levelArray[i];
                        } else {
                            returnString += "Cleric " + this.levelArray[i];
                            firstLevelWritten = true;
                        } // End if/else.
                        break;
                    case 3:
                        if (firstLevelWritten) {
                            returnString += " , Druid " + this.levelArray[i];
                        } else {
                            returnString += "Druid " + this.levelArray[i];
                            firstLevelWritten = true;
                        } // End if/else.
                        break;
                    case 4:
                        if (firstLevelWritten) {
                            returnString += " , Fighter " + this.levelArray[i];
                        } else {
                            returnString += "Fighter " + this.levelArray[i];
                            firstLevelWritten = true;
                        } // End if/else.
                        break;
                    case 5:
                        if (firstLevelWritten) {
                            returnString += " , Monk " + this.levelArray[i];
                        } else {
                            returnString += "Monk " + this.levelArray[i];
                            firstLevelWritten = true;
                        } // End if/else.
                        break;
                    case 6:
                        if (firstLevelWritten) {
                            returnString += " , Paladin " + this.levelArray[i];
                        } else {
                            returnString += "Paladin " + this.levelArray[i];
                            firstLevelWritten = true;
                        } // End if/else.
                        break;
                    case 7:
                        if (firstLevelWritten) {
                            returnString += " , Ranger " + this.levelArray[i];
                        } else {
                            returnString += "Ranger " + this.levelArray[i];
                            firstLevelWritten = true;
                        } // End if/else.
                        break;
                    case 8:
                        if (firstLevelWritten) {
                            returnString += " , Rogue " + this.levelArray[i];
                        } else {
                            returnString += "Rogue " + this.levelArray[i];
                            firstLevelWritten = true;
                        } // End if/else.
                        break;
                    case 9:
                        if (firstLevelWritten) {
                            returnString += " , Sorcerer " + this.levelArray[i];
                        } else {
                            returnString += "Sorcerer " + this.levelArray[i];
                            firstLevelWritten = true;
                        } // End if/else.
                        break;
                    case 10:
                        if (firstLevelWritten) {
                            returnString += " , Warlock " + this.levelArray[i];
                        } else {
                            returnString += "Warlock " + this.levelArray[i];
                            firstLevelWritten = true;
                        } // End if/else.
                        break;
                    case 11:
                        if (firstLevelWritten) {
                            returnString += " , Wizard " + this.levelArray[i];
                        } else {
                            returnString += "Wizard " + this.levelArray[i];
                            firstLevelWritten = true;
                        } // End if/else.
                        break;
                } // End switch (i).
            } // End if.
        } // End for.
        return returnString;
    } // End public String getLevelString();
    
    // For subClass array.  This setter method requires the programmer to enter the index to be altered, as well as the new value for that index in the subClass array.
    public void setSubClass(int indexIn, String subClassIn) {
        this.subClassArray[indexIn] = subClassIn;
    } // End setSubClass.
    // Allows the programmer to get the entire subclass array.
    public String[] getSubClassArray() {return this.subClassArray;}
    
    // For faction.
    public void setFaction(String factionIn) {this.faction = factionIn;}
    public String getFaction() {return this.faction;}
    
    // For proficiency bonus.  No setter method is provided as this will always be calculated internally based upon the character's total level.
    public int getProficiencyBonus() {return this.proficiencyBonus;}
    
    // For maximum hit points.
    public void setHitPointsMaximum(int hitPointsMaximumIn) {this.hitPointsMaximum = hitPointsMaximumIn;}
    public int getHitPointsMaximum() {return this.hitPointsMaximum;}
    
    // For current hit points.
    public void setHitPointsCurrent(int hitPointsCurrentIn) {this.hitPointsCurrent = hitPointsCurrentIn;}
    public int getHitPointsCurrent() {return this.hitPointsCurrent;}
    
    // For armor class.
    public void setArmorClass(int armorClassIn) {this.armorClass = armorClassIn;}
    public int getArmorClass() {return this.armorClass;}
    
    // For initiative.
    public void setInitiative(int initiativeIn) {this.initiative = initiativeIn;}
    public int getInitiative() {return this.initiative;}
    
    // For passive perception.
    public int getPassivePerception() {return this.passivePerception;}
    
    // For size.
    public void setSize(String sizeIn) {this.size = sizeIn;}
    public String getSize() {return this.size;}
    
    // For speed.
    public void setSpeed(int speedIn) {this.speed = speedIn;}
    public int getSpeed() {return this.speed;}
    
    // For background.
    public void setBackground(String backgroundIn) {this.background = backgroundIn;}
    public String getBackground() {return this.background;}
    
    // For experience points.
    public void setExperiencePoints(int experiencePointsIn) {this.experiencePoints = experiencePointsIn;}
    public int getExperiencePoints() {return this.experiencePoints;}
    
    // For alignment.
    public void setAlignment(String alignmentIn) {this.alignment = alignmentIn;}
    public String getAlignment() {return this.alignment;}
    
    // For languages.
    public void addLanguage(String languageIn) {this.languages.add(languageIn);}
    public void removeLanguage(String languageIn) {this.languages.remove(languageIn);}
    public ArrayList<String> getLanguages() {return this.languages;}
    
    // For weight.
    public void setHeight(int heightIn) {this.height = heightIn;}
    public int getHeight() {return this.height;}
    
    // For sex.
    public void setSex(String sexIn) {this.sex = sexIn;}
    public String getSex() {return this.sex;}
    
    // For character image.
    public void setCharacterImg(String characterImgIn) {this.characterImg = characterImgIn;}
    public String getCharacterImg() {return this.characterImg;}
    
    // For height.
    public void setWeight(int weightIn) {this.weight = weightIn;}
    public int getWeight() {return this.weight;}
    
    // For Strength.
    public void setAbilityScoreStrength(int strengthIn) {this.abilityScoreStrength = strengthIn;}
    public int getAbilityScoreStrength() {return this.abilityScoreStrength;}
    
    // For Dexterity.
    public void setAbilityScoreDexterity(int dexterityIn) {this.abilityScoreDexterity = dexterityIn;}
    public int getAbilityScoreDexterity() {return this.abilityScoreDexterity;}
    
    // For Constitution.
    public void setAbilityScoreConstitution(int constitutionIn) {   // Method accounts for potential alterations in maximum hit point values by reverting level-based Constitution bonuses
        int currentHPValue = this.hitPointsMaximum;                 // before changing Constutition value and then re-calculating them with the new Constitution value.
        currentHPValue -= this.level * AttributeBonusCalculator.getAttributeBonus(this.abilityScoreConstitution);
        this.abilityScoreConstitution = constitutionIn;
        this.hitPointsMaximum = currentHPValue + (this.level * AttributeBonusCalculator.getAttributeBonus(this.abilityScoreConstitution));
    } // End setAbilityScoreConstitution.
    public int getAbilityScoreConstitution() {return this.abilityScoreConstitution;}
    
    // For Intelligence.
    public void setAbilityScoreIntelligence(int intelligenceIn) {this.abilityScoreIntelligence = intelligenceIn;}
    public int getAbilityScoreIntelligence() {return this.abilityScoreIntelligence;}
    
    // For Wisdom.
    public void setAbilityScoreWisdom(int wisdomIn) {this.abilityScoreWisdom = wisdomIn;}
    public int getAbilityScoreWisdom() {return this.abilityScoreWisdom;}
    
    // For Charisma.
    public void setAbilityScoreCharisma(int charismaIn) {this.abilityScoreCharisma = charismaIn;}
    public int getAbilityScoreCharisma() {return this.abilityScoreCharisma;}
    
    // For total skill modifier array.
    public int[] getTotalSkillModifierArray() {return this.totalSkillModifierArray;}    // Getter.
    
    // For saving throw proficiency array.
    public boolean[] getSavingThrowProficiencyArray() {return this.savingThrowProficiencyArray;}    // Getter.
    public void setSavingThrowProficiencyArrayValue(int indexIn, boolean newValueIn) {              // Setter for an individual index.
        this.savingThrowProficiencyArray[indexIn] = newValueIn;
    } // End public void setSavingThrowProficiencyArrayValue().
    
    // For saving throw final value array.
    public int[] getSavingThrowFinalValueArray() {return this.savingThrowFinalValueArray;}  // Getter.
    
    // For weapon proficiency list.
    public void addWeaponProficiency(String weaponProficiencyIn) {this.weaponProficiencyList.add(weaponProficiencyIn);}
    public void removeWeaponProficiency(String weaponProficiencyIn) {this.weaponProficiencyList.remove(weaponProficiencyIn);}
    public ArrayList<String> getWeaponProficiencyList() {return this.weaponProficiencyList;}
    
    // For armor proficiency list.
    public void addArmorProficiency(String armorProficiencyIn) {this.armorProficiencyList.add(armorProficiencyIn);}
    public void removeArmorProficiency(String armorProficiencyIn) {this.armorProficiencyList.remove(armorProficiencyIn);}
    public ArrayList<String> getArmorProficiencyList() {return this.armorProficiencyList;}
    
    // For equipped weapons list.
    public void addEquippedWeapon(String weaponIn) {this.equippedWeaponsList.add(weaponIn);}
    public void removeEquippedWeapon(String weaponIn) {this.equippedWeaponsList.remove(weaponIn);}
    public ArrayList<String> getEquippedWeaponList() {return this.equippedWeaponsList;}
    
    // For equipped armor.
    public void setEquippedArmor(String armorIn) {this.equippedArmor = armorIn;}
    public String getEquippedArmor() {return this.equippedArmor;}
    
    // For equipped shield.
    public void setEquippedShield(String shieldIn) {this.equippedShield = shieldIn;}
    public String getEquippedShield() {return this.equippedShield;}
    
    // For items list.
    public ArrayList<Item> getItemsList() {return this.itemsList;} // Getter.
    
    // This method adds a new item to the character's current inventory, and sets the quantity of that item to the specified parameter.
    public void addItem(Item itemIn, int quantityToAddIn) {
        if (this.hasItem(itemIn.getID())) {
            Item itemToIncrement = this.getInventoryItemByID(itemIn.getID());
            itemToIncrement.setQuantity(itemToIncrement.getQuantity() + quantityToAddIn);
        } else {
            itemIn.setQuantity(quantityToAddIn);
            this.itemsList.add(itemIn);
        } // End if/else.
    } // End add item method.
    
    // This method removes an item from the character's current inventory.
    public void removeItem(Item itemIn) {
        this.itemsList.remove(itemIn);
    } // End remove item method.
    
    // This method changes the quantity of an item currently in the character's inventory.
    public void changeItemQuantity(Item itemIn, int newQuantityIn) {
        if (this.itemsList.contains(itemIn)) {
            Item currentItem = this.getInventoryItemByID(itemIn.getID());
            currentItem.setQuantity(newQuantityIn);
        } // End if.
    } // End change item quantity method.
    
    // A method used to check if a character currently has an item in their inventory, via scanning the item IDs in itemsList.
    public boolean hasItem(String itemIDToCheckIn) {
        for (Item currentItem : this.itemsList) {
            if (currentItem.getID().equalsIgnoreCase(itemIDToCheckIn)) {
                return true;
            } // End if.
        } // End for.
        return false;
    } // End public boolean hasItem.
    
    // A method used to get an item, by ID from the character's inventory.
    public Item getInventoryItemByID(String itemIDToGetIn) {
        for (Item currentItem : this.itemsList) {
            if (currentItem.getID().equalsIgnoreCase(itemIDToGetIn)) {
                return currentItem;
            } // End if.
        } // End for.
        return new Item();
    } // End public Item getInventoryItemByID.
    
    // For skillsList.
    public ArrayList<Skill> getSkillsList() {return this.skillsList;} // Getter.
    
    // A method used to get a skill in the character's skillsList by ID.
    public Skill getCharacterSkillByID(String skillIDToGetIn) {
        for (Skill currentSkill : this.skillsList) {
            if (currentSkill.getID().equalsIgnoreCase(skillIDToGetIn)) {
                return currentSkill;
            } // End if.
        } // End for.
        return MasterLists.getSkillByID("skillAthletics");
    } // End public Skill getCharacterSkillByID.
    
    // Only to be used when creating a new character.  Copies the master list of Skill objects into skillsList, overwriting all data.
    public void initializeSkillsList() {
        Map<String, Skill> currentMasterSkillMap = MasterLists.getMasterSkillList();
        
        for (Skill skill : currentMasterSkillMap.values()) {
            this.skillsList.add(skill);
        } // End for.
        
        // Sort the skillsList via ID, providing alphabetical order.
        SkillIDCompare skillIDcompare = new SkillIDCompare();
        Collections.sort(skillsList, skillIDcompare);
    } // End public void initializeSkillsList.
    
    // Private class for comparing skill IDs.
    private class SkillIDCompare implements Comparator<Skill> {
        public int compare(Skill skill1, Skill skill2) {
            return(skill1.getID().compareTo(skill2.getID()));
        } // End public int compare.
    } // End private class SkillIDCompare.
    
    // For adding a spell to the Character's knownSpells list.
    public void addKnownSpell(String spellIDIn) {
        this.knownSpells.add(MasterLists.getSpellByID(spellIDIn));
    } // End public void addKnownSpell.
    
    // For removing a spell from the Character's knownSpells list.
    public void removeKnownSpell(Spell spellToRemoveIn) {
            ListIterator<Spell> iter = (ListIterator<Spell>) this.getKnownSpells().listIterator();
            while (iter.hasNext()) {
                if (iter.next().getName().equals(spellToRemoveIn.getName())) {
                    iter.remove();
                } // End if.
            } // End while.
    } // End public void removeKnownSpell.
    
    // For getting the list of all known spells.
    public ArrayList<Spell> getKnownSpells() {return this.knownSpells;}
    
    // For adding a spell to the Character's memorizedSpells list.
    public void addMemorizedSpell(String spellIDIn) {
        this.memorizedSpells.add(MasterLists.getSpellByID(spellIDIn));
    } // End public void addKnownSpell.
    
    // For removing a spell from the Character's memorizedSpells list.
    public void removeMemorizedSpell(Spell spellToRemoveIn) {
        if (this.memorizedSpells.contains(spellToRemoveIn)) {
            this.memorizedSpells.remove(spellToRemoveIn);
        } // End if.
    } // End public void removeKnownSpell.
    
    // For getting the list of all known spells.
    public ArrayList<Spell> getMemorizedSpells() {return this.memorizedSpells;}
    
    // For adding a feature to the Character's featuresList.
    public void addClassFeature(String classFeatureIDIn) {
        this.featuresList.add(MasterLists.getClassFeatureByID(classFeatureIDIn));
    } // End public void addClassFeature.
    
    // For removing a feature from the Character's featuresList.
    public void removeClassFeature(ClassFeature classFeatureToRemoveIn) {
        if (this.featuresList.contains(classFeatureToRemoveIn)) {
            this.featuresList.remove(classFeatureToRemoveIn);
        } // End if.
    } // End public void removeClassFeature.
    
    // For getting the list of all known features.
    public ArrayList<ClassFeature> getFeaturesList() {return this.featuresList;}
    
    /////////////////////////////////
    // Initialize Character Method //
    /////////////////////////////////
    
    // This method will initialize the character's starting languages, equipment, saving throws, and skills, based upon their race and class choices.
    public void initializeCharacter() {
        this.languages.add("Common"); // All races can speak Common.
        
        switch (this.race) {
            case "Hill Dwarf": case "Mountain Dwarf":
                this.languages.add("Dwarvish");
                break;
            case "High Elf":
                this.languages.add("Elvish");
                this.languages.add("Celestial");
                break;
            case "Dark Elf":
                this.languages.add("Elvish");
                this.languages.add("Undercommon");
                break;
            case "Wood Elf":
                this.languages.add("Elvish");
                this.languages.add("Sylvan");
                break;
            case "Stout": case "LightFoot":
                this.languages.add("Halfling");
                break;
            case "Human":
                this.languages.add("Orc");
                break;
            case "Dragonborn":
                this.languages.add("Draconic");
                break;
            case "Forest Gnome": case "Rock Gnome":
                this.languages.add("Gnomish");
                break;
            case "Half-Elf":
                this.languages.add("Elvish");
                this.languages.add("Orc");
                break;
            case "Half-Orc":
                this.languages.add("Orc");
                break;
            case "Tiefling":
                this.languages.add("Infernal");
                break;
        } // End switch (this.race).
        
        // Determine the index of the class so that all saving throws, equipment, and skills can be assigned properly, based upon this chosen class.
        int classIndex = 0;
        for (int i = 0; i < this.levelArray.length; i++) {
            if (this.levelArray[i] != 0) {
                classIndex = i;
                break;
            } // End if.
        } // End for.
        
        switch (classIndex) {
            case 0: // Barbarian
                populateLevelOneFeatures("classBarbarian");                                             // Populate level 1 Barbarian features.
                this.savingThrowProficiencyArray = new boolean[]{true,false,true,false,false,false};    // Barbarians are proficient in Strength and Constitution saving throws.
                this.getCharacterSkillByID("skillAthletics").setIsProficient(true);                     // Barbarians are proficient in Athletics and Intimidation.
                this.getCharacterSkillByID("skillIntimidation").setIsProficient(true);
                this.addItem(MasterLists.getWeaponByID("weaponGreataxe"), 1);                           // Barbarian starting gear includes a greataxe...
                this.addItem(MasterLists.getWeaponByID("weaponHandaxe"), 2);                            // 2 handaxes...
                this.addItem(MasterLists.getItemByID("itemExplorersPack"), 1);                          // explorer's pack...
                this.addItem(MasterLists.getWeaponByID("weaponJavelin"), 4);                            // and 4 javelins.
                break;
            case 1: // Bard
                populateLevelOneFeatures("classBard");                                                  // Populate level 1 Bard features.
                this.savingThrowProficiencyArray = new boolean[]{false,true,false,false,false,true};    // Bards are proficient in Dexterity and Charisma saving throws.
                this.getCharacterSkillByID("skillPerformance").setIsProficient(true);                   // Bards are proficient in Performance, Persuasion, and Sleight of Hand.
                this.getCharacterSkillByID("skillPersuasion").setIsProficient(true);
                this.getCharacterSkillByID("skillSleightOfHand").setIsProficient(true);
                this.addItem(MasterLists.getWeaponByID("weaponRapier"), 1);                             // Bard starting gear includes a rapier...
                this.addItem(MasterLists.getItemByID("itemDiplomatsPack"), 1);                          // diplomat's pack...
                this.addItem(MasterLists.getItemByID("itemLute"), 1);                                   // and a lute.
                break;
            case 2: // Cleric
                populateLevelOneFeatures("classCleric");                                                // Populate level 1 Cleric features.
                this.savingThrowProficiencyArray = new boolean[]{false,false,false,false,true,true};    // Clerics are proficient in Wisdom and Charisma saving throws.
                this.getCharacterSkillByID("skillInsight").setIsProficient(true);                       // Clerics are proficient in Insight and Religion.
                this.getCharacterSkillByID("skillReligion").setIsProficient(true);
                this.addItem(MasterLists.getWeaponByID("weaponMace"), 1);                               // Cleric starting gear includes a mace...
                this.addItem(MasterLists.getArmorByID("armorScaleMailMediumArmor"), 1);                 // scale mail...
                this.addItem(MasterLists.getWeaponByID("weaponCrossbowLight"), 1);                      // light crossbow...
                this.addItem(MasterLists.getItemByID("itemCrossbowBolts20"), 1);                        // 20 crossbow bolts...
                this.addItem(MasterLists.getArmorByID("armorShield"), 1);                               // shield...
                this.addItem(MasterLists.getItemByID("itemReliquary"), 1);                              // and a holy symbol in the form of a reliquary.
                break;
            case 3: // Druid
                populateLevelOneFeatures("classDruid");                                                 // Populate level 1 Druid features.
                this.savingThrowProficiencyArray = new boolean[]{false,false,false,true,true,false};    // Druids are proficient in Intelligence and Wisdom saving throws.
                this.getCharacterSkillByID("skillNature").setIsProficient(true);                        // Druids are proficient in Nature and Perception.
                this.getCharacterSkillByID("skillPerception").setIsProficient(true);
                this.addItem(MasterLists.getArmorByID("armorShield"), 1);                               // Druid starting gear includes a shield...
                this.addItem(MasterLists.getWeaponByID("weaponScimitar"), 1);                           // scimitar...
                this.addItem(MasterLists.getArmorByID("armorLeatherLightArmor"), 1);                    // leather armor...
                this.addItem(MasterLists.getItemByID("itemExplorersPack"), 1);                          // explorer's pack...
                this.addItem(MasterLists.getItemByID("itemSprigOfMistletoe"), 1);                       // and a druidic focus in the form of a sprig of mistletoe.
                break;
            case 4: // Fighter
                populateLevelOneFeatures("classFighter");                                               // Populate level 1 Fighter features.
                this.savingThrowProficiencyArray = new boolean[]{true,false,true,false,false,false};    // Fighters are proficient in Strength and Constitution saving throws.
                this.getCharacterSkillByID("skillAthletics").setIsProficient(true);                     // Fighters are proficient in Athletics and Perception.
                this.getCharacterSkillByID("skillPerception").setIsProficient(true);
                this.addItem(MasterLists.getArmorByID("armorChainMailHeavyArmor"), 1);                  // Fighter starting gear includes chainmail...
                this.addItem(MasterLists.getWeaponByID("weaponLongsword"), 1);                          // longsword...
                this.addItem(MasterLists.getArmorByID("armorShield"), 1);                               // shield...
                this.addItem(MasterLists.getWeaponByID("weaponCrossbowLight"), 1);                      // light crossbow...
                this.addItem(MasterLists.getItemByID("itemCrossbowBolts20"), 1);                        // 20 crossbow bolts...
                this.addItem(MasterLists.getItemByID("itemDungeoneersPack"), 1);                        // and a dungeoneer's pack.
                break;
            case 5: // Monk
                populateLevelOneFeatures("classMonk");                                                  // Populate level 1 Monk features.
                this.savingThrowProficiencyArray = new boolean[]{true,true,false,false,false,false};    // Monks are proficient in Strength and Dexterity saving throws.
                this.getCharacterSkillByID("skillAcrobatics").setIsProficient(true);                    // Monks are proficient in Acrobatics and Athletics.
                this.getCharacterSkillByID("skillAthletics").setIsProficient(true);
                this.addItem(MasterLists.getWeaponByID("weaponQuarterstaff"), 1);                       // Monk starting gear includes a quarterstaff...
                this.addItem(MasterLists.getItemByID("itemDungeoneersPack"), 1);                        // dungeoneer's pack...
                this.addItem(MasterLists.getWeaponByID("weaponDart"), 10);                              // and 10 darts.
                break;
            case 6: // Paladin
                populateLevelOneFeatures("classPaladin");                                               // Populate level 1 Paladin features.
                this.savingThrowProficiencyArray = new boolean[]{false,false,false,false,true,true};    // Paladins are proficient in Wisdom and Charisma saving throws.
                this.getCharacterSkillByID("skillPersuasion").setIsProficient(true);                    // Paladins are proficient in Persuasion and Religion.
                this.getCharacterSkillByID("skillReligion").setIsProficient(true);
                this.addItem(MasterLists.getWeaponByID("weaponWarhammer"), 1);                          // Paladin starting gear includes a warhammer...
                this.addItem(MasterLists.getArmorByID("armorShield"), 1);                               // shield...
                this.addItem(MasterLists.getWeaponByID("weaponJavelin"), 5);                            // 5 javelins...
                this.addItem(MasterLists.getItemByID("itemPriestsPack"), 1);                            // priests' pack...
                this.addItem(MasterLists.getArmorByID("armorChainMailHeavyArmor"), 1);                  // chainmail...
                this.addItem(MasterLists.getItemByID("itemAmulet"), 1);                                 // and a holy symbol in the form of an amulet.
                break;
            case 7: // Ranger
                populateLevelOneFeatures("classRanger");                                                // Populate level 1 Ranger features.
                this.savingThrowProficiencyArray = new boolean[]{true,true,false,false,false,false};    // Rangers are proficient in Strength and Dexterity saving throws.
                this.getCharacterSkillByID("skillAnimalHandling").setIsProficient(true);                // Rangers are proficient in Animal Handling and Survival.
                this.getCharacterSkillByID("skillSurvival").setIsProficient(true);
                this.addItem(MasterLists.getArmorByID("armorScaleMailMediumArmor"), 1);                 // Ranger starting gear includes scale mail...
                this.addItem(MasterLists.getWeaponByID("weaponShortsword"), 2);                         // two shortswords...
                this.addItem(MasterLists.getItemByID("itemExplorersPack"), 1);                          // explorer's pack...
                this.addItem(MasterLists.getWeaponByID("weaponLongbow"), 1);                            // longbow...
                this.addItem(MasterLists.getItemByID("itemQuiver"), 1);                                 // quiver...
                this.addItem(MasterLists.getItemByID("itemArrows20"), 1);                               // and 20 arrows.
                break;
            case 8: // Rogue
                populateLevelOneFeatures("classRogue");                                                 // Populate level 1 Rogue features.
                this.savingThrowProficiencyArray = new boolean[]{false,true,false,true,false,false};    // Rogues are proficient in Dexterity and Intelligence saving throws.
                this.getCharacterSkillByID("skillAcrobatics").setIsProficient(true);                    // Rogues are proficient in Acrobatics, Deception, Perception, and Stealth.
                this.getCharacterSkillByID("skillDeception").setIsProficient(true);
                this.getCharacterSkillByID("skillPerception").setIsProficient(true);
                this.getCharacterSkillByID("skillStealth").setIsProficient(true);
                this.addItem(MasterLists.getWeaponByID("weaponShortsword"), 1);                         // Rogue starting gear includes a shortsword...
                this.addItem(MasterLists.getWeaponByID("weaponShortbow"), 1);                           // shortbow...
                this.addItem(MasterLists.getItemByID("itemQuiver"), 1);                                 // quiver...
                this.addItem(MasterLists.getItemByID("itemArrows20"), 1);                               // 20 arrows...
                this.addItem(MasterLists.getItemByID("itemBurglarsPack"), 1);                           // burglar's pack...
                this.addItem(MasterLists.getArmorByID("armorLeatherLightArmor"), 1);                    // leather armor...
                this.addItem(MasterLists.getWeaponByID("weaponDagger"), 2);                             // 2 daggers...
                this.addItem(MasterLists.getItemByID("itemThievesTools"), 1);                           // and thieves' tools.
                break;
            case 9: // Sorcerer
                populateLevelOneFeatures("classSorcerer");                                              // Populate level 1 Sorcerer features.
                this.savingThrowProficiencyArray = new boolean[]{false,false,true,false,false,true};    // Sorcerers are proficient in Constitution and Charisma saving throws.
                this.getCharacterSkillByID("skillArcana").setIsProficient(true);                        // Sorcerers are proficient in Arcana and Persuasion.
                this.getCharacterSkillByID("skillPersuasion").setIsProficient(true);
                this.addItem(MasterLists.getWeaponByID("weaponQuarterstaff"), 1);                       // Sorcerer starting gear includes a quarterstaff...
                this.addItem(MasterLists.getItemByID("itemComponentPouch"), 1);                         // component pouch...
                this.addItem(MasterLists.getItemByID("itemDungeoneersPack"), 1);                        // dungeoneer's pack...
                this.addItem(MasterLists.getWeaponByID("weaponDagger"), 2);                             // and two daggers.
                break;
            case 10: // Warlock
                populateLevelOneFeatures("classWarlock");                                               // Populate level 1 Warlock features.
                this.savingThrowProficiencyArray = new boolean[]{false,false,false,false,true,true};    // Warlocks are proficient in Wisdom and Charisma saving throws.
                this.getCharacterSkillByID("skillArcana").setIsProficient(true);                        // Warlocks are proficient in Arcana and Investigation.
                this.getCharacterSkillByID("skillInvestigation").setIsProficient(true); 
                this.addItem(MasterLists.getWeaponByID("weaponQuarterstaff"), 1);                       // Warlock starting gear includes a quarterstaff...
                this.addItem(MasterLists.getItemByID("itemComponentPouch"), 1);                         // component pouch...
                this.addItem(MasterLists.getItemByID("itemScholarsPack"), 1);                           // scholar's pack...
                this.addItem(MasterLists.getArmorByID("armorLeatherLightArmor"), 1);                    // leather armor...
                this.addItem(MasterLists.getWeaponByID("weaponHandaxe"), 1);                            // handaxe...
                this.addItem(MasterLists.getWeaponByID("weaponDagger"), 2);                             // and two daggers.
                break;
            case 11: // Wizard
                populateLevelOneFeatures("classWizard");                                                // Populate level 1 Wizard features.
                this.savingThrowProficiencyArray = new boolean[]{false,false,false,true,true,false};    // Wizards are proficient in Intelligence and Wisdom saving throws.
                this.getCharacterSkillByID("skillArcana").setIsProficient(true);                        // Wizards are proficient in Arcana and History.
                this.getCharacterSkillByID("skillHistory").setIsProficient(true);
                this.addItem(MasterLists.getWeaponByID("weaponQuarterstaff"), 1);                       // Wizard starting gear includes a quarterstaff...
                this.addItem(MasterLists.getItemByID("itemComponentPouch"), 1);                         // component pouch...
                this.addItem(MasterLists.getItemByID("itemScholarsPack"), 1);                           // scholar's pack...
                this.addItem(MasterLists.getItemByID("itemSpellbook"), 1);                              // and a spellbook.
                break;
        } // End switch (classIndex).   
    } // End public void initializeCharacter.
    
    // Helper method to add level 1 features to the class based upon the class ID.
    private void populateLevelOneFeatures(String classIDIn) {
        ClassType currentClass = MasterLists.getClassTypeByID(classIDIn);
        
        String featureString[] = currentClass.getFeatures()[0];
            
        for (String feature : featureString) {
            this.featuresList.add(MasterLists.getClassFeatureByID(feature));
        } // End for.
    } // End private void populateLevelOneFeatures.
    
    ////////////////////////
    // Recalculate Method //
    ////////////////////////
    
    // This method will recalculate and re-correlate all data in Character.java.  To be used whenever an Attribute or any other notable field changes.
    public void recalculate() {
        int preCalculateHitPointMaxBase = this.hitPointsMaximum - (this.level * AttributeBonusCalculator.getAttributeBonus(this.abilityScoreConstitution));     // Calculate base hitpoints.
        this.level = IntStream.of(this.levelArray).sum();                                                                                                       // Recalculate level, as all other values key off this.
        this.hitPointsMaximum = preCalculateHitPointMaxBase + (this.level * AttributeBonusCalculator.getAttributeBonus(this.abilityScoreConstitution));         // Calculate max hitpoints based on current level.
        // Now, recalculate proficiency bonus.
        switch (this.level) {
            case 1: case 2: case 3: case 4:
                this.proficiencyBonus = 2;
                break;
            case 5: case 6: case 7: case 8:
                this.proficiencyBonus = 3;
                break;
            case 9: case 10: case 11: case 12:
                this.proficiencyBonus = 4;
                break;
            case 13: case 14: case 15: case 16:
                this.proficiencyBonus = 5;
                break;
            case 17: case 18: case 19: case 20:
                this.proficiencyBonus = 6;
                break;
            default:
                this.proficiencyBonus = 2;
                break;
        } // End switch (this.level).
        this.initiative = AttributeBonusCalculator.getAttributeBonus(this.abilityScoreDexterity);  // Recalculate initiative.
        // Iterate through skills list.  Update bonuses with new values.  Dump total values into totalSkillModifierArray.
        for (int i = 0; i < skillsList.size(); i++) {
            Skill currentSkill = skillsList.get(i);
            String relevantAbilityScore = currentSkill.getRelevantAbilityScore().toLowerCase();
            switch (relevantAbilityScore) {
                case "strength":
                    if (currentSkill.getIsProficient()) {
                        totalSkillModifierArray[i] = this.proficiencyBonus + AttributeBonusCalculator.getAttributeBonus(this.abilityScoreStrength) + currentSkill.getMiscBonus();
                    } else {
                        totalSkillModifierArray[i] = AttributeBonusCalculator.getAttributeBonus(this.abilityScoreStrength) + currentSkill.getMiscBonus();
                    } // End if else.
                    break;
                case "dexterity":
                    if (currentSkill.getIsProficient()) {
                        totalSkillModifierArray[i] = this.proficiencyBonus + AttributeBonusCalculator.getAttributeBonus(this.abilityScoreDexterity) + currentSkill.getMiscBonus();
                    } else {
                        totalSkillModifierArray[i] = AttributeBonusCalculator.getAttributeBonus(this.abilityScoreDexterity) + currentSkill.getMiscBonus();
                    } // End if else.
                    break;
                case "constitution":
                    if (currentSkill.getIsProficient()) {
                        totalSkillModifierArray[i] = this.proficiencyBonus + AttributeBonusCalculator.getAttributeBonus(this.abilityScoreConstitution) + currentSkill.getMiscBonus();
                    } else {
                        totalSkillModifierArray[i] = AttributeBonusCalculator.getAttributeBonus(this.abilityScoreConstitution) + currentSkill.getMiscBonus();
                    } // End if else.
                    break;
                case "intelligence":
                    if (currentSkill.getIsProficient()) {
                        totalSkillModifierArray[i] = this.proficiencyBonus + AttributeBonusCalculator.getAttributeBonus(this.abilityScoreIntelligence) + currentSkill.getMiscBonus();
                    } else {
                        totalSkillModifierArray[i] = AttributeBonusCalculator.getAttributeBonus(this.abilityScoreIntelligence) + currentSkill.getMiscBonus();
                    } // End if else.
                    break;
                case "wisdom":
                    if (currentSkill.getIsProficient()) {
                        totalSkillModifierArray[i] = this.proficiencyBonus + AttributeBonusCalculator.getAttributeBonus(this.abilityScoreWisdom) + currentSkill.getMiscBonus();
                    } else {
                        totalSkillModifierArray[i] = AttributeBonusCalculator.getAttributeBonus(this.abilityScoreWisdom) + currentSkill.getMiscBonus();
                    } // End if else.
                    break;
                case "charisma":
                    if (currentSkill.getIsProficient()) {
                        totalSkillModifierArray[i] = this.proficiencyBonus + AttributeBonusCalculator.getAttributeBonus(this.abilityScoreCharisma) + currentSkill.getMiscBonus();
                    } else {
                        totalSkillModifierArray[i] = AttributeBonusCalculator.getAttributeBonus(this.abilityScoreCharisma) + currentSkill.getMiscBonus();
                    } // End if else.
                    break;
            } // End switch.
        } // End for.
        // Update values for saving throws.
        for (int i = 0; i < this.savingThrowProficiencyArray.length; i++) {
            switch (i) {
                case 0:
                    if (this.savingThrowProficiencyArray[0] == true) {
                        this.savingThrowFinalValueArray[0] = this.proficiencyBonus + AttributeBonusCalculator.getAttributeBonus(this.abilityScoreStrength);
                    } else {
                        this.savingThrowFinalValueArray[0] = AttributeBonusCalculator.getAttributeBonus(this.abilityScoreStrength);
                    } // End if/else.
                    break;
                case 1:
                    if (this.savingThrowProficiencyArray[1] == true) {
                        this.savingThrowFinalValueArray[1] = this.proficiencyBonus + AttributeBonusCalculator.getAttributeBonus(this.abilityScoreDexterity);
                    } else {
                        this.savingThrowFinalValueArray[1] = AttributeBonusCalculator.getAttributeBonus(this.abilityScoreDexterity);
                    } // End if/else.
                    break;
                case 2:
                    if (this.savingThrowProficiencyArray[2] == true) {
                        this.savingThrowFinalValueArray[2] = this.proficiencyBonus + AttributeBonusCalculator.getAttributeBonus(this.abilityScoreConstitution);
                    } else {
                        this.savingThrowFinalValueArray[2] = AttributeBonusCalculator.getAttributeBonus(this.abilityScoreConstitution);
                    } // End if/else.
                    break;
                case 3:
                    if (this.savingThrowProficiencyArray[3] == true) {
                        this.savingThrowFinalValueArray[3] = this.proficiencyBonus + AttributeBonusCalculator.getAttributeBonus(this.abilityScoreIntelligence);
                    } else {
                        this.savingThrowFinalValueArray[3] = AttributeBonusCalculator.getAttributeBonus(this.abilityScoreIntelligence);
                    } // End if/else.
                    break;
                case 4:
                    if (this.savingThrowProficiencyArray[4] == true) {
                        this.savingThrowFinalValueArray[4] = this.proficiencyBonus + AttributeBonusCalculator.getAttributeBonus(this.abilityScoreWisdom);
                    } else {
                        this.savingThrowFinalValueArray[4] = AttributeBonusCalculator.getAttributeBonus(this.abilityScoreWisdom);
                    } // End if/else.
                    break;
                case 5:
                    if (this.savingThrowProficiencyArray[5] == true) {
                        this.savingThrowFinalValueArray[5] = this.proficiencyBonus + AttributeBonusCalculator.getAttributeBonus(this.abilityScoreCharisma);
                    } else {
                        this.savingThrowFinalValueArray[5] = AttributeBonusCalculator.getAttributeBonus(this.abilityScoreCharisma);
                    } // End if/else.
                    break;
            } // End switch (i).
        } // End for.
        // Recalculate passive perception.
        for (Skill characterSkill : this.skillsList) {
            if (characterSkill.getID().equalsIgnoreCase("skillPerception")) {
                if (characterSkill.getIsProficient()) {
                    this.passivePerception = 10 + this.proficiencyBonus + AttributeBonusCalculator.getAttributeBonus(this.abilityScoreWisdom) + characterSkill.getMiscBonus();
                } else {
                    this.passivePerception = 10 + AttributeBonusCalculator.getAttributeBonus(this.abilityScoreWisdom) + characterSkill.getMiscBonus();
                } // End if/else.
            } // End if.
        } // End for.
    } // End recalculate method.
    
} // End public class Character.