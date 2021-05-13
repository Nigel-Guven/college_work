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

include     c:\masm32\include\windows.inc
include     c:\masm32\include\kernel32.inc
include		c:\masm32\include\user32.inc
include		c:\masm32\include\msvcrt.inc
include		c:\masm32\include\ca296.inc

includelib c:\masm32\lib\ca296.lib
includelib kernel32.lib
includelib user32.lib
includelib msvcrt.lib

;
; Data & Code
;

.data
x			DWORD	0
fstr		BYTE	"%+d",0

.code
	main:nop
	    invoke version
		invoke readRPN ;read a valid RPN string
		mov x, eax ;save original string address

	 ;go through string and do one of 3 ops for each character, push a digit, add 2 stack items into one, or sub 2 stack items
	evalLoop:
		mov ebx, 0; reset ebx (needed)

		mov bl, [eax] ; move one byte (char) into ebx (bl register is 8 bit and linked to the bottom 8 bits of ebx)
		cmp ebx, 0 ; check for end of string (0/null byte)
		je endEvalLoop ; if the string is over break loop

	digit:
		cmp ebx, "0" ; Compare to char '0', If it's greater/equal on the ascii table assume it's a digit
		jl enddigit ; otherwise it's + or -
			sub ebx, "0" ; get literal value of digit, digits go in order on the ascii table starting at "0"(48)
			push ebx ; push dword containing our number onto stack
		jmp opdone ;if we have done an op we can jump to the end
	enddigit:

	plus:
		cmp ebx, "+" ; if it's an add op, pop two values and add, push result
		jne endplus ; otherwise don't
			pop ecx
			pop edx
			add ecx, edx
			push ecx 
		jmp opdone
	endplus:
	
	minus:
		cmp ebx, "-" ; if it's a sub op, pop two values and sub, push result
		jne endminus ; otherwise don't
			pop edx
			pop ecx
			sub ecx, edx
			push ecx 
		jmp opdone
	endminus:

	opdone:
		inc eax ; move along 1 byte in string
		jmp evalLoop ; loop

	endEvalLoop:

	pop eax ; final result
	invoke crt_printf, offset fstr, eax ; print with sign

	invoke crt_getchar ; pause
	invoke ExitProcess, 0

	end main
