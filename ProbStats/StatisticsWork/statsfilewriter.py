import csv
#in class program 02/28/2023


def slopefunctioncalculator() :
    y = [] 
    for i in range(1, 100, +1) :
        y.append((i, (2*i+4)))
    return y

file = open("function_csv", "w")
writer = csv.writer(file)
writer.writerow((slopefunctioncalculator()))
file.close 