// Work-in-progress
// 2nd Panel is almost done, need to do 3rd and 4th and cleanup 
// and match design plan's variable names

//Imports
import java.awt.Color;
import java.awt.Insets;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


@SuppressWarnings("serial")    //Suppress serialVersionUID warning when compiling with -Xlint
public class NewCharacter extends JFrame{

    private JPanel cardPanel;
    private JPanelRace  card1;
    private JPanelClass card2;
//    JPanel card3;
//    JPanel card4;

    public NewCharacter(){
        super("Create a Character");
        setSize (700, 700);
        setMinimumSize(new Dimension(700, 700));
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        card1 = new JPanelRace();
        card2 = new JPanelClass();
        //card3 = new JPanelA();
        //card4 = new JPanelA();

        cardPanel = new JPanel(new CardLayout());
        cardPanel.add(card1, "Card 1");
        cardPanel.add(card2, "Card 2");
        //cardPanel.add(card3, "Card 3");
        //cardPanel.add(card4, "Card 4");

        add(cardPanel,BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

	
    public static void main(String[] args){


        NewCharacter nc = new NewCharacter();

    }

	
	

    private class JPanelRace extends JPanel{

		///////////////
		// Variables //
		///////////////

		//Panels 
		private JPanel racePanel;    //1st Panel
		private JPanel classPanel;   //2nd Panel
		private JPanel abilityPanel; //3rd Panel
		private JPanel infoPanel;    //4th Panel

		//Text Area for displaying the world and search results
		private JTextArea jtaInfo = new JTextArea ();

		//Labels
		private JLabel raceLblA   = new JLabel("Select a Race", JLabel.CENTER);
		private JLabel raceLblB   = new JLabel("Select a Race", JLabel.LEFT);
		private JLabel subraceLbl = new JLabel("Select a Subrace", JLabel.CENTER);
		private JLabel raceTxtLbl = new JLabel("Dwarf", JLabel.CENTER);
		private JLabel blankLbl = new JLabel("", JLabel.CENTER);

		//Combo boxes
		private String [] racesStr      = {"Dwarf","Elf","Human","Halfling",
										   "Dragonborn","Gnome","Half-Elf",
										   "Half-Orc","Tiefling"};
		private String [] subracesStr   = {"Hill Dwarf","Mountain Dwarf"};
		private JComboBox<String> raceCombo    = new JComboBox<String> (racesStr);
		private JComboBox<String> subRaceCombo = new JComboBox<String> (subracesStr);

		//Buttons
		private JButton jbNext = new JButton ("Save and Continue >");    
//		private JButton jbPrev = new JButton ("< Prev");    

        private Font titleFont;
		
		//Scroll pane for jtextarea
		private JScrollPane jsp;

		private String [] raceDescrStr   = {
			"Bold and hardy, dwarves are known as skilled warriors, miners, and "+
			"workers of stone and metal. \n\nRacial Traits: +2 Constitution, "+
			"Darkvision, Dwarven Resilience, Dwarven Combat Training, Stonecunning",
			
			"Elves are a magical people of otherworldly grace, living in the world "+
			"but not entirely part of it. \n\nRacial Traits: +2 Dexterity, Darkvision, "+
			"Keen Senses, Fey Ancestry, Trance",
			
			"Humans are the most adaptable and ambitious people among the common "+
			"races. Whatever drives them, humans are the innovators, the achievers, "+
			"and the pioneers of the worlds.  \n\nRacial Traits:  +1 to All Ability Scores, "+
			"Extra Language",
			
			"The diminutive halflings survive in a world full of larger creatures by "+
			"avoiding notice or, barring that, avoiding offense.  \n\nRacial Traits: "+
			"+2 Dexterity, Lucky, Brave, Halfling Nimbleness",
			
			"Dragonborn look very much like dragons "+
			"standing erect in humanoid form, though they lack wings or a tail.  \n\n"+
			"Racial Traits: +2 Strength, +1 Charisma, Draconic Ancestry, Breath "+
			"Weapon, Damage Resistance",
			
			"A gnome's energy and enthusiasm for living shines through every inch "+
			"of his or her tiny body.  \n\nRacial Traits: +2 Intelligence, Darkvision, "+
			"Gnome Cunning",
			
			"Half-elves combine what some say are the best qualities of their elf "+
			"and human parents.  \n\nRacial Traits:  +2 Charisma, +1 to Two Other "+
			"Ability Scores, Darkvision, Fey Ancestry, Skill Versatility", 
			
			"Half-orcs' grayish pigmentation, sloping foreheads, jutting jaws, "+ 
			"prominent teeth, and towering builds make their orcish heritage "+
			"plain for all to see.  \n\nRacial Traits:  +2 Strength, +1 Constitution, "+
			"Darkvision, Menacing, Relentless Endurance, Savage Attacks",
			
			"To be greeted with stares and whispers, to suffer violence and insult "+
			"on the street, to see mistrust and fear in every eye: this is the lot "+
			"of the tiefling.  \n\nRacial Traits:  +2 Charisma, +1 Intelligence, "+
			"Darkvision, Hellish Resistance, Infernal Legacy"};

		//////////////////////
		// End of Variables //
		//////////////////////


        public JPanelRace(){

			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();

			//Title
			titleFont = new Font("Serif", Font.BOLD, 32);
			raceLblA.setFont(titleFont);
			gbc.fill = GridBagConstraints.NONE;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.insets = new Insets(0,0,5,0);  //bottom padding
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.gridheight = 1;
			gbc.weighty = 1.0;
			gbc.weightx = 1.0;
			add(raceLblA , gbc);

			
			/////////////////
			// Right Panel //
			/////////////////
			JPanel right = new JPanel(new BorderLayout());
			
			//Race Description Label
			titleFont = new Font("Serif", Font.BOLD, 26);
			raceTxtLbl.setFont(titleFont);
			right.add(raceTxtLbl,BorderLayout.NORTH);

			//Set up JTextArea for Description
			jtaInfo.setEditable(false);
			jtaInfo.setLineWrap(true);
			jtaInfo.setWrapStyleWord(true);
			jtaInfo.setFont (new java.awt.Font ("Monospaced", 0, 12));
			jsp = new JScrollPane (jtaInfo);
			jsp.setPreferredSize(new Dimension(300, 300));
			jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jtaInfo.setText(raceDescrStr[0]);
			right.add(jsp,BorderLayout.CENTER);

			
			////////////////
			// Left Panel //
			////////////////
			JPanel left = new JPanel(new GridLayout(0, 1));
			
			//Race Selection Label
			//titleFont = new Font("Serif", Font.PLAIN, 26);
			raceLblB.setFont(titleFont);
			left.add(raceLblB);
			
			//Race Combo
			left.add(raceCombo);
			
			left.add(blankLbl);
			left.add(blankLbl);

			//Select subrace Label
			//titleFont = new Font("Serif", Font.PLAIN, 18);
			subraceLbl.setFont(titleFont);
			left.add(subraceLbl, gbc);

			//Subrace combo
			left.add(subRaceCombo, gbc);
			
			gbc.fill = GridBagConstraints.NONE;
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.insets = new Insets(0,0,5,0);  //bottom padding
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.gridwidth = GridBagConstraints.RELATIVE;
			add(left, gbc);
			
			gbc.fill = GridBagConstraints.NONE;
			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.insets = new Insets(0,0,5,0);  //bottom padding
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			add(right, gbc);
			
			

			//Save & Continue button
			gbc.gridx = 0;
			gbc.gridy = 6;
			gbc.insets = new Insets(0,0,5,0);  //bottom padding
			gbc.anchor = GridBagConstraints.LAST_LINE_END;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			add(jbNext, gbc);

			setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

			//Action Listeners
			jbNext.addActionListener   (e -> switchStateNext());
			
			raceCombo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					JComboBox comboBox = (JComboBox) event.getSource();
					subRaceCombo.removeAllItems();
					Object selected = comboBox.getSelectedItem();
					String rStr = selected.toString();
					
					raceTxtLbl.setText(rStr);
					switch(rStr){
						case "Dwarf":
							jtaInfo.setText(raceDescrStr[0]);
							subRaceCombo.addItem("Hill Dwarf");
							subRaceCombo.addItem("Mountain Dwarf");
							break;
						case "Elf":
							jtaInfo.setText(raceDescrStr[1]);
							subRaceCombo.addItem("High Elf");
							subRaceCombo.addItem("Wood Elf");
							subRaceCombo.addItem("Dark Elf");
							break;
						case "Human":
							jtaInfo.setText(raceDescrStr[2]);
							break;
						case "Halfling":
							jtaInfo.setText(raceDescrStr[3]);
							subRaceCombo.addItem("Lightfoot");
							subRaceCombo.addItem("Stout");
							break;
						case "Dragonborn":
							jtaInfo.setText(raceDescrStr[4]);
							break;
						case "Gnome":
							jtaInfo.setText(raceDescrStr[5]);
							subRaceCombo.addItem("Forest Gnome");
							subRaceCombo.addItem("Rock Gnome");
							break;
						case "Half-Elf":
							jtaInfo.setText(raceDescrStr[6]);
							break;
						case "Half-Orc":
							jtaInfo.setText(raceDescrStr[7]);
							break;
						case "Tiefling":
							jtaInfo.setText(raceDescrStr[8]);						
							break;
						default:
							jtaInfo.setText("Error, Invalid");	
							break;
					}

										   

				}
			});
		}

 //       private void switchStatePrev(){
 //           CardLayout c1 = (CardLayout)(cardPanel.getLayout());
 //           c1.previous(cardPanel);
//        }

        private void switchStateNext(){
            CardLayout c1 = (CardLayout)(cardPanel.getLayout());
            c1.next(cardPanel);

        }

    }
	
	
	
	
    private class JPanelClass extends JPanel{

		///////////////
		// Variables //
		///////////////


		//Text Area for displaying the world and search results
		private JTextArea jtaInfo = new JTextArea ();

		//Labels
		private JLabel classLblA   = new JLabel("Select a Class", JLabel.CENTER);
		private JLabel classLblB   = new JLabel("Select a Class", JLabel.LEFT);
		private JLabel startLvlLbl = new JLabel("Starting Level", JLabel.LEFT);
		private JLabel classTxtLbl = new JLabel("Paladin", JLabel.CENTER);
		private JLabel blankLbl = new JLabel("", JLabel.CENTER);

		//Combo boxes
		private String [] classesStr      = {"Dwarf","Elf","Human","Halfling",
										   "Dragonborn","Gnome","Half-Elf",
										   "Half-Orc","Tiefling"};
		private JComboBox<String> classCombo    = new JComboBox<String> (classesStr);
		
		//Level Spinner
		SpinnerNumberModel snm = new SpinnerNumberModel(new Integer(0),new Integer(0),new Integer(100),new Integer(1));
		JSpinner spnLevl = new JSpinner(snm);

		//Buttons
		private JButton jbNext = new JButton ("Save and Continue >");    
		private JButton jbPrev = new JButton ("< Prev");    

        private Font titleFont;
		
		//Scroll pane for jtextarea
		private JScrollPane jsp;

		private String [] classDescrStr   = {
			"Bold and hardy, dwarves are known as skilled warriors, miners, and "+
			"workers of stone and metal. \n\nRacial Traits: +2 Constitution, "+
			"Darkvision, Dwarven Resilience, Dwarven Combat Training, Stonecunning",
			
			"Elves are a magical people of otherworldly grace, living in the world "+
			"but not entirely part of it. \n\nRacial Traits: +2 Dexterity, Darkvision, "+
			"Keen Senses, Fey Ancestry, Trance",
			
			"Humans are the most adaptable and ambitious people among the common "+
			"races. Whatever drives them, humans are the innovators, the achievers, "+
			"and the pioneers of the worlds.  \n\nRacial Traits:  +1 to All Ability Scores, "+
			"Extra Language",
			
			"The diminutive halflings survive in a world full of larger creatures by "+
			"avoiding notice or, barring that, avoiding offense.  \n\nRacial Traits: "+
			"+2 Dexterity, Lucky, Brave, Halfling Nimbleness",
			
			"Dragonborn look very much like dragons "+
			"standing erect in humanoid form, though they lack wings or a tail.  \n\n"+
			"Racial Traits: +2 Strength, +1 Charisma, Draconic Ancestry, Breath "+
			"Weapon, Damage Resistance",
			
			"A gnome's energy and enthusiasm for living shines through every inch "+
			"of his or her tiny body.  \n\nRacial Traits: +2 Intelligence, Darkvision, "+
			"Gnome Cunning",
			
			"Half-elves combine what some say are the best qualities of their elf "+
			"and human parents.  \n\nRacial Traits:  +2 Charisma, +1 to Two Other "+
			"Ability Scores, Darkvision, Fey Ancestry, Skill Versatility", 
			
			"Half-orcs' grayish pigmentation, sloping foreheads, jutting jaws, "+ 
			"prominent teeth, and towering builds make their orcish heritage "+
			"plain for all to see.  \n\nRacial Traits:  +2 Strength, +1 Constitution, "+
			"Darkvision, Menacing, Relentless Endurance, Savage Attacks",
			
			"To be greeted with stares and whispers, to suffer violence and insult "+
			"on the street, to see mistrust and fear in every eye: this is the lot "+
			"of the tiefling.  \n\nRacial Traits:  +2 Charisma, +1 Intelligence, "+
			"Darkvision, Hellish Resistance, Infernal Legacy"};

		//////////////////////
		// End of Variables //
		//////////////////////


        public JPanelClass(){

			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();

			//Title
			titleFont = new Font("Serif", Font.BOLD, 32);
			classLblA.setFont(titleFont);
			gbc.fill = GridBagConstraints.NONE;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.insets = new Insets(0,0,5,0);  //bottom padding
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.gridheight = 1;
			gbc.weighty = 1.0;
			gbc.weightx = 1.0;
			add(classLblA , gbc);

			
			/////////////////
			// Right Panel //
			/////////////////
			JPanel right = new JPanel(new BorderLayout());
			
			//Class Description Label
			titleFont = new Font("Serif", Font.BOLD, 26);
			classTxtLbl.setFont(titleFont);
			right.add(classTxtLbl,BorderLayout.NORTH);

			//Set up JTextArea for Description
			jtaInfo.setEditable(false);
			jtaInfo.setLineWrap(true);
			jtaInfo.setWrapStyleWord(true);
			jtaInfo.setFont (new java.awt.Font ("Monospaced", 0, 12));
			jsp = new JScrollPane (jtaInfo);
			jsp.setPreferredSize(new Dimension(300, 300));
			jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jtaInfo.setText(classDescrStr[0]);
			right.add(jsp,BorderLayout.CENTER);

			
			////////////////
			// Left Panel //
			////////////////
			JPanel left = new JPanel(new GridLayout(0, 1));
			
			//Class Selection Label
			//titleFont = new Font("Serif", Font.PLAIN, 26);
			classLblB.setFont(titleFont);
			left.add(classLblB);
			
			//Starting Level Value
			left.add(classCombo);
			
			left.add(blankLbl);
			left.add(blankLbl);

			//Select Starting Level
			//titleFont = new Font("Serif", Font.PLAIN, 18);
			startLvlLbl.setFont(titleFont);
			left.add(startLvlLbl, gbc);

			//Starting Level
			left.add(spnLevl, gbc);
			
			gbc.fill = GridBagConstraints.NONE;
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.insets = new Insets(0,0,5,0);  //bottom padding
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.gridwidth = GridBagConstraints.RELATIVE;
			add(left, gbc);
			
			gbc.fill = GridBagConstraints.NONE;
			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.insets = new Insets(0,0,5,0);  //bottom padding
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			add(right, gbc);
			
			//Prev button
			gbc.gridx = 0;
			gbc.gridy = 6;
			gbc.insets = new Insets(0,0,5,0);  //bottom padding
			gbc.anchor = GridBagConstraints.LAST_LINE_START;
			gbc.gridwidth = GridBagConstraints.RELATIVE;
			add(jbPrev, gbc);
			

			//Save & Continue button
			gbc.gridx = 1;
			gbc.gridy = 6;
			gbc.insets = new Insets(0,0,5,0);  //bottom padding
			gbc.anchor = GridBagConstraints.LAST_LINE_END;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			add(jbNext, gbc);

			setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

			//Action Listeners
			jbNext.addActionListener   (e -> switchStateNext());
			jbPrev.addActionListener   (e -> switchStatePrev());
			
			classCombo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					JComboBox comboBox = (JComboBox) event.getSource();
					
					Object selected = comboBox.getSelectedItem();
					String rStr = selected.toString();
					
					classTxtLbl.setText(rStr);
					switch(rStr){
						case "Dwarf":
							jtaInfo.setText(classDescrStr[0]);
							break;
						case "Elf":
							jtaInfo.setText(classDescrStr[1]);
							break;
						case "Human":
							jtaInfo.setText(classDescrStr[2]);
							break;
						case "Halfling":
							jtaInfo.setText(classDescrStr[3]);
							break;
						case "Dragonborn":
							jtaInfo.setText(classDescrStr[4]);
							break;
						case "Gnome":
							jtaInfo.setText(classDescrStr[5]);
							break;
						case "Half-Elf":
							jtaInfo.setText(classDescrStr[6]);
							break;
						case "Half-Orc":
							jtaInfo.setText(classDescrStr[7]);
							break;
						case "Tiefling":
							jtaInfo.setText(classDescrStr[8]);						
							break;
						default:
							jtaInfo.setText("Error, Invalid");	
							break;
					}

										   

				}
			});
		}

        private void switchStatePrev(){
            CardLayout c1 = (CardLayout)(cardPanel.getLayout());
            c1.previous(cardPanel);
        }

        private void switchStateNext(){
            CardLayout c1 = (CardLayout)(cardPanel.getLayout());
            c1.next(cardPanel);

        }

    }	
	

}



 