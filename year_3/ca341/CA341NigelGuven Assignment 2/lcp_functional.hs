lcp :: Eq String => [[String]] -> [Char]
lcp [] = []
lcp list = foldr1 isCommon list

isCommon :: Eq String => [String] -> [String] -> [Char]
isCommon (x:xs) (y:ys)
 | x==y = x:isCommon xs ys
isCommon _ _ = []
