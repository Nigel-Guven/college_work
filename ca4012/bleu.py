#!usr/bin/python

import sys
import math


def ngram(string,n):
    string = string.split(' ')
    output = []

    for i in range(len(string)-n+1):
        output.append(string[i:i+n])
    return output

def commonality(list1,list2):
    precision = 0

    for element in list1:
        if element in list2:
            precision += 1
    return precision 

def addToList(list1,list2):

    for element in list2:
        list1.append(element)


def main():
    try:
        translation = open("translation.txt", "r+")
        reference = open("reference.txt","r+")



        trans_contents = translation.read()
        ref_contents = reference.read()
        
        print("-------------------------")
        print(ngram("Homer ate the donut",1))
        print(ngram("Homer ate the donut",2))
        print(ngram("Homer ate the donut",3))
        print(ngram("the donut Homer ate",1))
        print(ngram("the donut Homer ate",2))
        print(ngram("the donut Homer ate",3))
        print("-------------------------")
        
        trans_list_one = ngram(trans_contents,1)
        trans_list_two = ngram(trans_contents,2)
        trans_list_thr = ngram(trans_contents,3)
        trans_list_for = ngram(trans_contents,4)

        ref_list = ngram(ref_contents,1)
        addToList(ref_list,ngram(ref_contents,2))
        addToList(ref_list,ngram(ref_contents,3))
        addToList(ref_list,ngram(ref_contents,4))

        onegram = float(commonality(trans_list_one,ref_list)/float(len(trans_list_one)))
        twogram = float(commonality(trans_list_two,ref_list)/float(len(trans_list_two)))
        threegram = float(commonality(trans_list_thr,ref_list)/float(len(trans_list_thr)))
        #fourgram = float(commonality(trans_list_for,ref_list)/float(len(trans_list_for)))
        precision = onegram*twogram*threegram #*fourgram
        avg= float((len(ref_contents)))/4.0
        brevity = min(1,(float(len(trans_contents)/float(avg))))
        healy = math.pow(precision, 1.0/4) * brevity
        
        print(healy)
        
    except IndexError:
        print("Please type two filenames.")


if __name__ == "__main__": 
    main()
