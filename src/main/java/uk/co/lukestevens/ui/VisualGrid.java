package uk.co.lukestevens.ui;

import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import uk.co.lukestevens.utils.Grid;

public class VisualGrid{
	
	public static JPanel getInputView(String title, Grid<?> grid) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel.add(new JLabel(title));
		
		for(String line : grid.toString().split("\n")) {
			line = line.replace("0", " ")
					.replace("1", "X")
					.replace("2", "#")
					.replace("3", "_")
					.replace("4", "o");
					
			JLabel label = new JLabel(line);
			label.setFont(new Font("Consolas", Font.PLAIN, 24));
			panel.add(label);
		}
		
		panel.setSize(1000, 1000);
		return panel;
	}

}
