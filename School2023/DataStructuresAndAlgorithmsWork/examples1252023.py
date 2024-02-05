#in class 01/25/2023

x = 5
y = 10
s = "x = {0} and y = {1}".format(x, y)
print(s) #Prints x = 5 and y = 10
z = 2.5
s2 = "z = {0:.4f}".format(z)
print(s2) #Prints 2.5000, or z to 4 decimal points
s3 = f"x = {x}" #Prints x = 5

#you can also index a string with s[x], where s is the string name and x is the index. 
#you can also use a negative index to start at the end of a sting.

s = "hello world"
s2 = s[6:11] #slices the sting and makes s2 = world
s3 = s[:5] #this slices everythign before index 5
s4 = s[6:] #this slices everything after index 6
print(s)
print(s2)
print(s3)
print(s4)


x = [1, 2, 3, 4]
y = [5, 6, 7, 8]
z = x + y
print(z) #prints 1, 2, 3, ... 6, 7, 8

z[1:3] = [0, 0, 0, 0] #slices and replaces index 1 and 2 with 0000 
print(z)

#lists are good for stacks, using append and pop (FiFo)

stack = []
stack.append(3)
stack.append(6)
print(stack)
x = stack.pop()
print(x)
print(stack)

x = [2, 4, 6, 8]
y = x.pop(0)
print(y)
print(x)

#deque is better for queue (shocker)

import collections
from collections import deque #can import specific things with this

q = deque()
print(q)

#append will add to the right, appendleft will add to the left
#pop will pop from right, while popleft will pop from the left

q.append(5)
q.append(7)
q.append(18)
print(q)
x = q.popleft() 
print (x)
print(q)

import math
########################################################################
def is_prime(n) :
    if n < 2 :
        return False
    for m in range(2, math.floor(math.sqrt(n))+1) : # from in class 01/23/2025
        if n % m == 0 :
            return False
    return True
########################################################################
primes = []
for i in range (2,100) :
    if is_prime(i) :
        primes.append(i)
print(primes) #prints the first 100 primes

primes = [ i for i in range(2,100) if is_prime(i)] #does the same as above in less lines
print(primes)

# if we include a third number in range, that will be the increment.  f/e
y = [i for i in range(1, 1000, 7)]
print(y) #this will print every seventh number from 1 - 1000 starting at 1

#we can also count down with negative numbers. -1 will count down by 1, but higher number needs to come first


