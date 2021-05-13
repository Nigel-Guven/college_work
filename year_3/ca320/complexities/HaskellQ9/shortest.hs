shortest :: [[a]] -> [a]

shortest []       = []
shortest [xs]     = xs
shortest (xs:xss) = let ys = shortest xss
                    in  if length xs < length ys then xs else ys
