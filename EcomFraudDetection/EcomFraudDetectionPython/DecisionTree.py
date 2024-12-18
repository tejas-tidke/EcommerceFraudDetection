# Load libraries
import pandas as pd 
from sklearn.ensemble import DecisionTreeClasifier
from sklearn.model_selection import train_test_split # Import train_test_split function
from sklearn import metrics #Import scikit-learn metrics module for accuracy calculation
from DBOperations import *
from sklearn.metrics import precision_score, recall_score, f1_score, accuracy_score
def classification(userid='NA'):
    col_names = ['login_attempts','login_time','editProfile','ProfileAuth','ProfileAuthTime','PassRecovery','product_range','amt','addrchanged','email_changed','shopamt','label']
    # load dataset
    pima = pd.read_csv("dataset.csv", header=0, names=col_names)
    #split dataset in features and target variable
    feature_cols = ['login_attempts','login_time','editProfile','ProfileAuth','ProfileAuthTime','PassRecovery','product_range','amt','addrchanged','email_changed','shopamt']
    X = pima[feature_cols] # Features
    y = pima.target # Target variable
    # Split dataset into training set and test set
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=1) # 70% training and 30% test
    # Create Decision Tree classifer object
    clf = DecisionTreeClasifier()

    # Train Decision Tree Classifer
    clf = clf.fit(X_train,y_train)
    y_pred = clf.predict(X_test)
    print("Accuracy:",metrics.accuracy_score(y_pred, y_test))
    print('Recall: %.3f' % recall_score(y_test, y_pred))
    	
    print('Precision: %.3f' % precision_score(y_test, y_pred))
    print('F1 Score: %.3f' % f1_score(y_test, y_pred))
    #Z_test=[[0,0,0,1,1,0,1,1,1,0,0,0,0,0,0,0,1,1]]
    Z_test=  [getReadings(userid)]
    #Predict the response for test dataset
    print(Z_test)
    # Model Accuracy, how often is the classifier correct?
    
    y_pred = clf.predict(Z_test)
    print(y_pred)
    return y_pred
  
#classification([1, 32, 1, 0, '1', '0', '0', 196.66666666666666, 53.68647100930565, '0'])
#classification('abc')