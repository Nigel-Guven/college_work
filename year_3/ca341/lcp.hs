lcp :: Eq a => [[a]] -> [a]
lcp [] = []
lcp list = foldr1 isCommon list

isCommon :: Eq a => [a] -> [a] -> [a]
isCommon (x:xs) (y:ys)
 | x==y = x:isCommon xs ys
isCommon _ _ = []
