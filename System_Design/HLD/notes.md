
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
Below are **clear, interview-ready explanations** of **SQL, NoSQL, Object Storage, Cache, and CDN**, written as **clean system-design notes**.

---

# 1. SQL Databases (Relational Databases)

## What it is

SQL databases store data in **tables with rows and columns** and use **structured schemas**.

Examples:

* MySQL
* PostgreSQL
* Oracle
* SQL Server

---

## Key characteristics

* Fixed schema
* Tables with relationships (foreign keys)
* ACID transactions
* Strong consistency
* Uses SQL query language

---

## Advantages

* Strong data integrity
* Supports complex queries and joins
* Ideal for transactional workloads
* Mature ecosystem

---

## Limitations

* Harder to scale horizontally
* Schema changes can be expensive
* Less flexible for unstructured data

---

## Use cases

* Banking and finance systems
* E-commerce orders and payments
* User accounts and authentication
* Inventory management

---

## Interview one-liner

SQL databases provide structured, strongly consistent data storage with ACID guarantees.

---

# 2. NoSQL Databases

## What it is

NoSQL databases store data in **non-relational formats** and are designed for **scalability and flexibility**.

---

## Types of NoSQL databases

### Key-Value Stores

* Redis
* DynamoDB
* Riak

### Document Stores

* MongoDB
* CouchDB

### Column-Family Stores

* Cassandra
* HBase

### Graph Databases

* Neo4j
* Amazon Neptune

---

## Key characteristics

* Schema-less or flexible schema
* Horizontal scalability
* Eventual consistency (often)
* Optimized for specific access patterns

---

## Advantages

* Handles massive scale
* Flexible data models
* High availability
* Fast reads and writes

---

## Limitations

* Weaker consistency guarantees
* Limited joins and complex queries
* Data duplication is common

---

## Use cases

* User profiles
* Session storage
* Recommendation systems
* IoT data
* Social graphs

---

## Interview one-liner

NoSQL databases trade strong consistency for scalability and flexibility.

---

# SQL vs NoSQL (Quick Comparison)

| Aspect       | SQL          | NoSQL                       |
| ------------ | ------------ | --------------------------- |
| Schema       | Fixed        | Flexible                    |
| Transactions | ACID         | BASE / eventual consistency |
| Scaling      | Vertical     | Horizontal                  |
| Joins        | Supported    | Limited                     |
| Best for     | Transactions | Large-scale data            |

---

# 3. Object Storage

## What it is

Object storage stores data as **objects**, not files or blocks.

Each object contains:

* Data
* Metadata
* Unique ID

Examples:

* Amazon S3
* Google Cloud Storage
* Azure Blob Storage

---

## Key characteristics

* Flat namespace
* Massive scalability
* High durability
* Accessed via HTTP APIs

---

## Advantages

* Cheap storage
* Extremely durable
* Easy to scale
* Ideal for unstructured data

---

## Limitations

* Higher latency than databases
* No partial updates (replace whole object)
* Limited querying capability

---

## Use cases

* Images and videos
* Backups and archives
* Static website assets
* Data lakes

---

## Interview one-liner

Object storage is optimized for storing massive amounts of unstructured data with high durability.

---

# 4. Cache

## What it is

A cache is a **fast, in-memory data store** that stores **frequently accessed data** to reduce latency.

Examples:

* Redis
* Memcached

---

## Key characteristics

* In-memory
* Very low latency
* Temporary storage
* Data can be evicted

---

## Cache strategies

* Read-through
* Write-through
* Write-behind
* Cache-aside (most common)

---

## Advantages

* Dramatically improves performance
* Reduces database load
* Improves scalability

---

## Limitations

* Data can become stale
* Requires eviction strategies
* Cache consistency challenges

---

## Use cases

* User sessions
* Frequently read objects
* API responses
* Leaderboards
* Rate limiting

---

## Interview one-liner

A cache improves performance by storing frequently accessed data in memory.

---

# 5. CDN (Content Delivery Network)

## What it is

A CDN is a **distributed network of servers** that deliver content from locations **closest to the user**.

```
User → Nearest CDN Edge → Origin Server
```

Examples:

* Cloudflare
* Akamai
* AWS CloudFront

---

## Key characteristics

* Globally distributed edge servers
* Caches static content
* Reduces latency
* Absorbs traffic spikes

---

## Advantages

* Faster content delivery
* Reduces load on origin servers
* Improves availability
* Built-in DDoS protection

---

## Limitations

* Cache invalidation complexity
* Not ideal for highly dynamic content
* Added cost

---

## Use cases

* Images, videos, CSS, JS
* Static websites
* Streaming platforms
* Software downloads

---

## Interview one-liner

A CDN reduces latency by serving content from geographically closer servers.

---

# How These Fit Together (System Design View)

Example: E-commerce platform

* CDN → serves images and static assets
* Cache → stores frequently accessed product data
* SQL → stores orders and payments
* NoSQL → stores user sessions and recommendations
* Object Storage → stores product images and videos

---

# Final System Design Tip

Use **SQL for correctness**, **NoSQL for scale**, **cache for speed**, **object storage for files**, and **CDN for global delivery**.

---

Below is a **detailed, interview-ready explanation of NoSQL databases**, broken down by **types**, with **real examples**, **data models**, **use cases**, and **when to choose each**.

---

# NoSQL Databases — Detailed Explanation with Types

## What is NoSQL?

NoSQL databases are **non-relational databases** designed to handle:

* Massive scale
* High availability
* Flexible or evolving schemas
* Distributed systems

They typically **trade strong consistency and joins** for **performance, scalability, and availability**.

---

## Why NoSQL Exists (Problem SQL Struggles With)

Traditional SQL databases:

* Scale vertically (harder and expensive)
* Require fixed schemas
* Struggle with very large distributed workloads

NoSQL databases:

* Scale horizontally (add more machines)
* Handle unstructured or semi-structured data
* Are optimized for specific access patterns

---

# Types of NoSQL Databases

## 1. Key-Value Stores

### What it is

The simplest NoSQL model.

Data is stored as:

```
Key → Value
```

Example:

```
"user:123" → "{name: 'Alice', age: 25}"
```

---

### Characteristics

* Extremely fast
* No schema
* Value is treated as a blob
* No querying inside the value

---

### Popular Examples

* Redis
* Amazon DynamoDB
* Riak
* Aerospike

---

### Use cases

* Session storage
* Caching
* User preferences
* Shopping carts
* Rate limiting

---

### Pros

* Very fast reads and writes
* Highly scalable
* Simple design

---

### Cons

* Limited querying
* No relationships
* Application handles data structure

---

### Interview one-liner

Key-value stores provide ultra-fast access by mapping unique keys to values.

---

## 2. Document Stores

### What it is

Stores data as **documents**, usually in **JSON or BSON** format.

Example document:

```json
{
  "id": 123,
  "name": "Alice",
  "email": "alice@gmail.com",
  "orders": [
    { "orderId": 1, "amount": 500 },
    { "orderId": 2, "amount": 300 }
  ]
}
```

---

### Characteristics

* Flexible schema
* Nested data supported
* Queryable fields
* Each document is self-contained

---

### Popular Examples

* MongoDB
* CouchDB
* Firebase Firestore

---

### Use cases

* User profiles
* Content management systems
* Product catalogs
* Mobile applications

---

### Pros

* Flexible schema
* Easy to evolve data model
* Natural JSON mapping

---

### Cons

* Limited joins
* Data duplication
* Transactions are limited (though improving)

---

### Interview one-liner

Document databases store semi-structured data as JSON-like documents with flexible schemas.

---

## 3. Column-Family Stores (Wide-Column Databases)

### What it is

Data is stored in **rows and columns**, but columns are grouped into **column families**.

Unlike SQL:

* Rows can have different columns
* Schema is partially flexible

---

### Example

```
UserID | name | email | last_login | device
-------------------------------------------
123    | Alice| a@x.com| 2025-01-01 | mobile
456    | Bob  |        | 2025-01-02 |
```

---

### Characteristics

* Optimized for large-scale writes
* Designed for distributed storage
* Query patterns must be known in advance

---

### Popular Examples

* Apache Cassandra
* Apache HBase
* Google Bigtable

---

### Use cases

* Time-series data
* IoT data
* Logs and metrics
* Recommendation engines

---

### Pros

* Massive scalability
* High write throughput
* Fault-tolerant

---

### Cons

* Complex data modeling
* Limited querying
* No joins

---

### Interview one-liner

Column-family stores are optimized for large-scale, distributed workloads with predictable query patterns.

---

## 4. Graph Databases

### What it is

Stores data as:

* Nodes (entities)
* Edges (relationships)
* Properties (metadata)

Example:

```
(Alice) —[FRIEND]→ (Bob)
```

---

### Characteristics

* Optimized for relationship traversal
* Schema-flexible
* Uses graph query languages

---

### Popular Examples

* Neo4j
* Amazon Neptune
* JanusGraph

---

### Use cases

* Social networks
* Recommendation systems
* Fraud detection
* Knowledge graphs

---

### Pros

* Excellent for connected data
* Fast relationship queries
* Intuitive data modeling

---

### Cons

* Hard to scale horizontally
* Not suitable for simple key-value access
* Smaller ecosystem

---

### Interview one-liner

Graph databases excel at storing and querying highly connected data.

---

## NoSQL Consistency Model (CAP Theorem)

NoSQL systems often follow **BASE** instead of ACID:

* Basically Available
* Soft state
* Eventual consistency

Some NoSQL databases allow **tunable consistency**:

* DynamoDB
* Cassandra

---

## SQL vs NoSQL (Quick Recap)

| Feature      | SQL          | NoSQL               |
| ------------ | ------------ | ------------------- |
| Schema       | Fixed        | Flexible            |
| Scaling      | Vertical     | Horizontal          |
| Transactions | Strong ACID  | Eventual (mostly)   |
| Joins        | Yes          | Limited             |
| Best for     | Transactions | Large-scale systems |

---

## Choosing the Right NoSQL Database (Interview Tip)

* Need **speed and simplicity** → Key-Value
* Need **flexible JSON data** → Document store
* Need **massive writes and scale** → Column-family
* Need **relationship-heavy queries** → Graph database

---

## Real-World Example (E-commerce)

* Redis → user sessions and cart
* MongoDB → product catalog
* Cassandra → clickstream events
* Neo4j → recommendations

---

## Final One-liner

NoSQL databases are purpose-built systems optimized for scale, availability, and flexible data models rather than strict relational guarantees.

---


