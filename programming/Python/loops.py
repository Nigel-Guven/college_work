



count = 0
while (count <= 80):
	if(count<10):
		x = str(count)
		print '0' + x
	else:
		print count
		
	count = count + 2

for letter in 'letter':
	print letter	

cars = ['bmw','audi','dacia','ford','peugeot','fiat','jaguar','toyota','mercedes','seat','landrover','chrysler','dodge','tata','hyundai','tesla']

mycars = [i for i in cars if len(i) < 6]
print cars
print mycars


for i in cars:	
	if i == 'seat' or len(i) > 7:
		pass
		print "This breaks"
		break
		
	elif i == 'audi':		
		pass 
		print "this continues"
		continue
	
	print i	


	
