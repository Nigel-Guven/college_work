:- set_prolog_flag(double_quotes, chars).

lcp([], []).
lcp([H], H).
lcp([H1,H2|T], P) :-
    maplist(append(P), L, [H1,H2|T]),
    (   is_empty(L) ;   maplist(head, L, Hs),
        compare_chars_i(Hs)
    ).

is_empty([[]|_]).
is_empty([[_|_]|T]) :-
    is_empty(T).

head([H|_], H).

compare_chars_i(L) :-
    (   member(H1, L), member(H2, L), H1 \= H2 -> true ;   list_to_set(L, S),
        compare_chars_ii(S)
    ). 
compare_chars_ii([H|T]) :-
    (   member(H1, T), dif(H, H1) ;   compare_chars_ii(T)).