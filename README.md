# High-Frequency trading order simulator (Clean Architecture)

## ✅ Features

- Create an order
- Get all orders
- Get order by ID
- Cancel order (only if status is `PENDING`)
- Simulate execution `PENDING` orders
- Swagger UI for testing
- Basic Auth for protection

---

## 🧾 Project Structure

```
src/
├── adapter/
        ├── controller/
        ├── dto/
        ├── mapper/
├── domain/
        ├── model/
        ├── repository/
├── infrastructure/
        ├── annotation/
        ├── config/
        ├── persistence/
├── service/
        ├── dto/
        ├── exception/
        ├── usecases/
```


---

## 🚀 Running the App

### 1. Clone

```bash
git clone https://gitlab.com/poc4845928/hft.git
cd hft
```

### 2. Build and run

Using MAVEN

```
./mvnw spring-boot:run
```

Using JAVA
```
./mvnw clean package
java -jar target/hft-0.0.1-SNAPSHOT.jar
```

---

## 🔐 Basic Auth

Use these credentials in Swagger or HTTP clients.

| Username | Password |
|----------|----------|
| admin    | 1234     |

---

## 📘 API Documentation

```
http://localhost:9009/swagger-ui.html
```

---

## 🧪 Running Tests

```bash
./mvnw test
```

---

## 🧪 Sample Commands

### Create an Order
```bash
curl --location 'localhost:9009/orders' \
--header 'Authorization: Basic YWRtaW46MTIzNA==' \
--header 'Content-Type: application/json' \
--data '{
    "symbol": "BTC",
    "price": 128812.98,
    "quantity": 36,
    "side": "buy"
}'
```

### Get all order
```bash
curl --location 'localhost:9009/orders' \
--header 'Authorization: Basic YWRtaW46MTIzNA=='
```

### Get order by id
```bash
curl --location 'localhost:9009/orders/1' \
--header 'Authorization: Basic YWRtaW46MTIzNA=='
```

### Cancel order
```bash
curl --location --request POST 'localhost:9009/orders/1/cancel' \
--header 'Authorization: Basic YWRtaW46MTIzNA=='
```

### Simulate executed order
```bash
curl --location --request POST 'localhost:9009/orders/simulate-execution' \
--header 'Authorization: Basic YWRtaW46MTIzNA=='
```

