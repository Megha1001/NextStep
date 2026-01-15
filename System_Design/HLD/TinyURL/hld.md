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


### Memory
- Referring to cache memory
    - We store = 30GB/day 
    - 5% of total storage = 1.5GB/day

### Network and bandwidth
- Ingress : Data flowing into our system
    - 30GB/day = 30GB/(24 * 60 * 60) = 30*1000MB/(24 * 60 * 60) = 300MB/(24 * 6 * 6) = 0.35MB/sec
- Egress : Data flowing out of our system
    - When user ask for the long URL
        - 6B/day * 200Bytes = 1200GB/day = 1200GB/(24 * 60 * 60) = 12000MB/(24 * 6 * 6) = 13.8MB/sec


## API DESIGN
- Generating a short URL
![alt text](API_Design/Generating_short_url.png)

- Getting back long URL with short URL
    - EndPoint : /{shortURL} : because this is the endpoint we are typing in the browser
    ![alt text](API_Design/Get_long_Url_back.png)


## HLD
- Generating a short URL 
    ![alt text](HLD/Generate_short_URL.png)
    - Problem Collision
        ![alt text](image.png)