#Nicholas Stephani, 09/19/2023


import pandas as pd
import matplotlib.pyplot as plt
from pandas.api.types import is_numeric_dtype

def dataAnalyzer(fileName) :

    #First we go through and create the table of information from the file needed. 
    data = pd.read_csv(fileName)
    data.columns = ['ID number', 'Clump Thickness', 'Uniformity of Cell Size', 'Uniformity of Cell Shape', 'Marginal Adhesion', 'Single Epithelial Cell Size', 'Bare Nuclei', 'Bland Chromatin', 'Normal Nucleoli', 'Mitoses', 'Class:']

    #Then we go through and print the mean, standard deviation, minimum, and maximum for each numerical dataset
    for col in data.columns:
        if (is_numeric_dtype(data[col]) and ((col != 'ID number') and (col != 'Class:'))):
            print('%s:' % (col))
            print('\t Mean = %.2f' % data[col].mean())
            print('\t Standard deviation = %.2f' % data[col].std())
            print('\t Minimum = %.2f' % data[col].min())
            print('\t Maximum = %.2f' % data[col].max())

    #Then we make the ID dumber and Class into objects
    data = data.astype({'ID number' : object})
    data = data.astype({'Class:' : object})

    #and count the frequency for the two
    print(data['ID number'].value_counts())
    print(data['Class:'].value_counts())

    #then, we print a congomeration of various data for all the datasets
    print(data.describe(percentiles= [], include = 'all'))
    
    #before finally creating a boxplot to better visuialize the data
    plt.plot(data['Clump Thickness'])
    plt.show()
    pass

if __name__ == "__main__" :
    print("Hello! This program reads a CSV file and helps display the information")
    #C:/SourceCode/Datamining/breast-cancer-wisconsin.data
    file = input("Please enter a valid file to read: ")
    dataAnalyzer(file)
    pass