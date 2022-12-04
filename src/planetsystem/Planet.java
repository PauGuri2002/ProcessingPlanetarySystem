package planetsystem;
import java.util.ArrayList;
import processing.core.*;

public class Planet implements PConstants {
	PApplet applet;
	
	private float posX = 0;
	private float posY = 0;
	private float posZ = 0;
	private float radius = 10;
	//private float ringAngle = 0;
	private float distance = 0;
	private float angle = 0;
	private float orbitalSpeed = 0;
	
	PShape sphere;
	Planet parent;
	ArrayList<Planet> childPlanets = new ArrayList<Planet>();
	
	public Planet(PApplet a, float posX_, float posY_, float posZ_, float radius_) {
		applet = a;
		
		posX = posX_;
		posY = posY_;
		posZ = posZ_;
		parent = this;
		radius = radius_;
		sphere = applet.createShape(SPHERE, radius);
		//ringAngle = PApplet.radians(applet.random(-30, 30));
	}
	
	private Planet(PApplet a, Planet parent_, float distance_, float radius_, float orbitalSpeed_) {
		applet = a;
		
		parent = parent_;
		distance = distance_;
		orbitalSpeed = orbitalSpeed_;
		angle = applet.random(TWO_PI);
		radius = radius_;
		sphere = applet.createShape(SPHERE, radius);
		//ringAngle = PApplet.radians(applet.random(-30, 30));
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
	
	public Planet addChild(float distance_, float radius_, float orbitalSpeed_) {
		Planet p = new Planet(applet, this, distance_, radius_, orbitalSpeed_);
		childPlanets.add(p);
	  	return p;
	}
	
	public Planet getChild(int i) {
		return childPlanets.get(i);
	}
	
	public Planet removeChild(int i) {
		return childPlanets.remove(i);
	}
	
	public boolean removeChild(Planet p) {
		return childPlanets.remove(p);
	}
	
	public void draw() {
		this.render(1);
	}
	
	public void draw(float simulationSpeed) {
		this.render(simulationSpeed);
	}
	
	public void drawChildren() {
		this.renderChildren(1);
	}
	
	public void drawChildren(float simulationSpeed) {
		this.renderChildren(simulationSpeed);
	}
	
	public void drawAll() {
		this.render(1);
		this.renderChildren(1);
	}
	
	public void drawAll(float simulationSpeed) {
		this.render(simulationSpeed);
		this.renderChildren(simulationSpeed);
	}
	
	private void render(float simulationSpeed) {
		applet.push();
		applet.rotate(angle);
		applet.translate(parent.posX + distance, parent.posY, parent.posZ);
		applet.rotateX(PApplet.radians(-90));
		applet.shape(sphere);
		applet.pop();
	
		angle+= orbitalSpeed*simulationSpeed/applet.frameRate;
	}
	
	public void renderChildren(float simulationSpeed) {
		for(int i = 0; i<childPlanets.size(); i++){
			childPlanets.get(i).draw(simulationSpeed);
		}
	}
	
	/*public void renderRing(PImage img){
		applet.push();
		applet.rotate(angle);
		applet.translate(distance, 0, 0);
	  	applet.scale(0.9f);
	  	applet.rotateX(ringAngle);
	  	applet.image(img, -152.5f, -152.5f);
	  	applet.pop();
	}*/
	
	public double[] getPosition(){
		double[] pos = {distance*Math.cos(angle), distance*Math.sin(angle)};
		return pos;
	}
}