from __future__ import print_function

#str = raw_input("What goes in: ");
#print "Must come out: ",str 

#str = input("Enter valid Python: ");
#print "Output is : ", str

f = open('hellopad.txt', 'wb')
f.write("Python added in")
f.close()
f = open('hellopad.txt', 'r')
for line in f:
	print(line, end = '')
f.close()
f = open('hellopad.txt', 'wb')
f.write("Python added in")
f.close()


	
