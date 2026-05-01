# 🌾 KisanSetu - Smart Farmer Exchange

A Spring Boot + MongoDB backend for connecting farmers directly with buyers.

## 📁 Project Structure

```
kisansetu09/
├── pom.xml                              # Maven configuration
├── README.md                            # This file
└── src/
    └── main/
        ├── java/com/kisansetu/
        │   ├── KisanSetuApplication.java
        │   ├── config/                  # Configuration classes
        │   ├── controller/              # REST API Controllers
        │   │   ├── AuthController.java
        │   │   ├── CropController.java
        │   │   ├── OrderController.java
        │   │   ├── EscrowController.java
        │   │   └── HealthController.java
        │   ├── dto/                     # Data Transfer Objects
        │   │   ├── AuthResponse.java
        │   │   ├── LoginRequest.java
        │   │   ├── RegisterRequest.java
        │   │   ├── CropRequest.java
        │   │   ├── OrderRequest.java
        │   │   ├── PaymentRequest.java
        │   │   ├── PriceSuggestionRequest.java
        │   │   └── PriceSuggestionResponse.java
        │   ├── exception/               # Exception Handlers
        │   │   └── GlobalExceptionHandler.java
        │   ├── model/                   # MongoDB Document Models
        │   │   ├── User.java
        │   │   ├── Crop.java
        │   │   ├── Order.java
        │   │   └── Escrow.java
        │   ├── repository/              # MongoDB Repositories
        │   │   ├── UserRepository.java
        │   │   ├── CropRepository.java
        │   │   ├── OrderRepository.java
        │   │   └── EscrowRepository.java
        │   └── service/                 # Business Logic
        │       ├── AuthService.java
        │       ├── CropService.java
        │       ├── OrderService.java
        │       └── PriceSuggestionService.java
        └── resources/
            └── application.properties     # App configuration
```

## 🚀 Quick Start

### Prerequisites
- Java 17+
- MongoDB (local or Atlas)
- Maven 3.6+

### 1. Start MongoDB
```bash
# Local MongoDB
mongod --dbpath /path/to/data

# Or use MongoDB Atlas (cloud) - update connection string in application.properties
```

### 2. Configure Application
Edit `src/main/resources/application.properties`:

```properties
# For local MongoDB
spring.data.mongodb.uri=mongodb://localhost:27017/kisansetu

# For MongoDB Atlas (production)
spring.data.mongodb.uri=mongodb+srv://username:password@cluster.mongodb.net/kisansetu?retryWrites=true&w=majority
```

### 3. Run the Application
```bash
# Using Maven
mvn spring-boot:run

# Or build and run
mvn clean package
java -jar target/kisansetu-backend-1.0.0.jar
```

### 4. Verify
```bash
curl http://localhost:8080/
```

## 📡 API Endpoints

### Authentication (`/api/auth`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register new user (FARMER/BUYER) |
| POST | `/api/auth/login` | Login user |
| GET | `/api/auth/health` | Health check |

### Crops (`/api/crops`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/crops` | Create new crop listing |
| GET | `/api/crops` | Get all available crops |
| GET | `/api/crops?availableOnly=false` | Get all crops (including unavailable) |
| GET | `/api/crops/{id}` | Get crop by ID |
| GET | `/api/crops/farmer/{farmerId}` | Get crops by farmer |
| GET | `/api/crops/search?name=tomato` | Search crops by name |
| GET | `/api/crops/category/{category}` | Get crops by category |
| PUT | `/api/crops/{id}` | Update crop |
| PATCH | `/api/crops/{id}/availability?available=true` | Update availability |
| DELETE | `/api/crops/{id}` | Delete crop |
| POST | `/api/crops/price-suggestion` | Get AI price suggestion |

### Orders (`/api/orders`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/orders` | Create order |
| GET | `/api/orders` | Get all orders |
| GET | `/api/orders/{id}` | Get order by ID |
| GET | `/api/orders/number/{orderNumber}` | Get order by number |
| GET | `/api/orders/buyer/{buyerId}` | Get buyer's orders |
| GET | `/api/orders/farmer/{farmerId}` | Get farmer's orders |
| POST | `/api/orders/{orderId}/confirm` | Confirm order (Farmer) |
| POST | `/api/orders/{orderId}/pay` | Pay for order (Buyer) → Escrow |
| POST | `/api/orders/{orderId}/ship` | Mark as shipped (Farmer) |
| POST | `/api/orders/{orderId}/deliver` | Mark as delivered |
| POST | `/api/orders/{orderId}/complete` | Complete order → Release payment |
| POST | `/api/orders/{orderId}/cancel` | Cancel order |

### Escrow (`/api/escrow`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/escrow` | Get all escrow records |
| GET | `/api/escrow/{id}` | Get escrow by ID |
| GET | `/api/escrow/order/{orderId}` | Get escrow by order |
| GET | `/api/escrow/number/{escrowNumber}` | Get escrow by number |

### Health Check

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Service info |
| GET | `/health` | Health status |

## 📋 Sample Request/Response

### 1. Register User

**Request:**
```json
POST /api/auth/register
Content-Type: application/json

{
  "email": "farmer1@example.com",
  "password": "password123",
  "fullName": "Ramesh Kumar",
  "phone": "9876543210",
  "address": "Village Rampur, District Lucknow, UP",
  "role": "FARMER"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Registration successful",
  "user": {
    "id": "65a1b2c3d4e5f6g7h8i9j0k1",
    "email": "farmer1@example.com",
    "fullName": "Ramesh Kumar",
    "phone": "9876543210",
    "address": "Village Rampur, District Lucknow, UP",
    "role": "FARMER",
    "createdAt": "2024-01-15T10:30:00"
  }
}
```

### 2. Login

**Request:**
```json
POST /api/auth/login
Content-Type: application/json

{
  "email": "farmer1@example.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Login successful",
  "user": {
    "id": "65a1b2c3d4e5f6g7h8i9j0k1",
    "email": "farmer1@example.com",
    "fullName": "Ramesh Kumar",
    "role": "FARMER"
  }
}
```

### 3. Create Crop Listing

**Request:**
```json
POST /api/crops
Content-Type: application/json

{
  "name": "Organic Tomatoes",
  "category": "vegetable",
  "quantity": 500,
  "pricePerKg": 35,
  "farmerId": "65a1b2c3d4e5f6g7h8i9j0k1",
  "farmerName": "Ramesh Kumar",
  "imageUrl": "https://example.com/tomatoes.jpg",
  "description": "Fresh organic tomatoes from my farm",
  "location": "Lucknow, UP"
}
```

**Response:**
```json
{
  "id": "65b2c3d4e5f6g7h8i9j0k1l2",
  "name": "Organic Tomatoes",
  "category": "vegetable",
  "quantity": 500,
  "pricePerKg": 35,
  "unit": "kg",
  "farmerId": "65a1b2c3d4e5f6g7h8i9j0k1",
  "farmerName": "Ramesh Kumar",
  "imageUrl": "https://example.com/tomatoes.jpg",
  "description": "Fresh organic tomatoes from my farm",
  "location": "Lucknow, UP",
  "isAvailable": true,
  "listedAt": "2024-01-15T11:00:00",
  "suggestedPrice": 33.25,
  "marketTrend": 1.0
}
```

### 4. Get Price Suggestion (AI)

**Request:**
```json
POST /api/crops/price-suggestion
Content-Type: application/json

{
  "cropName": "tomato",
  "quantity": 500,
  "location": "Delhi",
  "quality": "premium"
}
```

**Response:**
```json
{
  "cropName": "tomato",
  "suggestedPricePerKg": 52.9,
  "minPrice": 44.97,
  "maxPrice": 60.84,
  "marketTrend": "STABLE",
  "confidence": "HIGH",
  "reasoning": "Price calculated based on: Base price Rs.30.00/kg; Location factor: +15%; Quality factor: +30%"
}
```

### 5. Place Order

**Request:**
```json
POST /api/orders
Content-Type: application/json

{
  "cropId": "65b2c3d4e5f6g7h8i9j0k1l2",
  "quantity": 50,
  "buyerId": "65c3d4e5f6g7h8i9j0k1l2m3",
  "buyerName": "Suresh Trader",
  "deliveryAddress": "Market Road, Delhi - 110001",
  "deliveryNotes": "Deliver before 9 AM"
}
```

**Response:**
```json
{
  "id": "65d4e5f6g7h8i9j0k1l2m3n4",
  "orderNumber": "ORD-20240115123045-AB12",
  "cropId": "65b2c3d4e5f6g7h8i9j0k1l2",
  "cropName": "Organic Tomatoes",
  "farmerId": "65a1b2c3d4e5f6g7h8i9j0k1",
  "farmerName": "Ramesh Kumar",
  "buyerId": "65c3d4e5f6g7h8i9j0k1l2m3",
  "buyerName": "Suresh Trader",
  "quantity": 50,
  "pricePerKg": 35,
  "totalAmount": 1750,
  "status": "PLACED",
  "paymentStatus": "PENDING",
  "deliveryAddress": "Market Road, Delhi - 110001",
  "deliveryNotes": "Deliver before 9 AM",
  "placedAt": "2024-01-15T12:30:45"
}
```

### 6. Order Status Flow

**Step 1: Confirm Order (Farmer)**
```bash
POST /api/orders/{orderId}/confirm
```

**Step 2: Pay (Buyer - goes to escrow)**
```bash
POST /api/orders/{orderId}/pay
```
Response: `"Payment successful. Amount held in escrow."`

**Step 3: Ship (Farmer)**
```bash
POST /api/orders/{orderId}/ship
```

**Step 4: Mark Delivered**
```bash
POST /api/orders/{orderId}/deliver
```

**Step 5: Complete (Buyer confirms - payment released)**
```bash
POST /api/orders/{orderId}/complete
```
Response: `"Order completed. Payment released to farmer."`

## 🔄 Order Status Flow

```
PLACED → CONFIRMED → PAID (Escrow) → SHIPPED → DELIVERED → COMPLETED
   ↓         ↓           ↓
CANCELLED  CANCELLED   REFUNDED
```

| Status | Description | Who Can Trigger |
|--------|-------------|-----------------|
| PLACED | Order created, awaiting farmer confirmation | System |
| CONFIRMED | Farmer accepted the order | Farmer |
| PAID | Buyer paid, amount in escrow | Buyer |
| SHIPPED | Farmer has shipped the goods | Farmer |
| DELIVERED | Goods delivered to buyer | System/Delivery |
| COMPLETED | Buyer confirmed receipt, payment released | Buyer |
| CANCELLED | Order cancelled | Either party |

## 💰 Escrow Payment Flow

1. **Buyer pays** → Money held in escrow
2. **Order completes** → Payment released to farmer
3. **Order cancelled** → Refund to buyer

## 🧪 Test Data

### Create Sample Users
```bash
# Register Farmer
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"email":"farmer@test.com","password":"pass123","fullName":"Farmer Joe","role":"FARMER","phone":"9876543210","address":"Village Farm"}'

# Register Buyer
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"email":"buyer@test.com","password":"pass123","fullName":"Buyer Jane","role":"BUYER","phone":"9876543211","address":"City Center"}'
```

### Create Sample Crop
```bash
curl -X POST http://localhost:8080/api/crops \
  -H "Content-Type: application/json" \
  -d '{"name":"Fresh Potatoes","category":"vegetable","quantity":1000,"pricePerKg":25,"farmerId":"<FARMER_ID>","farmerName":"Farmer Joe","location":"Punjab"}'
```

## 🛠️ Tech Stack

- **Backend:** Spring Boot 3.2.5
- **Database:** MongoDB
- **Security:** BCrypt password hashing
- **Validation:** Jakarta Validation
- **Build Tool:** Maven

## 🔧 Debugging

If you provide code with issues, I will:
1. Identify the exact problem
2. Fix only the broken parts
3. Provide 1-2 line explanation

**Common issues:**
- MongoDB connection failed → Check `application.properties`
- 404 errors → Verify endpoint paths
- Validation errors → Check required fields in DTOs

## 📞 Support

For issues:
1. Check MongoDB is running
2. Verify application.properties configuration
3. Check logs at `logging.level.org.springframework.data.mongodb=DEBUG`

---
**Built for Hackathons & Quick Deployment** 🚀
