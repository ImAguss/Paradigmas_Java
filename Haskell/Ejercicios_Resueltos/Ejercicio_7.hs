cubo_lista ::(Num a) => [a] -> [a]
cubo_lista lista = [x * x * x| x <- lista]
