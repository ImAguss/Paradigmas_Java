pares :: (Integral a) => [a] -> Integer
pares [] = 0
pares (x:xs)
  | even x = 1 + pares xs
  | otherwise = pares xs
