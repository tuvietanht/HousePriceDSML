import pickle

import pandas as pd
from sklearn.model_selection import train_test_split, GridSearchCV, RandomizedSearchCV
from sklearn.pipeline import Pipeline
from sklearn.preprocessing import StandardScaler, OrdinalEncoder, OneHotEncoder
from sklearn.impute import SimpleImputer
from sklearn.compose import ColumnTransformer
from sklearn.ensemble import RandomForestRegressor

pd.set_option('display.width', 600)
pd.set_option('display.max_rows', 150)
pd.set_option('display.max_columns', 16)

df = pd.read_csv('housing_data_raw.csv')
# data visualization
# print(df.info())
# print(df.describe())
# print(df["Shape"].unique())

target = "Money"
x = df.drop(target, axis=1)
y = df[target]
df = pd.get_dummies(df, drop_first=True)

x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.2, random_state=100)

numerical_transformer = Pipeline(steps=[
    ('imputer', SimpleImputer(strategy="median")),
    ('scaler', StandardScaler())
])

shape_values = ['公寓', '電梯大樓', '透天厝', '別墅']
floor_min_values = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18",
                    "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34",
                    "35", "36", "37", "38", "All"]

ordinal_transformer = Pipeline(steps=[
    ('imputer', SimpleImputer(strategy="most_frequent")),
    ('scaler', OrdinalEncoder(categories=[shape_values, floor_min_values]))
])

nominal_transformer = Pipeline(steps=[
    ('imputer', SimpleImputer(strategy="most_frequent")),
    ('scaler', OneHotEncoder())
])

preprocessor = ColumnTransformer(transformers=[
    ("num_features", numerical_transformer,
     ["Area", "MainArea", "Age", "TopSchool", "Parking", "Balcony", "LowStruct", "Room", "LivingRoom", "Bathroom",
      "Floor_max"]),
    ("ord_features", ordinal_transformer, ["Shape", "Floor_min"]),
    ("nom_features", nominal_transformer, ["DisTrict"]),
])

reg = Pipeline(steps=[('preprocessor', preprocessor),
                      ("model", RandomForestRegressor())
                      ])

params = {
    # param for root model
    "model__n_estimators": [50, 100, 200, 500],
    "model__criterion": ["squared_error", "absolute_error", "friedman_mse", "poisson"],
    "model__max_features": ["sqrt", "log2", None],

    # param for features
    "preprocessor__num_features__imputer__strategy": ["median", "mean"],
}

grid_reg = GridSearchCV(reg, param_grid=params, cv=6, verbose=1, scoring="r2", n_jobs=-1)
grid_reg.fit(x_train, y_train)

pickle.dump(grid_reg, open("HousePriceModel.pickle", "wb"))
