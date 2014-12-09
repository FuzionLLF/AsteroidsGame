import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class AsteroidsGame extends PApplet {

SpaceShip milleniumFalcon;
Star [] group;
ArrayList<Asteroids> rocks;
ArrayList<Bullets> pew = new ArrayList<Bullets>(); //your variable declarations here
public void setup() 
{
  size(700,700);
  //background(0);
  milleniumFalcon = new SpaceShip();
  group = new Star[100];
  for(int i = 0; i < group.length; i++)
    {
      group[i] = new Star();
    }
  rocks = new ArrayList<Asteroids>();
  for(int r = 0; r < 10; r++)
    {
      rocks.add(new Asteroids());
    }
  //your code here
}
public void draw() 
{
  background(0);
  fill(255);
  milleniumFalcon.show();
  milleniumFalcon.move();
  for(int i = 0; i < group.length;i++)
  {
    group[i].show();
  }
  for(int r = 0;r < rocks.size();r++)
  {
    rocks.get(r).show();
    rocks.get(r).move();
    if(dist((float)rocks.get(r).getX(),(float)rocks.get(r).getY(),(float)milleniumFalcon.getX(),(float)milleniumFalcon.getY() ) < 20)
    {
      rocks.remove(r);
    }
  }
  for(int b = 0; b<pew.size();b++)
  {
    {
      pew.get(b).show();
      pew.get(b).move();
    }
  }
  for(int r=0;r<rocks.size();r++)
  {
    for(int b=0;b<pew.size();b++)
    {
      if(dist(rocks.get(r).getX(),rocks.get(r).getY(),pew.get(b).getX(),pew.get(b).getY())<20)
      {
        rocks.remove(r);
        pew.remove(b);
        rocks.add(new Asteroids());
      }
    }
  }
  //your code here
}
public void keyPressed()
{
  if(key == 'w')
  {
    milleniumFalcon.accelerate(0.1f);
    //milleniumFalcon.rotate();
  }
  else if(key == 'a')
  {
    //milleniumFalcon.accelerate(0.1);
    milleniumFalcon.rotate((int)-5);
  }
  else if(key == 's')
  {
    milleniumFalcon.accelerate(-0.1f);
  }
  else if(key == 'd' )
  {
    //milleniumFalcon.accelerate(0.1);
    milleniumFalcon.rotate((int)5);
  }
  else if(key == 'h')
  {
    milleniumFalcon.Hyperspace();
  }
  else if(key == ' ')
  {
  for(int b = 0; b<10;b++)
    {
      pew.add(new Bullets(milleniumFalcon));
    }
  }
}
class Star
{
  private int sizeX,sizeY,x,y;
  public Star()
  {
    sizeX = 2;
    sizeY = 2;
    x = (int)(Math.random()*700);
    y = (int)(Math.random()*700);
  }
  public void show()
  {
    noStroke();
    fill(255,255,0);
    ellipse(x,y,sizeX,sizeY);
  }
}
class SpaceShip extends Floater 
{    
  public SpaceShip()
  {
    setX(350); 
    setY(350);
    setDirectionX(0); 
    setDirectionY(0);
    setPointDirection(270);
    myColor = color(255,255,255);
    corners = 3;
    xCorners = new int[corners];
    yCorners = new int[corners];
    xCorners[0] = -8;
    yCorners[0] = -8;
    xCorners[1] = 16;
    yCorners[1] = 0;
    xCorners[2] = -8;
    yCorners[2] = 8;
  }
  public void setX(int x){myCenterX = x;}
  public int getX(){return (int)myCenterX;}
  public void setY(int y){myCenterY = y;}
  public int getY(){return (int)myCenterY;}
  public void setDirectionX(double x){myDirectionX = x;}
  public double getDirectionX(){return myDirectionX;}
  public void setDirectionY(double y){myDirectionY = y;}
  public double getDirectionY(){return myDirectionY;}
  public void setPointDirection(int degrees){myPointDirection = degrees;}
  public double getPointDirection(){return myPointDirection;}    
    
  public void Hyperspace()
  {
    int rando = (int)(Math.random()*600)+100;
    int rando2 = (int)(Math.random()*600)+100;
    myCenterX = rando;
    myCenterY = rando2;
  }
    //your code here
}
class Asteroids extends Floater
{
  private int spin; 
  public Asteroids()
  {
    myColor = color(128,128,128);
    corners = 6;
    xCorners = new int[corners];
    yCorners = new int[corners];
    xCorners[0] = -11;
    yCorners[0] = -8;
    xCorners[1] = 7;
    yCorners[1] = -8;
    xCorners[2] = 13;
    yCorners[2] = 0;
    xCorners[3] = 6;
    yCorners[3] = 10;
    xCorners[4] = -11;
    yCorners[4] = 8;
    xCorners[5] = -5;
    yCorners[5] = 0;
    myCenterX=(int)(0);
    myCenterY=(int)(Math.random()*5);
    myDirectionX=(int)(Math.random()*6)-3;
    myDirectionY=(int)(Math.random()*6)-3;
    myPointDirection=(int)(Math.random()*180);
    spin = (int)(Math.random()*6)-3;
  }
  public void move()
  {
   rotate(spin);
   super.move();
  }
  public void setX(int x){myCenterX = x;}
  public int getX(){return (int)myCenterX;}
  public void setY(int y){myCenterY = y;}
  public int getY(){return (int)myCenterY;}
  public void setDirectionX(double x){myDirectionX = x;}
  public double getDirectionX(){return myDirectionX;}
  public void setDirectionY(double y){myDirectionY = y;}
  public double getDirectionY(){return myDirectionY;}
  public void setPointDirection(int degrees){myPointDirection = degrees;}
  public double getPointDirection(){return myPointDirection;}
  
}
class Bullets extends Floater
{
  private double dRadians;
  public Bullets(SpaceShip ship)
  {
    myCenterX = ship.getX();
    myCenterY = ship.getY();    
    myPointDirection = ship.getPointDirection();
    dRadians = myPointDirection*(Math.PI/180);
    myDirectionX = 3*Math.cos(dRadians) + ship.getDirectionX();
    myDirectionY = 3*Math.sin(dRadians) + ship.getDirectionY();
  }
  
  public void setX(int x){myCenterX = x;}
  public int getX(){return (int)myCenterX;}
  public void setY(int y){myCenterY = y;}
  public int getY(){return (int)myCenterY;}
  public void setDirectionX(double x){myDirectionX = x;}
  public double getDirectionX(){return myDirectionX;}
  public void setDirectionY(double y){myDirectionY = y;}
  public double getDirectionY(){return myDirectionY;}
  public void setPointDirection(int degrees){myPointDirection = degrees;}
  public double getPointDirection(){return myPointDirection;} 
  
  public void show()
  {
    noStroke();
    fill(245,7,7);
    ellipse((int)myCenterX,(int)myCenterY,5,5);
  }
    public void move()
  {
    //change the x and y coordinates by myDirectionX and myDirectionY       
    myCenterX += myDirectionX;    
    myCenterY += myDirectionY;
  }
}
abstract class Floater //Do NOT modify the Floater class! Make changes in the SpaceShip class 
{   
  protected int corners;  //the number of corners, a triangular floater has 3   
  protected int[] xCorners;   
  protected int[] yCorners;   
  protected int myColor;   
  protected double myCenterX, myCenterY; //holds center coordinates   
  protected double myDirectionX, myDirectionY; //holds x and y coordinates of the vector for direction of travel   
  protected double myPointDirection; //holds current direction the ship is pointing in degrees    
  abstract public void setX(int x);  
  abstract public int getX();   
  abstract public void setY(int y);   
  abstract public int getY();   
  abstract public void setDirectionX(double x);   
  abstract public double getDirectionX();   
  abstract public void setDirectionY(double y);   
  abstract public double getDirectionY();   
  abstract public void setPointDirection(int degrees);   
  abstract public double getPointDirection(); 
  //Accelerates the floater in the direction it is pointing (myPointDirection)   
  public void accelerate (double dAmount)   
  {          
    //convert the current direction the floater is pointing to radians    
    double dRadians =myPointDirection*(Math.PI/180);     
    //change coordinates of direction of travel    
    myDirectionX += ((dAmount) * Math.cos(dRadians));    
    myDirectionY += ((dAmount) * Math.sin(dRadians));       
  }   
  public void rotate (int nDegreesOfRotation)   
  {     
    //rotates the floater by a given number of degrees    
    myPointDirection+=nDegreesOfRotation;   
  }   
  public void move ()   //move the floater in the current direction of travel
  {      
    //change the x and y coordinates by myDirectionX and myDirectionY       
    myCenterX += myDirectionX;    
    myCenterY += myDirectionY;     
    //wrap around screen    
    if(myCenterX >width)
    {     
      myCenterX = 0;    
    }    
    else if (myCenterX<0)
    {     
      myCenterX = width;    
    }    
    if(myCenterY >height)
    {    
      myCenterY = 0;    
    }   
    else if (myCenterY < 0)
    {     
      myCenterY = height;    
    }   
  }   
  public void show ()  //Draws the floater at the current position  
  {             
    fill(myColor);   
    stroke(myColor);    
    //convert degrees to radians for sin and cos         
    double dRadians = myPointDirection*(Math.PI/180);                 
    int xRotatedTranslated, yRotatedTranslated;    
    beginShape();         
    for(int nI = 0; nI < corners; nI++)    
    {     
      //rotate and translate the coordinates of the floater using current direction 
      xRotatedTranslated = (int)((xCorners[nI]* Math.cos(dRadians)) - (yCorners[nI] * Math.sin(dRadians))+myCenterX);     
      yRotatedTranslated = (int)((xCorners[nI]* Math.sin(dRadians)) + (yCorners[nI] * Math.cos(dRadians))+myCenterY);      
      vertex(xRotatedTranslated,yRotatedTranslated);    
    }   
    endShape(CLOSE);  
  }   
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "AsteroidsGame" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
