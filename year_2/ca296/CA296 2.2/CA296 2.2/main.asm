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
zeroey		BYTE	' is Zero		',0
posity			BYTE	' is Positive	',0
negaty			BYTE	' is Negative	',0
	
message     BYTE	'Press OK to continue',0
caption     BYTE	'Welcome to CA296',0

.code
	main:nop

		invoke version
		invoke readInteger
		add eax,x
		
		cmp eax,0
		je ISZERO
		jg ISPOS
		jl ISNEG

		ISZERO:
		invoke writeInteger,eax
		invoke crt_printf,ADDR zeroey
		invoke MessageBox,0,ADDR message,ADDR caption,MB_OK
		invoke ExitProcess,0

		ISPOS:
		invoke writeInteger,eax
		invoke crt_printf,ADDR posity
		invoke MessageBox,0,ADDR message,ADDR caption,MB_OK
		invoke ExitProcess,0

		ISNEG:
		invoke writeInteger,eax
		invoke crt_printf,ADDR negaty
		invoke MessageBox,0,ADDR message,ADDR caption,MB_OK
		invoke ExitProcess,0

	end main
