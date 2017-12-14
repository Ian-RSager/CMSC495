import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.ListIterator;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultFormatter;

public class CharacterCreator {
    
    Character character;
    MasterLists msl;
    
    JFrame frame;
    
    TransparentJPanel mainPanel;
    
    //top panel components
    TransparentJPanel topMainPanel;
    TransparentJPanel photoNamePanel;
    JLabel photoLabel;
    JLabel nameLabel;
    TransparentJPanel bioInfoPanel;
    
    
    //ability scores panel components
    TransparentJPanel abilityScoresPanel;
    AbilityScorePanel strengthPanel;
    AbilityScorePanel dexterityPanel;
    AbilityScorePanel constitutionPanel;
    AbilityScorePanel intelligencePanel;
    AbilityScorePanel wisdomPanel;
    AbilityScorePanel charismaPanel;
    
    //center panel components
    TransparentJPanel centerPanel;
    TransparentJPanel centerSubPanel;
    
    //throws and skills center subpanel
    TransparentJPanel skillsAndThrowsPanel;
    TransparentJPanel savingThrowsPanel;
    TransparentJPanel skillsPanel;
    String[] skills = {"Acrobatics", "Animal Handling", "Arcana", "Athletics", "Deception",
            "History", "Insight", "Intimidation", "Investigation", "Medicine", "Nature",
            "Perception", "Performance", "Persuasion", "Religion", "Sleight of Hand",
            "Stealth", "Survival"};
    
    //stats subpanel components
    TransparentJPanel statsPanel;
    StatsPanel hitPointsPanel;
    StatsPanel armorClassPanel;
    StatsPanel initiativePanel;
    StatsPanel proficiencyBonusPanel;
    StatsPanel speedPanel;
    StatsPanel passivePerceptionPanel;
    
    //features, equippedItems, langauges subpanel
    TransparentJPanel featuresAndEquippedItemsSubPanel;
    TransparentJPanel languagesPanel;
    JLabel languagesLabel;
    
    //features subpanel components
    TransparentJPanel featuresPanel;
    TransparentJPanel featuresDisplayPanel;
    TransparentJPanel featuresButtonsPanel;
    JTextPane featuresTextPane;
    JButton addFeatureButton;
    JButton removeFeatureButton;
    
    //equipped items subpanel components
    TransparentJPanel equippedItemsPanel;
    TransparentJPanel equippedItemsDisplayPanel;
    TransparentJPanel equippedItemsButtonsPanel;
    JButton equipItemButton;
    JButton unequipItemButton;
    
    //inventoryAndSpellsPanel components
    TransparentJPanel inventoryAndSpellsPanel;
    TransparentJPanel inventoryPanel;
    TransparentJPanel inventoryButtonsPanel;
    TransparentJPanel inventoryDisplayPanel;
    JTextPane inventoryTextPane;
    JScrollPane inventoryScrollPane;
    JButton addItemButton;
    JButton removeItemButton;
    
    TransparentJPanel spellsPanel;
    JTextPane spellsTextPane;
    TransparentJPanel spellsButtonsPanel;
    TransparentJPanel spellsDisplayPanel;
    JScrollPane spellsScrollPane;
    JLabel addSpellLabel;
    JComboBox addSpellComboBox;
    JLabel removeSpellLabel;
    JComboBox removeSpellComboBox;
    
    //top menu bar components
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem newSheetMenuItem;
    JMenuItem loadSheetMenuItem;
    JMenuItem saveSheetMenuItem;
    JMenuItem quitMenuItem;
    
    
    /*
     * GUI constructor
     */
    
    
    public CharacterCreator(Character inCharacter) {
        
        this.character = inCharacter;
        
        /*
         * Set frame
         */
        frame = new JFrame("Character Creator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setMinimumSize(new Dimension(700, 685));
        frame.setLocationRelativeTo(null);
        JLabel background=new JLabel(new ImageIcon("background.jpg"));
        frame.add(background);
        background.setLayout(new BorderLayout());
        
        
        /*
         * Set Menu bar
         */
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        // File menu
        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        newSheetMenuItem = new JMenuItem("New Character Sheet");
        loadSheetMenuItem = new JMenuItem("Load Character Sheet");
        saveSheetMenuItem = new JMenuItem("Save Character Sheet");
        quitMenuItem = new JMenuItem("Quit");
        fileMenu.add(newSheetMenuItem);
        fileMenu.add(loadSheetMenuItem);
        fileMenu.add(saveSheetMenuItem);
        fileMenu.add(quitMenuItem);
        
        // Menu bar listeners
        
        newSheetMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Object[] options = {"Create New Character", "Load Character"};
                int selection = JOptionPane.showConfirmDialog(newSheetMenuItem, "Would you like to save the current character?");
                switch (selection) {
                    case JOptionPane.CANCEL_OPTION:
                        break;
                    case JOptionPane.NO_OPTION:
                        frame.dispose();
                        NewCharacter nc = new NewCharacter();
                        nc.newCharacterGui();
                        break;
                    case JOptionPane.YES_OPTION:
                        saveCharacter();
                        frame.dispose();
                        NewCharacter nc2 = new NewCharacter();
                        nc2.newCharacterGui();
                        break;
                }
            }
        });
        
        quitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selection = JOptionPane.showConfirmDialog(newSheetMenuItem, "Would you like to save the current character?");
                switch (selection) {
                    case JOptionPane.CANCEL_OPTION:
                        break;
                    case JOptionPane.NO_OPTION:
                        System.exit(0);
                        break;
                    case JOptionPane.YES_OPTION:
                        saveCharacter();
                        System.exit(0);
                        break;
                }
            }
            
        });
        
        saveSheetMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                saveCharacter();
            }
        });
        
        loadSheetMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                loadCharacter();
            }
        });
        
        
        /*
         * Set Tabbed pane
         */
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        UIManager.put("TabbedPane.contentAreaColor", new Color(0, 0, 0, 0));
        background.add(tabbedPane);
        mainPanel = new TransparentJPanel();
        mainPanel.setBackground(new Color(255, 236, 186));
        inventoryAndSpellsPanel = new TransparentJPanel();
        tabbedPane.add("Main", mainPanel);
        tabbedPane.add("Inventory and Spells", inventoryAndSpellsPanel);
        
        
        
        /*
         * Set main panel components
         */
        mainPanel.setLayout(new BorderLayout());
        
        // Top main panel biographical info
        topMainPanel = new TransparentJPanel();
        topMainPanel.setLayout(new BorderLayout());
        mainPanel.add(topMainPanel, BorderLayout.NORTH);
        
        // Photo and name
        photoNamePanel = new TransparentJPanel();
        photoNamePanel.setLayout(new BorderLayout());
        topMainPanel.add(photoNamePanel, BorderLayout.WEST);
        BufferedImage photo;
		try {
			photo = ImageIO.read(new File(character.getCharacterImg()));
			Image resizedPhoto = photo.getScaledInstance(80, 112, Image.SCALE_SMOOTH);
			photoLabel = new JLabel(new ImageIcon(resizedPhoto));
			// photoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
			photoLabel.setSize(50, 50);
			photoNamePanel.add(photoLabel, BorderLayout.CENTER);
		} catch (IOException e) {
			//e.printStackTrace();
		} catch (NullPointerException e) {
			//System.out.println("No image found");
		}
        
        nameLabel = new JLabel(character.getName(), SwingConstants.CENTER);
        nameLabel.setFont(new Font(null, Font.BOLD, 20));
        photoNamePanel.add(nameLabel, BorderLayout.SOUTH);
        photoNamePanel.setBorder(new EmptyBorder(0, 13, 0, 7));
        
        // Class, level, background, faction, race, alignment, XP
        bioInfoPanel = new TransparentJPanel();
        Border bioInfoPanelBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border bioInfoPanelMargin = new EmptyBorder(0, 10, 0, 0);   
        bioInfoPanel.setLayout(new GridLayout(0, 3));
        TransparentJPanel bioInfoPanelHelper = new TransparentJPanel();
        bioInfoPanelHelper.setBorder(BorderFactory.createCompoundBorder(bioInfoPanelMargin, bioInfoPanelBorder));
        TransparentJPanel subBioInfoPanel = new TransparentJPanel();
        subBioInfoPanel.setBorder(new EmptyBorder(3, 0, 0, 30));
        subBioInfoPanel.setLayout(new GridLayout(3, 1));
        bioInfoPanelHelper.setLayout(new BorderLayout());
        bioInfoPanelHelper.add(bioInfoPanel, BorderLayout.CENTER);
        bioInfoPanelHelper.add(subBioInfoPanel, BorderLayout.EAST);
        
        topMainPanel.add(bioInfoPanelHelper, BorderLayout.CENTER);
        String[] characterClassOptions = {"Barbarian", "Bard", "Cleric", "Druid", "Fighter", "Monk", "Paladin", "Ranger", "Rogue", "Sorcerer", "Warlock", "Wizard"};
        String characterClass = "";
        for (int i = 0; i < character.getLevelArray().length; i++) {
            if (character.getLevelArray()[i] != 0) {
                characterClass = characterClassOptions[i];        
                break;
            } // End if.
        } // End for.
        
        BasicInfoPanel classInfoPanel = new BasicInfoPanel("Class and Level", characterClass + " Lvl " +
                                                           String.valueOf(character.getLevel()), character);
        bioInfoPanel.add(classInfoPanel);
        BasicInfoPanel backgroundInfoPanel = new BasicInfoPanel("Background", character.getBackground(), character);
        bioInfoPanel.add(backgroundInfoPanel);
        BasicInfoPanel factionInfoPanel = new BasicInfoPanel("Faction", character.getFaction(), character);
        bioInfoPanel.add(factionInfoPanel);
        BasicInfoPanel raceInfoPanel = new BasicInfoPanel("Race", character.getRace(), character);
        bioInfoPanel.add(raceInfoPanel);
        BasicInfoPanel alignmentInfoPanel = new BasicInfoPanel("Alignment", character.getAlignment(), character);
        bioInfoPanel.add(alignmentInfoPanel);
        BasicInfoPanel experiencePointsInfoPanel = new BasicInfoPanel("Experience Points",
                                                                      String.valueOf(character.getExperiencePoints()), character);
        experiencePointsInfoPanel.addSpinner();
        bioInfoPanel.add(experiencePointsInfoPanel);
        BasicInfoPanel heightInfoPanel = new BasicInfoPanel("Height", String.valueOf(character.getHeight()) + " ft", character);
        heightInfoPanel.basicInfoNameLabel.setFont(new Font(null, Font.ITALIC, 10));
        heightInfoPanel.basicInfoValue.setFont(new Font(null, Font.BOLD, 12));
        subBioInfoPanel.add(heightInfoPanel);
        BasicInfoPanel weightInfoPanel = new BasicInfoPanel("Weight", String.valueOf(character.getWeight()) + " lbs", character);
        weightInfoPanel.basicInfoNameLabel.setFont(new Font(null, Font.ITALIC, 10));
        weightInfoPanel.basicInfoValue.setFont(new Font(null, Font.BOLD, 12));
        subBioInfoPanel.add(weightInfoPanel);
        BasicInfoPanel sexInfoPanel = new BasicInfoPanel("Sex", character.getSex(), character);
        sexInfoPanel.basicInfoNameLabel.setFont(new Font(null, Font.ITALIC, 10));
        sexInfoPanel.basicInfoValue.setFont(new Font(null, Font.BOLD, 12));
        subBioInfoPanel.add(sexInfoPanel);
        
        // Ability scores panel
        abilityScoresPanel = new TransparentJPanel();
        abilityScoresPanel.setLayout(new GridLayout(0,1));
        mainPanel.add(abilityScoresPanel, BorderLayout.WEST);
        strengthPanel = new AbilityScorePanel("Strength", character, this);
        abilityScoresPanel.add(strengthPanel);
        dexterityPanel = new AbilityScorePanel("Dexterity", character, this);
        abilityScoresPanel.add(dexterityPanel);
        constitutionPanel = new AbilityScorePanel("Constitution", character, this);
        abilityScoresPanel.add(constitutionPanel);
        intelligencePanel = new AbilityScorePanel("Intelligence", character, this);
        abilityScoresPanel.add(intelligencePanel);
        wisdomPanel = new AbilityScorePanel("Wisdom", character, this);
        abilityScoresPanel.add(wisdomPanel);
        charismaPanel = new AbilityScorePanel("Charisma", character, this);
        abilityScoresPanel.add(charismaPanel);
        
        
        // Center panel
        centerPanel = new TransparentJPanel();
        centerPanel.setLayout(new BorderLayout());
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        centerSubPanel = new TransparentJPanel();
        centerSubPanel.setLayout(new BorderLayout());
        centerPanel.add(centerSubPanel, BorderLayout.CENTER);
        
        // Skills and saving throws subpanel
        skillsAndThrowsPanel = new TransparentJPanel();
        centerPanel.add(skillsAndThrowsPanel, BorderLayout.WEST);
        skillsAndThrowsPanel.setLayout(new BoxLayout(skillsAndThrowsPanel, BoxLayout.Y_AXIS));
        
        skillsPanel = new TransparentJPanel();
        skillsPanel.setLayout(new GridLayout(0,1));
        JScrollPane skillsScrollPane = new JScrollPane(skillsPanel);
        skillsScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                                                                                                   Color.BLACK, 1, true), "Skills"));
        skillsScrollPane.setOpaque(false);
        skillsScrollPane.getViewport().setOpaque(false);
        skillsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        savingThrowsPanel = new TransparentJPanel();
        savingThrowsPanel.setLayout(new GridLayout(0,1));
        skillsAndThrowsPanel.add(savingThrowsPanel);
        skillsAndThrowsPanel.add(skillsScrollPane);
        
        // Saving throws panel
        savingThrowsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                                                                                                    Color.BLACK, 1, true), "Saving Throws"));
        String[] savingThrows = {"Strength", "Dexterity", "Constitution", "Intelligence", "Wisdom", "Charisma"};
        int[] savingThrowFinalArray = character.getSavingThrowFinalValueArray();
        boolean[] savingThrowProficiencyArray = character.getSavingThrowProficiencyArray();
        for (int i=0; i<savingThrows.length; i++) {
        	final int index = i;
            String text = String.valueOf(savingThrowFinalArray[i]) + " " + savingThrows[i];
            TransparentJCheckBox tjcb = new TransparentJCheckBox(text, savingThrowProficiencyArray[i]);
            savingThrowsPanel.add(tjcb);
            tjcb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(tjcb.isSelected() == false) character.setSavingThrowProficiencyArrayValue(index, false);
					else character.setSavingThrowProficiencyArrayValue(index, true);      	
				}
			});
        }
        
        
        // Skills panel

        int[]skillModifierArray = character.getTotalSkillModifierArray();
        ArrayList<Skill> characterSkillProficiency = character.getSkillsList();
        for(int i=0; i<skills.length; i++) {
        	final int index = i;
			boolean selected = characterSkillProficiency.get(i).isProficient;
			TransparentJCheckBox tjcb = new TransparentJCheckBox(String.valueOf(skillModifierArray[i]) +
                                                     " " + skills[i] + "     ", selected);
            skillsPanel.add(tjcb);
            tjcb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(tjcb.isSelected() == false) character.getSkillsList().get(index).setIsProficient(false);
					else character.getSkillsList().get(index).setIsProficient(true);     	
				}
			});
            
        }
        

        // Stats panel
        statsPanel = new TransparentJPanel();
        statsPanel.setLayout(new GridLayout(0,1));
        centerSubPanel.add(statsPanel, BorderLayout.WEST);
        hitPointsPanel = new StatsPanel("Hit Points", character, this);
        statsPanel.add(hitPointsPanel);
        armorClassPanel = new StatsPanel("Armor Class", character, this);
        statsPanel.add(armorClassPanel);
        initiativePanel = new StatsPanel("Initiative", character, this);
        statsPanel.add(initiativePanel);
        proficiencyBonusPanel = new StatsPanel("Proficiency Bonus", character, this);
        statsPanel.add(proficiencyBonusPanel);
        speedPanel = new StatsPanel("Speed", character, this);
        statsPanel.add(speedPanel);
        passivePerceptionPanel = new StatsPanel("Passive Perception", character, this);
        statsPanel.add(passivePerceptionPanel);
        
        // features and equipped items subpanel
        featuresAndEquippedItemsSubPanel = new TransparentJPanel();
        centerSubPanel.add(featuresAndEquippedItemsSubPanel, BorderLayout.CENTER);
        featuresAndEquippedItemsSubPanel.setLayout(new GridLayout(1, 0));
        
        featuresPanel = new TransparentJPanel();
        featuresPanel.setLayout(new BorderLayout());
        equippedItemsPanel = new TransparentJPanel();
        equippedItemsPanel.setLayout(new BorderLayout());
        featuresAndEquippedItemsSubPanel.add(featuresPanel);
        featuresAndEquippedItemsSubPanel.add(equippedItemsPanel);
        
        //features panel buttons
        featuresButtonsPanel = new TransparentJPanel();
        featuresButtonsPanel.setLayout(new GridLayout(1,0));
        addFeatureButton = new JButton("Add Feature");
        removeFeatureButton = new JButton("Remove Feature");
        featuresButtonsPanel.add(addFeatureButton);
        featuresButtonsPanel.add(removeFeatureButton);
        //can be added later if buttons desired
        //featuresPanel.add(featuresButtonsPanel, BorderLayout.SOUTH);
        
        //equipped items panel buttons
        equippedItemsButtonsPanel = new TransparentJPanel();
        equippedItemsButtonsPanel.setLayout(new GridLayout(1,0));
        equipItemButton = new JButton("Equip Item");
        unequipItemButton = new JButton("Unequip Item");
        equippedItemsButtonsPanel.add(equipItemButton);
        equippedItemsButtonsPanel.add(unequipItemButton);
        //can be added later if buttons desired
        //equippedItemsPanel.add(equippedItemsButtonsPanel, BorderLayout.SOUTH);
        
        // features display panel
        featuresTextPane = new JTextPane();
        featuresTextPane.setEditable(false);
        JScrollPane featuresScrollPane = new JScrollPane(featuresTextPane);
        featuresPanel.add(featuresScrollPane, BorderLayout.CENTER);
        featuresScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                                                                                                     Color.BLACK, 1, true), "Features"));
        featuresScrollPane.setOpaque(false);
        featuresScrollPane.getViewport().setOpaque(false);
        featuresScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        featuresTextPane.setOpaque(false);
        ArrayList<ClassFeature> characterFeatures = character.getFeaturesList();
        for (ClassFeature feature: characterFeatures) {
                String levelInfo = "";
                try {
                    for (int i = 0; i<feature.levelingInfo.length; i++) {
                        if(feature.levelingInfo[i].length == 1) break;
                        if(feature.levelingInfo[i][0].matches("[0-9]+")) break;
                        levelInfo += feature.levelingInfo[i][0] + feature.levelingInfo[i][1] + "<br>";
                    }
                
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    levelInfo = "";
                    System.out.println("array exception");
                }
                catch (NullPointerException e) {
                    levelInfo = "";
                }

                featuresTextPane.insertComponent(new JLabel(
                            "<html><b>" + feature.getName() +
                            "</b><br>Description: " + feature.getDescription() + 
                            "<br>" + levelInfo + "<br></html>"));
         
                
        }
        featuresTextPane.setCaretPosition(0);
        
        // equippedItems display panel
        equippedItemsDisplayPanel = new TransparentJPanel();
        equippedItemsDisplayPanel.setLayout(new GridLayout(0,1));
        //JScrollPane equippedItemsScrollPane = new JScrollPane(equippedItemsDisplayPanel);
        equippedItemsPanel.add(equippedItemsDisplayPanel, BorderLayout.CENTER);
        equippedItemsDisplayPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                                                                                                          Color.BLACK, 1, true), "Equipped Items"));
        // equipped items panels
        TransparentJPanel wornArmorSubPanel = new TransparentJPanel();
        wornArmorSubPanel.setLayout(new BoxLayout(wornArmorSubPanel, BoxLayout.Y_AXIS));
        TransparentJPanel wornArmorLabelsSubPanel = new TransparentJPanel();
        wornArmorLabelsSubPanel.setLayout(new GridLayout(0, 2));
        JLabel wornArmorLabel = new JLabel("Worn Armor:");
        wornArmorLabelsSubPanel.add(wornArmorLabel);  
        ArrayList<String> wornArmorOptions = new ArrayList<>();
        wornArmorOptions.add("None");
        for (Item item: character.getItemsList()) {
        	if (item instanceof Armor) wornArmorOptions.add(item.getName());
        }
        JComboBox wornArmorBox = new JComboBox(wornArmorOptions.toArray(new String[wornArmorOptions.size()]));
        wornArmorLabelsSubPanel.add(wornArmorBox);
        JTextPane wornArmorDescriptionTextPane = new JTextPane();
        JScrollPane wornArmorDescriptionScrollPane = new JScrollPane(wornArmorDescriptionTextPane);
        wornArmorDescriptionTextPane.setOpaque(false);
        wornArmorDescriptionTextPane.setEditable(false);
        JLabel wornArmorDescriptionLabel = new JLabel("");
        wornArmorDescriptionLabel.setPreferredSize(new Dimension(100, 100));
        wornArmorDescriptionTextPane.insertComponent(wornArmorDescriptionLabel);
        wornArmorDescriptionScrollPane.setOpaque(false);
        wornArmorDescriptionScrollPane.getViewport().setOpaque(false);
        wornArmorSubPanel.add(wornArmorLabelsSubPanel);
        wornArmorSubPanel.add(wornArmorDescriptionScrollPane);
        equippedItemsDisplayPanel.add(wornArmorSubPanel);
        wornArmorBox.addActionListener((ActionEvent e) -> {
        	String choice = (String)wornArmorBox.getSelectedItem();
        	if (choice.matches("None")) {
        		wornArmorDescriptionLabel.setText("");
        		wornArmorDescriptionLabel.setPreferredSize(new Dimension(100, 100));
        	}
        	else {
        		String id = "";
        		wornArmorDescriptionLabel.setPreferredSize(null);
        		for (Item armorToDisplay: character.getItemsList()) {
        			if (armorToDisplay instanceof Armor) {
        				if (armorToDisplay.getName().matches(choice)) {
        					id = armorToDisplay.getID(); 
        				}
        			}
        		}
            	Armor armorToDisplay = MasterLists.getArmorByID(id);
            	String armorLabelString = "<html>Description: " + armorToDisplay.getDescription() +
    					"<br>Cost: " + armorToDisplay.getSingleCost() + 
    					"<br>Cost Currency: " + armorToDisplay.getCostCurrency() + 
    					"<br>Weight: " + armorToDisplay.getSingleWeight() +
    					"<br>Quantity: " + armorToDisplay.getQuantity() +
    					"<br>Armor Type: " + armorToDisplay.getArmorType() +
    					"<br>Armor Class: " + armorToDisplay.getArmorClass() +
    					"<br>Max Dexterity Bonus: " + armorToDisplay.getMaxDexterityBonus() +
    					"<br>Don Time: " + armorToDisplay.getDonTime() +
    					"<br>Doff Time: " + armorToDisplay.getDoffTime() +
    					"<br>Strength Threshhold: " + armorToDisplay.getStrengthThreshold() +
    					"<br>Has Stealth Penalty: " + armorToDisplay.getHasStealthPenalty() +
            			"<br></html>";
            	wornArmorDescriptionLabel.setText(armorLabelString);
        	}  	
        });
        
        TransparentJPanel wieldedShieldSubPanel = new TransparentJPanel();
        wieldedShieldSubPanel.setLayout(new BoxLayout(wieldedShieldSubPanel, BoxLayout.Y_AXIS));
        TransparentJPanel wieldedShieldLabelsSubPanel = new TransparentJPanel();
        wieldedShieldLabelsSubPanel.setLayout(new GridLayout(0, 2));
        JLabel wieldedShieldLabel = new JLabel("Wielded Shield:");
        wieldedShieldLabelsSubPanel.add(wieldedShieldLabel);  
        ArrayList<String> wieldedShieldOptions = new ArrayList<>();
        wieldedShieldOptions.add("None");
        for (Item item: character.getItemsList()) {
        	if (item instanceof Armor) wieldedShieldOptions.add(item.getName());
        }
        JComboBox wieldedShieldBox = new JComboBox(wieldedShieldOptions.toArray(new String[wieldedShieldOptions.size()]));
        wieldedShieldLabelsSubPanel.add(wieldedShieldBox);
        JTextPane wieldedShieldDescriptionTextPane = new JTextPane();
        JScrollPane wieldedShieldDescriptionScrollPane = new JScrollPane(wieldedShieldDescriptionTextPane);
        wieldedShieldDescriptionTextPane.setOpaque(false);
        wieldedShieldDescriptionTextPane.setEditable(false);
        JLabel wieldedShieldDescriptionLabel = new JLabel("");
        wieldedShieldDescriptionLabel.setPreferredSize(new Dimension(100, 100));
        wieldedShieldDescriptionTextPane.insertComponent(wieldedShieldDescriptionLabel);
        wieldedShieldDescriptionScrollPane.setOpaque(false);
        wieldedShieldDescriptionScrollPane.getViewport().setOpaque(false);
        wieldedShieldSubPanel.add(wieldedShieldLabelsSubPanel);
        wieldedShieldSubPanel.add(wieldedShieldDescriptionScrollPane);
        equippedItemsDisplayPanel.add(wieldedShieldSubPanel);
        wieldedShieldBox.addActionListener((ActionEvent e) -> {
        	String choice = (String)wieldedShieldBox.getSelectedItem();
        	if (choice.matches("None")) {
        		wieldedShieldDescriptionLabel.setText("");
        		wieldedShieldDescriptionLabel.setPreferredSize(new Dimension(100, 100));
        	}
        	else {
        		String id = "";
        		wieldedShieldDescriptionLabel.setPreferredSize(null);
        		for (Item armorToDisplay: character.getItemsList()) {
        			if (armorToDisplay instanceof Armor) {
        				if (armorToDisplay.getName().matches(choice)) {
        					id = armorToDisplay.getID(); 
        				}
        			}
        		}
            	Armor armorToDisplay = MasterLists.getArmorByID(id);
            	String armorLabelString = "<html>Description: " + armorToDisplay.getDescription() +
    					"<br>Cost: " + armorToDisplay.getSingleCost() + 
    					"<br>Cost Currency: " + armorToDisplay.getCostCurrency() + 
    					"<br>Weight: " + armorToDisplay.getSingleWeight() +
    					"<br>Quantity: " + armorToDisplay.getQuantity() +
    					"<br>Armor Type: " + armorToDisplay.getArmorType() +
    					"<br>Armor Class: " + armorToDisplay.getArmorClass() +
    					"<br>Max Dexterity Bonus: " + armorToDisplay.getMaxDexterityBonus() +
    					"<br>Don Time: " + armorToDisplay.getDonTime() +
    					"<br>Doff Time: " + armorToDisplay.getDoffTime() +
    					"<br>Strength Threshhold: " + armorToDisplay.getStrengthThreshold() +
    					"<br>Has Stealth Penalty: " + armorToDisplay.getHasStealthPenalty() +
            			"<br></html>";
            	wieldedShieldDescriptionLabel.setText(armorLabelString);
        	}  	
        });
        
        TransparentJPanel weaponOneSubPanel = new TransparentJPanel();
        weaponOneSubPanel.setLayout(new BoxLayout(weaponOneSubPanel, BoxLayout.Y_AXIS));
        TransparentJPanel weaponOneLabelsSubPanel = new TransparentJPanel();
        weaponOneLabelsSubPanel.setLayout(new GridLayout(0, 2));
        JLabel weaponOneLabel = new JLabel("Weapon One:");
        weaponOneLabelsSubPanel.add(weaponOneLabel);  
        ArrayList<String> weaponOneOptions = new ArrayList<>();
        weaponOneOptions.add("None");
        for (Item item: character.getItemsList()) {
        	if (item instanceof Weapon) weaponOneOptions.add(item.getName());
        }
        JComboBox weaponOneBox = new JComboBox(weaponOneOptions.toArray(new String[weaponOneOptions.size()]));
        weaponOneLabelsSubPanel.add(weaponOneBox);
        JTextPane weaponOneDescriptionTextPane = new JTextPane();
        JScrollPane weaponOneDescriptionScrollPane = new JScrollPane(weaponOneDescriptionTextPane);
        weaponOneDescriptionTextPane.setOpaque(false);
        weaponOneDescriptionTextPane.setEditable(false);
        JLabel weaponOneDescriptionLabel = new JLabel("");
        weaponOneDescriptionLabel.setPreferredSize(new Dimension(100, 100));
        weaponOneDescriptionTextPane.insertComponent(weaponOneDescriptionLabel);
        weaponOneDescriptionScrollPane.setOpaque(false);
        weaponOneDescriptionScrollPane.getViewport().setOpaque(false);
        weaponOneSubPanel.add(weaponOneLabelsSubPanel);
        weaponOneSubPanel.add(weaponOneDescriptionScrollPane);
        equippedItemsDisplayPanel.add(weaponOneSubPanel);
        weaponOneBox.addActionListener((ActionEvent e) -> {
        	String choice = (String)weaponOneBox.getSelectedItem();
        	if (choice.matches("None")) {
        		weaponOneDescriptionLabel.setText("");
        		weaponOneDescriptionLabel.setPreferredSize(new Dimension(100, 100));
        	}
        	else {
        		String id = "";
        		weaponOneDescriptionLabel.setPreferredSize(null);
        		for (Item weaponToDisplay: character.getItemsList()) {
        			if (weaponToDisplay instanceof Weapon) {
        				if (weaponToDisplay.getName().matches(choice)) {
        					id = weaponToDisplay.getID(); 
        				}
        			}
        		}
            	Weapon weaponToDisplay = MasterLists.getWeaponByID(id);
            	String weaponsLabelString = "<html>Description: " + weaponToDisplay.getDescription() +
    					"<br>Cost: " + weaponToDisplay.getSingleCost() + 
    					"<br>Cost Currency: " + weaponToDisplay.getCostCurrency() + 
    					"<br>Weight: " + weaponToDisplay.getSingleWeight() +
    					"<br>Quantity: " + weaponToDisplay.getQuantity() +
    					"<br>Damage: " + weaponToDisplay.getDamage() +
    					"<br>Melee: " + weaponToDisplay.getIsMelee() +
    					"<br>Ranged: " + weaponToDisplay.getIsRanged() +
    					"<br>Has Reach: " + weaponToDisplay.getHasReach() +
    					"<br>Normal Range: " + weaponToDisplay.getNormalRange() +
    					"<br>Maximum Range: " + weaponToDisplay.getMaximumRange() +
    					"<br>Is Loading: " + weaponToDisplay.isLoading +
    					"<br>Has Finesse: " + weaponToDisplay.getHasFinesse() +
    					"<br>Is Heavy: " + weaponToDisplay.getIsHeavy() +
    					"<br>Is Light: " + weaponToDisplay.getIsLight() +
    					"<br>Is Two Handed: " + weaponToDisplay.getIsTwoHanded() +
    					"<br>Is Versatile: " + weaponToDisplay.getIsVersatile() +
    					"<br>Is Special? " + weaponToDisplay.getIsSpecial() +
    					"<br>Special Description: " + weaponToDisplay.getIsSpecialDescription() +
            			"<br></html>";
            	weaponOneDescriptionLabel.setText(weaponsLabelString);
        	}  	
        });
        
        TransparentJPanel weaponTwoSubPanel = new TransparentJPanel();
        weaponTwoSubPanel.setLayout(new BoxLayout(weaponTwoSubPanel, BoxLayout.Y_AXIS));
        TransparentJPanel weaponTwoLabelsSubPanel = new TransparentJPanel();
        weaponTwoLabelsSubPanel.setLayout(new GridLayout(0, 2));
        JLabel weaponTwoLabel = new JLabel("Weapon Two:");
        weaponTwoLabelsSubPanel.add(weaponTwoLabel);  
        ArrayList<String> weaponTwoOptions = new ArrayList<>();
        weaponTwoOptions.add("None");
        for (Item item: character.getItemsList()) {
        	if (item instanceof Weapon) weaponTwoOptions.add(item.getName());
        }
        JComboBox weaponTwoBox = new JComboBox(weaponTwoOptions.toArray(new String[weaponTwoOptions.size()]));
        weaponTwoLabelsSubPanel.add(weaponTwoBox);
        JTextPane weaponTwoDescriptionTextPane = new JTextPane();
        JScrollPane weaponTwoDescriptionScrollPane = new JScrollPane(weaponTwoDescriptionTextPane);
        weaponTwoDescriptionTextPane.setOpaque(false);
        weaponTwoDescriptionTextPane.setEditable(false);
        JLabel weaponTwoDescriptionLabel = new JLabel("");
        weaponTwoDescriptionLabel.setPreferredSize(new Dimension(100, 100));
        weaponTwoDescriptionTextPane.insertComponent(weaponTwoDescriptionLabel);
        weaponTwoDescriptionScrollPane.setOpaque(false);
        weaponTwoDescriptionScrollPane.getViewport().setOpaque(false);
        weaponTwoSubPanel.add(weaponTwoLabelsSubPanel);
        weaponTwoSubPanel.add(weaponTwoDescriptionScrollPane);
        equippedItemsDisplayPanel.add(weaponTwoSubPanel);
        weaponTwoBox.addActionListener((ActionEvent e) -> {
        	String choice = (String)weaponTwoBox.getSelectedItem();
        	if (choice.matches("None")) {
        		weaponTwoDescriptionLabel.setText("");
        		weaponTwoDescriptionLabel.setPreferredSize(new Dimension(100, 100));
        	}
        	else {
        		String id = "";
        		weaponTwoDescriptionLabel.setPreferredSize(null);
        		for (Item weaponToDisplay: character.getItemsList()) {
        			if (weaponToDisplay instanceof Weapon) {
        				if (weaponToDisplay.getName().matches(choice)) {
        					id = weaponToDisplay.getID(); 
        				}
        			}
        		}
            	Weapon weaponToDisplay = MasterLists.getWeaponByID(id);
            	String weaponsLabelString = "<html>Description: " + weaponToDisplay.getDescription() +
    					"<br>Cost: " + weaponToDisplay.getSingleCost() + 
    					"<br>Cost Currency: " + weaponToDisplay.getCostCurrency() + 
    					"<br>Weight: " + weaponToDisplay.getSingleWeight() +
    					"<br>Quantity: " + weaponToDisplay.getQuantity() +
    					"<br>Damage: " + weaponToDisplay.getDamage() +
    					"<br>Melee: " + weaponToDisplay.getIsMelee() +
    					"<br>Ranged: " + weaponToDisplay.getIsRanged() +
    					"<br>Has Reach: " + weaponToDisplay.getHasReach() +
    					"<br>Normal Range: " + weaponToDisplay.getNormalRange() +
    					"<br>Maximum Range: " + weaponToDisplay.getMaximumRange() +
    					"<br>Is Loading: " + weaponToDisplay.isLoading +
    					"<br>Has Finesse: " + weaponToDisplay.getHasFinesse() +
    					"<br>Is Heavy: " + weaponToDisplay.getIsHeavy() +
    					"<br>Is Light: " + weaponToDisplay.getIsLight() +
    					"<br>Is Two Handed: " + weaponToDisplay.getIsTwoHanded() +
    					"<br>Is Versatile: " + weaponToDisplay.getIsVersatile() +
    					"<br>Is Special? " + weaponToDisplay.getIsSpecial() +
    					"<br>Special Description: " + weaponToDisplay.getIsSpecialDescription() +
            			"<br></html>";
            	weaponTwoDescriptionLabel.setText(weaponsLabelString);
        	}  	
        });
        
        // languages panel
        languagesPanel = new TransparentJPanel();
        equippedItemsPanel.add(languagesPanel, BorderLayout.NORTH); // add languages above equipment
        languagesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                                                                                                 Color.BLACK, 1, true), "Languages"));
        String languagesKnown = "";
        for (String language: character.getLanguages()) {
        	languagesKnown = languagesKnown + "   " + language;
        }
        languagesLabel = new JLabel(languagesKnown);
        languagesPanel.add(languagesLabel);
        
        // inventory and spells page
        inventoryAndSpellsPanel.setLayout(new GridLayout(1,0));
        inventoryPanel = new TransparentJPanel();
        inventoryAndSpellsPanel.add(inventoryPanel);
        spellsPanel = new TransparentJPanel();
        inventoryAndSpellsPanel.add(spellsPanel);
        inventoryDisplayPanel = new TransparentJPanel();
        spellsDisplayPanel = new TransparentJPanel();
        
        /*
         * Set inventory and spells panel components
         */
        
        //inventory panel
//        inventoryDisplayPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
//                                                                                                        Color.BLACK, 1, true), "Inventory"));
        JTextPane inventoryTextPane = new JTextPane();
        TransparentJPanel inventoryTextPanel = new TransparentJPanel();
        inventoryTextPanel.setLayout(new BoxLayout(inventoryTextPanel, BoxLayout.Y_AXIS));
        inventoryTextPane.setOpaque(false);
        inventoryTextPane.setEditable(false);
        //inventoryTextPane.setLayout(new BoxLayout(inventoryTextPane, BoxLayout.PAGE_AXIS));
        inventoryScrollPane = new JScrollPane(inventoryTextPanel);
        inventoryScrollPane.setOpaque(false);
        inventoryScrollPane.getViewport().setOpaque(false);
        inventoryScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        inventoryScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                                                                                                        Color.BLACK, 1, true), "Inventory"));
        inventoryDisplayPanel.add(inventoryScrollPane);

        ArrayList<JLabel> weaponsLabels = new ArrayList<>();
        ArrayList<JLabel> armorLabels = new ArrayList<>();
        ArrayList<JLabel> itemLabels = new ArrayList<>();
        
        for (Item item: character.getItemsList()) {
            if (item instanceof Weapon) {
            	String weaponsLabelString = "<html><b><span style='font-size:14px'>" + item.getName() + 	
            			"</span></b><i>  (Weapon)</i>" +
            			"<br>Description: " + item.getDescription() +
    					"<br>Cost: " + item.getSingleCost() + 
    					"<br>Cost Currency: " + ((Weapon) item).getCostCurrency() + 
    					"<br>Weight: " + ((Weapon) item).getSingleWeight() +
    					"<br>Quantity: " + ((Weapon) item).getQuantity() +
    					"<br>Damage: " + ((Weapon) item).getDamage() +
    					"<br>Melee: " + ((Weapon) item).getIsMelee() +
    					"<br>Ranged: " + ((Weapon) item).getIsRanged() +
    					"<br>Has Reach: " + ((Weapon) item).getHasReach() +
    					"<br>Normal Range: " + ((Weapon) item).getNormalRange() +
    					"<br>Maximum Range: " + ((Weapon) item).getMaximumRange() +
    					"<br>Is Loading: " + ((Weapon) item).isLoading +
    					"<br>Has Finesse: " + ((Weapon) item).getHasFinesse() +
    					"<br>Is Heavy: " + ((Weapon) item).getIsHeavy() +
    					"<br>Is Light: " + ((Weapon) item).getIsLight() +
    					"<br>Is Two Handed: " + ((Weapon) item).getIsTwoHanded() +
    					"<br>Is Versatile: " + ((Weapon) item).getIsVersatile() +
    					"<br>Is Special? " + ((Weapon) item).getIsSpecial() +
    					"<br>Special Description: " + ((Weapon) item).getIsSpecialDescription() +
            			"<br><br></html>";
            	weaponsLabels.add(new JLabel(weaponsLabelString));
            }
            else if (item instanceof Armor) {
            	String armorLabelString = "<html><b><span style='font-size:14px'>" + item.getName() + 	
            			"</span></b><i>  (Armor)</i>" +
            			"<br>Description: " + item.getDescription() +
    					"<br>Cost: " + item.getSingleCost() + 
    					"<br>Cost Currency: " + ((Armor) item).getCostCurrency() + 
    					"<br>Weight: " + ((Armor) item).getSingleWeight() +
    					"<br>Quantity: " + ((Armor) item).getQuantity() +
    					"<br>Armor Type: " + ((Armor) item).getArmorType() +
    					"<br>Armor Class: " + ((Armor) item).getArmorClass() +
    					"<br>Max Dexterity Bonus: " + ((Armor) item).getMaxDexterityBonus() +
    					"<br>Don Time: " + ((Armor) item).getDonTime() +
    					"<br>Doff Time: " + ((Armor) item).getDoffTime() +
    					"<br>Strength Threshhold: " + ((Armor) item).getStrengthThreshold() +
    					"<br>Has Stealth Penalty: " + ((Armor) item).getHasStealthPenalty() +
            			"<br><br></html>";
            	armorLabels.add(new JLabel(armorLabelString));
            }
            else {
            	itemLabels.add(new JLabel("<html><b><span style='font-size:14px'>" + item.getName() + 	
            			"</span></b><i>  (Item)</i>" +
            			"<br>Description: " + item.getDescription() +
    					"<br>Cost: " + item.getSingleCost() + 
    					"<br>Cost Currency: " + item.getCostCurrency() + 
    					"<br>Weight: " + item.getSingleWeight() +
    					"<br>Quantity: " + item.getQuantity() +
            			"<br><br></html>"));
            }
        }

        
        for (JLabel weaponsLabel: weaponsLabels) {
        	inventoryTextPanel.add(weaponsLabel);
        }
        for (JLabel armorLabel: armorLabels) {
        	inventoryTextPanel.add(armorLabel);
        }
        for (JLabel itemLabel: itemLabels) {
        	inventoryTextPane.add(itemLabel);
        }
        

        inventoryPanel.setLayout(new BorderLayout());
        inventoryButtonsPanel = new TransparentJPanel();
        inventoryButtonsPanel.setLayout(new GridLayout(1,0));
        addItemButton = new JButton("Add Item");
        removeItemButton = new JButton("Remove Item");
        inventoryButtonsPanel.add(addItemButton);
        inventoryButtonsPanel.add(removeItemButton);
        
        inventoryPanel.add(inventoryScrollPane, BorderLayout.CENTER);

        //can be added later if buttons desired
        //inventoryPanel.add(inventoryButtonsPanel, BorderLayout.SOUTH);
        
        //spells panel                                                                                             Color.BLACK, 1, true), "Spells"));
        spellsTextPane = new JTextPane();
        spellsTextPane.setEditable(false);
        spellsTextPane.setOpaque(false);
        spellsTextPane.setLayout(new BoxLayout(spellsTextPane, BoxLayout.Y_AXIS));
        spellsScrollPane = new JScrollPane(spellsTextPane);
        spellsScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                Color.BLACK, 1, true), "Spells"));
        spellsScrollPane.setOpaque(false);
        spellsScrollPane.getViewport().setOpaque(false);
        spellsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        HashMap<String, Spell> spellMap = MasterLists.getMasterSpellList();
        ArrayList<String> spellList = new ArrayList<String>();
        for (Spell spell: spellMap.values()) {
        		spellList.add(spell.name);
        }
        
        ArrayList<JLabel> displayedSpellsList = new ArrayList<>();
        	
        ArrayList<Spell> knownSpells = character.getKnownSpells();
            for (Spell spell: knownSpells) {
                    String spellDescription = "<html><b>Name: " + spell.name + 
        					"</b><br>Level: " + spell.level + 
        					"</b><br>Duration: " + spell.duration + 
        					"</b><br>Range: " + spell.range + 
        					"</b><br>Casting time: " + spell.castingTime + 					
        					"<br>Description: " + spell.description + "<br><br></html>";
                    displayedSpellsList.add(new JLabel(spellDescription));
            }
        for (JLabel spellLabel: displayedSpellsList) {
        		spellsTextPane.insertComponent(spellLabel);
        }
        
        ArrayList<String> currentSpellList = new ArrayList<String>();
        	for (Spell knownSpell: knownSpells) {
        		currentSpellList.add(knownSpell.name);
        }
        	
        spellsPanel.setLayout(new BorderLayout());
        spellsButtonsPanel = new TransparentJPanel();
        spellsButtonsPanel.setLayout(new GridLayout(1,0));
        addSpellLabel = new JLabel("Add New Spell:");
        addSpellLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        	
        Collections.sort(spellList, String.CASE_INSENSITIVE_ORDER);
        String[] spellOptions = spellList.toArray(new String[spellList.size()]);
        addSpellComboBox = new JComboBox(spellOptions);  
        addSpellComboBox.addActionListener((ActionEvent e) -> {
			String choice = (String) addSpellComboBox.getSelectedItem();
			Spell newSpell = spellMap.get(choice);
			character.addKnownSpell(choice);
			displayedSpellsList.add(new JLabel("<html><b>Name: " + newSpell.name + 
					"</b><br>Level: " + newSpell.level + 
					"</b><br>Duration: " + newSpell.duration + 
					"</b><br>Range: " + newSpell.range + 
					"</b><br>Casting time: " + newSpell.castingTime + 					
					"<br>Description: " +newSpell.description + "<br><br></html>"));
			spellsTextPane.removeAll();
			for (JLabel spellLabel: displayedSpellsList) {
        			spellsTextPane.insertComponent(spellLabel);
			}
			spellList.remove(newSpell.name);
			addSpellComboBox.setModel(new DefaultComboBoxModel(spellList.toArray(new String[spellList.size()])));
			currentSpellList.add(newSpell.name);
			//Collections.sort(currentSpellList, String.CASE_INSENSITIVE_ORDER);
			removeSpellComboBox.setModel(new DefaultComboBoxModel(currentSpellList.toArray(new String[currentSpellList.size()])));
        });
        
        removeSpellLabel = new JLabel("Remove Spell:");
        removeSpellLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        removeSpellComboBox = new JComboBox(character.getMemorizedSpells().toArray(new String[character.getMemorizedSpells().size()]));
        removeSpellComboBox.addActionListener((ActionEvent e) -> {
			String choice = (String) removeSpellComboBox.getSelectedItem();
			character.removeKnownSpell(spellMap.get(choice));
			
			ListIterator<JLabel> iter = displayedSpellsList.listIterator();
			while (iter.hasNext()) {
				if(iter.next().getText().startsWith("<html><b>Name: " + choice)) {
					iter.remove();
				}
			}
			
			spellsTextPane.removeAll();
			for (JLabel spellLabelToDisplay: displayedSpellsList) {
				spellsTextPane.selectAll();
        		spellsTextPane.add(spellLabelToDisplay);
			}			
			spellsTextPane.revalidate();
			spellsTextPane.repaint();
			//spellsTextPane.setCaretPosition(0);
			
			Spell removedSpell = spellMap.get(choice);
			spellList.add(0, removedSpell.name);
			Collections.sort(spellList, String.CASE_INSENSITIVE_ORDER);
			addSpellComboBox.setModel(new DefaultComboBoxModel(spellList.toArray(new String[spellList.size()])));
			currentSpellList.remove(removedSpell.name);
			removeSpellComboBox.setModel(new DefaultComboBoxModel(currentSpellList.toArray(new String[currentSpellList.size()])));
        });
        
        spellsButtonsPanel.add(addSpellLabel);
        spellsButtonsPanel.add(addSpellComboBox);
        spellsButtonsPanel.add(removeSpellLabel);
        spellsButtonsPanel.add(removeSpellComboBox);   
        
        spellsPanel.add(spellsScrollPane, BorderLayout.CENTER);
        spellsPanel.add(spellsButtonsPanel, BorderLayout.SOUTH);

        refresh();
    }
    
    public void display() {
        frame.setVisible(true);
    }
    
    
    private void saveCharacter() {
        JFileChooser jfc = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("ser file", "ser");
        jfc.addChoosableFileFilter(filter);
        jfc.setFileFilter(filter);
        int returnVal = jfc.showSaveDialog(saveSheetMenuItem);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            File saveFile = new File(file.getAbsolutePath() + ".ser");
            try {
                FileOutputStream fos = new FileOutputStream(saveFile);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(character);
                oos.close();
            }
            
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void loadCharacter() {
        JFileChooser jfc = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("ser file", "ser");
        jfc.addChoosableFileFilter(filter);
        jfc.setFileFilter(filter);
        int returnVal = jfc.showOpenDialog(loadSheetMenuItem);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            try {
                frame.dispose();
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Character loadCharacter = (Character) ois.readObject();
                CharacterCreator l = new CharacterCreator(loadCharacter);
                l.display();
                ois.close();
            }
            
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void refresh() {
    	character.recalculate();
    	
    	//stats panel refresh
    	hitPointsPanel.value = character.getHitPointsCurrent();
    	hitPointsPanel.model.setValue(hitPointsPanel.value);
    	hitPointsPanel.maxHitPointLabel.setText("/" + character.getHitPointsMaximum());
        armorClassPanel.value = character.getArmorClass();
        armorClassPanel.model.setValue(armorClassPanel.value);
        initiativePanel.value = character.getInitiative();
        initiativePanel.statLabel.setText(String.valueOf(initiativePanel.value));
        proficiencyBonusPanel.value = character.getProficiencyBonus();
        proficiencyBonusPanel.statLabel.setText(String.valueOf(proficiencyBonusPanel.value));
        speedPanel.value = character.getSpeed();
        speedPanel.model.setValue(speedPanel.value);        
        passivePerceptionPanel.value = character.getPassivePerception();
        passivePerceptionPanel.statLabel.setText(String.valueOf(passivePerceptionPanel.value));
        
        //saving throws panel refresh   
        String[] savingThrows = {"Strength", "Dexterity", "Constitution", "Intelligence", "Wisdom", "Charisma"};
        int[] savingThrowFinalArray = character.getSavingThrowFinalValueArray();
        boolean[] savingThrowProficiencyArray = character.getSavingThrowProficiencyArray();
        Component[] savingThrowsComponent = savingThrowsPanel.getComponents();
        for (int i=0; i<savingThrowsComponent.length; i++) {
            if(savingThrowsComponent[i] instanceof TransparentJCheckBox) {
        		TransparentJCheckBox tjcb = (TransparentJCheckBox)savingThrowsComponent[i];
        		tjcb.setText(String.valueOf(savingThrowFinalArray[i]) + " " + savingThrows[i]);
        		tjcb.setSelected(character.getSavingThrowProficiencyArray()[i]);
        	}
            
        }
          
        //skills panel refresh
        int[]skillModifierArray = character.getTotalSkillModifierArray();
        ArrayList<Skill> characterSkillProficiency = character.getSkillsList();
        Component[] skillsComponent = skillsPanel.getComponents();
        for(int i=0; i<skillsComponent.length; i++) {
        	if (skillsComponent[i] instanceof TransparentJCheckBox) {
        		TransparentJCheckBox tjcb = (TransparentJCheckBox)skillsComponent[i];
        		tjcb.setText(String.valueOf(skillModifierArray[i]) +
                                                     " " + skills[i] + "     ");
    			boolean selected = characterSkillProficiency.get(i).isProficient;
    			tjcb.setSelected(selected);
        	}          
        }   	
    }
    
    
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        
        EventQueue.invokeAndWait(new Runnable() {
            @Override
            public void run() {
            	MasterLists msl = new MasterLists();
                Object[] options = {"Create New Character", "Load Character"};
                int selection = JOptionPane.showOptionDialog(null, "Welcome to Character Creator!\nSelect an Option:",
                                                             "Character Creator", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                                                             null,     //do not use a custom Icon
                                                             options,  //the titles of buttons
                                                             null); //default button title
                switch(selection) {
                    case 0:
                        NewCharacter nc = new NewCharacter();
                        nc.newCharacterGui();
                        break;
                    case 1:
                        Character loadCharacter = new Character();
                        CharacterCreator c = new CharacterCreator(loadCharacter);
                        c.display();
                        c.loadCharacter();
                        break;
                    case JOptionPane.CLOSED_OPTION:
                        System.exit(0);
                        break;
                }
            }
        });
    }
}

class TransparentJCheckBox extends JCheckBox {
    public TransparentJCheckBox(String text, Boolean selected) {
        super(text, selected);
        setOpaque(false);
    }
}

class TransparentJPanel extends JPanel {
    {
        setOpaque(false);
        setFocusable(true);
    }
}

class BasicInfoPanel extends JPanel {
    JLabel basicInfoNameLabel;
    JTextField basicInfoValue;
    String value;
    Character character;
    
    public BasicInfoPanel(String inName, String inValue, Character inCharacter) {
        this.value = inValue;
        this.character = inCharacter;
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        basicInfoValue = new JTextField(inValue);
        basicInfoValue.setOpaque(false);
        basicInfoValue.setEditable(false);
        basicInfoValue.setHorizontalAlignment(JTextField.CENTER);
        basicInfoValue.setFont(new Font(null, Font.BOLD, 20));
        basicInfoValue.setBackground(new Color(0,0,0,0));
        basicInfoValue.setBorder(new EmptyBorder(0,0,0,0));
        add(basicInfoValue);
        
        basicInfoNameLabel = new JLabel(inName);
        basicInfoNameLabel.setOpaque(false);
        basicInfoNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        basicInfoNameLabel.setBorder(new EmptyBorder(0,0,8,0));
        add(basicInfoNameLabel);
        
    }
    
    public void setEditable(JTextField basicInfoValueIn) {
        basicInfoValue.setEditable(true);
        basicInfoValue.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                basicInfoValue.setBackground(new Color(255, 255, 255));
                basicInfoValue.setOpaque(true);
                basicInfoValue.repaint();
                
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                basicInfoValue.setBackground(new Color(0, 0, 0));
                basicInfoValue.setOpaque(false);
                basicInfoValue.repaint();
            }
        });
    }
    
    public void addSpinner() {
        JSpinner basicInfoValueSpinner;
        int spinnerValue = Integer.valueOf(value);
        remove(basicInfoValue);
        SpinnerModel model = new SpinnerNumberModel(spinnerValue, 0, 9999, 1);
        basicInfoValueSpinner = new JSpinner(model);
        basicInfoValueSpinner.setFont(new Font(null, Font.BOLD, 24));
        basicInfoValueSpinner.setOpaque(false);
        basicInfoValueSpinner.getEditor().setOpaque(false);
        JFormattedTextField textField = ((JSpinner.DefaultEditor)basicInfoValueSpinner.getEditor()).getTextField();
        textField.setOpaque(false);
        textField.setColumns(2);
        textField.setBorder(new EmptyBorder(0,0,0,5));
        textField.setEditable(false);
        JPanel helperPanel = new TransparentJPanel();
        helperPanel.add(basicInfoValueSpinner);
        add(helperPanel, BorderLayout.CENTER);
        add(helperPanel);
        add(basicInfoNameLabel);
        
        DefaultFormatter formatter = (DefaultFormatter) textField.getFormatter();
        formatter.setCommitsOnValidEdit(true);
        basicInfoValueSpinner.addChangeListener(new ChangeListener() {
            
            @Override
            public void stateChanged(ChangeEvent e) {
                character.setExperiencePoints((int) basicInfoValueSpinner.getValue());
                
            }
        });
    }
}


class AbilityScorePanel extends JPanel {
    
    JLabel abilityScoreNameLabel;
    JSpinner spinner;
    JLabel modifierLabel;
    int value = 1;
    int modifier = 0;
    
    public AbilityScorePanel(String inAbility, Character inCharacter, CharacterCreator cc) {
        
        setOpaque(false);
        Border margin = new EmptyBorder(5,5,0,5);
        Border padding = new EmptyBorder(5,5,5,5);
        Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        Border inside = new CompoundBorder(border, padding);
        setBorder(new CompoundBorder(margin, inside));
        setLayout(new BorderLayout());
         
        switch(inAbility) {
            case "Strength" : value = inCharacter.getAbilityScoreStrength();
                break;
            case "Dexterity" : value = inCharacter.getAbilityScoreDexterity();
                break;
            case "Constitution" : value = inCharacter.getAbilityScoreConstitution();
                break;
            case "Intelligence" : value = inCharacter.getAbilityScoreIntelligence();
                break;
            case "Wisdom" : value = inCharacter.getAbilityScoreWisdom();
                break;
            case "Charisma" : value = inCharacter.getAbilityScoreCharisma();
                break;
            default:
                break;
        }
        
        SpinnerModel model = new SpinnerNumberModel(value, 1, 30, 1);
        spinner = new JSpinner(model);
        spinner.setFont(new Font(null, Font.BOLD, 24));
        spinner.setOpaque(false);
        spinner.getEditor().setOpaque(false);
        JFormattedTextField textField = ((JSpinner.DefaultEditor)spinner.getEditor()).getTextField();
        textField.setOpaque(false);
        textField.setColumns(2);
        textField.setBorder(new EmptyBorder(0,0,0,5));
        textField.setEditable(false);
        
        // listener to change set new values in character.java if spinner buttons are used
        DefaultFormatter formatter = (DefaultFormatter) textField.getFormatter();
        formatter.setCommitsOnValidEdit(true);
        spinner.addChangeListener(new ChangeListener() {
            
            @Override
            public void stateChanged(ChangeEvent e) {
                switch (inAbility) {
                    case "Strength":
                        inCharacter.setAbilityScoreStrength((int) spinner.getValue());
                        break;
                    case "Dexterity":
                        inCharacter.setAbilityScoreDexterity((int) spinner.getValue());
                        break;
                    case "Constitution":
                        inCharacter.setAbilityScoreConstitution((int) spinner.getValue());
                        break;
                    case "Intelligence":
                        inCharacter.setAbilityScoreIntelligence((int) spinner.getValue());
                        break;
                    case "Wisdom":
                        inCharacter.setAbilityScoreWisdom((int) spinner.getValue());
                        break;
                    case "Charisma":
                        inCharacter.setAbilityScoreCharisma((int) spinner.getValue());
                        break;
                    default:
                        break;
                }
                modifier = AttributeBonusCalculator.getAttributeBonus((int) spinner.getValue());
                String modifierString = String.valueOf(modifier);
                if (modifier > 0) modifierString = "+" + modifierString;
                modifierLabel.setText(" (" + modifierString + ")");
                cc.refresh();           
            }
        });
        
        abilityScoreNameLabel = new JLabel(inAbility, SwingConstants.CENTER);
        modifier = AttributeBonusCalculator.getAttributeBonus(value);
        String modifierString = String.valueOf(modifier);
        if (modifier > 0) modifierString = "+" + modifierString;
        modifierLabel = new JLabel((" (" + modifierString + ")"), SwingConstants.RIGHT);
        modifierLabel.setFont(new Font(null, Font.ITALIC, 11));
        add(abilityScoreNameLabel, BorderLayout.NORTH);
        JPanel helperPanel = new TransparentJPanel();
        helperPanel.add(spinner);
        add(helperPanel, BorderLayout.CENTER);
        helperPanel.add(modifierLabel);
    }
}

class StatsPanel extends JPanel {
    JLabel statNameLabel;
    JLabel statNumberLabel;
    String inStat;
    public JLabel statLabel;
    public SpinnerModel model;
    public int value = 0;
    JLabel maxHitPointLabel;
    
    public StatsPanel(String inStat, Character inCharacter, CharacterCreator cc)
    
    {
    	this.inStat = inStat;
    	setOpaque(false);
        Border margin = new EmptyBorder(5,5,0,5);
        
        Border padding = new EmptyBorder(5,5,5,5);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1, true);
        Border inside = new CompoundBorder(border, padding);
        setBorder(new CompoundBorder(margin, inside));
        setLayout(new BorderLayout());
        
        statNameLabel = new JLabel(inStat, SwingConstants.CENTER);
        add(statNameLabel, BorderLayout.NORTH);
        
        
        switch (inStat) {
            case "Hit Points" :
                value = inCharacter.getHitPointsCurrent();
                break;
            case "Armor Class" :
                value = inCharacter.getArmorClass();
                break;
            case "Initiative" :
                value = inCharacter.getInitiative();
                break;
            case "Proficiency Bonus" :
                value = inCharacter.getProficiencyBonus();
                break;
            case "Speed" :
                value = inCharacter.getSpeed();
                break;
            case "Passive Perception" :
                value = inCharacter.getPassivePerception();
                break;
        }
        
        if (inStat.matches("Proficiency Bonus") || inStat.matches("Passive Perception") || inStat.matches("Initiative")) {
            statLabel = new JLabel(String.valueOf(value));
            statLabel.setFont(new Font(null, Font.BOLD, 20));
            statLabel.setHorizontalAlignment(JLabel.CENTER);
            add(statLabel, BorderLayout.CENTER);
        }
        
        else {
            model = new SpinnerNumberModel(value, 0, 99, 1);
            JSpinner spinner = new JSpinner(model);
            spinner.setFont(new Font(null, Font.BOLD, 20));
            spinner.setOpaque(false);
            spinner.getEditor().setOpaque(false);
            JFormattedTextField textField = ((JSpinner.DefaultEditor)spinner.getEditor()).getTextField();
            textField.setOpaque(false);
            textField.setColumns(2);
            textField.setBorder(new EmptyBorder(0,0,0,5));
            textField.setEditable(false);
            JPanel helperPanel = new TransparentJPanel();
            helperPanel.add(spinner);
            if(inStat.matches("Hit Points")) {
            		maxHitPointLabel = new JLabel("/" + inCharacter.getHitPointsMaximum());
            		maxHitPointLabel.setOpaque(false);
            		helperPanel.add(maxHitPointLabel);
            }
            add(helperPanel, BorderLayout.CENTER);
            
            // listener to change set new values in character.java if spinner buttons are used
            DefaultFormatter formatter = (DefaultFormatter) textField.getFormatter();
            formatter.setCommitsOnValidEdit(true);
            spinner.addChangeListener(new ChangeListener() {
                
                @Override
                public void stateChanged(ChangeEvent e) {
                    switch (inStat) {
                        case "Hit Points":
                            inCharacter.setHitPointsCurrent((int) spinner.getValue());
                            break;
                        case "Armor Class":
                            inCharacter.setArmorClass((int) spinner.getValue());
                            break;
                        case "Initiative":
                            //inCharacter.setInitiative((int) spinner.getValue());
                            break;
                        case "Proficiency Bonus":
                            
                            break;
                        case "Speed":
                            inCharacter.setSpeed((int) spinner.getValue());
                            break;
                        case "Passive Perception":
                            
                            break;
                        default:
                            break;
                    }
                    cc.refresh();
                    
                }
            });
        }    
    }
}

