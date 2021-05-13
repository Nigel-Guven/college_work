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

LinkedList STRUCT	
	value SDWORD 0
	next	DWORD	0
LinkedList ENDS

;
; Data & Code
;

.data
x			DWORD	50 DUP(0)
y			DWORD	20,21,22,23
message     BYTE	'Press OK to continue',0
caption     BYTE	'Welcome to CA296',0
index		DWORD	0
array		DWORD	0


testIndex	DWORD	0
leng		DWORD	0


list DWORD	0
temp	DWORD	0
value	DWORD	0
nodes	DWORD	0

.code
	main:nop

		invoke version

values:
		
		mov edx, TYPE DWORD		;getting the current index address in x
		mov eax, index			;getting the current index address in x
		mul edx					;getting the current index address in x
		add eax, offset x		;getting the current index address in x
		mov array, eax			;store it on array


		invoke readInteger
		mov y, eax				;save user input in y

		cmp y, 0				;check if negative
		jl createL				;if so start creating linklist


		cmp index, 0			;only first value is for sure unique, so test index for 0!
		jg init


		mov ebx, array			;putting uniques value in array
		mov eax, y				;putting uniques value in array
		mov [ebx], eax			;putting uniques value in array

		inc index				;index for next Unique value
		jmp values


init:
		mov testIndex, 0
		mov leng, length x		;length of arrray to search

	
isUnique?:
		mov edx, TYPE DWORD		;getting the values of the array
		mov eax, testIndex		;getting the values of the array
		mul edx					;getting the values of the array
		add eax, offset x		;getting the values of the array

		mov ebx, [eax]			;getting the values of the array
		cmp ebx, y				;compare the value with user input
		je values				;not unique then!

		inc testIndex			;check index to length of array
		mov ebx, length x		;check index to length of array
		cmp testIndex, ebx		;check index to length of array
		je uniqueValues			;if at the end of array, value is unique so save it
		jmp isUnique?

uniqueValues:	
		

		mov ebx, array			;write the unique value into array
		mov eax, y				;write the unique value into array
		mov [ebx], eax			;write the unique value into array

		inc index				;next index to write to
		jmp values


createL:
		mov	ebx, index
		mov nodes, ebx

		mov list, 0
		mov index, 0

createNextNode:
		mov eax, index			;getting the values from array
		mov ebx, TYPE DWORD		;getting the values from array
		mul ebx					;getting the values from array
		add eax, offset x		;getting the values from array
		mov ebx, [eax]			;getting the values from array
		mov value, ebx

		invoke allocate, SIZEOF LinkedList

		mov ebx, value
		mov [eax].LinkedList.value, ebx

		mov ebx, list						;address pointer for next
		mov [eax].LinkedList.next, ebx		;address pointer for next

		mov list,eax		;address of current node

		inc index

		mov ebx, index
		cmp	nodes, ebx
		je	writer
		jmp createNextNode

		
writer:	

		mov eax, list
		mov temp, eax

printList:

		cmp temp,0
		je	printListEnd

		mov eax,temp
		mov eax,[eax].LinkedList.value
		invoke writeInteger,eax

		mov eax,temp
		mov eax,[eax].LinkedList.next
		mov temp,eax

		jmp printList

	printListEnd:
		;invoke writeInteger, index
		;invoke writeArray, offset x, 10
		
		invoke MessageBox,0,ADDR message,ADDR caption,MB_OK
		invoke ExitProcess,0

	end main
