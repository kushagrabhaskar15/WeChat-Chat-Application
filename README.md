# 💬 Real-Time Chat Application

## 📌 Overview

This is a real-time chat application built using **Spring Boot** and **WebSockets (STOMP)**.
It allows users to communicate instantly within conversations, with messages stored persistently in a database.

---

## 🚀 Features

* Real-time messaging using WebSockets
* Conversation-based chat system
* Persistent message storage
* Clean service-layer architecture
* Scalable backend design

---

## 🛠️ Tech Stack

### Backend

* Java
* Spring Boot
* Spring WebSocket (STOMP)
* Spring Data JPA
* Hibernate

### Tools

* Maven / Gradle
* ModelMapper
* WebSocket (SockJS + STOMP)

---

## ⚙️ How It Works

1. Client sends message to:

   ```
   /app/message.sendMessage/{conversationId}
   ```

2. Backend processes the message:

   * Saves it to database
   * Broadcasts it to subscribers

3. Clients subscribed to:

   ```
   /topic/conversation/{conversationId}
   ```

   receive the message instantly

---

## 📂 Project Structure

```
src/
 ├── controller/
 ├── service/
 ├── repository/
 ├── entity/
 ├── dto/
 └── config/
```

* **Controller** → Handles WebSocket endpoints
* **Service** → Business logic
* **Repository** → Database access
* **Entity** → JPA models
* **DTO** → Data transfer objects

---

## ▶️ Getting Started

### Prerequisites

* Java 17+
* Maven / Gradle
* MySQL / PostgreSQL

---

### Run the Application

1. Clone the repository:

   ```
   git clone https://github.com/your-username/your-repo.git
   ```

2. Navigate to project:

   ```
   cd your-repo
   ```

3. Run the application:

   ```
   ./mvnw spring-boot:run
   ```

---

## 🔌 WebSocket Endpoints

### Send Message

```
/app/message.sendMessage/{conversationId}
```

### Subscribe to Messages

```
/topic/conversation/{conversationId}
```

---

## 📄 API Behavior

* Messages are stored before being broadcast
* Each message belongs to a specific conversation
* Real-time updates are pushed to all subscribed clients

---

## 🧪 Future Improvements

* User authentication & authorization
* Typing indicators
* Message read receipts
* File/media sharing
* Pagination for chat history

---

## 📜 License

This project is licensed under the MIT License.
