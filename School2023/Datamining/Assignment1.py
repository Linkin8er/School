#Nicholas Stephani 
#10/01/2023


def search_For_Confidance(fileName) :

    file = open(fileName, "r")
    size = file.readlines()
    fileLength = len(size)
    file.close()

    file = open(fileName, "r")
    spamList = [] 

    for i in range (0, fileLength, +1) :
        currentLine = file.readline()
        currentLineSplit = currentLine.split()

        if len(currentLineSplit) != 0 :
            if currentLineSplit[0] == 'X-DSPAM-Confidence:':
                spamList.append(currentLineSplit[1])

    averageTotal = 0.0

    for i in range (0, len(spamList)) :
        averageTotal = averageTotal + float(spamList[i])

    file.close()
    return (averageTotal/len(spamList))

def list_Unique_Words(fileName) :

    file = open(fileName, "r")
    size = file.readlines()
    fileLength = len(size)
    file.close()

    file = open(fileName, "r")

    uniqueWords = []
    for i in range (0, fileLength, +1) :
        currentLine = file.readline()
        currentLineSplit = currentLine.split()

        #these two if statements look for a line starting with from
        if len(currentLineSplit) != 0 :
            for i in range (0, len(currentLineSplit)) :
                if not is_In_Checker(currentLineSplit[i], uniqueWords) :
                    uniqueWords.append(currentLineSplit[i])

    file.close()
    return (sorted(uniqueWords))
                    
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
                    
    emailList = list(emailCounter.items())
    emailList.sort(key = lambda i:i[1], reverse = True)
    
    #This finally prints the dictionary and closes the file
    return(emailList[0])
    file.close()

def is_In_Checker(value, list = []) :


    alreadyIn = False
    for i in range (0, len(list)) :
        if value == list[i] : alreadyIn = True

    return alreadyIn

if __name__ == "__main__" :
    print("Hello! This program reads a file and helps display the information. Would you like to\n1) Find the average confidance\n2) Alphabetically list unique words\n3) Read a mailbox for the most emails")
    choice = input("Please enter a number 1, 2, or 3: ")

    #C:\SourceCode\Datamining\mbox.txt
    if choice == "1" :
        file = input("Please enter a valid file to read: ")
        print("confidance is: " + str(search_For_Confidance(file)))

    #C:\SourceCode\Datamining\romeo.txt
    if choice == "2" :
        file = input("Please enter a valid file to read: ")
        print(list_Unique_Words(file))

    #C:\SourceCode\Datamining\mbox.txt
    if choice == "3" :
        fileChoice = input("Please enter a valid mail log file: ")
        print(dictionary_counter(fileChoice))
    
    pass