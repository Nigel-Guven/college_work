maximum :: [Int] -> Int
maximum [x] = x
maximum (x:xs) 
 | (maximum xs) > x = maximum xs
 | otherwise = x