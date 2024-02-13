package run;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI {
	
	protected static String stadium = null;
	protected static String g = null;
	protected static String platform = null;
	protected static String desc = null;
	protected static int resetCount = 0;
	
	public static String getDir() {
		
		String dirScan = null;
		
		File dirTXT = new File("src\\DIRECTORY.txt");
		try {
			Scanner sc = new Scanner(dirTXT);
			
			dirScan = sc.nextLine();
			
			sc.close();
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return dirScan;
	}
	
	public static void reset(JTextField stadText, JTextField descText) {
		
		stadium = stadText.getText();
		desc = descText.getText();
		
		RenameHandler.takeData(resetCount, RenameHandler.stadium, RenameHandler.g, RenameHandler.platform, RenameHandler.desc);
		
		stadText.setText("");
		stadium = null;
		g = null;
		platform = null;
		descText.setText("");
		desc = null;
		
	}

	public static void createGUI() {

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(null);
		
		JLabel dirLabel = new JLabel("Directory:   ");
		dirLabel.setBounds(40, 20, 460, 25);
		panel.add(dirLabel);
		
		String dir = getDir();
		
		if(dir != null) {
			dirLabel.setText("Directory:   " + dir);
		}
		
		JLabel stadLabel = new JLabel("Stadium Name:");
		stadLabel.setBounds(200, 50, 200, 80);
		panel.add(stadLabel);
		
		JTextField stadText = new JTextField();
		stadText.setBounds(160, 110, 165, 25);
		panel.add(stadText);
		
		// GEN
		
		JLabel genLabel = new JLabel("Gen: (none)");
		genLabel.setBounds(210, 120, 200, 80);
		panel.add(genLabel);
		
		JButton gen4 = new JButton("g4");
		gen4.setBounds(150, 180, 80, 25);
		gen4.addActionListener(new ActionListener() {
			
			@Override
	        public void actionPerformed(ActionEvent e) {
	            g = "4";
	            genLabel.setText("Gen: (g4)");
	            
	        } });
		panel.add(gen4);
		
		JButton gen5 = new JButton("g5");
		gen5.setBounds(250, 180, 80, 25);
		gen5.addActionListener(null);
		gen5.addActionListener(new ActionListener() {
			
			@Override
	        public void actionPerformed(ActionEvent e) {
	            g = "5";
	            genLabel.setText("Gen: (g5)");
	            
	        } });
		panel.add(gen5);
		
		// PLATFORM
		
		JLabel platLabel = new JLabel("Platform: (none)");
		platLabel.setBounds(200, 190, 200, 80);
		panel.add(platLabel);
		
		JButton pc = new JButton("pc");
		pc.setBounds(110, 250, 80, 25);
		pc.addActionListener(new ActionListener() {
			
			@Override
	        public void actionPerformed(ActionEvent e) {
	            platform = "pc";
	            platLabel.setText("Platform: (pc)");
	            
	        } });
		panel.add(pc);
		
		JButton xbox = new JButton("xbox");
		xbox.setBounds(200, 250, 80, 25);
		xbox.addActionListener(new ActionListener() {
			
			@Override
	        public void actionPerformed(ActionEvent e) {
	            platform = "xbox";
	            platLabel.setText("Platform: (xbox)");
	            
	        } });
		panel.add(xbox);
		
		JButton ps = new JButton("ps");
		ps.setBounds(290, 250, 80, 25);
		ps.addActionListener(new ActionListener() {
			
			@Override
	        public void actionPerformed(ActionEvent e) {
	            platform = "ps";
	            platLabel.setText("Platform: (ps)");
	            
	        } });
		panel.add(ps);

		// DESCRIPTION
		
		JLabel descLabel = new JLabel("Description:");
		descLabel.setBounds(210, 260, 200, 80);
		panel.add(descLabel);
		
		JTextField descText = new JTextField();
		descText.setBounds(160, 320, 165, 25);
		panel.add(descText);
		
		// DONE / RESET
		
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("File Renamer");
		frame.setVisible(true);
		frame.setResizable(false);

		frame.add(panel, BorderLayout.CENTER);
		
		JButton done = new JButton("DONE");
		done.setBounds(195, 380, 100, 40);
		done.addActionListener(new ActionListener() {
			
			@Override
	        public void actionPerformed(ActionEvent e) {
	            
				reset(stadText, descText);
				frame.setVisible(false);
				RenameHandler.go = 1;
				resetCount++;
				
	        } });
		panel.add(done);

	}

}
