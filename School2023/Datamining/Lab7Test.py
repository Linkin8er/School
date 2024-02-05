from sklearn.datasets import load_iris
from sklearn import tree
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

loans = pd.read_csv("https://s3-us-west-2.amazonaws.com/static-resources.zybooks.com/loans.csv")
y = loans.status
exp = loans.experience == "Y"
bool_val = np.multiply(exp, 1)
df = pd.DataFrame(bool_val, columns = ["experience"])
X = pd.concat([loans.amount, df], axis = 1)

clf = tree.DecisionTreeClassifier(min_samples_split = 4)
model = clf.fit(X, y)
tree.plot_tree(model, feature_names = X.columns, label = 'none', impurity = True, class_names = ["Risky", "Safe"], rounded = True, filled = True, proportion = True)
plt.show()