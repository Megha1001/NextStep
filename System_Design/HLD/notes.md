
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

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

# 🔁 Forward Proxy vs Reverse Proxy

## 1️⃣ Forward Proxy

### 🔹 What it is

A **forward proxy** sits **between the client and the internet**.

> It represents the **client**.

```
Client → Forward Proxy → Internet (Server)
```

The **server does NOT know** the original client.

---

### 🔹 Why it’s used

* Hide client identity (anonymity)
* Access control (block sites)
* Caching
* Bypass geo-restrictions
* Corporate monitoring

---

### 🔹 Example

* Company proxy for employee internet access
* VPN
* TOR (multi-hop proxies)

---

### 🔹 Key characteristics

* Client **must know** about the proxy
* Used mainly for **outbound traffic**
* Common in **enterprise networks**

---

### 🔹 Interview one-liner

> **A forward proxy acts on behalf of clients, forwarding their requests to external servers.**

---

## 2️⃣ Reverse Proxy

### 🔹 What it is

A **reverse proxy** sits **in front of servers**.

> It represents the **server**.

```
Client → Reverse Proxy → Backend Servers
```

The **client does NOT know** which server handled the request.

---

### 🔹 Why it’s used

* Load balancing
* Security (hide backend servers)
* TLS termination
* Caching
* Rate limiting
* DDoS protection

---

### 🔹 Example

* NGINX
* HAProxy
* Cloudflare
* AWS ALB / ELB

---

### 🔹 Key characteristics

* Client is **unaware** of backend servers
* Used for **inbound traffic**
* Core component in **system design**

---

### 🔹 Interview one-liner

> **A reverse proxy acts on behalf of servers, receiving client requests and distributing them to backend services.**

---

## 🔁 Side-by-side comparison

| Feature          | Forward Proxy              | Reverse Proxy               |
| ---------------- | -------------------------- | --------------------------- |
| Represents       | Client                     | Server                      |
| Location         | Client-side                | Server-side                 |
| Client awareness | Client knows               | Client doesn’t know         |
| Server awareness | Server doesn’t know client | Server doesn’t know backend |
| Main use         | Outbound control           | Inbound scaling & security  |

---

## 🧠 Real-world analogy

* **Forward proxy** → Personal assistant sending emails *for you*
* **Reverse proxy** → Receptionist routing visitors to staff

---

## 🎯 System Design usage

When designing a system:

* Use **forward proxy** for:

  * Corporate internet access
  * Privacy tools
* Use **reverse proxy** for:

  * Microservices
  * Load balancing
  * API gateways

---

## 🚀 Common confusion (important)

> **API Gateway = Reverse Proxy**

Yes — API gateways are specialized reverse proxies.

---

## ✅ Final takeaway

* **Forward proxy hides the client**
* **Reverse proxy hides the server**

