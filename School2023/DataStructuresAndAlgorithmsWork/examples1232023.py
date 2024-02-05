#1/23/2023 in class examples

import math


def larger(a, b) : 
    if a > b :
        return a
    else :
        return b

def min_value(a) :
    m = math.inf
    for i in range(len(a)) :
        if a[i] < m :
            m = a[i]
    return m

def min_value2(a) :
    m = math.inf
    for x in a :
        if x < m :
            m = x
    return m

def is_prime(n) :
    if n < 2 :
        return False
    for m in range(2, math.floor(math.sqrt(n))+1) :
        if n % m == 0 :
            return False
    return True

def is_prime2(n) :
    return n>=2 and all(n%m != 0 for m in range(2, math.floor(math.sqrt(n))+1))

t = (2, 3, 4, 5) #this makes a tuple! tuples can not be changed at all once defined. 
#useful becuase python can optimize a bit behind the scenes becuase it knows it will always be ___ things

