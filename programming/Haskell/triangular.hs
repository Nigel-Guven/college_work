triangle : Int -> Int

triangle 1 = 1
triangle n = n + triangle (n-1)
