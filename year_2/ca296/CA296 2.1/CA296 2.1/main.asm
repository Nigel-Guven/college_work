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
x			DWORD	0
y			DWORD	0
message     BYTE	'End Question',0
caption     BYTE	'Welcome to CA296',0

.code
	main:nop

		invoke version
		invoke readInteger
		mov x,eax
		
		mov ebx,x
		invoke readInteger
		mov y,eax
		cmp eax,ebx
		jge Great
		jle Less

Great:
		invoke writeInteger,eax
		invoke MessageBox,0,ADDR message,ADDR caption,MB_OK
		invoke ExitProcess,0

Less:
		invoke writeInteger,ebx
		invoke MessageBox,0,ADDR message,ADDR caption,MB_OK
		invoke ExitProcess,0

	end main
