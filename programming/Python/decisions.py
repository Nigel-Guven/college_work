def stuff():
	x = 2
	w = str(x)
	w = int(x)
	y = 3
	z = [x for x in range(1,100)]

	for i in z:
		if i%y==0:
			print (str(i) + " evens with 3.\t")
		if (i%w) == 0:
			print (str(i) + " evens with 2.\t")
		else: 
			print (str(i) + " is not even or triple")
def main():
	stuff()
if "__main__" == __name__:
	main()
