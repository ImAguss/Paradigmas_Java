convertir :: Int -> [Int]
convertir 0 = []
convertir 1 = [1]
convertir x = mod x 2 : convertir (div x 2)
