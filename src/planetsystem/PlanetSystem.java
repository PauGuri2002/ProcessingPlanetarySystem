package planetsystem;

import java.util.ArrayList;
import processing.core.PApplet;

public class PlanetSystem extends ArrayList<Planet> {
	
	private static final long serialVersionUID = 5957382547036955476L;

	PApplet applet;
	
	float x = 0, y = 0, z = 0;
	float speed = 1;
	
	public PlanetSystem(PApplet a) {
		super();
		
		applet = a;
	}
	
	public PlanetSystem(PApplet a, float posx, float posy) {
		super();
		
		applet = a;
		x = posx;
		y = posy;
	}
	
	public PlanetSystem(PApplet a, float posx, float posy, float posz) {
		super();
		
		applet = a;
		x = posx;
		y = posy;
		z = posz;
	}
	
	public PlanetSystem(PApplet a, float[] positions) {
		super();
		
		applet = a;
		x = positions[0];
		y = positions[1];
		z = positions[2];
	}
	
	public Planet addPlanet(int distance_, int radius_, float orbitalSpeed_) {
		Planet p = new Planet(applet, distance_, radius_, orbitalSpeed_);
		super.add(p);
		return p;
	}
	
	public PlanetSystem setSimulationSpeed(float s) {
		speed = s;
		return this;
	}
	
	public float getSimulationSpeed() {
		return speed;
	}
	
	public PlanetSystem setPosition(float posx, float posy, float posz) {
		x = posx;
		y = posy;
		z = posz;
		return this;
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
