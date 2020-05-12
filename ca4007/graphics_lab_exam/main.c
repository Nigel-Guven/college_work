#include <GL/gl.h>
#include <GL/glext.h>
#include <GL/glut.h>
#include <windows.h>
#include <math.h>

/*

    |-------------------|
    |  DATE AND TIME    |
    |-------------------|

DATE: 15th April 2020 TIME: 2:00-4:00 p.m.

    |-------------------|
    |     STUDENTS      |
    |-------------------|

Nigel Guven - 14493422 & Shaun Carey - 16450454

    |-------------------|
    |PROGRAM DESCRIPTION|
    |-------------------|
- This program creates and presents a yellow object with 5 shapes which align like a flower, using loops.
- The flower also possesses a cylindrical stem with which we eventually had trouble with aligning properly!
- A user may utilise keyboard bindings to have a look at the flower from different angles.
*/

//Variable for drawing circular objects
GLfloat PI = 3.14;

// Colour array for shape
GLfloat yellow[3] = {1.0, 1.0, 0.0};

// Array for user by keyboard buttons to realign the flower object
GLfloat origin[3] = {0,0,0};
GLfloat orientation[3] = {0,0,0};

void draw_cylinder(GLfloat radius, GLfloat height, GLubyte R, GLubyte G, GLubyte B)
{
    GLfloat x              = 1.0;
    GLfloat y              = 0.0;
    GLfloat angle          = 0.5;
    GLfloat angle_stepsize = 0.1;
    glColor3ub(139,69,19);
    glBegin(GL_QUAD_STRIP);
    angle = 0.5;

    while( angle < 2*PI )
    {
        x = radius * cos(angle);
        y = radius * sin(angle);
        glVertex3f(x, y , height);
        glVertex3f(x, y , 0.0);
        angle = angle + angle_stepsize;
    }

    glVertex3f(radius, 0.0, height);
    glVertex3f(radius, 0.0, 0.0);
    glEnd();
}

//Draw Petal function
void drawPetal(GLfloat startx, GLfloat starty, GLfloat inAngle, GLfloat radius)
{
    glPushMatrix();
    GLfloat inAngle_degs = inAngle * 180/PI;
    glRotatef(inAngle_degs, 0.0, 0.0, 1.0);
    glTranslatef(radius, 0.0, 0.0);

    glBegin(GL_TRIANGLE_FAN);
    glVertex3f(-radius/5, 0.0, -0.2);
    glColor3fv(yellow);

    GLfloat angle, z_value;
    for(angle = 0; angle < 2*PI; angle += 2*PI/100)
    {
        glVertex3f(2*radius * cos(angle), radius * sin(angle), 0);
    }
    glEnd();

    glPopMatrix();
}

void display(void)
{
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    glTranslatef(origin[0], origin[1], origin[2]);
    glRotatef(orientation[0], 1.0, 0.0, 0.0);
    glRotatef(orientation[1], 0.0, 1.0, 0.0);
    glRotatef(orientation[2], 0.0, 0.0, 1.0);
    if(1)
    glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
    else
    glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);

    GLfloat radius = .5;

    GLint i;
    GLfloat angle,x,y;
    for(i = 0; i < 5; i++)
    {
        angle = i*(8*PI / 5);
        x = radius * cos(angle);
        y = radius * sin(angle);
        drawPetal(x, y, angle, radius);
    }

    draw_cylinder(0.02, 9.0, 139,69,19);
    glFlush();
}

void keypress(unsigned char key, int x, int y)
{
    switch(key)
    {
        case 'x':
        orientation[0] += 5.0;
        break;
        case 'X':
        orientation[0] -= 5.0;
        break;
        case 'y':
        orientation[1] += 5.0;
        break;
        case 'Y':
        orientation[1] -= 5.0;
        break;
        case 'z':
        orientation[2] += 5.0;
        break;
        case 'Z':
        orientation[2] -= 5.0;
        break;
    }
    glutPostRedisplay();
}

int main(int argc, char** argv)
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
    glutInitWindowSize(500,500);
    glutInitWindowPosition(500,500);
    glutCreateWindow("examCA4007");
    glEnable(GL_DEPTH_TEST);
    glClearColor(1, 0.5, 0.5, 1);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(60, 1, 1.0, 100.0);
    glMatrixMode(GL_MODELVIEW);
    origin[2] = -5;
    glutKeyboardFunc(keypress);
    glutDisplayFunc(display);
    glutMainLoop();
    return 0;
}
