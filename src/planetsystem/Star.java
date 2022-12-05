package planetsystem;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PShape;

/**
 * A Star is a stationary astral body that can contain any number of Child planets. It is the base for creating any planetary system. 
 * 
 * @author Pau Guri Viura
 *
 */
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
	
	/**
	 * @param a the parent PApplet
	 * @param posX_ the x position of the Star
	 * @param posY_ the y position of the Star
	 * @param posZ_ the z position of the Star
	 * @param radius_ the radius of the Star
	 * @param rotationPeriod_ the time (in seconds) for the Star to do a full rotation around itself
	 */
	public Star(PApplet a, float posX_, float posY_, float posZ_, float radius_, float rotationPeriod_) {
		applet = a;
		
		posX = posX_;
		posY = posY_;
		posZ = posZ_;
		radius = radius_;
		rotationPeriod = rotationPeriod_;
		sphere = applet.createShape(SPHERE, radius);
	}
	
	/**
	 * Adds a child Planet to the Star
	 * 
	 * @param distance_ the distance from the center of the parent Star to the center of the Child planet.
	 * @param radius_ the radius of the child Planet
	 * @param translationPeriod_ the time (in seconds) for the child Planet to do a full rotation around the parent Star
	 * @param rotationPeriod_ the time (in seconds) for the child Planet to do a full rotation around itself
	 */
	public Planet addChild(float distance_, float radius_, float translationPeriod_, float rotationPeriod_) {
		Planet p = new Planet(applet, this, distance_, radius_, translationPeriod_, rotationPeriod_);
		childPlanets.add(p);
	  	return p;
	}
	
	/**
	 * Returns the child Planet with the specified index
	 * 
	 * @param i the index of the Planet to get.
	 */
	public Planet getChild(int i) {
		return childPlanets.get(i);
	}
	
	/**
	 * Returns an array with all the children of this Star
	 */
	public Planet[] getAllChildren() {
		return (Planet[]) childPlanets.toArray();
	}
	
	/**
	 * Removes the child Planet with the specified index
	 * @param i the index of the Planet to be removed
	 * @return the removed Planet.
	 */
	public Planet removeChild(int i) {
		return childPlanets.remove(i);
	}
	
	/**
	 * Removes the specified Planet as a child of the Star
	 * @param p the Planet to be removed
	 * @return true if the specified Planet was a child of the Star
	 */
	public boolean removeChild(Planet p) {
		return childPlanets.remove(p);
	}
	
	/**
	 * Sets an image to the Star
	 * @param img the image to be set as texture
	 * @return itself
	 */
	public Star setTexture(PImage img) {
		sphere.setTexture(img);
		return this;
	}
	
	/**
	 * Removes the image texture of the Star
	 * @return itself
	 */
	public Star noTexture() {
		sphere.noTexture();
		return this;
	}
	
	/**
	 * Sets a color to the Star
	 * @param r the Red component of the color (0 to 255)
	 * @param g the Green component of the color (0 to 255)
	 * @param b the Blue component of the color (0 to 255)
	 * @return itself
	 */
	public Star setColor(int r, int g, int b) {
		sphere.fill(r,g,b);
		return this;
	}
	
	/**
	 * Sets the position of the Star
	 * 
	 * @param posX_ The new x position of the Star
	 * @param posY_ The new y position of the Star
	 * @param posZ_ The new z position of the Star
	 * @return itself
	 */
	public Star setPosition(float posX_, float posY_, float posZ_) {
		posX = posX_;
		posY = posY_;
		posZ = posZ_;
		return this;
	}
	
	/**
	 * Gets the current position of the star
	 * 
	 * @return an array containing x, y and z coordinates of the Star
	 */
	public float[] getPosition(){
		float[] pos = {posX, posY, posZ};
		return pos;
	}
	
	/**
	 * Sets the radius of the Star
	 * 
	 * @param radius_ the new radius of the Star
	 * @return itself
	 */
	public Star setRadius(float radius_) {
		radius = radius_;
		return this;
	}
	
	/**
	 * Gets the current radius of the Star
	 */
	public float getRadius() {
		return radius;
	}
	
	/**
	 * Gets the current rotation angle of the Star (the angle the Star is facing)
	 * @return the angle in radians
	 */
	public float getRotationAngle() {
		return rotationAngle;
	}
	
	/**
	 * Sets the rotation period of the Star
	 * Rotation period is the time for the child Planet to do a full rotation around itself.
	 * @param rotationPeriod_ the new rotation period (in seconds)
	 * @return itself
	 */
	public Star setRotationPeriod(float rotationPeriod_) {
		rotationPeriod = rotationPeriod_;
		return this;
	}
	
	/**
	 * Gets the current rotation period of the Star
	 * Rotation period is the time for the child Planet to do a full rotation around itself.
	 * @return the angle in radians
	 */
	public float getRotationPeriod() {
		return rotationPeriod;
	}
	
	/**
	 * Draws the Star in P3D
	 */
	public void draw() {
		this.render(1);
	}
	
	/**
	 * Draws the Star in P3D with a custom simulation speed
	 * @param simulationSpeed the simulation speed multiplier (1 by default)
	 */
	public void draw(float simulationSpeed) {
		this.render(simulationSpeed);
	}
	
	/**
	 * Draws all the children Planets of the Star in P3D
	 */
	public void drawChildren() {
		this.renderChildren(1);
	}
	
	/**
	 * Draws all the children Planets of the Star in P3D with a custom simulation speed
	 * @param simulationSpeed the simulation speed multiplier (1 by default)
	 */
	public void drawChildren(float simulationSpeed) {
		this.renderChildren(simulationSpeed);
	}
	
	/**
	 * Draws the Star and all its children Planets in P3D
	 */
	public void drawAll() {
		this.render(1);
		this.renderChildren(1);
	}
	
	/**
	 * Draws the Star and all its children Planets in P3D with a custom simulation speed
	 * @param simulationSpeed the simulation speed multiplier (1 by default)
	 */
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
	
	/**
	 * Helper function. Converts a period to angular speed
	 * @param period the period (time to do a full rotation) in seconds
	 * @return the corresponding angular speed (in rad/s)
	 */
	public float periodToAngularSpeed(float period) {
		return period == 0 ? 0 : (2*PI) / (period*applet.frameRate);
	}
}
