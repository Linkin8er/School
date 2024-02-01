#Nicholas Stephani 10/12/2023
import csv

#This goes through and reads a csv file and counts the number of each item in the file. 
def reader(filename):
    item_count = {}
    
    #first we open the file and read it with a csv_reader
    file = open(filename, 'r', newline='')
    csv_reader = csv.reader(file)
            
    #then we go through the csv_reader line though line
    for row in csv_reader:
        #we have to make sure there is actually a row and it is not empty
        if row:  
            #if so, we go through each item in the rows
            for i in range(0, len(row), +1):
                item = row[i]
                #if the item is already in the dictionary, we increase the count
                if item in item_count:
                    item_count[item] += 1
                #otherwise, it must be new so we create a new item at 1
                else:
                    item_count[item] = 1
    
    #finally we sort the list from highest count to lowest
    sorted_items = sorted(item_count.items(), key=lambda x: x[1], reverse=True)

    return sorted_items

if __name__ == '__main__':
    
    print("Hello! This program reads a csv file and helps display the information. Will you please input a valid CSV file?")

    #C:\SourceCode\Datamining\groceries.csv
    filename = input("Please enter a valid file to read: ")
    sorted_items = reader(filename)
    
    print("The shopping trips have included:")
    for item, count in sorted_items:
        print(f"{item}: {count}")
