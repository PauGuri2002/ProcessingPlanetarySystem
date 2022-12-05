package planetsystem;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PShape;

public class Star implements AstralBody,PConstants {
	PApplet applet;
	
	private float posX = 0;
	private float posY = 0;
	private float posZ = 0;
	private float radius = 10;
	private float rotationAngle = 0;
	private float rotationPeriod = 0;
	
	PShape sphere;
	ArrayList<Planet> childPlanets = new ArrayList<Planet>();
	
	public Star(PApplet a, float posX_, float posY_, float posZ_, float radius_, float rotationPeriod_) {
		applet = a;
		
		posX = posX_;
		posY = posY_;
		posZ = posZ_;
		radius = radius_;
		rotationPeriod = rotationPeriod_;
		sphere = applet.createShape(SPHERE, radius);
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
	
	public Star setTexture(PImage img) {
		sphere.setTexture(img);
		return this;
	}
	
	public Star noTexture() {
		sphere.noTexture();
		return this;
	}
	
	public Star setColor(int r, int g, int b) {
		sphere.fill(r,g,b);
		return this;
	}
	
	public float[] getPosition(){
		float[] pos = {posX, posY, posZ};
		return pos;
	}
	
	public float getRadius() {
		return radius;
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
		applet.translate(posX, posY, posZ);
		applet.rotateX(-PI/2);
		applet.rotateY(rotationAngle);
		applet.shape(sphere);
		applet.pop();
		
		rotationAngle += periodToAngularSpeed(rotationPeriod)*simulationSpeed;
	}
	
	private void renderChildren(float simulationSpeed) {
		for(int i = 0; i<childPlanets.size(); i++){
			childPlanets.get(i).drawAll(simulationSpeed);
		}
	}
	
	public float periodToAngularSpeed(float period) {
		return period == 0 ? 0 : (2*PI) / (period*applet.frameRate);
	}
}
