from flask import Flask, render_template, request
import pandas as pd
import joblib

app = Flask(__name__)
model = joblib.load('HousePriceModel.pickle')

@app.route('/')
def home():
    return render_template('SignIn.html')


@app.route('/predict', methods=['POST'])
def predict():
    if request.method == 'POST':
        shape = request.form['shape']
        area = float(request.form['area'])
        main_area = float(request.form['main_area'])
        age = float(request.form['age'])
        top_school = float(request.form['top_school'])
        parking = float(request.form['parking'])
        balcony = float(request.form['balcony'])
        low_struct = float(request.form['low_struct'])
        district = request.form['district']
        room = float(request.form['room'])
        living_room = float(request.form['living_room'])
        bathroom = float(request.form['bathroom'])
        floor_min = request.form['floor_min']
        floor_max = float(request.form['floor_max'])

        temp_df = pd.DataFrame([[shape, area, main_area, age, top_school, parking, balcony, low_struct,
                                 district, room, living_room, bathroom, floor_min, floor_max]],
                               columns=['Shape', 'Area', 'MainArea', 'Age', 'TopSchool', 'Parking', 'Balcony',
                                        'LowStruct', 'DisTrict', 'Room', 'LivingRoom', 'Bathroom', 'Floor_min',
                                        'Floor_max'])

        prediction = model.predict(temp_df)
        return render_template('MainPage.html', prediction=prediction[0])


if __name__ == '__main__':
    app.run(debug=True)
