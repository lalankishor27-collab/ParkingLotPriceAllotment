ParkingLotPriceAllotment-with-db-complete
----------------------------------------
This package contains a complete Java project with SQLite persistence. Place sqlite-jdbc JAR in lib/ and then compile/run as described:

javac -cp "lib/*" -d out src\parking\*.java
java -cp "out;lib/*" parking.Main

"C:\Program Files\DB Browser for SQLite\DB Browser for SQLite.exe"

java --enable-native-access=ALL-UNNAMED -cp "out;lib/*" parking.Main