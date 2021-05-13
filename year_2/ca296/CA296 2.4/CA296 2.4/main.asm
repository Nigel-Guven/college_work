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
message     BYTE	'Press OK to continue',0
caption     BYTE	'Welcome to CA296',0

.code
	main:nop

		invoke version
		STARTLOOP:

		invoke readInteger
		add eax,x
		cmp eax,0
		jl VERYFIN
		mov ebx,eax

		INNERLOOP:
		
		cmp y,ebx
		je FIN
		invoke writeInteger,y
		add y,1
		
		jmp INNERLOOP

		


		FIN:
		jmp STARTLOOP
		VERYFIN:
		invoke MessageBox,0,ADDR message,ADDR caption,MB_OK
		invoke ExitProcess,0

	end main
