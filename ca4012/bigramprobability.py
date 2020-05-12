#!usr/bin/python

import sys
import codecs

def readFile(input_file):
    with codecs.open(input_file, 'r') as corpus:
        return corpus.readlines()
        
def getBigramsFromCorpus(input_file):
    corpus = readFile(input_file)
    bigramCL = []
    
    for sentence in corpus:
        for i in range(len(sentence.split())-2+1):
            bigramCL.append(sentence.split()[i:i+2])
    
    return bigramCL
    
def getBigramsFromSentence(sent):
    bigramSL = []
    
    for i in range(len(sent.split())-2+1):
        bigramSL.append(sent.split()[i:i+2])
    
    return bigramSL
    
def getVocab(input_file):
    
    wordDict = {}
    corpus=readFile(input_file)
    for sentence in corpus:
        for i in range(len(sentence.split())):
            if sentence.split()[i] in wordDict:
                wordDict[sentence.split()[i]]+=1
            else:
                wordDict[sentence.split()[i]]=1
    return wordDict
    
    
def bigramP(sent, input_file):

    bigramsSentence = getBigramsFromSentence(sent)
    bigramsCorpus = getBigramsFromCorpus(input_file)
    
    totalBigramProb = 1
    for bigramS in bigramsSentence:
        bigramCount = bigramsCorpus.count(bigramS)
        totalCount = 0
        for bigramC in bigramsCorpus:
            if bigramS[0] == bigramC[0]:
                    totalCount += 1
        totalBigramProb*= float(bigramCount)/float(totalCount)
        
    print(totalBigramProb)
    
        
def bigramPSmoothing(sent, input_file):

    bigramsSentence = getBigramsFromSentence(sent)
    bigramsCorpus = getBigramsFromCorpus(input_file)
    
    vocabDict = getVocab(input_file)
    vocabCount = len(vocabDict)
    
    
    totalBigramProb = 1
    for bigramS in bigramsSentence:
        bigramCount = bigramsCorpus.count(bigramS)
        totalCount = 0
        for bigramC in bigramsCorpus:
            if bigramS[0] == bigramC[0]:
                    totalCount += 1
        totalBigramProb*= float(bigramCount+1)/float(totalCount+vocabCount)
        
    print(totalBigramProb)
       
def main():
    
    try:

        input = sys.argv[1]
    
        bigramP(input,"corpus3.txt")
    
    except IndexError:
        print("Please write input string")

if __name__ == "__main__":
    main()