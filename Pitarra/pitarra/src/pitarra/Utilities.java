package pitarra;

import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public final class Utilities {

	public static final String readFile(String pathAndFilename) {
		File file = new File(pathAndFilename);

		if (file.canRead()) {
			Scanner scan;
			String fileContents = "";

			try {
				scan = new Scanner(file);
			} catch (FileNotFoundException e) {
				return null;
			}

			while (scan.hasNextLine())
				fileContents += scan.nextLine() + "\n";
			scan.close();

			return fileContents;
		} else {
			return null;
		}
	}

	public static final String[] readFilePerLine(String pathAndFilename) {
		File file = new File(pathAndFilename);
		final String separator = "---";
		String forCompare = "";

		if (file.canRead()) {
			Scanner scan, scanB;
			int c = 0;
			int lineNum = 0;

			try {
				scan = new Scanner(file);
				scanB = new Scanner(file);
			} catch (FileNotFoundException e) {
				return null;
			}

			while (scanB.hasNextLine()) {
				if (!scanB.nextLine().equals(separator)) {
					lineNum++;
				}
			}

			String[] fileContents = new String[lineNum];
			for (int x = 0; x < lineNum; x++) {
				fileContents[x] = "";
			}

			while (scan.hasNextLine()) {
				forCompare = scan.nextLine();

				if (forCompare.equals(separator)) {
					fileContents[c] += "\n";
					c++;
				} else {
					fileContents[c] += forCompare;
				}
			}
			scan.close();
			scanB.close();

			return fileContents;
		} else {
			return null;
		}
	}

	public static void drawBackdrop(Graphics page, ImageIcon backdrop,
			int width, int height, JPanel panel) {
		if (backdrop != null) {
			page.drawImage(backdrop.getImage(), 0, 0, width, height, 0, 0,
					backdrop.getIconWidth(), backdrop.getIconHeight(), panel);
		}
	}

	// static class is never instantiated
	private Utilities() {

	}
}