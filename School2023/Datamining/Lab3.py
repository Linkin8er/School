#Nicholas Stephani, 09/19/2023


import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
from pandas.api.types import is_numeric_dtype

def dataManipulator(fileName) :

    #First we go through and create the table of information from the file needed. 
    data = pd.read_csv(fileName)
    data.columns = ['ID number', 'Clump Thickness', 'Uniformity of Cell Size', 'Uniformity of Cell Shape', 'Marginal Adhesion', 'Single Epithelial Cell Size', 'Bare Nuclei', 'Bland Chromatin', 'Normal Nucleoli', 'Mitoses', 'Class:']

    #Then we make the Class into  an object
    #data = data.astype({'Class:' : object})

    print(data.shape)

    data.drop(columns = ['ID number'], inplace=True)

    print(data.shape)

    data = data.replace('?', np.nan)

    print(data.isnull().sum().sum())

    for col in data.columns :
        missingValues = data[col].isnull().values.any()
        if(missingValues) :
            
            print(data[col][616])
            print(np.nan)

            for value in range(0, len(data[col])) :
                
                if np.isnan(float(data[col][value])) :
                    print(data[col].shape[0])
                    data = data.replace({col: np.nan}, {col : data[col].median()})
                    print(data.isnull().sum().sum())
                    print(data[col].shape[0])

    

if __name__ == "__main__" :
    print("Hello! This program reads a CSV file and helps display the information")
    #C:/SourceCode/Datamining/breast-cancer-wisconsin.data
    file = input("Please enter a valid file to read: ")
    dataManipulator(file)
    pass