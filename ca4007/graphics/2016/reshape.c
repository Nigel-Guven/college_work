#include <GL/gl.h>
#include <GL/glext.h>
#include <GL/glut.h>
#include <stdio.h>
/*
// gcc Reshape.c -o Reshape glut32.lib -lopengl32 -lglu32
// reshaping should keep the objects size, not keep the aspect ratio


//Draws square
void drawSquare() {
	glBegin(GL_POLYGON);
		glVertex2f(-0.5,-0.5);
		glVertex2f(-0.5,0.5);
		glVertex2f(0.5,0.5);
		glVertex2f(0.5,-0.5);
	glEnd();
}

//Display the square
void display(void)
{
	glClear(GL_COLOR_BUFFER_BIT);

	glColor3f(1.0,0.0,0.0);
	drawSquare();

	glFlush();
	return;
}


// Idle function reshape
void MyReshape(GLsizei w, GLsizei h)
{
	printf("(w,h) = (%d,%d)\n", w, h);
	// when reshaped the square is the same size. but doesn't stay in the center
	glViewport(0, 0, 500, 500);

	return;
}

// Mouse function define where mouse is and if inside square change color etc.
void MyMouse(GLint x, GLint y) {
	if (x > 125 && x < 375 && y > 125 && y < 375)
		glColor3f(0.0,1.0,0.0);
	else
		glColor3f(1.0,0.0,0.0);

	drawSquare();

	glFlush();
	return;
}

int main(int argc, char **argv)
{
	glutInit(&argc, argv);
	glutInitWindowSize(500,500);
	glutCreateWindow("Reshape");
	glutDisplayFunc(display);
	glutReshapeFunc(MyReshape);
	glutPassiveMotionFunc(MyMouse);
	glutMainLoop();
}
*/
