def Cartesian(A, B):
    combined = []
    for x in A:
        for y in B:
            cartesian = (x,y)
            combined.append(cartesian)
    return combined

def tester():
    A = ['a','b','c']
    B = [1,2]
    print("The cartesian set of A and B will be " + str(Cartesian(A,B)))

tester()