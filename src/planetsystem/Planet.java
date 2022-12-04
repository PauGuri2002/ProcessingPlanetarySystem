package planetsystem;
import java.util.ArrayList;
import processing.core.*;

public class Planet implements PConstants {
	PApplet applet;
	
	  float distance, radius, angle, orbitalSpeed, ringAngle;
	  PShape sphere;
	  ArrayList<Planet> childPlanets = new ArrayList<Planet>();
	
	  public Planet(PApplet a, float distance_, float radius_, float orbitalSpeed_) {
		  applet = a;
		  distance = distance_;
		  orbitalSpeed = orbitalSpeed_;
		  radius = radius_;
		  angle = applet.random(TWO_PI);
		  sphere = applet.createShape(SPHERE, radius);
		  ringAngle = PApplet.radians(applet.random(-30, 30));
	  }
	  
	  public Planet setTexture(PImage img) {
		  sphere.setTexture(img);
		  return this;
	  }
	  
	  public Planet noTexture() {
		  sphere.noTexture();
		  return this;
	  }
	  
	  public Planet setColor(int r, int g, int b) {
		  sphere.fill(r,g,b);
		  return this;
	  }
	  
	  public Planet setDistance(float distance_) {
		  distance = distance_;
		  return this;
	  }
	  
	  public Planet setRadius(float radius_) {
		  radius = radius_;
		  return this;
	  }
	  
	  public Planet setOrbitalSpeed(float orbitalSpeed_) {
		  orbitalSpeed = orbitalSpeed_;
		  return this;
	  }
	  
	  public Planet addChild(float distance_, float radius_, float orbitalSpeed_) {
		  Planet p = new Planet(applet, distance_, radius_, orbitalSpeed_);
		  childPlanets.add(p);
		  return p;
	  }
	
	  public void draw(float speedMultiplier) {
	    
	    applet.push();
	    applet.rotate(angle);
	    applet.translate(distance, 0, 0);
	    applet.push();
	    applet.rotateX(PApplet.radians(-90));
	    applet.shape(sphere);
	    applet.pop();
	    
	    for(int i = 0; i<childPlanets.size(); i++){
	      childPlanets.get(i).draw(speedMultiplier);
	    }
	    applet.pop();
	
	    angle+= orbitalSpeed*speedMultiplier/applet.frameRate;
	  }
	  
	  public void renderRing(PImage img){
		  applet.push();
		  applet.rotate(angle);
		  applet.translate(distance, 0, 0);
		  applet.scale(0.9f);
		  applet.rotateX(ringAngle);
		  applet.image(img, -152.5f, -152.5f);
		  applet.pop();
	  }
	  
	  public double[] getPosition(){
	    double[] pos = {distance*Math.cos(angle), distance*Math.sin(angle)};
	    return pos;
	  }
	  
	  public float getRadius(){
	    return radius;
	  }
}
