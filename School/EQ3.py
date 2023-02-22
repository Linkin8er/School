def implies_function(x,y):
    
    if (x and not y):
        return print ((str(x) + "\t" + str(y) + "\tFalse"))
    else:
        return print(str(x) + "\t" + str(y) + "\tTrue")

def OR_function(x,y):
    if (not x or y):
        return print(str(x) + "\t" + str(y) + "\t" + str(not x) + "\tTrue")
    else:
        return print(str(x) + "\t" + str(y) + "\t" + str(not x) + "\tFalse")

def prints_function():
    print("The table for x implies q is:")
    print ("p\tq\tout-put")
    implies_function(True, True)
    implies_function(True, False)
    implies_function(False, True)
    implies_function(False, False)
    print()
    print("The table for NOT p OR is:")
    print ("p\tq\tNOT p\tout-put")
    OR_function(True, True)
    OR_function(True, False)
    OR_function(False, True)
    OR_function(False, False)

def main():
    prints_function()

main()