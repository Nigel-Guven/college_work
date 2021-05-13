
.586
.model flat,stdcall
option casemap:none

include     c:\masm32\include\windows.inc
include     c:\masm32\include\kernel32.inc
include		c:\masm32\include\user32.inc
include		c:\masm32\include\msvcrt.inc
include		c:\masm32\include\ca296.inc
EXTERNDEF displayMaximum:NEAR

COMMENT @
	void displayMaximum(int x,int y) {
		if (x >= y) {
			writeInteger(x);
		} else {
			writeInteger(y);
		}
	}
	@

.data
x			DWORD	0

.code

	displayMaximum:nop
		
		;create stack frame with no local vars
		push ebp
		mov ebp, esp
		
		; grab args into registers
		mov eax, [ebp+8]
		mov ebx, [ebp+12]
		
		;compare
		cmp eax, ebx
		jl less
		jge more

		;print the right one
	less: 
		invoke writeInteger, ebx
		jmp done
	
	more:
		invoke writeInteger, eax
		jmp done

	done:
		; exit stack frame
		pop ebp
		;return to where we were called from 
		;(pops return address from stack)
		;(removes 8 bytes worth of arguments from stack)
		ret 8

	end 
