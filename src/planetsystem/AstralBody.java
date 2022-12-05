package planetsystem;
import processing.core.*;

public interface AstralBody {
	
	
	public AstralBody setTexture(PImage img);
	
	public AstralBody noTexture();
	
	public AstralBody setColor(int r, int g, int b);
	
	public float getRadius();
	
	public AstralBody addChild(float distance_, float radius_, float translationPeriod_, float rotationPeriod_);
	
	public AstralBody getChild(int i);
	
	public AstralBody removeChild(int i);
	
	public boolean removeChild(Planet p);
	
	public void draw();
	
	public void draw(float simulationSpeed);
	
	public void drawChildren();
	
	public void drawChildren(float simulationSpeed);
	
	public void drawAll();
	
	public void drawAll(float simulationSpeed);
	
	/*public void renderRing(PImage img){
		applet.push();
		applet.rotate(angle);
		applet.translate(distance, 0, 0);
	  	applet.scale(0.9f);
	  	applet.rotateX(ringAngle);
	  	applet.image(img, -152.5f, -152.5f);
	  	applet.pop();
	}*/
	
	public float[] getPosition();
	
	public float periodToAngularSpeed(float period);
	
	/*
	 * TO DO
	 * 
	 * - Implement planet rings
	 * - Implement get for most variables
	 * - Fix planet childs not orbiting with the correct pivot
	 * 
	 */
}