#!usr/bin/python#
from frequency import word_frequency
import collections

def unigramProbability(freq_list):

    sum = 1
    for i in freq_list:
        sum = i*sum
    
    print(sum)

def main():

    input_string = 'the cat sat on the mat with a cat'
    input_list = input_string.split()
    number_of_tokens = len(input_list)
    
    word_count_dict = collections.Counter(input_list)
    
    mylist = word_frequency(word_count_dict,number_of_tokens)

    unigramProbability(mylist)
        


if __name__ == "__main__":
	main()