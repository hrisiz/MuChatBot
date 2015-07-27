

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

//import com.javacodegeeks.snippets.Write_thread;

public class Main implements Runnable{
	static String text;
	static int click;
	static int delay;
	@Override
	public void run() {
		while(true){
			Robot robot = null;
			try {
				robot = new Robot();
				
			} catch (AWTException e1) {
				System.out.println("Low level input control is not allowed " + e1.getMessage());
			}
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			for(int i = 0; i < text.length(); i++){
				
				int key = KeyEvent.getExtendedKeyCodeForChar(text.charAt(i));
				robot.keyPress(key);
				robot.keyRelease(key);
			}
			try {
			    Thread.sleep(500);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			try {
			    Thread.sleep(delay);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("ClickForMe");
		frame.setBounds(1000, 0, 300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel(); 
		panel.setBounds(0, 0, 200, 200);
		frame.add(panel);
			
		  
		JTextField text_input = new JTextField(20);
		text_input.setBounds(0, 100, 100, 100);
		panel.add(text_input);
		
		JTextField delay_input = new JTextField(20);
		delay_input.setBounds(0, 0, 100, 100);	
		panel.add(delay_input);
		  
		JButton b3 = new JButton("Start");
		panel.add(b3, BorderLayout.AFTER_LAST_LINE);
		click = 1;
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				text = text_input.getText();
				delay = Integer.parseInt(delay_input.getText())*1000;
				if(click == 1){
					(new Thread(new Main())).start();
					click++;
				}
			}
		});
		frame.setVisible(true);
	}

}
