
# Protocols for System Design — Short Notes

## 1. TCP (Transmission Control Protocol)

**Use when reliability matters**

* Connection-oriented
* Guarantees:

  * In-order delivery
  * Reliable delivery (ACKs + retransmissions)
  * Flow control (sliding window)
  * Congestion control
* Higher latency compared to UDP

**Use cases**

* HTTP/1.1, HTTP/2
* Databases
* File transfer systems
* Payment and financial systems

---

## 2. UDP (User Datagram Protocol)

**Use when speed matters**

* Connectionless
* No guarantees:

  * No delivery guarantee
  * No ordering
  * No retransmissions
* Very low latency

**Use cases**

* DNS
* VoIP
* Video streaming
* Online gaming

---

## 3. HTTP / HTTPS

**Application-layer request–response protocol**

* Stateless
* Runs over TCP (or QUIC in HTTP/3)
* HTTPS = HTTP + TLS (encryption, authentication)

### HTTP Versions

| Version  | Key Point                               |
| -------- | --------------------------------------- |
| HTTP/1.1 | Head-of-line blocking                   |
| HTTP/2   | Multiplexing over single TCP connection |
| HTTP/3   | Runs over QUIC (UDP-based)              |

**Use cases**

* REST APIs
* Web applications
* Microservices communication

---

## 4. QUIC

**Modern transport protocol**

* Built on UDP
* Provides:

  * Reliability
  * Ordering
  * Encryption by default
* Faster connection setup than TCP

**Use cases**

* HTTP/3
* Real-time web applications
* Mobile and unreliable networks

---

## 5. WebSocket

**Full-duplex communication protocol**

* Persistent connection
* Low latency
* Bi-directional communication

**Use cases**

* Chat applications
* Live notifications
* Multiplayer games
* Real-time dashboards

---

## 6. gRPC

**High-performance RPC framework**

* Built on HTTP/2
* Uses Protocol Buffers (binary serialization)
* Strongly typed APIs

**Use cases**

* Microservices communication
* Internal service-to-service calls

---

## 7. DNS (Domain Name System)

**Name resolution system**

* Converts domain names to IP addresses
* Mostly uses UDP
* Cached at multiple levels (browser, OS, ISP)

**Use cases**

* Service discovery
* Load balancing via DNS

---

## 8. MQTT

**Lightweight publish/subscribe protocol**

* Designed for low bandwidth
* Supports persistent sessions
* Runs over TCP

**Use cases**

* IoT systems
* Sensors
* Mobile and constrained devices

---

## 9. AMQP / Kafka Protocols

**Messaging and streaming protocols**

* Reliable message delivery
* Supports:

  * Publish/Subscribe
  * Queues
  * Ordered message processing (topic-based)

**Use cases**

* Event-driven architectures
* Asynchronous processing
* Data pipelines

---

## 10. TLS / SSL

**Security protocol**

* Encryption
* Authentication
* Data integrity

**Used with**

* HTTPS
* Secure gRPC
* Secure WebSockets

---

## Protocol Selection Cheat Sheet (Interview Ready)

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

## One-line System Design Tip

Protocol choice is always a trade-off between reliability, latency, and complexity.

---

# Forward Proxy vs Reverse Proxy

## 1. Forward Proxy

### What it is

A forward proxy sits between the client and the internet and represents the client.

```
Client → Forward Proxy → Internet (Server)
```

The server does not know the original client.

---

### Why it’s used

* Hide client identity
* Access control and filtering
* Caching
* Bypass geo-restrictions
* Corporate monitoring

---

### Examples

* Corporate internet proxy
* VPN
* TOR network

---

### Key Characteristics

* Client must be aware of the proxy
* Used mainly for outbound traffic
* Common in enterprise environments

---

### Interview One-liner

A forward proxy acts on behalf of clients and forwards their requests to external servers.

---

## 2. Reverse Proxy

### What it is

A reverse proxy sits in front of backend servers and represents the server.

```
Client → Reverse Proxy → Backend Servers
```

The client does not know which backend server processes the request.

---

### Why it’s used

* Load balancing
* Security and server hiding
* TLS termination
* Caching
* Rate limiting
* DDoS protection

---

### Examples

* NGINX
* HAProxy
* Cloudflare
* AWS ALB / ELB

---

### Key Characteristics

* Client is unaware of backend servers
* Used for inbound traffic
* Core component in system design

---

### Interview One-liner

A reverse proxy acts on behalf of servers and distributes client requests to backend services.

---

## Side-by-Side Comparison

| Feature          | Forward Proxy               | Reverse Proxy                |
| ---------------- | --------------------------- | ---------------------------- |
| Represents       | Client                      | Server                       |
| Location         | Client-side                 | Server-side                  |
| Client awareness | Client knows                | Client does not know         |
| Server awareness | Server does not know client | Server does not know backend |
| Primary use      | Outbound control            | Inbound scaling and security |

---

## Real-world Analogy

* Forward proxy: Personal assistant sending requests on your behalf
* Reverse proxy: Receptionist routing visitors to employees

---

## System Design Usage

Use a forward proxy for:

* Corporate internet access
* Privacy and anonymity tools

Use a reverse proxy for:

* Microservices architectures
* Load balancing
* API gateways

---

## Common Interview Confusion

API Gateway is a specialized form of reverse proxy.

---

## Final Takeaway

* Forward proxy hides the client
* Reverse proxy hides the server

---

If you want, I can also:

* Convert this into **PDF / markdown notes**
* Create **flash cards for interview revision**
* Add **real system design examples (Netflix, WhatsApp, Uber)**
