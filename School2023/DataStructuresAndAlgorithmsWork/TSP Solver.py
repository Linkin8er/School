import sys
import random
import math

#this goes through and reads a file so that we can make a list of touples of the locations
def fileReader(fileName) :

    file = open(fileName, "r")
    locationList = []

    #First, we go through and find the dimension of the dataset
    dimensionFound = False
    while(not dimensionFound) :
        checker = file.readline()
        checkerSplit = checker.split()
        if (checkerSplit[0] == "DIMENSION:"):
            size = int(checkerSplit[1])
            dimensionFound = True

    #then, there are two lines we can ignore, before we get to the data
    file.readline()
    file.readline()

    #finally, we will go through the remaining lines, adding their locations to the list
    for i in range(size) :
        checker = file.readline()
        checkerSplit = checker.split()
        locationList.append((int(checkerSplit[0]), float(checkerSplit[1]), float(checkerSplit[2])))

    #always close your files!
    file.close()
    return locationList

def hillClimber(list):
    solutionPath = []
    cityList = list

    for i in range(len(list)) :
        randomCity = cityList[random.randint(0, len(cityList)-1)]
        solutionPath.append(randomCity)
        cityList.remove(randomCity)
    print(solutionPath)
    print(lengthCalculator(solutionPath))
    return solutionPath

def lengthCalculator(path) :
    distance = 0
    for i in range(len(path) - 1) :
        distance += round(math.sqrt((path[i][1]-path[i][1])*(path[i][1]-path[i][1]) + (path[i][2]-path[i][2])*(path[i][2]-path[i][2])))
    distance += round(math.sqrt((path[0][1]-path[len(path)-1][1])*(path[0][1]-path[len(path)-1][1]) + (path[0][2]-path[len(path)-1][2])*(path[0][2]-path[len(path)-1][2])))
    return distance
        
def getNeighbours(solution):
    neighbours = []
    for i in range(len(solution)):
        for j in range(i + 1, len(solution)):
            neighbour = solution.copy()
            neighbour[i] = solution[j]
            neighbour[j] = solution[i]
            neighbours.append(neighbour)
    return neighbours


if __name__ == "__main__" :
    fileName = sys.argv[1]
    locationList = fileReader(fileName)
    hillClimber(locationList)
