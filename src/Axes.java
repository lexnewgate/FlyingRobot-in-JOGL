/**
 * A class for a set of Axes. 
 * Currently uses the GLUT objects, but could be changed in future to use 
 * objects constructed using the Mesh class.
 *
 * @author    Dr Steve Maddock
 * @version   1.0 (26/07/2013)
 */

import javax.media.opengl.*;
import com.jogamp.opengl.util.gl2.GLUT;
  
public class Axes {

  private boolean switchedOn;
  private double xLength, yLength, zLength;
  /**
   * Constructor.
   */
  public Axes(double xLength, double yLength, double zLength) {
    switchedOn = false;
    this.xLength = xLength;
	this.yLength = yLength;
	this.zLength = zLength;
  }    
  
  public boolean getSwitchedOn() { return switchedOn; }

  public void setSwitchedOn(boolean b) { switchedOn = b; }
  
  public void display(GL2 gl, GLUT glut) {
    // All axes will be set to same specular, shininess and emission settings.
    // Each axis will have will be set to different ambient and diffuse values.
    float[] matXAmbientDiffuse = {0.7f, 0.0f, 0.0f, 1.0f};
    float[] matYAmbientDiffuse = {0.0f, 0.7f, 0.0f, 1.0f};
    float[] matZAmbientDiffuse = {0.0f, 0.0f, 0.7f, 1.0f};
    float[] matSpecular = {1.0f, 1.0f, 1.0f, 1.0f};
    float[] matShininess = {32.0f};
    float[] matEmission = {0.0f, 0.0f, 0.0f, 1.0f};

    double cylinderRadius = 0.05;
    double coneRadius = 0.05;
    double coneHeight = 0.2;
    
    int sphereslices = 20;
    int spherestacks = 20;
    int coneslices = 10;
    int conestacks = 10;

    // use glMaterialfv. There is no glMaterialdv
    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, matXAmbientDiffuse, 0);
    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, matSpecular, 0);
    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SHININESS, matShininess, 0);
    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_EMISSION, matEmission, 0);

    // x axis is half red
    gl.glPushMatrix();
      gl.glTranslated(xLength/2.0, 0.0, 0.0);
      gl.glScaled(xLength/2.0, cylinderRadius, cylinderRadius);
      glut.glutSolidSphere(1.0, sphereslices, spherestacks);
    gl.glPopMatrix();
    gl.glPushMatrix();
      gl.glTranslated(xLength, 0.0f, 0.0f);
      gl.glRotated(90.0f, 0.0f, 1.0f, 0.0f);
      // auxSolidCone(...) is defined with the point along the z axis
      // and the base on the xy plane.
      glut.glutSolidCone(coneRadius, coneHeight, coneslices, conestacks);
    gl.glPopMatrix();

    // y axis is half green
    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, matYAmbientDiffuse, 0);
    gl.glPushMatrix();
      gl.glTranslated(0.0f, yLength/2.0f, 0.0f);
      gl.glScaled(cylinderRadius, yLength/2.0f, cylinderRadius);
      glut.glutSolidSphere(1.0f, sphereslices, spherestacks);
    gl.glPopMatrix();
    gl.glPushMatrix();
      gl.glTranslated(0.0f, yLength, 0.0f);
      gl.glRotated(-90.0f, 1.0f, 0.0f, 0.0f);
      glut.glutSolidCone(coneRadius, coneHeight, coneslices, conestacks);
    gl.glPopMatrix();

    // z axis is half blue
    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, matZAmbientDiffuse, 0);
    gl.glPushMatrix();
      gl.glTranslated(0.0f, 0.0f, zLength/2.0f);
      gl.glScaled(cylinderRadius, cylinderRadius, yLength/2.0f);
      glut.glutSolidSphere(1.0f, sphereslices, spherestacks);
    gl.glPopMatrix();
    gl.glPushMatrix();
      gl.glTranslated(0.0f, 0.0f, zLength);
      glut.glutSolidCone(coneRadius, coneHeight, coneslices, conestacks);
    gl.glPopMatrix();
  }
  
  public String toString() {
    return "["+xLength+", "+yLength+", "+zLength+"]";
  }

}
