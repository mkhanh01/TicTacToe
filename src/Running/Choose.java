package Running;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Choose extends JFrame implements MouseListener{
	
	private JPanel pnChoose, p1;
	private JButton btnChoose1;
	private JButton btnChoose2;
	
	public Choose() {
		
		createChoose();
		
		setTitle("TIC-TAC-TOE by Khanh");
		setSize(350,120);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	
	private void createChoose() {
		add(pnChoose = new JPanel());
		pnChoose.add(p1 = new JPanel(), BorderLayout.SOUTH);
		p1.setPreferredSize(new Dimension(300,100));
		p1.add(btnChoose1 = new JButton("EASY game"));
		p1.add(btnChoose2 = new JButton("Perfect TICTACTOE")) ;
		
		btnChoose1.setBackground(new Color(255, 105 ,180));
		btnChoose1.setForeground(new Color(0,0,0));
		btnChoose2.setBackground(new Color(255, 105 ,180));
		btnChoose2.setForeground(new Color(0,0,0));
		btnChoose1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TicTacToeEasyLevel();
				Choose.this.setVisible(false);
				
			}
		});
		btnChoose2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TicTacToe();
				Choose.this.setVisible(false);
			}
		});
	}
	
	public static void main(String[] args) {
		new Choose();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
