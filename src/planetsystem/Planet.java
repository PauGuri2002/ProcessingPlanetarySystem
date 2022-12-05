package planetsystem;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PShape;

public class Planet implements AstralBody,PConstants {
PApplet applet;
	
	private float radius = 10;
	private float distance = 0;
	private float translationAngle = 0;
	private float rotationAngle = 0;
	private float translationPeriod = 0;
	private float rotationPeriod = 0;
	
	AstralBody parent;
	PShape sphere;
	ArrayList<Planet> childPlanets = new ArrayList<Planet>();
	
	protected Planet(PApplet a, AstralBody parent_, float distance_, float radius_, float translationPeriod_, float rotationPeriod_) {
		applet = a;
		
		parent = parent_;
		distance = distance_;
		translationPeriod = translationPeriod_;
		rotationPeriod = rotationPeriod_;
		translationAngle = (float) Math.random()*TWO_PI;
		radius = radius_;
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
	
	public Planet[] getAllChildren() {
		return (Planet[]) childPlanets.toArray();
	}
	
	public Planet removeChild(int i) {
		return childPlanets.remove(i);
	}
	
	public boolean removeChild(Planet p) {
		return childPlanets.remove(p);
	}
	
	public AstralBody getParent() {
		return parent;
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
	
	public float[] getPosition(){
		float[] pos = {(float) (parent.getPosition()[0] + distance*Math.cos(translationAngle)), (float) (parent.getPosition()[1] + distance*Math.sin(translationAngle)), parent.getPosition()[2]};
		return pos;
	}
	
	public Planet setDistance(float distance_) {
		distance = distance_;
		return this;
	}
	
	public float getDistance() {
		return distance;
	}
	
	public Planet setRadius(float radius_) {
		radius = radius_;
		return this;
	}
	
	public float getRadius() {
		return radius;
	}
	
	public float getTranslationAngle() {
		return translationAngle;
	}
	
	public Planet setTranslationPeriod(float translationPeriod_) {
		translationPeriod = translationPeriod_;
		return this;
	}
	
	public float getTranslationPeriod() {
		return translationPeriod;
	}
	
	public float getRotationAngle() {
		return rotationAngle;
	}
	
	public Planet setRotationPeriod(float rotationPeriod_) {
		rotationPeriod = rotationPeriod_;
		return this;
	}
	
	public float getRotationPeriod() {
		return rotationPeriod;
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
	
	public void render(float simulationSpeed) {
		applet.push();
		applet.translate(parent.getPosition()[0], parent.getPosition()[1], parent.getPosition()[2]);
		applet.rotate(translationAngle);
		applet.translate(distance,0,0);
		
		applet.rotateX(-PI/2);
		applet.rotateY(rotationAngle);
		applet.shape(sphere);
		applet.pop();
		
		translationAngle += periodToAngularSpeed(translationPeriod)*simulationSpeed;
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
