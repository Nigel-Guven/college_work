;
; CA296 Console Template
; Version 1
;

;
; Preamble
;

.586
.model flat,stdcall
.stack 4096
option casemap:none

include     p:\masm32\include\windows.inc
include     p:\masm32\include\kernel32.inc
include		p:\masm32\include\user32.inc
include		p:\masm32\include\msvcrt.inc
include		p:\masm32\include\ca296.inc

includelib p:\masm32\lib\ca296.lib
includelib kernel32.lib
includelib user32.lib
includelib msvcrt.lib


;
; Data & Code
;

LinkedList STRUCT
	value SDWORD 0
	next	DWORD	0
LinkedList ENDS


.data
message     BYTE	'Press OK to continue',0
caption     BYTE	'Welcome to CA296',0


array		DWORD	50 DUP(0)
y			DWORD	20,21,22,23
userInput	DWORD	0
inputCount	DWORD	0

tempMin		DWORD	0
searchIndex	DWORD	1

minValue	DWORD	0
minFlag		DWORD	0

newMinAddy	DWORD	0
oldMinAddy	DWORD	0

oldMinValue	DWORD	0

numOfNode	DWORD	0
nodeNext	DWORD	0
nodeValue	DWORD	0


.code
	main:nop

		invoke version

takeInput:
		invoke readInteger
		mov userInput, eax

		cmp eax, 0
		jl startSort

		
		mov edx, TYPE userInput		;put values in array
		mov eax, inputCount
		mul edx
		add eax, OFFSET array
		mov ebx, userInput	
		mov	[eax], ebx


		inc inputCount		;used to track the index of array
		jmp takeInput

startSort:
		
		mov minFlag, 0			; used to check if we found new min
		

		mov ebx, inputCount
		dec ebx
		cmp tempMin, ebx
		je finSort						;if tempMin index is the same as length of array, we are done  
		
		mov edx, TYPE userInput			;set the smallest value to searchIndex of array
		mov eax, tempMin
		mul edx
		add eax, OFFSET array
		mov oldMinAddy, eax				;min address
		mov ebx, [eax]
		mov minValue, ebx

		;invoke writeInteger, minValue
		inc tempMin						;increment for next sort

		mov eax, tempMin
		mov searchIndex, eax
		
continueSort:
		;set searchIndex as 1 index up from (tempMin-1)
		;and start comparing
		mov edx, TYPE userInput
		mov eax, searchIndex 
		mul edx
		add eax, OFFSET array
		mov ebx, [eax]


		cmp minValue, ebx
		jg changeMin

		inc searchIndex
		mov ebx, inputCount
		cmp searchIndex, ebx				;if searchIndex is end of array,  we need to start again
											;but first swap new Min if found
		je swapper
		jmp continueSort

changeMin:
		;found a smaller value
		inc minFlag

		;update minValue and continue searching until end of array
		mov minValue, ebx
		
		
		mov edx, TYPE userInput		;save address of the newMin value
		mov eax, searchIndex 
		mul edx
		add eax, OFFSET array
		mov newMinAddy, eax			;swap the value at this address with old min 

		inc searchIndex
		mov ebx, inputCount
		cmp searchIndex, ebx			;if searchIndex is end of array,  we need to start again
											;but first swap new Min if found
		je swapper
		jmp continueSort

swapper:
		cmp minFlag, 0
		je startSort

		;else make the swap
		;then jump to startSOrt

		mov eax, oldMinAddy
		mov ebx, [eax]			;what was old min value?
				
		mov eax, newMinAddy		;new minAddy
		mov [eax], ebx			;mov old min into new addy

		mov eax, oldMinAddy
		mov ebx, minValue
		mov [eax], ebx

		jmp startSort


finSort:
		mov	eax, inputCount		;creat nodes from values of array back to front
		mov numOfNode, eax

createNodes:
		
		dec numOfNode
		mov eax, numOfNode		;getting the values from sorted array
		mov	edx, TYPE DWORD
		mul edx
		add eax, OFFSET array
		mov ebx, [eax]
		mov nodeValue, ebx

		invoke allocate, SIZEOF LinkedList

		mov ebx, nodeValue
		mov [eax].LinkedList.value, ebx

		mov ebx, nodeNext
		mov [eax].LinkedList.next, ebx

		mov nodeNext, eax		;address of current node, so we can link to nextNode

		;dec numOfNode

		mov ebx, inputCount
		cmp numOfNode, 0
		je writer
		jmp createNodes


writer:
		mov eax, nodeNext
		mov numOfNode, eax

printLL:
		cmp numOfNode, 0
		je	printListEnd

		mov eax,numOfNode
		mov eax,[eax].LinkedList.value
		invoke writeInteger,eax

		mov eax,numOfNode
		mov eax,[eax].LinkedList.next
		mov numOfNode,eax

		jmp printLL



printListEnd:
		

		
		invoke MessageBox,0,ADDR message,ADDR caption,MB_OK
		invoke ExitProcess,0

	end main
