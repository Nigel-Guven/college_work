try:
	f = open("nullfile.txt",'r')
	f.write("This is a test file for IO Handling")
except IOError:
	print "No!, cannot find file or read"
else:
	print "Yes."
