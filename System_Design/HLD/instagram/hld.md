# Instagram High-Level Design (HLD)

## Requirements

### Functional Requirements

- Create a post
  - Image post
  - Video post
  - Text post
- Follow / Unfollow users
- News Feed
  - Show posts of users that a person follows
  - Posts should be in reverse chronological order (most recent to oldest)
- Like posts
- Comment on posts
- Notifications

### Non-Functional Requirements (Availability / Scalability)

- News Feed System
  - Availability
    - The system should be highly available
    - Target uptime: 99.999%
  - Eventual Consistency
    - New posts may not appear immediately
    - Posts should appear within 1–2 seconds
  - Latency
    - Low latency is required
    - News feed should load within 1–2 seconds after clicking Home
  - Scalability
    - The system should scale globally
  - Extensibility
    - The system should be easy to extend in the future
    - Examples: replying to comments, post recommendations, IoT ads
  - Usability
    - News feed rendering should be very fast
    - Focus on good user experience

## Capacity Estimation

### Key Areas

- Daily Active Users (DAU)
- Monthly Active Users (MAU)
- Throughput
- Storage
- Memory
- Network

### User Metrics

- DAU: 500M
- MAU: 2B

### Throughput

- Read Throughput
- Write Throughput

#### Write Throughput

- Writing data to the system
