import math

class Point:
	def __init__(self,x,y):
		self.x = x
		self.y = y

	def displayPoint(self):
		print "(" , self.x, ",", self.y, ")"


	def midP(self,b):
		zx = (self.x + b.x)/2
		zy = (self.y + b.y)/2
		return Point(zx,zy)
		
	def distance(self,b):
		a = math.sqrt((b.x-self.x)*(b.x-self.x)+(b.y-self.y)*(b.y-self.y))	
		print "Distance is:",a
		
point1 = Point(5,2)
point2 = Point(1,2)
point1.displayPoint()
point2.displayPoint()

point3 = Point.midP(point1,point2)
point3.displayPoint()
Point.distance(point1,point2)
Point.distance(point2,point3)
Point.distance(point1,point3)





