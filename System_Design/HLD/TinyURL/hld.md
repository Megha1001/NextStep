# Design a Tiny URL High-Level Design (HLD)

## Requirements

URL Shortner : Its a service that takes a long URL and convert it into short, compact URL that redirects to the original long URL.

Advantages
- Easy to share
- Characters limits
- Clean and professional

### Functional Requirements
- To generate a short URL
- To get the long URL back


### Non-Functional Requirements
- Availability
- low latency
- Scalability


## Capacity Estimations

### DAU & MAU
    DAU : 300M
    MAU : 1B

### Throughput
- Read Throughput
    - Client reads the original long URL back
        - 300M * 20 (assumption each user requesting 20 long url) = 6B/day get URL request par day


- Write Throughput
    - Creating a short URL
    - 10% of daily active users will create a shortURL
        - 300M * 0.10 = 30M/day
        - Assume every user create 5 short URL = 5 * 30M/day = 150M/day = 150M url creation in one day


### Storage
- When the user send the request to convert short URL to long URL the mapping of short to long is saved in the backend
- Size of Mapping(Short URL to long URL mapping ) = 100Bytes(long URL) + 30 bytes(short URL) + 70Bytes(for metadata - timestamp, userInfo) = 200Bytes
    - 150M * 200Bytes = 30GB/day