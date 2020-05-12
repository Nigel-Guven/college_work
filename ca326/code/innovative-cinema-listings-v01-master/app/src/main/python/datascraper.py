from datetime import datetime as dt
from bs4 import BeautifulSoup	#https://www.crummy.com/software/BeautifulSoup/
from requests import get
import scriptfunctions

from classes import Cinema_Object
from classes import Film_Object


#DATA OBTAINED:

# > List of Cinema URLS							--cinema_listings_url
# > Cinema Names								--cinemaName
# > Film Names									--list_of_films
# > Film Times (String and DateTime Objects)	--list_of_times

def main():			#https://www.rte.ie/ DATA TAKEN FROM THIS SITE

	
	cinema_url_list = []
	all_cinemas_url = 'https://www.rte.ie/entertainment/listings/cinema/'		# scrape all cinema urls
	response_from_all_cinemas_url = get(all_cinemas_url)
	url_soup = BeautifulSoup(response_from_all_cinemas_url.text, 'html.parser')
	

	for link in url_soup.find_all('a'):
		uncut_url = str(link)
		
		if "venue" in uncut_url:
			uncut_url = uncut_url[10:]
			cinema_url = uncut_url.split('/',4)
			
			cinema_url = all_cinemas_url + cinema_url[3]
			cinema_url_list.append(cinema_url)

	
	#for i in cinema_url_list:
	#	print(i)
		
		
	cinema_listings_url = cinema_url_list[109]
	response_from_cinema_listings_url = get(cinema_listings_url)
	cinema_soup = BeautifulSoup(response_from_cinema_listings_url.text, 'html.parser')
	
	cinemaName = str(cinema_soup.find_all('h2'))
	cinemaName = scriptfunctions.remove_html_markup(cinemaName)

	
	cinema_data = str(cinema_soup.find_all('div', class_="large-8 cinema_single listing columns"))
	cinema_data = scriptfunctions.remove_html_markup(cinema_data)
	cinema_data = cinema_data.replace(r"\n", "\n")
	cinema_data = cinema_data.replace("[", "")
	cinema_data = cinema_data.replace("]", "")
	cinema_data = cinema_data.split("\n")
	cinema_data = cinema_data[11:]
	while '' in cinema_data:
		cinema_data.remove('')
	i = 0
	while i < len(cinema_data):									#tidy up film times i.e remove unwanted characters
		if cinema_data[i]=='':
			cinema_data.remove('')
		if i%2 == 0:
			cinema_data[i] = cinema_data[i].replace("&amp;", "&")
		if i%2 == 1:
			cinema_data[i] = cinema_data[i].replace("M", "")		
			cinema_data[i] = cinema_data[i].replace("Tu", "")
			cinema_data[i] = cinema_data[i].replace("W", "")
			cinema_data[i] = cinema_data[i].replace("Th", "")
			cinema_data[i] = cinema_data[i].replace("nF", "")
			cinema_data[i] = cinema_data[i].replace("FM-", "")
			cinema_data[i] = cinema_data[i].replace("F", "")
			cinema_data[i] = cinema_data[i].replace("Sa", "")
			cinema_data[i] = cinema_data[i].replace("Su", "")
			cinema_data[i] = cinema_data[i].replace("SS", "")
			cinema_data[i] = cinema_data[i].replace("n", "")
			cinema_data[i] = cinema_data[i].replace("-", "")
		i += 1
	
	list_of_films = []
	list_of_times = []
	
	for index, item in enumerate(cinema_data, start = 0):			#Loop divides data into film,times lists of same size
		if index % 2 == 0:
			list_of_films.append(item)
		else:
			list_of_times.append(item)
			
	imdb_film_url = 'https://www.imdb.com/showtimes/location?ref_=inth_shp'
	
	response_from_imdb_url = get(imdb_film_url)		
			
	##IMAGES
	
	imdb_image_soup = BeautifulSoup(response_from_imdb_url.text, 'html.parser')
	image_url_list = str(imdb_image_soup.find_all('div', class_="lister-item-image ribbonize"))
	image_url_list = image_url_list.replace(r"\n", "\n")

	image_url_list = image_url_list.split("\n")
	
	image_url_list = list(image_url_list)
	image_url_list = image_url_list[1:]
	
	image_url_list = image_url_list[::20]
	
	film_name_array = []
	film_image_url_array = []
	
	for index,item in enumerate(image_url_list, start = 0):
		
		unprocessed_line = item.split("=")
		unprocessed_line = unprocessed_line[3:]
		image_name = unprocessed_line[::7]
		
		
		
		for i in image_name:
			i = i.replace("&amp;", "&")
			i = i.lstrip('"')
			i = i.split('"')
			film_name_array.append(i[0])
			
		
		image_url = unprocessed_line[::4]
		
		for j in range(len(image_url)):
			if j%2 == 1:
				image_url[j] = image_url[j].lstrip('"')
				image_url[j] = image_url[j].rstrip('" src')
				film_image_url_array.append(image_url[j])


	print(film_image_url_array)
				
	## RATINGS, RELEASEDATE, RUNTIMES, GENRE, DESCRIPTION, DIRECTOR, ACTORS
	

	
	imdb_descr_soup = BeautifulSoup(response_from_imdb_url.text, 'html.parser')
	descr_data = str(imdb_descr_soup.find_all('div', class_="lister-item mode-grid"))
	descr_data = scriptfunctions.remove_html_markup(descr_data)
	descr_data = descr_data.replace(r"\n", " ")
	descr_data = descr_data.replace("&amp;", "&")
	descr_data = descr_data.replace("[", "")
	descr_data = descr_data.replace("]", "")
	descr_data = descr_data.replace("Rank:", "")
	descr_data = descr_data.replace("Metascore", "")
	descr_data = descr_data.replace("&gt; 1000", "")
	descr_data = descr_data.replace("&lt; 1000", "")
	descr_data = descr_data.replace(" &lt; 500", "")
	descr_data = descr_data.replace(",", "")
	descr_data = descr_data.replace(" Rate this", "")
	descr_data = descr_data.replace("Doragon b\\xf4ru ch\\xf4: Buror\\xee -", "")
	descr_data = descr_data.replace(" Release: ", "")
	descr_data = descr_data.replace(" Runtime: ", "")
	descr_data = descr_data.replace("Director: ", "")
	descr_data = descr_data.replace("Directors: ", "")
	descr_data = descr_data.replace("Stars: ", "")
	descr_data = descr_data.replace("\\xe4", "a")
	descr_data = descr_data.replace("\\xfc", "u")
	descr_data = descr_data.replace("\\xe1", "a")
	descr_data = descr_data.replace("\\xf6", "o")
	
	descr_data = descr_data.split("   ")
	descr_data = filter(None, descr_data)
	descr_data = [word for word in descr_data if len(word) > 5]
	#descr_data = [x for index, x in enumerate(descr_data) if index % 11 != 0]
	#del descr_data[3-1::1]
	
	
	
	for i in descr_data:
		if "votes" in i:
			descr_data.remove(i)
		elif "Votes" in i:
			descr_data.remove(i)
		elif "\\xa0" in i:
			descr_data.remove(i)
			
	descr_data.insert(54, "Vice")
	
	film_name = descr_data[0::9]
	film_rating = descr_data[1::9]
	film_releasedate = descr_data[2::9]
	film_runtime = descr_data[3::9]
	film_genre = descr_data[5::9]				
	film_description = descr_data[6::9]
	film_director = descr_data[7::9]			
	film_actors = descr_data[8::9]	
			
			
	film_genre = film_genre[:-1]		
	
	
	for ind,i in enumerate(film_genre):
		tmp = i.split('|')
		i = tmp[-1].strip()
		film_genre[ind] = i
		
	for ind,i in enumerate(film_director):
		
		i = i.replace("|", "")
		i = i.strip()
		film_director[ind] = i	
		


	
if __name__=="__main__":
	main()
	





