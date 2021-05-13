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

.data
lowlimit	DWORD	32		;0x20 in decimal
uplimit		DWORD	127		;0x7F in decimal
lineDiv		DWORD	0		;line divider counter

result		BYTE	'%c',0	;print as character to get ASCII symbol
newLine		BYTE	13,10,0	; ascii codes for carriage return and line feed. Printed as chars 

message     BYTE	'Press OK to continue',0
caption     BYTE	'Welcome to CA296',0

.code
	main:nop

		invoke version
		
Star:	invoke crt_printf, ADDR result, lowlimit 
		mov eax, uplimit        ;move upper limit to eax, compare against lower and jump to finish if ==
		cmp eax, lowlimit
		je Fin


		
		
		inc lowlimit					;move to next ascii char
		inc lineDiv				
		cmp lineDiv, 16
		jb Star							;if < 16 chars printed jump to start again, otherwise below
		
		invoke crt_printf, ADDR newLine ;print newline out using ascii codes
		mov lineDiv, 0					;reset line counter to zero
	
		jmp Star

Fin:	invoke MessageBox,0,ADDR message,ADDR caption,MB_OK
		invoke ExitProcess,0

		

	end main
