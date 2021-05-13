
from random import randint

def addRand():
	tmp = randint(1,9)
	return tmp

def fillArray(arr):
	while(len(arr)<9):
		tmp1 = addRand();
		while(tmp1 not in arr):
			arr.append(tmp1)
		
def pushArray(arr):
	tmp2 = arr[8]
	arr.insert(0,tmp2)
	arr.pop(9)
	return arr
	
row1 = []
row2 = []
row3 = []
row4 = []
row5 = []
row6 = []
row7 = []
row8 = []
row9 = []


fillArray(row1)


print row1
row2 = pushArray(row1)
print row2
row3 = pushArray(row2)
print row3
row4 = pushArray(row3)
print row4
row5 = pushArray(row4)
print row5
row6 = pushArray(row5)
print row6
row7 = pushArray(row6)
print row7
row8 = pushArray(row7)
print row8
row9 = pushArray(row8)
print row9




