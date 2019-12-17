import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Connect4 {


public static void main(String[] args) {
	
int s1 = Integer.parseInt(args[0]);
int s2 = Integer.parseInt(args[1]);

System.out.println("FIRST STRING : " + s1 + " SECOND STRING : " + s2);
Connect4JFrame newF = new Connect4JFrame(s1, s2);
		
newF.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		
newF.setVisible(true);
	}
}



class Connect4JFrame extends JFrame implements ActionListener {
	

private static final long SV1 = 1L;
private Button[] buttons;
private Label lSpacer;	
MenuItem neww, exitt, blackk, bluee;	
int[][]	 theeArrayArray;	
boolean	end=false;	
boolean	startTHEGAME;	
public static final int BLANK = 0;	
public static final int BLACK = 1;	
public static final int BROWN = 2;
public int theROW;	// 6 rows	
public int theColumn;	// 7 columns
public int numConnection;
public static final String theSpace = "     "; 
int activeColour = BLACK;
	
	

public Connect4JFrame(int theRow, int numConnection) {

this.theROW = theRow;
this.theColumn = theRow;
this.numConnection = numConnection;

setTitle("CS 151 CONNECT 4 HOMEWORK 3");
MenuBar mbar = new MenuBar();
		
Menu fileMenu = new Menu("File");
exitt = new MenuItem("Exit");
exitt.addActionListener(this);
fileMenu.add(exitt);
mbar.add(fileMenu);
Menu optMenu = new Menu("Options");

mbar.add(optMenu);
setMenuBar(mbar);

		
Panel panel = new Panel();
buttons = new Button[theROW];
for(int i = 0; i< theROW; i++){
	buttons[i] = new Button(String.valueOf(i+1));
	buttons[i].addActionListener(this);
	panel.add(buttons[i]);
	lSpacer = new Label(theSpace);
	panel.add(lSpacer);
}

add(panel, BorderLayout.NORTH);
initialize();

// Set to a reasonable size.
setSize(1024, 768);
	
} //end constructor

	
	
	
	
public void paint(Graphics g) {
	
g.setColor(Color.ORANGE);
g.fillRect(110, 50, 100+100*theColumn, 100+100*theROW);

for (int row=0; row<theROW; row++)
for (int col=0; col<theColumn; col++) {

if (theeArrayArray[row][col]==BLANK) g.setColor(Color.RED);
if (theeArrayArray[row][col]==BLACK) g.setColor(Color.BLACK);
if (theeArrayArray[row][col]==BROWN) g.setColor(Color.BLUE);

g.fillOval(160+100*col, 100+100*row, 100, 100);

			}
check4(g);

	} // paint

	
	
	
public void initialize() {
	
	
theeArrayArray = new int [theROW][theColumn] ;
		
for (int row = 0; row<theROW;    row++)
for (int col = 0; col<theColumn; col++)
				
theeArrayArray [row] [col] =     BLANK;
		
startTHEGAME = false;
		
	} // initialize

	
	
	
	
	
	
	
	

	
	
	
	
	
	
public void PutCircle(int z) {
	
	
	// put a disk on top of column n
		// if game is won, do nothing
		
if (end)     return;
startTHEGAME = true;
int row;
z -- ;

for (row=0; row<theROW; row++ )
	
if (theeArrayArray [row] [z ] > 0 ) break;

if (row>0) {
theeArrayArray [ -- row] [z] = activeColour;

if (activeColour == BLACK)
activeColour = BROWN;

	else
activeColour = BLACK;
			repaint();
		}
	}

	
	
	
	
	
	
public void theWinnerIS(Graphics f, int z) {

f.setColor(Color.BLACK);
f.setFont(new Font("Courier", Font.ITALIC, 50));
if ( z == BLACK )
f.drawString("BLACK Is The Winner", 2, 200);
else
f.drawString("BLUE Is The Winner!", 2, 200);
end=true;
	}

	
		
	
public void check4(Graphics g) {
	// see if there are 4 disks in a row: horizontal, vertical or diagonal
		// horizontal rows

for(int row = 0; row < theROW; row++)
{
	for(int col = 0; col < theROW; col++)
	{
		int cur = theeArrayArray[row][col];
		boolean flag = false;

		if(cur > 0)
		{
			int cur_row = row;
			int cur_col = col;
			int count = numConnection; 
			while(cur_row >= 0 && cur_row < theROW && cur_col >= 0 && cur_col < theROW && count > 0)
			{
				if(theeArrayArray[cur_row][cur_col] != cur)
					break;
				else
				{
					count--;
					cur_row++;
				}
			}

			if(count == 0)
				flag = true;

			cur_row = row;
			cur_col = col;
			count = numConnection;

			while(cur_row >= 0 && cur_row < theROW && cur_col >= 0 && cur_col < theROW && count > 0)
			{
				if(theeArrayArray[cur_row][cur_col] != cur)
					break;
				else
				{
					count--;
					cur_row++;
					cur_col++;
				}
			}

			if(count == 0)
				flag = true;

			cur_row = row;
			cur_col = col;
			count = numConnection;

			while(cur_row >= 0 && cur_row < theROW && cur_col >= 0 && cur_col < theROW && count > 0)
			{
				if(theeArrayArray[cur_row][cur_col] != cur)
					break;
				else
				{
					count--;
					cur_col++;
				}
			}

			if(count == 0)
				flag = true;

			cur_row = row;
			cur_col = col;
			count = numConnection;

			while(cur_row >= 0 && cur_row < theROW && cur_col >= 0 && cur_col < theROW && count > 0)
			{
				if(theeArrayArray[cur_row][cur_col] != cur)
					break;
				else
				{
					count--;
					cur_row--;
					cur_col++;
				}
			}

			if(count == 0)
				flag = true;

			if(flag == true)
			{
				theWinnerIS(g, cur);
			}

		}
	}
}







} // end check4

	
	
public int indexOf(Button b){
	int j = 0;
	while(j < theROW){
		if(buttons[j] == b) return j;
		j++;
	}
	return -1;
}
	
	
	
public void actionPerformed(ActionEvent z) {

	if(indexOf((Button)z.getSource()) != -1) PutCircle(indexOf((Button)z.getSource())+ 1);

if (z.getSource() == neww) {
end=false;
initialize();
repaint();

} 

else if (z.getSource() == exitt) {
System.exit(0);
} 

else if (z.getSource() == blackk) {
	
			// don't change colour to play in middle of game
if (!startTHEGAME) activeColour=BLACK;

} 

else if (z.getSource() == bluee) {
if (!startTHEGAME) activeColour=BROWN;

}
} // end ActionPerformed

} // class