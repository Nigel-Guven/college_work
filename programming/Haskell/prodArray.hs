prodArray :: [A] -> A

prodArray [] = 0
prodArray x  = x
prodArray (x:xs) = x * prodArray xs
