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
z			DWORD	0
format		byte	'%d',0
plus		byte	' + ',0
equals		byte	' = ',0

message     BYTE	'Press OK to continue',0
caption     BYTE	'Welcome to CA296',0

.code
	main:nop

		invoke version
		invoke readInteger
		mov x,eax
		add z,eax
		invoke readInteger
		mov y,eax
		add z,eax
		invoke crt_printf,ADDR format,x
		invoke crt_printf,ADDR plus
		invoke crt_printf,ADDR format,y
		invoke crt_printf,ADDR equals
		invoke crt_printf,ADDR format,z


		invoke MessageBox,0,ADDR message,ADDR caption,MB_OK
		invoke ExitProcess,0

	end main
