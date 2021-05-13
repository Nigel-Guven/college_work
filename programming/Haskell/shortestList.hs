shortestList :: [[a]] _> [a]

shortest [] = []
shortest [onearray] = onearray
shortest(x:xs) = cmpTwo x (shortest xs)

cmpTwo :: [[a]] -> [[a]] -> [[a]]
cmpTwo first second =
	if length first < length second
	then first
	else second 
