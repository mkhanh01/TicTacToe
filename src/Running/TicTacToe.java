package Running;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToe extends JFrame implements MouseListener{
	
	private JPanel mainP, p1, p2;
	private JButton[][] arBtn;
	private int[][] arr;
	private boolean[][] arrEnd;
	private JButton btnReset, btnChoose;
	private boolean turn;
	private boolean endGame ;
	private JLabel lb1, lb2, lb3;
	private int c, h, t; // computer and human
	
	
	public TicTacToe() {
		
		arBtn = new JButton[3][3];
		arr = new int[3][3];
		arrEnd = new boolean[3][3];
		
		createGUI();
		
		setTitle("TIC-TAC-TOE by Khanh");
		setSize(330,390);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void addP1() {
		mainP.add(p1 = new JPanel(), BorderLayout.CENTER);
		p1.setLayout(new GridLayout(3,3,5,5));
		for (int i = 0; i < arBtn.length;i++) {
			for (int j = 0; j < arBtn[i].length; j++) {
				arBtn[i][j] = new JButton();
				arBtn[i][j].addMouseListener(this);
				arBtn[i][j].setPreferredSize(new Dimension(80,80));
				arBtn[i][j].setBackground(new Color(0,139,139));
				arBtn[i][j].setFont(new Font("Times New Roman", Font.BOLD, 70));
				p1.add(arBtn[i][j]);
			}
		}
	}
	
	public void reset() {
		for (int i = 0; i < arBtn.length; i++) {
			for (int j = 0; j < arBtn[i].length; j++) {
				arBtn[i][j].setText("");
				arr[i][j] = 0;
				arrEnd[i][j] = false;
				turn = false;
				endGame = false;
			}
		}
	}
	
	private void createGUI() {
		add(mainP = new JPanel());
		addP1();
		mainP.add(p2 = new JPanel(), BorderLayout.SOUTH);
		p2.setPreferredSize(new Dimension(300,100));
		p2.add(btnReset = new JButton("Reset"));
		p2.add(new JLabel("     You: "));
		p2.add(lb1 = new JLabel("0"));
		p2.add(new JLabel("   Me: "));  
		p2.add(lb2 = new JLabel("0"));
		p2.add(new JLabel("   Draw: "));
		p2.add(lb3 = new JLabel("0"));
		p2.add(btnChoose = new JButton("Select the level"));
		btnReset.setEnabled(false);
		btnReset.setBackground(new Color(255, 105 ,180));
		btnReset.setForeground(new Color(0,0,0));
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
				btnReset.setEnabled(false);
			}
		});
		
		btnChoose.setBackground(new Color(255, 105 ,180));
		btnChoose.setForeground(new Color(0,0,0));
		btnChoose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Choose();
				TicTacToe.this.setVisible(false);
			}
		});
	}
	
	public void score() {
		lb1.setText(h + "");
		lb2.setText(c + "");
		lb3.setText(t + "");
	}
	
	public int checkResult(int[][] arr) {
		
		// kt ngang
		for (int i = 0; i < arr.length; i++) {
			int a = arr[i][0];
			if(a == arr[i][1] && arr[i][1] == arr[i][2]) {
				if(a == 1) {
					return -10;
				}else if(a == 2) {
					return 10;
				}
			}
		}
		
		// kt doc
		for (int i = 0; i < arr.length; i++) {
			int a = arr[0][i];
			if(a == arr[1][i] && arr[1][i] == arr[2][i]) {
				if(a == 1) {
					return -10;
				}else if(a == 2) {
					return 10;
				}
			}	
		}
		
		//kt xeo
		if(arr[0][0] == arr[1][1] && arr[0][0] == arr[2][2]) {
			if(arr[0][0] == 1) {
				return -10;
			} else if(arr[0][0] == 2) {
				return 10;
			}
		}
		
		if(arr[2][0] == arr[1][1] && arr[2][0] == arr[0][2]) {
			if(arr[2][0] == 1) {
				return -10;
			} else if(arr[2][0] == 2) {
				return 10;
			}
		}
		
		return 0;
	}
	
	public boolean checkMove(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if(arr[i][j] == 0)
					return true;
			}
		}
		return false;
	}
	
	public int minimax(int[][] arr, boolean isTurn) {
		int s = checkResult(arr);
		if(s == 10)
			return s;
		if(s == -10)
			return s;
		if(checkMove(arr) == false)
			return 0;
		if(isTurn == true) {
			int best = -10000;
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					if(arr[i][j] == 0) {
						arr[i][j] = 2;
						best = Math.max(best, minimax(arr, !isTurn));
						arr[i][j] = 0;
					}
				}
			}
			return best;
		}
		else {
			int best = 10000;
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					if(arr[i][j] == 0) {
						arr[i][j] = 1;
						best = Math.min(best, minimax(arr, !isTurn));
						arr[i][j] = 0;
					}
				}
			}
			return best;
		}
		
	}
	
	public Move bestMove(int[][] arr, boolean turn) {
		int best = -10000;
		Move result = new Move(-1, -1);
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if(arr[i][j] == 0) {
					arr[i][j] = 2;
					int score = minimax(arr, !turn);
					arr[i][j] = 0;
					if(score > best) {
						best = score;
						result.setX(i);
						result.setY(j);
					}
				}
			}
		}
		
		return result;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		for (int i = 0; i < arBtn.length; i++) {
			for (int j = 0; j < arBtn[i].length; j++) {
				if(e.getButton() == 1 && e.getSource() == arBtn[i][j] && turn == false  && arrEnd[i][j] == false) {
					arBtn[i][j].setText("x");
					arr[i][j] = 1;
					arBtn[i][j].setForeground(new Color(240,255,255));
					arrEnd[i][j] = true;
					turn = true;
					btnReset.setEnabled(true);
				}
			}
		}
		
		if(turn == true && checkMove(arr) == true) {
			Move p = bestMove(arr, turn);
			int i = p.getX();
			int j = p.getY();
			arBtn[i][j].setText("o");
			arBtn[i][j].setForeground(Color.yellow);
			arrEnd[i][j] = true;
			arr[i][j] = 2;
			turn = false;
		}
		
		int check = checkResult(arr);
		
		if(check == 10 && endGame == false) {
			c++;
			score();
			JOptionPane.showConfirmDialog(null, "You LOSE !!!", "End",JOptionPane.DEFAULT_OPTION);
			end();
			endGame = true;
			
		}
		else if(check == -10 && endGame == false) {
			h++;
			score();
			JOptionPane.showConfirmDialog(null, "You WINNN !!!", "End", JOptionPane.DEFAULT_OPTION);
			end();
			endGame = true;
			
		}
		
		if(endGame == false) {
			if(checkMove(arr) == false) {
				t++;
				score();
				JOptionPane.showConfirmDialog(null, "DRAW game !!!", "End", JOptionPane.DEFAULT_OPTION);
				endGame = true;
				
			}
		}
	}
	
	public void end() {
		for (int i = 0; i < arBtn.length; i++) {
			for (int j = 0; j < arBtn[i].length; j++) {
				arrEnd[i][j] = true;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
