# 🚚 Live Location Tracker Backend (Kafka + Spring Boot)

A **backend-only** project built using **Spring Boot**, **Apache Kafka**, and **MySQL** to demonstrate real-time, asynchronous communication between services using Kafka's **producer-consumer model**.

This project simulates a **live food delivery location tracking system**, where delivery agents send their location updates, and those updates are processed by **multiple consumers** independently.

---

## 🎯 Project Goals

- 🔁 Understand **Kafka's Producer-Consumer** architecture
- 🧵 Experience **asynchronous, decoupled processing**
- 🛠️ Learn how to set up and integrate Kafka in a Spring Boot app
- 📡 Simulate real-time updates like a food delivery app

---

## ⚙️ Tech Stack

| Layer            | Tool / Framework      |
|------------------|------------------------|
| Language         | Java 17               |
| Backend Framework| Spring Boot 3.x       |
| Messaging Queue  | Apache Kafka          |
| Database         | MySQL (JPA/Hibernate) |
| Build Tool       | Maven                 |
| Logging          | System logs (console) |

---

## 📁 Project Structure

```
src/main/java/com/app/food_delivery_backend
│
├── controller/
│   └── DummyProducerController.java   # REST API to send location
│
├── kafka/
│   ├── LocationProducer.java          # Kafka Producer
│   ├── LocationConsumer.java          # Kafka Consumer (saves to DB)
│   └── LocationLoggingConsumer.java   # Kafka Consumer (logs to console)
│
├── model/
│   └── LocationUpdate.java            # Entity representing location data
│
├── repository/
│   └── LocationUpdateRepository.java  # JPA Repository
│
├── service/
│   └── LocationService.java           # Service for DB ops
│
├── config/
│   └── JacksonConfig.java             # Enables LocalDateTime serialization
│
└── LiveTrackerApplication.java        # Main class
```

---

## 🚀 Setup & Run Instructions

### 🧩 Prerequisites

- Java 17+
- Maven 3.x
- Apache Kafka & Zookeeper
- MySQL (running locally)
- IntelliJ / VSCode (optional)

---

### 🔧 Step-by-Step Installation

#### 1. 🛠️ Clone the Repository

```bash
git clone https://github.com/TrulyGourav/Food-Delivery-Backend.git
cd Food-Delivery-Backend
```

---

#### 2. ⚙️ Start Kafka & Zookeeper (locally via terminal)

```bash
# Start Zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

# Start Kafka
bin/kafka-server-start.sh config/server.properties
```

---

#### 3. 🗃️ Create Kafka Topic

```bash
bin/kafka-topics.sh --create --topic location-updates --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```

---

#### 4. 🛢️ Setup MySQL

- Create a database (e.g., `locationdb`)
- Update `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/locationdb
    username: root
    password: your_password
```

---

#### 5. 📦 Build and Run the App

```bash
# In project root
mvn clean install
mvn spring-boot:run
```

---

## ✅ API Endpoints

### ➕ Send Location Update (produces to Kafka)

`POST /api/send-location`

```json
{
  "agentId": "AGENT007",
  "latitude": 28.6139,
  "longitude": 77.2090,
  "timestamp": "2025-07-11T15:45:00"
}
```

---

### 📥 Get Latest Location for Agent

`GET /api/location/{agentId}`

**Example:**
```bash
GET http://localhost:8080/api/location/AGENT007
```

---

## 👀 How Kafka Works Here

### 🔁 Producer
- REST controller receives a location update
- Converts it to JSON
- Publishes to Kafka topic: `location-updates`

### 📦 Consumers
1. **LocationConsumer**:
   - Reads message from Kafka
   - Saves it to MySQL

2. **LocationLoggingConsumer**:
   - Reads same message
   - Logs to console (for visualization)

### 🧠 Key Kafka Benefits Illustrated:
- **Asynchronous processing**
- **Multiple consumers on same message**
- **Loose coupling**
- **Non-blocking architecture**

---

## 🧪 How to Test

1. Start Kafka, Zookeeper, and MySQL
2. Run Spring Boot app
3. Use Postman or `curl` to POST location updates
4. Observe:
   - Console logs from both consumers
   - DB entries in `location_update` table
5. Call GET API to retrieve the latest location for an agent

---

## 🎓 Learning Outcomes

✅ Understand Kafka’s:
- Producer-Consumer pattern
- Topic and message flow
- Group-based consumption
- Async event-driven benefits

---

## 🤝 Contribution

This is a learning-focused backend project. Feel free to fork, enhance with:
- WebSocket or frontend
- ETA prediction simulation
- More Kafka topics
