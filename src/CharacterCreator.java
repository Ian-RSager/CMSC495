import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
    
    
    //stats subpanel components
    TransparentJPanel statsPanel;
    JPanel hitPointsPanel;
    JPanel armorClassPanel;
    JPanel initiativePanel;
    JPanel proficiencyBonusPanel;
    JPanel speedPanel;
    JPanel passivePerceptionPanel;
    
    //features, equippedItems, langauges subpanel
    TransparentJPanel featuresAndEquippedItemsSubPanel;
    TransparentJPanel languagesPanel;
    JLabel languagesLabel;
    
    //features subpanel components
    TransparentJPanel featuresPanel;
    TransparentJPanel featuresDisplayPanel;
    TransparentJPanel featuresButtonsPanel;
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
    JScrollPane inventoryScrollPane;
    JButton addItemButton;
    JButton removeItemButton;
   
    TransparentJPanel spellsPanel;
    TransparentJPanel spellsButtonsPanel;
    TransparentJPanel spellsDisplayPanel;
    JScrollPane spellsScrollPane;
    JButton addSpellButton;
    JButton removeSpellButton;
    
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
            photo = ImageIO.read(new File("portraits/Beldora.png"));
            Image resizedPhoto = photo.getScaledInstance(80, 112, Image.SCALE_SMOOTH);
            photoLabel = new JLabel(new ImageIcon(resizedPhoto));
            //photoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        photoLabel.setSize(50, 50);
        photoNamePanel.add(photoLabel, BorderLayout.CENTER);
        nameLabel = new JLabel(character.getName(), SwingConstants.CENTER);
        
        nameLabel.setFont(new Font(null, Font.BOLD, 20));
        photoNamePanel.add(nameLabel, BorderLayout.SOUTH);
        photoNamePanel.setBorder(new EmptyBorder(0, 13, 0, 7));
        
        // Class, level, background, faction, race, alignment, XP
        bioInfoPanel = new TransparentJPanel();
        Border bioInfoPanelBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border bioInfoPanelMargin = new EmptyBorder(0, 10, 0, 0);
        bioInfoPanel.setBorder(BorderFactory.createCompoundBorder(bioInfoPanelMargin, bioInfoPanelBorder));
        bioInfoPanel.setLayout(new GridLayout(0, 3));
        topMainPanel.add(bioInfoPanel, BorderLayout.CENTER);
        
        BasicInfoPanel classInfoPanel = new BasicInfoPanel("Class and Level", "Class " +
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
        
        
        // Ability scores panel
        abilityScoresPanel = new TransparentJPanel();
        abilityScoresPanel.setLayout(new GridLayout(0,1));
        mainPanel.add(abilityScoresPanel, BorderLayout.WEST);
        strengthPanel = new AbilityScorePanel("Strength", character);
        abilityScoresPanel.add(strengthPanel);
        dexterityPanel = new AbilityScorePanel("Dexterity", character);
        abilityScoresPanel.add(dexterityPanel);
        constitutionPanel = new AbilityScorePanel("Constitution", character);
        abilityScoresPanel.add(constitutionPanel);
        intelligencePanel = new AbilityScorePanel("Intelligence", character);
        abilityScoresPanel.add(intelligencePanel);
        wisdomPanel = new AbilityScorePanel("Wisdom", character);
        abilityScoresPanel.add(wisdomPanel);
        charismaPanel = new AbilityScorePanel("Charisma", character);
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
        		String text = String.valueOf(savingThrowFinalArray[i]) + " " + savingThrows[i];
        		savingThrowsPanel.add(new TransparentJCheckBox(text, savingThrowProficiencyArray[i])); 
        }
        
        
        // Skills panel
        String[] skills = {"Acrobatics", "Animal Handling", "Arcana", "Athletics", "Deception",
        	    "History", "Insight", "Intimidation", "Investigation", "Medicine", "Nature",
        	    "Perception", "Performance", "Persuasion", "Religion", "Sleight of Hand",
        	    "Stealth", "Survival"};
        
        int[]skillModifierArray = character.getTotalSkillModifierArray();
        for(int i=0; i<skills.length; i++) {
            boolean selected = false;
            //if (inCharacter.getSkillList().contains(skill)) selected = true; 
            skillsPanel.add(new TransparentJCheckBox(String.valueOf(skillModifierArray[i]) +
            		" " + skills[i] + "     ", selected));
            
        }
        
        // Stats panel
        statsPanel = new TransparentJPanel();
        statsPanel.setLayout(new GridLayout(0,1));
        centerSubPanel.add(statsPanel, BorderLayout.WEST);
        hitPointsPanel = new StatsPanel("Hit Points", character);
        statsPanel.add(hitPointsPanel);
        armorClassPanel = new StatsPanel("Armor Class", character);
        statsPanel.add(armorClassPanel);
        initiativePanel = new StatsPanel("Initiative", character);
        statsPanel.add(initiativePanel);
        proficiencyBonusPanel = new StatsPanel("Proficiency Bonus", character);
        statsPanel.add(proficiencyBonusPanel);
        speedPanel = new StatsPanel("Speed", character);
        statsPanel.add(speedPanel);
        passivePerceptionPanel = new StatsPanel("Passive Perception", character);
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
        featuresPanel.add(featuresButtonsPanel, BorderLayout.SOUTH);
        
        //equipped items panel buttons
        equippedItemsButtonsPanel = new TransparentJPanel();
        equippedItemsButtonsPanel.setLayout(new GridLayout(1,0));
        equipItemButton = new JButton("Equip Item");
        unequipItemButton = new JButton("Unequip Item");
        equippedItemsButtonsPanel.add(equipItemButton);
        equippedItemsButtonsPanel.add(unequipItemButton);
        equippedItemsPanel.add(equippedItemsButtonsPanel, BorderLayout.SOUTH);
        
        
        
        // features display panel
        featuresDisplayPanel = new TransparentJPanel();
        JScrollPane featuresScrollPane = new JScrollPane(featuresDisplayPanel);
        featuresPanel.add(featuresScrollPane, BorderLayout.CENTER);
        featuresScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                                                                                                     Color.BLACK, 1, true), "Features"));
        featuresScrollPane.setOpaque(false);
        featuresScrollPane.getViewport().setOpaque(false);
        featuresScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        // equippedItems display panel
        equippedItemsDisplayPanel = new TransparentJPanel();
        //equippedItemsDisplayPanel.setLayout(new GridLayout(0,1));
        JScrollPane equippedItemsScrollPane = new JScrollPane(equippedItemsDisplayPanel);
        equippedItemsPanel.add(equippedItemsScrollPane, BorderLayout.CENTER);
        equippedItemsScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                                                                                                          Color.BLACK, 1, true), "Equipped Items"));
        equippedItemsScrollPane.setOpaque(false);
        equippedItemsScrollPane.getViewport().setOpaque(false);
        equippedItemsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // equipped items combo boxes
        JLabel wornArmorLabel = new JLabel("Worn Armor");
        equippedItemsDisplayPanel.add(wornArmorLabel);
        String[] wornArmorOptions = { "None", "Chain Mail", "Hide" };
        JComboBox wornArmorBox = new JComboBox(wornArmorOptions);
        equippedItemsDisplayPanel.add(wornArmorBox);
        
        //        JLabel wieldedShieldLabel = new JLabel("Wielded Shield");
        //        equippedItemsDisplayPanel.add(wieldedShieldLabel);
        //        String[] wieldedShieldOptions = { "None", "Shield" };
        //        JComboBox wieldedShieldBox = new JComboBox(wieldedShieldOptions);
        //        equippedItemsDisplayPanel.add(wieldedShieldBox);
        
        // languages panel
        languagesPanel = new TransparentJPanel();
        equippedItemsPanel.add(languagesPanel, BorderLayout.NORTH); // add languages above equipment
        languagesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                                                                                                 Color.BLACK, 1, true), "Languages"));
        languagesLabel = new JLabel("Dwarvish, Common, Giant, Halfling");
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
        inventoryDisplayPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                                                                                                        Color.BLACK, 1, true), "Inventory"));
        inventoryScrollPane = new JScrollPane(inventoryDisplayPanel);
            for (Item item: character.getItemsList()) {
                    String itemDescription = item.name + "\n\t" + item.description + "\n";
                    inventoryDisplayPanel.add(new JLabel(itemDescription));
            }
        
        inventoryPanel.setLayout(new BorderLayout());
        inventoryButtonsPanel = new TransparentJPanel();
        inventoryButtonsPanel.setLayout(new GridLayout(1,0));
        addItemButton = new JButton("Add Item");
        removeItemButton = new JButton("Remove Item");
        inventoryButtonsPanel.add(addItemButton);
        inventoryButtonsPanel.add(removeItemButton);
        
        inventoryPanel.add(inventoryDisplayPanel, BorderLayout.CENTER);
        inventoryPanel.add(inventoryButtonsPanel, BorderLayout.SOUTH);
        
        //spells panel
        spellsDisplayPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                                                                                              Color.BLACK, 1, true), "Spells"));
        spellsScrollPane = new JScrollPane(spellsDisplayPanel);
//        for (Spell spell: character.getKnownSpells) {
//                String spellDescription = spell.name + "\n\t" + spell.description + "\n";
//                inventoryDisplayPanel.add(new JLabel(spellDescription));
//        }
    
		spellsPanel.setLayout(new BorderLayout());
		spellsButtonsPanel = new TransparentJPanel();
		spellsButtonsPanel.setLayout(new GridLayout(1,0));
		addSpellButton = new JButton("Add Spell");
		removeSpellButton = new JButton("Remove Spell");
		spellsButtonsPanel.add(addSpellButton);
		spellsButtonsPanel.add(removeSpellButton);
		
		spellsPanel.add(spellsDisplayPanel, BorderLayout.CENTER);
        spellsPanel.add(spellsButtonsPanel, BorderLayout.SOUTH);
    }
    
    private void display() {
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
			try {
				FileOutputStream fos = new FileOutputStream(file);
				System.out.println("saved");
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
    
    
    public static void main(String[] args) {
    		MasterLists.createMasterLists();
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
        SpinnerModel model = new SpinnerNumberModel(spinnerValue, 0, 99, 1);
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
    
    public AbilityScorePanel(String inAbility, Character inCharacter) {
        
        setOpaque(false);
        Border margin = new EmptyBorder(5,5,0,5);
        Border padding = new EmptyBorder(5,5,5,5);
        Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        Border inside = new CompoundBorder(border, padding);
        setBorder(new CompoundBorder(margin, inside));
        setLayout(new BorderLayout());
        
        int value = 0;
        
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
        
        SpinnerModel model = new SpinnerNumberModel(value, 0, 99, 1);
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
                
            }
        });
        
        abilityScoreNameLabel = new JLabel(inAbility, SwingConstants.CENTER);
        String fillerModifier = String.valueOf(Math.round(Math.random()*10 - 5));
        if (Integer.valueOf(fillerModifier) > 0) fillerModifier = "+" + fillerModifier;
        modifierLabel = new JLabel((" (" + fillerModifier + ")"), SwingConstants.RIGHT);
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
    
    public StatsPanel(String inStat, Character inCharacter)
    
    {
        setOpaque(false);
        Border margin = new EmptyBorder(5,5,0,5);
        
        Border padding = new EmptyBorder(5,5,5,5);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1, true);
        Border inside = new CompoundBorder(border, padding);
        setBorder(new CompoundBorder(margin, inside));
        setLayout(new BorderLayout());
        
        statNameLabel = new JLabel(inStat, SwingConstants.CENTER);
        add(statNameLabel, BorderLayout.NORTH);
        
        int value = 0;
        
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
        
         
        if (inStat.matches("Proficiency Bonus") || inStat.matches("Passive Perception")) {
        		JLabel statLabel = new JLabel(String.valueOf(value));
        		statLabel.setFont(new Font(null, Font.BOLD, 20));
        		statLabel.setHorizontalAlignment(JLabel.CENTER);
        		add(statLabel, BorderLayout.CENTER);
        }
        
        else {
	        SpinnerModel model = new SpinnerNumberModel(value, 0, 99, 1);
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
	                        inCharacter.setInitiative((int) spinner.getValue());
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
	                
	            }
	        });
        }
    }
}
