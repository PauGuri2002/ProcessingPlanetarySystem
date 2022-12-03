package planetsystem;
import java.util.ArrayList;
import processing.core.*;

public class Planet implements PConstants {
	PApplet applet;
	
	int r, size;
	  float v, ov, ringAngle;
	  PShape p, l;
	  ArrayList<Planet> moons = new ArrayList<Planet>();
	
	  public Planet(int radio, int tam, float vel_orbital, PImage tex) {
		r = radio;
	    v = applet.random(TWO_PI);
	    ov = vel_orbital;
	    size = tam;
	    p = applet.createShape(SPHERE, tam);
	    p.setTexture(tex);
	    ringAngle = PApplet.radians(applet.random(-30, 30));
	  }
	  
	  protected void setApplet(PApplet a) {
		  applet = a;
	  }
	  
	  public boolean addChild(Planet planet) {
		  planet.setApplet(applet);
		  moons.add(planet);
		  return true;
	  }
	
	  public void draw(float speedMultiplier) {
	    
	    applet.push();
	    applet.rotate(v);
	    applet.translate(r, 0, 0);
	    applet.push();
	    applet.rotateX(PApplet.radians(-90));
	    applet.shape(p);
	    applet.pop();
	    for(int i = 0; i<moons.size(); i++){
	      moons.get(i).draw(speedMultiplier);
	    }
	
	    applet.pop();
	
	    v = v + ov*speedMultiplier/applet.frameRate;
	  }
	  
	  public void renderRing(PImage img){
		  applet.push();
		  applet.rotate(v);
		  applet.translate(r, 0, 0);
		  applet.scale(0.9f);
		  applet.rotateX(ringAngle);
		  applet.image(img, -152.5f, -152.5f);
		  applet.pop();
	  }
	  
	  public double[] getPosition(){
	    double[] pos = {r*Math.cos(v), r*Math.sin(v)};
	    return pos;
	  }
	  
	  public int getSize(){
	    return size;
	  }
}
