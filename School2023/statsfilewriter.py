import csv
#in class program 02/28/2023

y = []
def slopefunctioncalculator() : 
    for i in range(1, 100, +1) :
        y.append((i, (2*i+4)))

file = open("function_csv.csv", "w", newline='')
writer = csv.writer(file)
slopefunctioncalculator()
for i in range(0, len(y)) :
    writer.writerow(y[i])
file.close 