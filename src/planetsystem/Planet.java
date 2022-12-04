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
	private float translationAngle = 0;
	private float rotationAngle = 0;
	private float translationPeriod = 0;
	private float rotationPeriod = 0;
	
	PlanetType planetType;
	PShape sphere;
	Planet parent;
	ArrayList<Planet> childPlanets = new ArrayList<Planet>();
	
	public Planet(PApplet a, float posX_, float posY_, float posZ_, float radius_, float rotationPeriod_) {
		applet = a;
		planetType = new ParentPlanet();
		
		posX = posX_;
		posY = posY_;
		posZ = posZ_;
		parent = this;
		radius = radius_;
		rotationPeriod = rotationPeriod_;
		sphere = applet.createShape(SPHERE, radius);
		//ringAngle = PApplet.radians(applet.random(-30, 30));
	}
	
	private Planet(PApplet a, Planet parent_, float distance_, float radius_, float translationPeriod_, float rotationPeriod_) {
		applet = a;
		planetType = new ChildPlanet();
		
		parent = parent_;
		distance = distance_;
		translationPeriod = translationPeriod_;
		rotationPeriod = rotationPeriod_;
		translationAngle = (float) Math.random()*TWO_PI;
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
	
	public float[] getPosition() {
		return planetType.getPosition();
	}
	
	public float getRadius() {
		return radius;
	}
	
	public Planet addChild(float distance_, float radius_, float translationPeriod_, float rotationPeriod_) {
		Planet p = new Planet(applet, this, distance_, radius_, translationPeriod_, rotationPeriod_);
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
		planetType.render(1);
	}
	
	public void draw(float simulationSpeed) {
		planetType.render(simulationSpeed);
	}
	
	public void drawChildren() {
		this.renderChildren(1);
	}
	
	public void drawChildren(float simulationSpeed) {
		this.renderChildren(simulationSpeed);
	}
	
	public void drawAll() {
		planetType.render(1);
		this.renderChildren(1);
	}
	
	public void drawAll(float simulationSpeed) {
		planetType.render(simulationSpeed);
		this.renderChildren(simulationSpeed);
	}
	
	public void renderChildren(float simulationSpeed) {
		for(int i = 0; i<childPlanets.size(); i++){
			childPlanets.get(i).drawAll(simulationSpeed);
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
	
	private float periodToAngularSpeed(float period) {
		return period == 0 ? 0 : (2*PI) / (period*applet.frameRate);
	}
	
	interface PlanetType {
		public void render(float simulationSpeed);
		public float[] getPosition();
	}
	
	private class ParentPlanet implements PlanetType {
		public void render(float simulationSpeed) {
			applet.push();
			applet.rotate(translationAngle);
			applet.translate(posX, posY, posZ);
			
			applet.rotateX(-PI/2);
			applet.rotateY(rotationAngle);
			applet.shape(sphere);
			applet.pop();
			
			rotationAngle += periodToAngularSpeed(rotationPeriod)*simulationSpeed;
		}
		
		public float[] getPosition(){
			float[] pos = {posX, posY, posZ};
			return pos;
		}
	}
	
	private class ChildPlanet implements PlanetType {
		public void render(float simulationSpeed) {
			applet.push();
			applet.rotate(translationAngle);
			applet.translate(parent.getPosition()[0] + distance, parent.getPosition()[1], parent.getPosition()[2]);
			
			applet.rotateX(-PI/2);
			applet.rotateY(rotationAngle);
			applet.shape(sphere);
			applet.pop();
			
			translationAngle += periodToAngularSpeed(translationPeriod)*simulationSpeed;
			rotationAngle += periodToAngularSpeed(rotationPeriod)*simulationSpeed;
		}
		
		public float[] getPosition(){
			float[] pos = {(float) (parent.getPosition()[0] + distance*Math.cos(translationAngle)), (float) (parent.getPosition()[1] + distance*Math.sin(translationAngle)), parent.getPosition()[2]};
			return pos;
		}
	}
	
	/*
	 * TO DO
	 * 
	 * - Implement planet rings
	 * - Implement get for most variables
	 * - Separate planets into CentralPlanet and ChildPlanet?
	 * 
	 */
}