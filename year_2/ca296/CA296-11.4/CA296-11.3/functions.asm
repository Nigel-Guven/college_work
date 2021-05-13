
.586
.model flat,stdcall
option casemap:none

include     c:\masm32\include\windows.inc
include     c:\masm32\include\kernel32.inc
include		c:\masm32\include\user32.inc
include		c:\masm32\include\msvcrt.inc
include		c:\masm32\include\ca296.inc
EXTERNDEF fibonacci:NEAR

COMMENT @
	int fibonacci(int n) {
		if (x<2){return x;};
		int var1 = fibonacci(n-1);
		int var2 = fibonacci(n-2);
		return var1 + var2;
	}
	@

.data
x			DWORD	0

.code

	fibonacci:nop
		
		;int fib(int n)
		push ebp
		mov ebp, esp
		sub esp, 8 ; create stack space for two dword variables or 8 bytes
		mov eax, DWORD PTR [ebp+8] ; load first argument into register
		
		;if(n<2){return n}
		;fib(0) = 0, fib(1) = 1, so eax is already the correct value
		cmp eax, 2
		jl done 

		mov ecx, DWORD PTR [ebp+8] ;move first argument into ecx
		sub ecx, 1
		push ecx
		call fibonacci
		mov [ebp-4], eax ;store fib(n-1) in local var 1

		mov ecx, DWORD PTR [ebp+8] ;move argument into ecx
		sub ecx, 2
		push ecx
		call fibonacci
		mov [ebp-8], eax ;store fib(n-2) in local var 2

		mov eax, [ebp-4] ;calculate fib(n) = fib(n-1) + fib(n-2)
		add eax, [ebp-8] ; leave in eax as return value
		jmp done

		;cleanup code for before we return
	done:
		add esp, (2 * type dword);destroy local vars
		pop ebp ; leave stack frame
		ret 4; return, destroying the argument (by popping a dword/4bytes from stack) as we do

	end 
