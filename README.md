# 🚗 Parking Lot Price Allotment System

A Java-based Parking Lot Management System that handles vehicle entry/exit, ticket generation, and dynamic pricing strategies using an SQLite database.

---

## ✨ Features

- 🚘 Vehicle entry and exit tracking  
- 🎫 Ticket generation system  
- 💰 Multiple pricing strategies:
  - Hourly Pricing
  - Flat + Hourly Pricing
  - Monthly Pass
  - Time-Based Pricing  
- 🗄️ SQLite database integration (JDBC)  
- 📊 DAO-based architecture  
- 🧠 Clean OOP design using Strategy Pattern  

---

## 🛠️ Tech Stack

- Java  
- SQLite (JDBC)  
- Object-Oriented Programming (OOP)  
- Design Patterns (Strategy Pattern, DAO)  

---

## 📁 Project Structure

```bash
ParkingLotPriceAllotment/
├── src/parking/              # Source code
│   ├── Main.java
│   ├── ParkingLot.java
│   ├── ParkingSlot.java
│   ├── Ticket.java
│   ├── Vehicle.java
│   ├── VehicleType.java
│   ├── DBUtil.java
│   ├── SlotDAO.java
│   ├── TicketDAO.java
│   ├── VehicleDAO.java
│   ├── PricingStrategy.java
│   ├── HourlyPricing.java
│   ├── FlatPlusHourlyPricing.java
│   ├── MonthlyPassPricing.java
│   └── TimeBasedPricing.java
├── out/                      # Compiled class files
├── lib/                      # External libraries
│   └── sqlite-jdbc-3.51.0.0.jar
├── parking.db                # SQLite database
├── .gitignore
└── README.md
```


## ⚙️ Setup & Run

🔹 Step 1: Compile
```bash
javac -cp "lib/sqlite-jdbc-3.51.0.0.jar" -d out src/parking/*.java
```

🔹 Step 2: Run
```bash
java -cp "out;lib/sqlite-jdbc-3.51.0.0.jar" parking.Main
```

💡 On Linux/Mac use : instead of ; in classpath

## 📊 Pricing Strategies

| Strategy              | Description                   |
| --------------------- | ----------------------------- |
| HourlyPricing         | Charges based on hours parked |
| FlatPlusHourlyPricing | Base fee + hourly rate        |
| MonthlyPassPricing    | Fixed monthly parking cost    |
| TimeBasedPricing      | Dynamic pricing based on time |

## 🧠 Concepts Used

Strategy Design Pattern

DAO (Data Access Object)

OOP Principles (Encapsulation, Abstraction, Polymorphism)

## 👨‍💻 Author

Lalan Kishor

MCA (AI & IoT)

National Institute of Technology Patna
