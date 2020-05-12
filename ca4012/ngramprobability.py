#!usr/bin/python

import sys
import codecs

def readFile(input_file):

    with codecs.open(input_file, 'r') as corpus:
        return corpus.readlines()
        
def getNgramsFromCorpus(input_file,num):
    corpus = readFile(input_file)
    ngramCL = [] 
    
    for sentence in corpus:
        for i in range(len(sentence.split())-num+1):
            ngramCL.append(sentence.split()[i:i+num])
    
    return ngramCL
    
def getNgramsFromSentence(sent,num):
    ngramSL = []
    
    for i in range(len(sent.split())-num+1):
        ngramSL.append(sent.split()[i:i+num])
    
    return ngramSL
    
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
    
def nGramsPSmoothing(sent, input_file,num):

    ngramsSentence = getNgramsFromSentence(sent, num)
    ngramsCorpus = getNgramsFromCorpus(input_file, num)
    
    vocabDict = getVocab(input_file)
    vocabCount = len(vocabDict)
    
    
    totalNGramProb = 1
    for ngramS in ngramsSentence:
        ngramCount = ngramsCorpus.count(ngramS)
        totalCount = 0
        for ngramC in ngramsCorpus:
            if ngramS[0] == ngramC[0]:
                    totalCount += 1
        totalNGramProb*= float(ngramCount+1)/float(totalCount+vocabCount)
        
    print(num , "-gram:" , totalNGramProb)
    
def main():
    
    try:
        input = sys.argv[1]
    
        nGramsPSmoothing(input,"corpus2.txt",1)
        nGramsPSmoothing(input,"corpus2.txt",2)
        nGramsPSmoothing(input,"corpus2.txt",3)
        nGramsPSmoothing(input,"corpus2.txt",4)
    
    except IndexError:
        print("Please write input string")

if __name__ == "__main__":
    main()