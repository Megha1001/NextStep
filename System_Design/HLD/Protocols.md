# 🌐 Protocols for System Design — Short Notes

## 1️⃣ TCP (Transmission Control Protocol)

**Use when reliability matters**

* Connection-oriented
* Guarantees:

  * In-order delivery
  * Reliability (ACKs + retransmissions)
  * Flow control (sliding window)
  * Congestion control
* Higher latency than UDP

**Use cases**

* HTTP/1.1, HTTP/2
* Databases
* File transfer
* Payment systems

---

## 2️⃣ UDP (User Datagram Protocol)

**Use when speed matters**

* Connectionless
* No guarantees:

  * No delivery
  * No ordering
  * No retransmission
* Low latency

**Use cases**

* DNS
* VoIP
* Video streaming
* Online gaming

---

## 3️⃣ HTTP / HTTPS

**Application-layer request–response protocol**

* Stateless
* Runs over TCP (or QUIC in HTTP/3)
* HTTPS = HTTP + TLS (encryption)

### HTTP versions

| Version  | Key point                    |
| -------- | ---------------------------- |
| HTTP/1.1 | Head-of-line blocking        |
| HTTP/2   | Multiplexing over single TCP |
| HTTP/3   | Runs over QUIC (UDP)         |

**Use cases**

* REST APIs
* Web apps
* Microservices

---

## 4️⃣ QUIC

**Modern transport protocol**

* Built on UDP
* Provides:

  * Reliability
  * Ordering
  * Encryption by default
* Faster connection setup than TCP

**Use cases**

* HTTP/3
* Real-time web apps
* Mobile networks

---

## 5️⃣ WebSocket

**Full-duplex communication**

* Persistent connection
* Low latency
* Bi-directional

**Use cases**

* Chat apps
* Live notifications
* Multiplayer games
* Real-time dashboards

---

## 6️⃣ gRPC

**High-performance RPC framework**

* Built on HTTP/2
* Uses Protocol Buffers (binary)
* Strongly typed APIs

**Use cases**

* Microservices communication
* Internal service-to-service calls

---

## 7️⃣ DNS (Domain Name System)

**Name resolution system**

* Converts domain → IP
* Mostly uses UDP
* Cached at multiple levels

**Use cases**

* Service discovery
* Load balancing (via DNS)

---

## 8️⃣ MQTT

**Lightweight pub/sub protocol**

* Low bandwidth
* Persistent sessions
* Works over TCP

**Use cases**

* IoT
* Sensors
* Mobile devices

---

## 9️⃣ AMQP / Kafka Protocols

**Messaging systems**

* Reliable message delivery
* Supports:

  * Pub/Sub
  * Queues
  * Message ordering (topic-based)

**Use cases**

* Event-driven systems
* Asynchronous processing
* Data pipelines

---

## 🔟 TLS / SSL

**Security protocol**

* Encryption
* Authentication
* Integrity

**Used with**

* HTTPS
* Secure gRPC
* Secure WebSockets

---

## 🧠 Protocol selection cheat sheet (INTERVIEW GOLD)

| Requirement                    | Protocol      |
| ------------------------------ | ------------- |
| Reliability                    | TCP           |
| Low latency                    | UDP           |
| Web APIs                       | HTTP/HTTPS    |
| Real-time updates              | WebSocket     |
| High-performance microservices | gRPC          |
| Modern web                     | HTTP/3 (QUIC) |
| IoT                            | MQTT          |
| Messaging                      | Kafka / AMQP  |

---

## 🎯 One-line System Design tip

> **Protocol choice is a trade-off between reliability, latency, and complexity.**


