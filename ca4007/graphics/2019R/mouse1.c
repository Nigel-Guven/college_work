
#include <GL/gl.h>
#include <GL/glext.h>
#include <GL/glut.h>
#include <math.h>

void display(void)
{
	glClear(GL_COLOR_BUFFER_BIT);

	glBegin(GL_TRIANGLE_FAN);
		glColor3f(1.0,1.0,1.0);
		glVertex2f(-0.5,-0.5);
		glVertex2f(-0.5,0.5);
		glColor3f(1.0,1.0,1.0);
		glVertex2f(0.5,0.5);
		glVertex2f(0.5,-0.5);
	glEnd();

	double theta;
	glBegin(GL_TRIANGLE_FAN);
	for(int i=0; i < 5000; i++)
	{
	    theta = theta + 0.01;
		glVertex2f(cos(theta),sin(theta));
	}
	glEnd();

	glFlush();
	return;
}

int main(int argc, char **argv)
{
	glutInit(&argc, argv);
	glutInitWindowPosition(500,500);
	glutInitWindowSize(500,500);

	glutCreateWindow("simple");
	glutDisplayFunc(display);
	glClearColor(1.0,0.0,0.0,0.0);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(-2.0,2.0,-2.0,2.0);
	glutMainLoop();
}
