import Data.Char

stringToUpper :: String -> String
stringToUpper [] = ""
stringToUpper (h:t) = toUpper h: stringToUpper t
