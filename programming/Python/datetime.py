import time;
from random import randint

ticks = time.time()

print time.asctime(time.localtime(time.time()))
print'\n'
print randint(0,9)

list1 =[1]

print list1

for i in range(0,8):
	x = (randint(0,9))
	if x < 5:
		x = x + 5
	elif x > 5:
		x = x + 3
	list1.append(x)

print list1
			
