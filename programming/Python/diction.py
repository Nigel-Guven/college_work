mydict1 = 	{	
				'Car':'Swift',
				'Brand':'Suzuki',
				'Year':'2009',					
				'Country':'South Korea'				
			}
mydict2 = 	{	
				'Car':'Landkreuser',
				'Brand':'Toyota',
				'Year':'2011', 
				'Country':'South Korea'				
			} 
mydict3 = 	{	
				'Car':'330e',
				'Brand':'BMW',
				'Year':'2017', 
				'Country':'Germany'				
			}
mydict4 = 	{	
				'Car':'F-Type',
				'Brand':'Jaguar',
				'Year':'2014', 
				'Country':'UK'			
			}
mydict5 = 	{	
				'Car':'Ibiza',	
				'Brand':'Seat',
				'Year':'2004', 
				'Country':'Spain'			
			} 

mylist = [mydict1,mydict2,mydict3,mydict4,mydict5]
			     
for i in mylist:
	if (i['Country'] == 'Germany'):
		print i


	

