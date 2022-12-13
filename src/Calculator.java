import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class Calculator extends Frame{

	JFrame jf = new JFrame("Calculator");
	JButton  BC =new JButton("C");
	JButton  N0 =new JButton("0");
	JButton  N1 =new JButton("1");
	JButton  N2 =new JButton("2");
	JButton  N3 =new JButton("3");
	JButton  N4 =new JButton("4");
	JButton  N5 =new JButton("5");
	JButton  N6 =new JButton("6");
	JButton  N7 =new JButton("7");
	JButton  N8 =new JButton("8");
	JButton  N9 =new JButton("9");
	JButton  BP =new JButton("+");
	JButton  BS =new JButton("-");
	JButton  BM =new JButton("X");
	JButton  BD =new JButton("/");
	JButton  tt =new JButton("=");
	JButton  E = new JButton("←");
	JButton  C = new JButton("C");
	JPanel shownum = new JPanel();
	JLabel L = new JLabel("");
	String show="0";
	String c ;
	int sum = 0 ;
	boolean first = true;
	Vector<JButton> v = new Vector<JButton>();
	Calculator(){
		jf.setSize(350,500 );
		jf.setVisible(true);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setLayout(null);
		shownum.setBounds(10, 15,315, 70);
		shownum.setBackground(Color.GRAY);
		v.add(N7);v.add(N8);v.add(N9);v.add(BP);//0-3
		v.add(N4);v.add(N5);v.add(N6);v.add(BS);//4-7
		v.add(N1);v.add(N2);v.add(N3);v.add(BM);//8-11
		v.add(C);v.add(N0);v.add(tt);v.add(BD);//12-15
		v.add(E);
		shownum.setLayout(new BorderLayout());
		//shownum.setLayout(null);
		setlable(L);
		shownum.add(L, BorderLayout.EAST);

		//L.setBounds(100,100,290, Panel.HEIGHT);
		jf.add(shownum);
		for(int i=0;i<16;i++) {
			if(i%4==0) {
				v.elementAt(i).setBounds(10, 150+70*(i/4), 80, 70);
			}
			else if(i%4==1) {
				v.elementAt(i).setBounds(90, 150+70*(i/4), 80, 70);
			}
			else if(i%4==2) {
				v.elementAt(i).setBounds(170, 150+70*(i/4), 80, 70);
			}
			else if(i%4==3) {
				v.elementAt(i).setBounds(250, 150+70*(i/4), 80, 70);
			}
			jf.add(v.elementAt(i));
			sbf(v.elementAt(i));
		}
		v.elementAt(16).setBounds(250,100,80,35);
		jf.add(v.elementAt(16));
		sbf(v.elementAt(16));
	}
	public void setlable(JLabel L) {
		L.setHorizontalAlignment(SwingConstants.RIGHT);
		Font f = new Font("",Font.BOLD,36);
		L.setFont(f);
		L.setForeground(Color.BLACK);
	}
	public void sbf(JButton b) {
		b.addActionListener(new ActionListener() {
			String bn ;
			public void actionPerformed(ActionEvent e) {
				if(first) {shownum.setBackground(new Color(110,170,0));first = false;}

				bn = b.getText();
				if(bn=="←") {show = show.substring(0, show.length()-1);}
				else if(bn == "=") {
					show = TT();
				}
				else if(bn=="C") {
					show = "0";
				}
				else if(bn != "0") {
					if(show=="0")	show = bn;
					else	show+=bn;
				}
				else {
					if(show!="0")	show+=bn;
					else	show = "0";
				}
				
				L.setText(show);
				
			}
			String TT() {
				
				if(show.indexOf("+")!= -1)c = "\\+";
				else if(show.indexOf("-")!=-1)c="-";
				else if(show.indexOf("/")!=-1)c="/";
				else if(show.indexOf("X")!=-1)c="X";
				else c="=";

				return F(c,show);
				
			}
			String F(String c,String s) {
				String[] RS = s.split(c);
				int len = RS.length;
				float sum =0;
				
				if(c == "\\+") {
					if(len == 1)return s;
					else{
						for(int i =0;i<len;i++) {
							if(RS[i].indexOf("-")!=-1)
								sum += Float.parseFloat(F("-",RS[i]));
							else if(RS[i].indexOf("/")!=-1)
								sum += Float.parseFloat(F("/",RS[i]));
							else if(RS[i].indexOf("X")!=-1)
								sum += Float.parseFloat(F("X",RS[i]));
							else {
								sum+=Float.parseFloat(RS[i]);
							}
						}
						return String.valueOf(sum);
					}
					
				}
				if(c=="-") {
					if(len == 1)return s;
					else {
						for(int i=0;i<len;i++) {
							System.out.println("3_"+i+":"+RS[i]);
							if(RS[i].indexOf("/")!=-1) {
								if(i!=0)sum-= Float.parseFloat(F("/",RS[i]));
								else sum+= Float.parseFloat(F("/",RS[i]));
							}
							else if(RS[i].indexOf("X")!=-1) {
								if(i!=0)sum-= Float.parseFloat(F("X",RS[i]));
								else sum+= Float.parseFloat(F("X",RS[i]));
							}
							else {
								if(i!=0)sum-= Float.parseFloat(RS[i]);
								else sum+=Float.parseFloat(RS[i]);
							}
								
						}
						return String.valueOf(sum);
					}
				}
				if(c=="/") {
					if(len == 1)return s;
					else {
						for(int i=0;i<len;i++) {		
							if(RS[i].indexOf("X")!=-1)
								if(i!=0)sum/= Float.parseFloat(F("X",RS[i]));
								else sum = Float.parseFloat(F("X",RS[i]));
							else {
								if(i!=0)sum/=Float.parseFloat(RS[i]);
								else sum +=Float.parseFloat(RS[i]);
							}
						}
						return String.valueOf(sum);
					}
				}
				if(c=="X") {
					if(len == 1)return s;
					else {
						sum = 1;
						for(int i=0;i<len;i++) {
							sum*= Float.parseFloat(F("X",RS[i]));
						}
						return String.valueOf(sum);
					}
				}
				else return s;
			}
		});
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Calculator();
	}

}






