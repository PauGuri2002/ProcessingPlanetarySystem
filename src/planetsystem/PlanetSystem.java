package planetsystem;

import java.util.ArrayList;
import processing.core.PApplet;

public class PlanetSystem extends ArrayList<Planet> {
	
	PApplet parent;
	
	float x, y, z;
	float speed = 1;
	
	/**
	 * Creates a new Planetary System
	 * 
	 * @param theParent the parent PApplet
	 * @param posx the x position of the center of the system
	 * @param posy the y position of the center of the system
	 * @param posz the z position of the center of the system
	 */
	public PlanetSystem(PApplet theParent, float posx, float posy, float posz) {
		super();
		
		parent = theParent;
		x = posx;
		y = posy;
		z = posz;
	}
	
	/**
	 * Creates a new Planetary System
	 * 
	 * @param theParent the parent PApplet
	 * @param positions a PVector with the x,y,z position of the center of the system
	 */
	public PlanetSystem(PApplet theParent, float[] positions) {
		super();
		
		parent = theParent;
		x = positions[0];
		y = positions[1];
		z = positions[2];
	}
	
	@Override
	public boolean add(Planet planet) {
		planet.setApplet(parent);
		return super.add(planet);
	}
	
	public void setSpeed(float s) {
		speed = s;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public void setPosition(float posx, float posy, float posz) {
		x = posx;
		y = posy;
		z = posz;
	}
	
	public float[] getPosition() {
		float[] pos = {x,y,z};
		return pos;
	}
	
	public void draw() {
		for (int i = 1; i<this.size(); i++) {
		    this.get(i).draw(speed);
		  }
	}
}
