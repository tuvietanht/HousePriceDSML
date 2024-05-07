import pickle
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.metrics import confusion_matrix, classification_report, mean_squared_error, mean_absolute_error, r2_score


pd.set_option('display.width', 600)
pd.set_option('display.max_rows', 150)
pd.set_option('display.max_columns', 16)

data = pd.read_csv("housing_data_raw.csv")
target = "Money"
x = data.drop(target, axis=1)
y = data[target]
x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.2, random_state=100)

cls = pickle.load(open("HousePriceModel.pickle", 'rb'))

y_predict = cls.predict(x_test)
# temp = ["電梯大樓", 47, 25.63, 22 , 0, 1, 1, 0, "鼓山區", 4, 2, 2, "4", 14]
# # temp2 = ["電梯大樓", 36.11, 19.49, 7, 1, 1, 1, 0, "鳳山區", 3, 2, 2, "8", 15]
# temp3 = ["公寓", 22, 22, 47, 0, 0, 0, 1, "鼓山區", 3, 2, 2, "2", 4]
# temp_df = pd.DataFrame([temp3],columns=['Shape', 'Area', 'MainArea', 'Age', 'TopSchool', 'Parking', 'Balcony', 'LowStruct',
#                                 'DisTrict', 'Room', 'LivingRoom', 'Bathroom', 'Floor_min', 'Floor_max'])
# print(cls.predict(temp_df))

# print(cls.best_estimator_)
# print(cls.best_score_)
# print(cls.best_params_)

print("MSE {}".format(mean_squared_error(y_test, y_predict)))
print("MAE {}".format(mean_absolute_error(y_test, y_predict)))
print("R2 {}".format(r2_score(y_test, y_predict)))

#         # MSE 35.23067096333333
#         # MAE 4.798896666666667
#         # R2 0.8299907579402186