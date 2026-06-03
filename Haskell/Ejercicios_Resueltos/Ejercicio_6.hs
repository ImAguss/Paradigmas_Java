mayor_lista :: [[a]] -> Int
mayor_lista [] = 0
mayor_lista lista = maximum ([ length(x) | x <- lista])
