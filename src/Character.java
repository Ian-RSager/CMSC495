package charactercreator;

import java.util.ArrayList;

/*
 * This class serves as a way to store the data that defines a given Character object.  That is to say, all the fields and information that one would reasonably
 * expect to need to know about an adventurer in Dungeons and Dragons - name, race, inventory, spells, alignment, and so on, that is NOT directly tied to the character's
 * class, race, level, or other constant values.
 */

public class Character {
    private int id;                                                 // Unique number ID for the character, for potential future use if more than one character can be opened at the same time.
    private String name;                                            // The character's name.
    private String race;                                            // The character's race, subraces are considered their own race.
    private String size;                                            // The character's size (small, medium, large, etc.)
    private int speed;                                              // The character's speed, in feet per round.
    private String background;                                      // The character's background.
    private int level;                                              // The character's total level.
    private int experiencePoints;                                   // The current value of experience the character has earned.
    private int[] levelArray = new int[]{0,0,0,0,0,0,0,0,0,0,0,0};  // The total levels the character has in each class.  In order, these represent levels of: Barbarian, Bard, Cleric, Druid, Fighter, Monk, Paladin, Ranger, Rogue, Sorcerer, Warlock, Wizard.
    private String alignment;                                       // The character's alignment.
    private int abilityScoreStrength;                               // The character's Strength value.
    private int abilityScoreDexterity;                              // The character's Dexterity value.
    private int abilityScoreConstitution;                           // The character's Constitution value.
    private int abilityScoreIntelligence;                           // The character's Intelligence value.
    private int abilityScoreWisdom;                                 // The character's Wisdom value.
    private int abilityScoreCharisma;                               // The character's Charisma value.
    private ArrayList<Item> itemsList = new ArrayList<>();          // A list of all items in the character's inventory.
    private ArrayList<String> skillsList = new ArrayList<>();       // A list of all skills the character is proficient in.  The arrayList should contain the String ids of each skill.
    private ArrayList<Spell> knownSpells = new ArrayList<>();       // A list of ALL spells the character currently knows, via spellbook or innate memorization.
    private ArrayList<Spell> memorizedSpells = new ArrayList<>();   // A list of the CURRENTLY MEMORIZED spells for the character.
    
    // Setters and getters.
    // For ID.
    public void setID(int idIn) {this.id = idIn;}
    public int getID() {return this.id;}
    // For name.
    public void setName(String nameIn) {this.name = nameIn;}
    public String getName() {return this.name;}
    // For race.
    public void setRace(String raceIn) {this.race = raceIn;}
    public String getRace() {return this.race;}
    // For size.
    public void setSize(String sizeIn) {this.size = sizeIn;}
    public String getSize() {return this.size;}
    // For speed.
    public void setSpeed(int speedIn) {this.speed = speedIn;}
    public int getSpeed() {return this.speed;}
    // For background.
    public void setBackground(String backgroundIn) {this.background = backgroundIn;}
    public String getBackground() {return this.background;}
    // For level.
    public void setLevel(int levelIn) {this.level = levelIn;}
    public int getLevel() {return this.level;}
    // For experience points.
    public void setExperiencePoints(int experiencePointsIn) {this.experiencePoints = experiencePointsIn;}
    public int getExperiencePoints() {return this.experiencePoints;}
    // For level array.  This setter method requires the programmer to enter the index to be altered, as well as the new value for that index in the level array.
    public void setClassLevel(int indexIn, int newLevelValueIn) {
        this.levelArray[indexIn] = newLevelValueIn;
    } // End set class level.
    // Allows the programmer to get the entire level array.
    public int[] getLevelArray() {return this.levelArray;}
    // For alignment.
    public void setAlignment(String alignmentIn) {this.alignment = alignmentIn;}
    public String getAlignment() {return this.alignment;}
    // For Strength.
    public void setAbilityScoreStrength(int strengthIn) {this.abilityScoreStrength = strengthIn;}
    public int getAbilityScoreStrength() {return this.abilityScoreStrength;}
    // For Dexterity.
    public void setAbilityScoreDexterity(int dexterityIn) {this.abilityScoreDexterity = dexterityIn;}
    public int getAbilityScoreDexterity() {return this.abilityScoreDexterity;}
    // For Constitution.
    public void setAbilityScoreConstitution(int constitutionIn) {this.abilityScoreConstitution = constitutionIn;}
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
    // For items list.
    // This method adds a new item to the character's current inventory.  If the item already exists in the character's inventory, the quantity is incremented by one.
    public void addItem(Item itemIn, int quantityToAddIn) {
        if (this.hasItem(itemIn.getID())) {
            Item itemToIncrement = this.getInventoryItemByID(itemIn.getID());
            itemToIncrement.setQuantity(itemToIncrement.getQuantity() + quantityToAddIn);
        } else {
            this.itemsList.add(itemIn);
            itemIn.setQuantity(quantityToAddIn);
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
    
    // For adding a skill to the Character's skillsList.
    public void addSkill(String skillIDIn) {
        if (!this.skillsList.contains(skillIDIn)) { // Only add the Skill id if it doesn't currently exist in the skills list.
            skillsList.add(skillIDIn);
        } // End if.
    } // End public void addSkill.
    
} // End public class Character.