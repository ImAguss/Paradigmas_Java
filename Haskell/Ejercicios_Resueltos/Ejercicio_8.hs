recursiva :: [a] -> [a]
recursiva [] = []
recursiva (x:xs)
  | elem x xs = recursiva xs 
  | otherwise = x:recursiva xs
