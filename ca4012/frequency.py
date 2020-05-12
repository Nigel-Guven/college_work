#!usr/bin/python
import collections



def unigramCount(corpus):

    words_and_counts = {}
    totalWords = 0
    
    for word in corpus.split():
        totalWords += 1
        
        if word in words_and_counts:
            words_and_counts[word] += 1
        else:
            words_and_counts[word] = 1
    
    return words_and_counts, totalWords
    
def unigramProbability(input,corpus):

    words_and_counts, totalWords = unigramCount(corpus)
    totalProb = 1
    
    for w in input.split():
        if w in words_and_counts:
            totalProb *= words_and_counts[w]/totalWords
            
    return totalProb

    
def word_frequency(word_dict,num_tokens):
    
    
    for key,value in word_dict.items():
        print('The word',key,'frequency is: ', float(value)/num_tokens)


def main():

    inputFile = open("corpus.txt", "r")
    
    input_string = inputFile.readlines()
    print(input_string)
    for line in input_string:
        line = line.strip()
        
        print(unigramProbability("a cat sat on the mat",line2))


if __name__ == "__main__":
	main()
    