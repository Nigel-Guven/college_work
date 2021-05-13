import tuples

miles = 53 
converter = 1.60934
kilometre = miles*converter

print kilometre

a = b = c = 32
age,name,weight = a,"Jim",79.6
print age,name,weight

#numbers,strings,lists,tuple,dictionary are 5 python types

del miles
del converter,a,b,c
print kilometre
#print miles

#python numerical types: int,long,float,complex

inty = 10
longy = 0xAA2342l
floaty = 32.8e18
complexy = 3.15j


str  = 'Nigel'
print str
print str [1:4]
print str [3:]
print str*5 +'\n'

big = ['abcdefg',345,0xAFF2,70.656]
tiny = [1,'"45ds"']

print big
print tiny
print big[0:3]
print big[:2]
print big+tiny

tuples = (4,'jim',complexy)

print tuples

bigdict = {'Mars': 'Venus'}
tinydict = {'Jupiter': "Saturn"}

print bigdict.keys()
print tinydict.values()
