# Nicholas Stephani
# 09/12/2023 Lab 1 work

#This goes through and reads a file and prints its lines in uppercase line by line
def file_processor(fileName) :
    
    #first we find the length of the file
    file = open(fileName, "r")
    size = file.readlines()
    fileLength = len(size)
    file.close()

    #then we iterate through and print the file line by line in all caps
    file = open(fileName, "r")
    for i in range (0, fileLength, +1) :
        currentLine = file.readline()
        print(currentLine.upper())
    file.close()

#This goes through and finds the maximum and minimum of a given list of numbers
def list_functions(list) :
    print("The highest number of your list is: " + str(max(list)))
    print("The lowest number of your list is: " + str(min(list)))

#This goes through and reads a mail log, counting the number of messages from each address
def dictionary_counter(fileName) :
    
    #Making a dictionary
    emailCounter = {}

    #this section simply goes through and finds the length of the file
    file = open(fileName, "r")
    size = file.readlines()
    fileLength = len(size)
    file.close()

    x = 0

    #this outer loop goes through and iterates through the file splitting the lines by the spaces
    file = open(fileName, "r")
    for i in range (0, fileLength, +1) :
        currentLine = file.readline()
        currentLineSplit = currentLine.split()

        #these two if statements look for a line starting with from
        if len(currentLineSplit) != 0 :
            if currentLineSplit[0] == 'From:':
                
                #once we find that, if it is the first time that email has shown up we set it to 1, otherwise we increase it by 1
                if currentLineSplit[1] in emailCounter :
                    emailCounter[currentLineSplit[1]] = emailCounter[currentLineSplit[1]] + 1

                else :
                    emailCounter[currentLineSplit[1]] = 1
                    
    #This finally prints the dictionary and closes the file
    print(emailCounter)
    file.close()

#The main function goes through and prompts the user to chose what function they wish to use
if __name__ == "__main__" :
    
    #C:SourceCode\Datamining\mbox-short.txt
    print("Good day! Would you like to:\n1) Print an uppercase file?\n2) Find the max and min of a list\n3) read a mail log")
    choice = input("Please enter a number 1, 2, or 3: ")

    if choice == "1" : 
        fileChoice = input("Please enter a valid file to read: ")
        file_processor(fileChoice)

    if choice == "2" :
        list = []
        termination = 0
        print("Please enter the numbers in your list, and enter done when finished")
        while termination == 0 :
            addition = input("Please enter your number: ")
            if addition == "done" : termination = 1
            else : list.append(int(addition))
        list_functions(list)

    if choice == "3" :
        fileChoice = input("Please enter a valid mail log file: ")
        dictionary_counter(fileChoice)
    pass