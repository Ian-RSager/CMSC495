import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class CharacterCreator {
	
	JFrame frame;
	
	TransparentJPanel mainPanel;
	
	//top panel components
	TransparentJPanel topMainPanel;
	TransparentJPanel photoNamePanel;
	JLabel photoLabel;
	JLabel nameLabel;
	TransparentJPanel classInfoPanel;
	JLabel classField;
	JLabel classLabel;
	JLabel backgroundField;
	JLabel backgroundLabel;
	JLabel factionField;
	JLabel factionLabel;
	JLabel raceField;
	JLabel raceLabel;
	JLabel alignmentField;
	JLabel alignmentLabel;
	JLabel experiencePointsField;
	JLabel experiencePointsLabel;

	
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
	
	//inventory and spells subpanel
	TransparentJPanel inventoryAndSpellsSubPanel;
	
	//inventory subpanel components
	TransparentJPanel inventoryPanel;
	
	//spells subpanel components
	TransparentJPanel spellsPanel;
	
	
	//TODO add treasure and features components
	TransparentJPanel treasurePanel;
	TransparentJPanel featuresPanel;
	
	//top menu bar components
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenuItem newSheetMenuItem;
	JMenuItem loadSheetMenuItem;
	JMenuItem saveSheetMenuItem;
	JMenuItem quitMenuItem;
	
	
	public CharacterCreator() {
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
		treasurePanel = new TransparentJPanel();
		featuresPanel = new TransparentJPanel();
		tabbedPane.add("MAIN", mainPanel);
		tabbedPane.add("TREASURE", treasurePanel);
		tabbedPane.add("FEATURES", featuresPanel);
		
		
		
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
		nameLabel = new JLabel("Bob", SwingConstants.CENTER);
		nameLabel.setFont(new Font(null, Font.BOLD, 20));
		photoNamePanel.add(nameLabel, BorderLayout.SOUTH);
		
		// Class, level, background, faction, race, alignment, XP
		classInfoPanel = new TransparentJPanel();
		Border classInfoPanelBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
		Border classInfoPanelMargin = new EmptyBorder(0, 10, 0, 0);
		classInfoPanel.setBorder(BorderFactory.createCompoundBorder(classInfoPanelMargin, classInfoPanelBorder));
		classInfoPanel.setLayout(new GridLayout(0, 3));
		topMainPanel.add(classInfoPanel, BorderLayout.CENTER);
		//row 1
		classField = new JLabel("Ranger 1");
		classInfoPanel.add(classField);
		backgroundField = new JLabel("Outlander");
		classInfoPanel.add(backgroundField);
		factionField = new JLabel("Emerald Enclave");
		classInfoPanel.add(factionField);
		//row 2
		classLabel = new JLabel("Class and Level");
		classInfoPanel.add(classLabel);
		backgroundLabel = new JLabel("Background");
		classInfoPanel.add(backgroundLabel);
		factionLabel = new JLabel("Faction");
		classInfoPanel.add(factionLabel);
		//row 3
		raceField = new JLabel("Wood Elf");
		classInfoPanel.add(raceField);
		alignmentField = new JLabel("Lawful Good");
		classInfoPanel.add(alignmentField);
		experiencePointsField = new JLabel("0");
		classInfoPanel.add(experiencePointsField);
		//row 4
		raceLabel = new JLabel("Race");
		classInfoPanel.add(raceLabel);
		alignmentLabel = new JLabel("Alignment");
		classInfoPanel.add(alignmentLabel);
		experiencePointsLabel = new JLabel("Experience Points");
		classInfoPanel.add(experiencePointsLabel);
		//formatting
		JLabel[] classInfoLabels = {classLabel, backgroundLabel, factionLabel, raceLabel,
				alignmentLabel, experiencePointsLabel};
		JLabel[] classInfoFields = {classField, backgroundField, 
				factionField, raceField, alignmentField, experiencePointsField};
		for (JLabel label: classInfoLabels) {
			label.setHorizontalAlignment(SwingConstants.CENTER);
		}
		for (JLabel label: classInfoFields) {
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font(null, Font.BOLD, 20));
		}
		
		// Ability scores panel
		abilityScoresPanel = new TransparentJPanel();
		abilityScoresPanel.setLayout(new GridLayout(0,1));
		mainPanel.add(abilityScoresPanel, BorderLayout.WEST);
		strengthPanel = new AbilityScorePanel("Strength");
		abilityScoresPanel.add(strengthPanel);
		dexterityPanel = new AbilityScorePanel("Dexterity");
		abilityScoresPanel.add(dexterityPanel);
		constitutionPanel = new AbilityScorePanel("Constitution");
		abilityScoresPanel.add(constitutionPanel);
		intelligencePanel = new AbilityScorePanel("Intelligence");
		abilityScoresPanel.add(intelligencePanel);
		wisdomPanel = new AbilityScorePanel("Wisdom");
		abilityScoresPanel.add(wisdomPanel);
		charismaPanel = new AbilityScorePanel("Charisma");
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
			skillsPanel.add(new JCheckBox(skill + "    "));
		}
		
		// Stats panel
		statsPanel = new TransparentJPanel();
		statsPanel.setLayout(new GridLayout(0,1));
		centerSubPanel.add(statsPanel, BorderLayout.WEST);
		hitPointsPanel = new StatsPanel("Hit Points");
		statsPanel.add(hitPointsPanel);
		armorClassPanel = new StatsPanel("Armor Class");
		statsPanel.add(armorClassPanel);
		initiativePanel = new StatsPanel("Initiative");
		statsPanel.add(initiativePanel);
		proficiencyBonusPanel = new StatsPanel("Proficiency Bonus");
		statsPanel.add(proficiencyBonusPanel);
		speedPanel = new StatsPanel("Speed");
		statsPanel.add(speedPanel);
		passivePerceptionPanel = new StatsPanel("Passive Perception");
		statsPanel.add(passivePerceptionPanel);
		
		// inventory and spells subpanel
		inventoryAndSpellsSubPanel = new TransparentJPanel();
		centerSubPanel.add(inventoryAndSpellsSubPanel, BorderLayout.CENTER);
		inventoryAndSpellsSubPanel.setLayout(new GridLayout(1, 0));
		inventoryPanel = new TransparentJPanel();
		spellsPanel = new TransparentJPanel();
		
		
		//Inventory panel
		JScrollPane inventoryScrollPane = new JScrollPane(inventoryPanel);
		inventoryAndSpellsSubPanel.add(inventoryScrollPane);
		inventoryScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
				Color.BLACK, 1, true), "Inventory"));
		inventoryScrollPane.setOpaque(false);
		inventoryScrollPane.getViewport().setOpaque(false);
		inventoryScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		//spells panel
		JScrollPane spellsScrollPane = new JScrollPane(inventoryPanel);
		inventoryAndSpellsSubPanel.add(spellsScrollPane);
		spellsScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
				Color.BLACK, 1, true), "Spells"));
		spellsScrollPane.setOpaque(false);
		spellsScrollPane.getViewport().setOpaque(false);
		spellsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
	}
	
	private void display() {
		frame.setVisible(true);
	}
	
	private static void setUIFont(javax.swing.plaf.FontUIResource f)
	{
	    java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
	    while (keys.hasMoreElements())
	    {
	        Object key = keys.nextElement();
	        Object value = UIManager.get(key);
	        if (value instanceof javax.swing.plaf.FontUIResource)
	        {
	            UIManager.put(key, f);
	        }
	    }
	}

	public static void main(String[] args) {
	
//		File font_file = new File("PTC55F.ttf");
//		Font sizedFont = new Font("Courier", Font.PLAIN, 12);
//		try {
//			Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
//			sizedFont = font.deriveFont(12f);
//		} catch (FontFormatException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		setUIFont (new javax.swing.plaf.FontUIResource(sizedFont));
		CharacterCreator c = new CharacterCreator();
		c.display();

	}

}

class TransparentJPanel extends JPanel {
    {
        setOpaque(false);
    }
}

class AbilityScorePanel extends JPanel {
	JLabel abilityScoreNameLabel;
	JLabel abilityScoreLabel;
	JLabel modifierLabel;
	
	public AbilityScorePanel(String inAbility)
	{
		setOpaque(false);
		Border margin = new EmptyBorder(5,5,5,5);
		
		Border padding = new EmptyBorder(5,5,5,5);
		Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED);
		Border inside = new CompoundBorder(border, padding);
		setBorder(new CompoundBorder(margin, inside));
		setLayout(new BorderLayout());
		abilityScoreNameLabel = new JLabel(inAbility, SwingConstants.CENTER);
		String fillerScore = String.valueOf(Math.round(Math.random()*20));
		abilityScoreLabel = new JLabel(fillerScore, SwingConstants.CENTER);
		abilityScoreLabel.setFont(new Font(null, Font.BOLD, 25));
		String fillerModifier = String.valueOf(Math.round(Math.random()*10 - 5));
		modifierLabel = new JLabel(fillerModifier, SwingConstants.RIGHT);
		add(abilityScoreNameLabel, BorderLayout.NORTH);
		add(abilityScoreLabel, BorderLayout.CENTER);
		add(modifierLabel, BorderLayout.SOUTH);		
	}
}

class StatsPanel extends JPanel {
	JLabel statNameLabel;
	JLabel statNumberLabel;
	
	public StatsPanel(String inStat)
	{
		setOpaque(false);
		Border margin = new EmptyBorder(5,5,0,5);
		
		Border padding = new EmptyBorder(5,5,5,5);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1, true);
		Border inside = new CompoundBorder(border, padding);
		setBorder(new CompoundBorder(margin, inside));
		setLayout(new BorderLayout());
		statNameLabel = new JLabel(inStat, SwingConstants.CENTER);
		String fillerNumber = String.valueOf(Math.round(Math.random()*100));
		statNumberLabel = new JLabel(fillerNumber, SwingConstants.CENTER);
		statNumberLabel.setFont(new Font(null, Font.BOLD, 20));
		add(statNameLabel, BorderLayout.NORTH);
		add(statNumberLabel, BorderLayout.CENTER);

	}
}
