
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * COP 3330-1
 * 11/29/10
 * MandelbrotSet Class
 * The class that draws out and creates the Mandelbrot set.
 * @author Juan Carlo Cervera
 * 
 */
public class MandelbrotSet {
		/**Button for generation.*/
		JButton button;
		JButton button2;
		
		/**A JLabel that will act as all the labels.*/
		JLabel label;
		
		/**Real Center text field*/
		JTextField RC;
		
		/**Imaginary Center text field*/ 
		JTextField IC;
		
		/**View Size text field*/
		JTextField VS;
		
		/**Maximum Sequence text field*/
		JTextField MS;
		
		/**The frame for the window.*/
		private JFrame window;
		
		/**The canvas for drawing out the Mandelbrot set.*/
		private Canvas canvas;
		
		/**The real center, starting at -0.4.*/
		double realCent = -0.4;
		
		/**The Imaginary Center, starting at 0.*/
		double imagCent = 0;
		
		/**The variable that will act as the real number section of the Complex Number.*/
		double real = -0.4;
		
		/**The variable that will act as the imaginary number of the Complex Number*/
		double imag = 0;
		
		/**The size of the canvas.*/
		static double size = 475;

		Random rand = new Random();
		
		/**
		 * Creates the window for the MandelbrotSet, including the button, text fields, labels, and canvas.
		 */
		public MandelbrotSet()
		{
				//Create the frame for the window.
				window = new JFrame("Mandelbrot Set");
				
				//Set it to exit on close.
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				//Set the layout to null.
				LayoutManager layout = null;
				window.setLayout(layout);
				
				
				//Create the Generate button.
				button = new JButton("Generate");
				button.setLocation(590, 590);
				button.setSize(100,25);
				window.add(button);
				button.addActionListener(new ObjectListener());
				
				//Create the Repeat button.
				button2 = new JButton("Repeat");
				button2.setLocation(790, 590);
				button2.setSize(100,25);
				window.add(button2);
				button2.addActionListener(new ObjectListener2());
				
				
				//Create the Real Center label.
				label = new JLabel("Real Center");
				label.setLocation(500, 500);
				label.setSize(100, 25);
				window.add(label);
				
				//Create the Imaginary Center label.
				label = new JLabel("Imaginary Center");
				label.setLocation(763, 500);
				label.setSize(100, 25);
				window.add(label);
				
				//Create the View Size label.
				label = new JLabel("View Size");
				label.setLocation(500, 545);
				label.setSize(100, 25);
				window.add(label);
				
				//Create the Sequence Length label.
				label = new JLabel("Sequence Length");
				label.setLocation(763, 545);
				label.setSize(100, 25);
				window.add(label);
			
				//Create the Real Center text field.
				RC = new JTextField("-0.4");
				RC.setLocation(613, 500);
				RC.setSize(100, 25);
				window.add(RC);
				RC.addActionListener(new ObjectListener());
				
				//Create the Imaginary Center text field.
				IC = new JTextField("0");
				IC.setLocation(880, 500);
				IC.setSize(100, 25);
				window.add(IC);
				IC.addActionListener(new ObjectListener());
				
				//Create the View Size text field.
				VS = new JTextField("1.1");
				VS.setLocation(613, 545);
				VS.setSize(100, 25);
				window.add(VS);
				VS.addActionListener(new ObjectListener());
				
				//Create the Maximum Sequence Length text field.
				MS = new JTextField("50");
				MS.setLocation(880, 545);
				MS.setSize(100, 25);
				window.add(MS);
				MS.addActionListener(new ObjectListener());
				
				//Instantiate the canvas.
				canvas = new Canvas();
				
				//Create the canvas.
				canvas.setBackground(Color.black);
				canvas.setSize(480, 720);
				canvas.setLocation(5, 5);
				canvas.addMouseListener(new ObjectListener());
				window.add(canvas);
				
				//Set the size of the window, and keep it visible.
				window.setSize(1000, 780);
				window.setVisible(true);
				
		}
		
		/**
		 * Main function of the class MandelbrotSet.
		 * @param args
		 */
		public static void main (String[] args){
			new MandelbrotSet();
		}
		/**
		 * 
		 * @author Juan Carlo Cervera
		 * Class the starts up when any sort of action was performed.
		 *
		 */
		private class ObjectListener extends MouseAdapter implements ActionListener{
			
			/**
			 * If a new number was entered into the text box, or the generate button was pressed, performs the action.
			 */
			
			public void actionPerformed(ActionEvent arg0) {
				
				drawThatMandelbrot();
				
			}
			/**
			 * Checks whether the mouse has clicked anywhere on the canvas, and changes the Real and Imaginary center to the coordinates clicked.
			 * 
			 */
			public void mouseClicked(MouseEvent e) {
				
				double view = new Double(VS.getText());
				
				RC.setText(Double.toString((realCent - view)+(2*((view)*e.getX())/size)));
				IC.setText(Double.toString((imagCent + view)-(2*((view)*e.getY())/size)));
					
				drawThatMandelbrot();
			}
			/**
			 * Draws the Mandelbrot according to the information given in the text boxes.
			 */
			public void drawThatMandelbrot(){
				
				//Stores the real Center.
				realCent = new Double(RC.getText());
				
				//Stores the imaginary Center.
				imagCent = new Double(IC.getText());
				
				//Reads the view from the text box.
				double view= new Double(VS.getText());
				
				//Reads the sequence length from the text box
				int Seq = new Integer(MS.getText());
				
				//Initializes the graphics for the canvas.
				Graphics g = canvas.getGraphics();
				
				//Initializes the Complex Numbers
				ComplexNumber plexNum;
				ComplexNumber plexNumZ;
				
				//Variable for the magnitude.
				double magni = 0;
				
				for(int i=0; i<480; i++){
					for(int j=0; j<720; j++){
						
						//Creates the Complex Number
						plexNum = new ComplexNumber((realCent - view)+(2*((view*i)/size)), (imagCent + view)-(2*((view*j)/size)));
						plexNumZ = new ComplexNumber((realCent - view)+(2*((view*i)/size)), (imagCent + view)-(2*((view*j)/size)));
						
						//Initializes the Counter
						int counter=0;
						
						
						for(int k = 0; k < Seq; k++){
							
							//Squares the Complex Number
							plexNum.square();
							
							//Adds the Complex Numbers
							plexNum.add(plexNumZ);
							
							magni = plexNum.magnitude();
							
							//Checks if the magnitude is 2 or above, if so, it breaks the loop
							if(magni >= 2){
								break;
							}
							
							//Counter only counts if loop was not broken
							counter++;
							
						}
						
						if(counter == Seq){
							//Colors the Mandelbrot according to an equation I came up with to gradiate the colors throughout.
							Color c = new Color((int)((magni*512)%256), (int)((magni*8192)%256), (int)((magni*16384)%256));
							g.setColor(c);
							
							//Draws at the coordinates
							g.drawLine(i, j, i, j);
							
						}//Draw the Mandelbrot
						
						else{
							//Uses the equation shown in class to color the not-Mandelbrot accordingly
							Color c = new Color((int)((magni*16384)%256), (int)((magni*8192)%256), (int)((magni*4096)%256));
							g.setColor(c);
							
							//Draws at the coordinates
							g.drawLine(i , j, i, j);
							
						}//Draw the not-Mandelbrot
						
					}//end of j for loop
					
				}//end of i for loop
				
			}//End of drawThatMandelbrot
			
		}//End of Object Listener
		
		private class ObjectListener2 implements ActionListener{
				public void actionPerformed(ActionEvent arg0) {
				
				for(int i = 0; i<=10; i++)
					drawThatMandelbrot();
					
				}
				public void drawThatMandelbrot(){
					
					//Stores the real Center.
					realCent = rand.nextDouble()*2-1;
					
					//Stores the imaginary Center.
					imagCent = rand.nextDouble()*2-1;
					
					//Reads the view from the text box.
					double view= rand.nextDouble();
					
					//Reads the sequence length from the text box
					int Seq = new Integer(MS.getText());
					
					
					//Initializes the graphics for the canvas.
					Graphics g = canvas.getGraphics();
					
					//Initializes the Complex Numbers
					ComplexNumber plexNum;
					ComplexNumber plexNumZ;
					
					//Variable for the magnitude.
					double magni = 0;
					
					for(int i=0; i<475; i++){
						for(int j=0; j<475; j++){
							
							//Creates the Complex Number
							plexNum = new ComplexNumber((realCent - view)+(2*((view*i)/size)), (imagCent + view)-(2*((view*j)/size)));
							plexNumZ = new ComplexNumber((realCent - view)+(2*((view*i)/size)), (imagCent + view)-(2*((view*j)/size)));
							
							//Initializes the Counter
							int counter=0;
							
							
							for(int k = 0; k < Seq; k++){
								
								//Squares the Complex Number
								plexNum.square();
								
								//Adds the Complex Numbers
								plexNum.add(plexNumZ);
								
								magni = plexNum.magnitude();
								
								//Checks if the magnitude is 2 or above, if so, it breaks the loop
								if(magni >= 2){
									break;
								}
								
								//Counter only counts if loop was not broken
								counter++;
								
							}
							
							if(counter == Seq){
								//Colors the Mandelbrot according to an equation I came up with to gradiate the colors throughout.
								Color c = new Color((int)(magni*250/2), (int)(magni*250/2), (int)(magni*50/2));
								g.setColor(c);
								
								//Draws at the coordinates
								g.drawLine(i, j, i, j);
								
							}//Draw the Mandelbrot
							
							else{
								//Uses the equation shown in class to color the not-Mandelbrot accordingly
								Color c = new Color((Seq - counter)*0/Seq, (Seq - counter)*0/Seq, (Seq - counter)*0/Seq);
								g.setColor(c);
								
								//Draws at the coordinates
								g.drawLine(i , j, i, j);
								
							}//Draw the not-Mandelbrot
							
						}//end of j for loop
						
					}//end of i for loop
					
				}//End of drawThatMandelbrot
				
		}//End of Objectlistener2

}//End of class MandelbrotSet



