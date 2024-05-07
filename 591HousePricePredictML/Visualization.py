# import mysql.connector
# import pandas as pd
# import seaborn as sn
# from lazypredict.Supervised import LazyRegressor
# from matplotlib import pyplot as plt
# from sklearn.impute import SimpleImputer
# from sklearn.model_selection import train_test_split
# from sklearn.pipeline import Pipeline
# from sklearn.preprocessing import StandardScaler
#
# pd.set_option('display.width', 600)
# pd.set_option('display.max_rows', 350)
# pd.set_option('display.max_columns', 16)
#
# url = "localhost"
# username = "root"
# password = "Binpro123"
# database = "591houseprice"
#
# try:
#     connection = mysql.connector.connect(host=url, user=username, password=password, database=database)
#     if connection.is_connected():
#         query = f"SELECT * FROM houseprice"
#         df = pd.read_sql( query, connection)
#
#         # Room
#         df[['Room', 'LivingRoom', 'Bathroom']] = df['Layout'].str.extract(r'(\d+)房(\d+)廳(\d+)衛').astype(float)
#         df.drop(columns=["ID", "Layout"], inplace=True)
#
#         # Area
#         df['Area'] = df['Area'].str.replace('權狀', '')
#         df['Area'] = df['Area'].str.replace('坪', '')
#         df['Area'] = df['Area'].astype(float)
#
#         # MainArea
#         df['MainArea'] = df['MainArea'].str.replace('主建', '')
#         df['MainArea'] = df['MainArea'].str.replace('坪', '')
#         df['MainArea'] = df['MainArea'].astype(float)
#
#         # District
#         df['DisTrict'] = df['DisTrict'].str.replace('-', '').astype(str)
#
#         # Age
#         df['Age'] = df['Age'].str.replace('年', '')
#         df['Age'] = df['Age'].str.replace('不詳', '20')
#         df.loc[df['Age'].str.contains('個月'), 'Age'] = df.loc[df['Age'].str.contains('個月'), 'Age'].str.replace(
#             '個月', '').astype(float) / 12
#         df['Age'] = df['Age'].astype(float)
#
#         # Floor
#         df['Floor'] = df['Floor'].str.replace('整棟', 'All')
#         df['Floor'] = df['Floor'].str.replace('F', '')
#         df['Floor'] = df['Floor'].str.replace(r'\d+~\d+', 'All')
#         df['Floor'] = df['Floor'].str.replace('BAll', 'All')
#         df[['Floor_min', 'Floor_max']] = df['Floor'].str.split('/', expand=True)
#         # print(df['Floor_min'].value_counts().sort_values(ascending=False))
#         df.drop(columns=['Floor'], inplace=True)
#
#         # Money
#         df['Money'] = df['Money'].str.replace(',', '').astype(float)
#
#         # print(df.describe())
#
#         # Drop Nan
#         df.dropna(inplace=True)
#
#         df.to_csv('housing_data_preprocessed.csv', index=False)
#         # data visualization
#         print(df.describe())
#         print(df.info())
#         df.plot(kind="density", subplots=True, layout=(3, 4), sharex=False)
#         # sn.heatmap(df.corr(), annot=True , cmap = "BuPu")
#         plt.show()
#
#         exit()
#
#         # check values
#         target = "Money"
#         x = df.drop(target, axis=1)
#         y = df[target]
#         df = pd.get_dummies(df, drop_first=True)
#         x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.2, random_state=100)
#
#         numerical_transformer = Pipeline(steps=[
#             ('imputer', SimpleImputer(strategy="NONE")),
#             ('scaler', StandardScaler())
#         ])
#
#         education_values = ['some high school', 'high school', 'some college', "associate's degree",
#                             "bachelor's degree",
#                             "master's degree"]
#         gender_values = ["female", "male"]
#         lunch_values = x_train["lunch"].unique()
#         test_values = x_train["test preparation course"].unique()
#         #
#         ordinal_transformer = Pipeline(steps=[
#             ('imputer', SimpleImputer(strategy="most_frequent")),
#             ('scaler', OrdinalEncoder(categories=[education_values, gender_values, lunch_values, test_values]))
#         ])
#         #
#         nominal_transformer = Pipeline(steps=[
#             ('imputer', SimpleImputer(strategy="most_frequent")),
#             ('scaler', OneHotEncoder())
#         ])
#
#         preprocessor = ColumnTransformer(transformers=[
#             ("num_features", numerical_transformer, ["reading score", "writing score"]),
#             ("ord_features", ordinal_transformer,
#              ["parental level of education", "gender", "lunch", "test preparation course"]),
#             ("nom_features", nominal_transformer, ["race/ethnicity"]),
#         ])
#
#         reg = Pipeline(steps=[
#             ('preprocessor', preprocessor),
#             ("model", RandomForestRegressor())
#         ])
#
#         params = {
#             # param for root model
#             "model__n_estimators": [50, 100, 200],
#             "model__criterion": ["squared_error", "absolute_error", "friedman_mse", "poisson"],
#             "model__max_features": ["sqrt", "log2", None],
#
#             # param for features
#             "preprocessor__num_features__imputer__strategy": ["median", "mean"],
#         }
#
#         # grid_reg = GridSearchCV(reg, param_grid=params, cv=6, verbose=1, scoring="r2", n_jobs=-1)
#         grid_reg = RandomizedSearchCV(reg, param_distributions=params, cv=3, verbose=1, scoring="r2", n_jobs=-1,
#                                       n_iter=20)
#
#
#
#         connection.close()
# except mysql.connector.Error as e:
#     print(e)
