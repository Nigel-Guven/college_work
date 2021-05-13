
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
			return x;
		} else {
			return y;
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
		
		;move arguments into registers
		mov eax, [ebp+8]
		mov ebx, [ebp+12]
		
		;compare, jump
		cmp eax, ebx
		jl less
		jge more

		;move correct value into eax for after we return
	less: 
		mov eax, ebx
		jmp done
	
	more:
		mov eax, eax
		jmp done

	done:
		;leave stack frame
		pop ebp
		;return to where function was called from, 
		;remove the arguments from stack (you could just ret 8 too, no need for the macros)
		ret (2 * type dword)

	end 
