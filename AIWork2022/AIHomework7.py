#Nicholas Stephani
import sys


#we need a mehtod that prints out a grid by looping throught the rows and columb, printing the values with a space
def gridPrinter(grid):
    for i in range(9):
        for j in range(9):
            print(grid[i][j],end = " ")
        print()

#this method will make the  soduko grid from a text file and returns said grid
def gridMaker(filename):
    file = open(filename, "r")

    #first we make the 9x9 matrix
    grid = []
    for i in range(9): 
        row=[] 
        for j in range(9): 
            row.append(0) 
        grid.append(row)

    #then we go line by line, getting the rows and adding them to the matrix
    for row in range(9):
        line = file.readline()
        line = line.split()
        for col in range(9):
            grid[row][col] = int(line[col])

    #Always close your files!
    file.close

    #then we can print the grid just to make sure it copies correctly
    gridPrinter(grid)
    print()

    #before finally returning the new grid
    return grid

#this is where we find the next number to work on and start trying solutions, using recursion and back tracking to check answeres
def sudukoSolver(grid, row, col):
# for this, we check the end cases first

    #first we check if we made it to the end 
    if (row == 8 and col == 9):
        return True
    
    #then we check if we made it to the end of a row, if so we start at th beginning of the next
    if col == 9:
        row += 1
        col = 0
    #then, if we reach a number already set, we skip over it 
    if grid[row][col] > 0:
        return sudukoSolver(grid, row, col + 1)
    

    #and finally we start the solve 
    for num in range(1, 10, 1): 
        #this tries to solve a spot with a number 1-10
        if legalChecker(grid, row, col, num):
            #if it finds a legal input, we save it
            grid[row][col] = num
            #and then move on to the next spot
            if sudukoSolver(grid, row, col + 1):
                return True
        #otherwise, we set to 0 and step back
        grid[row][col] = 0
    return False

#here is the real work horse of the code, where we run the checks to see if a number works   
def legalChecker(grid, row, col, num):

    #first we check if that number is already used in that row
    for y in range(9):
        if grid[row][y] == num:
            return False
             
    #and then we do the same for the colum
    for x in range(9):
        if grid[x][col] == num:
            return False
 

    #then we check the 3x3, and make sure out number isn't being used anywhere
    startingRow = row - row % 3
    startingCol = col - col % 3
    for i in range(3):
        for j in range(3):
            if grid[i + startingRow][j + startingCol] == num:
                return False
            
    #if all that works, then we move on, otherwise we would end up stepping back
    return True
 

if __name__ == "__main__" :

    #first we get the name of the file to solve
    print("What is the soduko file you would like to solve?")
    fileName = input()

    #then we take the file and make it into a workable matrix
    grid = gridMaker(fileName)

    #and then we begin the process of solving it with backtracking 
    if (sudukoSolver(grid, 0, 0)):
        gridPrinter(grid)
    else:
        #finally, if we setp back too far, then there is no solution.
        print("Solution does not exist:(")
    pass
 
