## Import csv file into mongodb
***Mongodb Database tools needed***<br><br>
mongoimport --uri="mongodb://localhost:27017" --db=IUM-TWEB --collection=reviews --type=csv --headerline --file=reviews.csv