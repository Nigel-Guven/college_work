count :: [a] -> Int
count [] = 0
count (_:xs) = 1 + count xs
