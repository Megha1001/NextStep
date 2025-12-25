
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

-----

Below is a **clear, interview-ready explanation** of **GraphQL, REST, and Message Queues**, written as **man-made notes** with simple language, examples, and comparisons.

---

# 1. REST (Representational State Transfer)

## What it is

REST is an **API design style** that uses **HTTP** to access and manipulate resources.

Resources are identified by **URLs**, and operations are performed using **HTTP methods**.

```
GET    /users/123
POST   /users
PUT    /users/123
DELETE /users/123
```

---

## Key principles

* Stateless (each request is independent)
* Client–server separation
* Uses standard HTTP methods
* Uses HTTP status codes
* Typically uses JSON over HTTP

---

## Advantages

* Simple and widely understood
* Easy to cache
* Works well with HTTP infrastructure
* Good tooling and ecosystem

---

## Limitations

* Over-fetching or under-fetching data
* Multiple requests needed for related data
* Versioning complexity (v1, v2, etc.)

---

## Use cases

* Public APIs
* CRUD-based applications
* Standard web and mobile backends

---

## Interview one-liner

REST exposes resources via URLs and uses HTTP methods to perform operations on them.

---

# 2. GraphQL

## What it is

GraphQL is a **query language for APIs** where the **client specifies exactly what data it needs**.

Instead of multiple endpoints, GraphQL usually has **a single endpoint**.

```
POST /graphql
```

Example query:

```
{
  user(id: 123) {
    name
    email
    posts {
      title
    }
  }
}
```

---

## Key characteristics

* Client controls the response shape
* Single endpoint
* Strongly typed schema
* Supports nested queries
* Real-time updates via subscriptions

---

## Advantages

* No over-fetching or under-fetching
* Fewer network requests
* Ideal for frontend-driven development
* Strong typing and self-documentation

---

## Limitations

* More complex to implement
* Harder to cache at HTTP level
* Performance tuning can be tricky
* Not ideal for simple CRUD APIs

---

## Use cases

* Mobile apps (limited bandwidth)
* Complex frontend requirements
* Rapidly evolving UIs
* Aggregating multiple backend services

---

## Interview one-liner

GraphQL lets clients request exactly the data they need using a strongly typed query language.

---

# 3. Message Queue

## What it is

A Message Queue is an **asynchronous communication mechanism** where producers send messages and consumers process them later.

```
Producer → Queue → Consumer
```

The producer and consumer do **not need to be online at the same time**.

---

## Key characteristics

* Asynchronous
* Decouples services
* Buffers traffic spikes
* Supports retries and acknowledgments

---

## Common systems

* RabbitMQ
* Kafka
* AWS SQS
* Google Pub/Sub

---

## Queue vs Pub/Sub

* Queue: One consumer processes each message
* Pub/Sub: Multiple consumers receive the same message

---

## Advantages

* Improves scalability
* Increases system reliability
* Enables event-driven architecture
* Prevents cascading failures

---

## Limitations

* Eventual consistency
* Increased system complexity
* Harder debugging
* Message ordering challenges

---

## Use cases

* Order processing
* Payment workflows
* Email and notification systems
* Log and event pipelines
* Background jobs

---

## Interview one-liner

A message queue enables asynchronous, decoupled communication between services.

---

# REST vs GraphQL vs Message Queue (Comparison)

| Aspect        | REST            | GraphQL         | Message Queue         |
| ------------- | --------------- | --------------- | --------------------- |
| Communication | Synchronous     | Synchronous     | Asynchronous          |
| Data fetching | Fixed responses | Client-defined  | Event-based           |
| Coupling      | Moderate        | Moderate        | Loose                 |
| Real-time     | Polling         | Subscriptions   | Event-driven          |
| Best for      | CRUD APIs       | Complex UI data | Background processing |
| Example tools | HTTP APIs       | Apollo, Relay   | Kafka, RabbitMQ       |

---

# When to Use What (Interview Tip)

* Use **REST** when:

  * APIs are simple and stable
  * You want easy caching and tooling

* Use **GraphQL** when:

  * Frontend needs flexibility
  * You want fewer API calls

* Use **Message Queues** when:

  * Work can be processed asynchronously
  * You need reliability and scalability

---

# One-line System Design Tip

REST and GraphQL are for **request–response**, Message Queues are for **event-driven, asynchronous workflows**.

---


