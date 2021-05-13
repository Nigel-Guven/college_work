delFirst :: Eq a => a -> [a] -> [a]
delFirst x [] = [] 
delFirst x (head:tail) = if (x == head) 
						 then
						 tail
						 else
						 (head:(delFirst x tail))
