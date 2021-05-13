tup1 = ('physics','Sam',69.7)
tup2 = ('geometry','John',49.7)
tup3 = ('calculus','Ian',80.6) 
tup4 = ('statistics','Andrew',33.9)

list1 = [tup1,tup2,tup3,tup4]

for t in list1:
	if t[2]>40:
		print t[0] + "/" + t[1]
	else:
		print "No"
