smallest :: [Int] -> Int

smallest [x] = x
smallest (x:xs) = min x smallest(xs)
