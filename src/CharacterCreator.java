
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
	JCheckBox strengthCheckBox;
	JCheckBox dexterityCheckBox;
	JCheckBox constitutionCheckBox;
	JCheckBox intelligenceCheckBox;
	JCheckBox wisdomCheckBox;
	JCheckBox charismaCheckBox;
	TransparentJPanel skillsPanel;
	String[] skills = {"Acrobatics", "Animal Handling", "Arcana", "Athletics", "Deception",
			"History", "Insight", "Intimidation", "Investigation", "Medicine", "Nature",
			"Perception", "Performance", "Persuasion", "Religion", "Sleight of Hand", 
			"Stealth", "Survival"};
	
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
	
	//inventory subpanel components
	TransparentJPanel featuresPanel;
	TransparentJPanel featuresDisplayPanel;
	TransparentJPanel featuresButtonsPanel;
	JButton addFeatureButton;
	JButton removeFeatureButton;
	
	//spells subpanel components
	TransparentJPanel equippedItemsPanel;
	TransparentJPanel equippedItemsDisplayPanel;
	TransparentJPanel equippedItemsButtonsPanel;
	JButton equipItemButton;
	JButton unequipItemButton;
	
	//TODO add inventoryAndSpellsPanel components
	TransparentJPanel inventoryAndSpellsPanel;
	TransparentJPanel inventoryPanel;
	TransparentJPanel spellsPanel;
	
	
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
	
	public CharacterCreator(Character CharacterIn) {
		
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
		
		
		/*
		 * Set Tabbed pane
		 */
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
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
			photo = ImageIO.read(new File("cabbage.jpg"));
			Image resizedPhoto = photo.getScaledInstance(100, 100, Image.SCALE_SMOOTH);	
			photoLabel = new JLabel(new ImageIcon(resizedPhoto));
			photoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
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
				String.valueOf(character.getLevel()));
		bioInfoPanel.add(classInfoPanel);
		BasicInfoPanel backgroundInfoPanel = new BasicInfoPanel("Background", character.getBackground());
		bioInfoPanel.add(backgroundInfoPanel);
		BasicInfoPanel factionInfoPanel = new BasicInfoPanel("Faction", "Faction");
		bioInfoPanel.add(factionInfoPanel);
		BasicInfoPanel raceInfoPanel = new BasicInfoPanel("Race", character.getRace());
		bioInfoPanel.add(raceInfoPanel);
		BasicInfoPanel alignmentInfoPanel = new BasicInfoPanel("Alignment", character.getAlignment());
		bioInfoPanel.add(alignmentInfoPanel);
		BasicInfoPanel experiencePointsInfoPanel = new BasicInfoPanel("Experience Points", 
				String.valueOf(character.getExperiencePoints()));
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
		strengthCheckBox = new JCheckBox("+5 Strength");
		savingThrowsPanel.add(strengthCheckBox);
		dexterityCheckBox = new JCheckBox("-1 Dexterity");
		savingThrowsPanel.add(dexterityCheckBox);
		constitutionCheckBox = new JCheckBox("+3 Constitution");
		savingThrowsPanel.add(constitutionCheckBox);
		intelligenceCheckBox = new JCheckBox("+0 Intelligence");
		savingThrowsPanel.add(intelligenceCheckBox);
		wisdomCheckBox = new JCheckBox("-2 Wisdom");
		savingThrowsPanel.add(wisdomCheckBox);
		charismaCheckBox = new JCheckBox("+2 Charisma");
		savingThrowsPanel.add(charismaCheckBox);
		
		// Skills panel
		for(String skill: skills) {
			boolean selected = false;
			// TODO need method to get character Skill list
			//if (inCharacter.getSkillList().contains(skill)) selected = true;
			skillsPanel.add(new JCheckBox("+3 " + skill + "    "), selected);
			
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
		
//		JLabel wieldedShieldLabel = new JLabel("Wielded Shield");
//		equippedItemsDisplayPanel.add(wieldedShieldLabel);
//		String[] wieldedShieldOptions = { "None", "Shield" };
//		JComboBox wieldedShieldBox = new JComboBox(wieldedShieldOptions);
//		equippedItemsDisplayPanel.add(wieldedShieldBox);
		
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
		
		//inventory panel
		inventoryPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
				Color.BLACK, 1, true), "Inventory"));
		
		//spells panel
		spellsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
				Color.BLACK, 1, true), "Spells"));
	}
	
	private void display() {
		frame.setVisible(true);
	}
	

	public static void main(String[] args) {
		Object[] options = {"Create New Character", "Load Character"};
		int selection = JOptionPane.showOptionDialog(null, "Welcome to Character Creator!\nSelect an Option:",
				"Character Creator", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
				null,     //do not use a custom Icon
				options,  //the titles of buttons
				null); //default button title
		switch(selection) {
		case 0: 
			NewCharacter nc = new NewCharacter();
			

			break;
		case 1:
			Character testCharacter = new Character();
			testCharacter.setName("Test Char");
			testCharacter.setLevel(4);
			testCharacter.setAbilityScoreCharisma(5);
			testCharacter.setAbilityScoreDexterity(6);
			testCharacter.setSpeed(15);
			CharacterCreator c = new CharacterCreator(testCharacter);
			c.display();
			break;
		case JOptionPane.CLOSED_OPTION:
			System.exit(0);
		}
		
		
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
	
	public BasicInfoPanel(String inName, String inValue) {
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		basicInfoValue = new JTextField(inValue);
		basicInfoValue.setOpaque(false);
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
}


class AbilityScorePanel extends JPanel {
	
	JLabel abilityScoreNameLabel;
	JSpinner spinner;
	//JLabel abilityScoreLabel;
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
		textField.setBackground(new Color(0,0,0,0));
		textField.setColumns(2);
		textField.setBorder(new EmptyBorder(0,0,0,5));
		textField.setEditable(false);
		
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
		
		int value = 0;
		
		switch (inStat) {
		case "Hit Points" :
			break;
		case "Armor Class" :
			break;
		case "Initiative" :
			break;
		case "Proficiency Bonus" :
			break;
		case "Speed" :
			value = inCharacter.getSpeed();
			break;
		case "Passive Perception" :
			break;
		}
		
		SpinnerModel model = new SpinnerNumberModel(value, 0, 99, 1);
		JSpinner spinner = new JSpinner(model);
		spinner.setFont(new Font(null, Font.BOLD, 20));
		spinner.setOpaque(false);
		spinner.getEditor().setOpaque(false);		
		JFormattedTextField textField = ((JSpinner.DefaultEditor)spinner.getEditor()).getTextField();
		textField.setBackground(new Color(0,0,0,0));
		textField.setColumns(2);
		textField.setBorder(new EmptyBorder(0,0,0,5));
		textField.setEditable(false);
		
		statNameLabel = new JLabel(inStat, SwingConstants.CENTER);
		add(statNameLabel, BorderLayout.NORTH);
		
		JPanel helperPanel = new TransparentJPanel();
		helperPanel.add(spinner);
		add(helperPanel, BorderLayout.CENTER);
	}
}





