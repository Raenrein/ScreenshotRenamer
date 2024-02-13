package run;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class RenameHandler{
	
	static int countFiles = getCountFiles();
	
	protected static String[] stadium = new String[countFiles];
	protected static String[] g = new String[countFiles];
	protected static String[] platform = new String[countFiles];
	protected static String[] desc = new String[countFiles];
	
	protected static int go = 0;
	
	public static void main(String[] args) {
		
		String dir = GUI.getDir();
		
		for(int i = 0; i < countFiles; i++) {
			GUI.createGUI();
			
			while(go != 1) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			go = 0;
		}
		
		
		String[] number = new String[5];
		for(int i = 0; i < number.length; i++) {
			number[i] = "1";
		}

		
		String[] finalNames = generateFinalNames(dir, stadium, g, platform, desc, number);

		renameAll(dir, finalNames);

//		2022-08-19_stadiumName_(g4/g5)_(pc/xbox/ps)_description_number.png
//		
//		2022-08-19 13_57_52-Window.png
		
		System.exit(0);
		
	}
	
	public static String getCreationDate(String fileDir) {
		
		
		// Creation date already includes ending space.
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
		
		String fileName = fileDir;

        Path file = Paths.get(fileName);
        
        String dateCreated = null;

        BasicFileAttributes attr;
		try {
			attr = Files.readAttributes(file, BasicFileAttributes.class);
			FileTime time = attr.creationTime();
			dateCreated = df.format(time.toMillis());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return dateCreated;
		
	}
	
	public static String[] generateFinalNames(String dir, String[] stadium, String[] g, String[] platform, String description[], String number[]) {
		
		File directory = new File(dir);
		
		File[] fileArr = directory.listFiles();

		System.out.println("Input: Read " + countFiles + " files from the directory\n");
		String[] finalNames = new String[countFiles];
		String[] creationDates = new String[countFiles];
		
		for(int i = 0; i < creationDates.length; i++) {
			creationDates[i] = getCreationDate(dir + "\\" + fileArr[i].getName());
		}
		
		for(int i = 0; i < finalNames.length; i++) {
			finalNames[i] = creationDates[i] + stadium[i] + "_" + "g" + g[i] + "_" + platform[i] + "_" + description[i] + "_" + number[i];
		}
		
		for(int i = 0; i < finalNames.length; i++) {
			for(int j = i+1; j < finalNames.length; j++) {
				if(finalNames[i].equals(finalNames[j])) {
					String newName = finalNames[j].substring(0, finalNames[j].length()-1);
					int curNum = Integer.parseInt(finalNames[j].substring(finalNames[j].length()-1))+1;
					
					finalNames[j] = newName + curNum;
				}
			}
		}
		
		return finalNames;
		
	}
	
	public static void renameAll(String dir, String[] finalNames) {
		
		File directory = new File(dir);
		File[] fileArr = directory.listFiles();
		for(int i = 0; i < fileArr.length; i++) {
			if(fileArr[i].isFile()) {
				fileArr[i].renameTo(new File(dir + "\\" + finalNames[i] + ".png"));
				System.out.println("Renamed " + "\"" + fileArr[i].getName() + "\" to: \n" + "\"" + finalNames[i] + ".png\"");
			}
			
		}
		
	}
	
	public static void takeData(int resetCount, String[] stadium, String[] g, String[] platform, String[] desc) {
		stadium[resetCount] = GUI.stadium;
		g[resetCount] = GUI.g;
		platform[resetCount] = GUI.platform;
		desc[resetCount] = GUI.desc;
	}
	
	public static int getCountFiles() {
		String dir = GUI.getDir();
		
		File path = new File(dir);
		
		int countFiles = path.list().length;
		
		return countFiles;
	}

}
