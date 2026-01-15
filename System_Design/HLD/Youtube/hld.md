# Youtube High-Level Design (HLD)

## Requirements

### Functional Requirements
Two types of users
- Viewers
    - Can Stream any video (The viewer is able to watch videos.)
    - Device Compatibility (Can stream on mobilies, laptops, computers etc)
- Content Creators
    - Can upload a video
    - Get notification of video upload

### Non-Functional Requirements
- Viewers
  - Low latency - No more wait time, no buffering, no lagging
  - Scalability
  - User Experience -> ABR (Adaptive bit rate)
  - Availability - Should be available 99.9999%

- Content Creators
    - Sclability : The platform should be able to handle numerous creator
    - Security : Nobody should have unauthorized access to those videos, should not be any piracy
    - Storage reliability : For example, it should not happen if creator upload something and it disappears


## Capacity Estimation

### DAU/MAU
- DAU : 100Million Users
- MAU : 2.5Billion Users

### Throughput
- Read Throughput
    - User watch video
        - Assumption : 1 User watch 10 Videos
        - 100M * 10 : 1B read request/day
- Write Throughput
    - Uploading video : 
        - Assumption : 1/250 upload a video
        - 100M/250 = 0.4Million write request/day

### Storage
- Mainly store the video data -> Metadata, video Content
- Assumption : Avg size of video upload : 600MB
    - 600MB * 0.4Millioin = 240TB/day
    - Since video stores for longer period of time we cant delete them immedietly
        - How much storage we need to 10 years
            - 240TB * 365 * 10 = 876PB

### Memory
- When we talk about memory what we mean is we are estimating cache memory
- For this, we need to make assumption of some % out of total storage
    - Assumption : 1% of storage : 2.4TB/day (Since the cost to store data in cache is pretty expensive. Hence, we took percentage like 1%, 3% or 5%)

### Network and Bandwidth estimation
- How much data flows in and out of our system per second
- Its different from throughput
    - In throughput we check how many request flowed in and out of our system. Here we are talking about data
- Ingress : How much data flows into our system
    - All the data that comes to our system will eventually stores somewhere (uploading req)
        - How much data stores in a day == how much data stores in our system = 240TB/day
            - 240TB/(24 * 60 * 60) = 2.7GB/sec
- Egress : How much data flows out of our system
    - How much data read from our system - Someone need to read that data -> Viewers
        - 1B read request/day * 600MB(1 video avg size) = 600PB/day = 600PB/(24 * 60 * 60) = 6.9TB/sec

## API Design
- Upload Content
    - the video that the client is trying to upload can be very very big (can be 10 mins or 2 hours). Not feasible to upload a view in single request
    - Multiple request are used to send video data in small chunks + video metadata
    - Two parts
        - When user clicks upload button 
            - A request send to youtube server asking to upload the metadata
            ![alt text](API-Design/Content_Creators/Upload-part1.png)
            - Use session URI to upload the video
            ![alt text](API-Design/Content_Creators/Upload-part-2.png)
            Note : PUT is used to update an existing resource, POST is used to create new resource
            PATCH is use for updating part of existing resource, PUT is used to updata the entire resource
- Streaming/Watching content
    - Multiple request are send to get the each chunk of video and all these chunks are stored at different location
    - So to stream the entire video client needs to know the location
    - So what youtube does it send the manifest first on client request
    - Manifest files is the files which has all the locations of these chunks
    ![alt text](API-Design/Viewers/streaming.png)

## High Level Design
- Upload a content
    - Step1 : Video metadata reques and server respond with sessionURI
        ![alt text](HLD/Content_creators/Generat_sessionURI.png)
        ![alt text](HLD/Content_creators/upload.png)
        - In step 4, we are storing videoId in message queue.
        - In step 5, Content Processor (workflow engine) is doing many steps like breaking video in chunks, generating in different resolution
        - In step 7, content processor is sending those variation that it has created in above step to CDN.
        - IN step 8, We are storing location of CDN to DB
    - Content Processor Workflow Engine
        - Divide in chunks - Content Chunker Service
            - Get the videoId from message queue and get the video from object storage and then divide that video in chunks and 
            store back to object store and put the chunkId in message queue
            ![alt text](HLD/Content_creators/Content_chunker_service.png)
        - Convert to different format .mov and .mp4 - Format Convertor service
            - After chunking format convertor service takes those chunks with chunkId and  convert it into different format and store on object storage
            - Why different formats -> as with different devices different formats are supported
            - Steps :
                - Retrieve the event from event queue that content chunker service uploaded
                - Uses the chunkId from the event and get the object from object storage
                - Convert into different formats
                - Upload the converted chunks to the object
                - For each uploaded object creates a chunkId and upload in message queue
                ![alt text](HLD/Content_creators/format_converter_service.png)
        - Convert to different resolution - Quality converter service
            - After converting to different format the quality convertor service converts it to different resolution
                - Needed so user should have the good experience - ABR
            - Steps :
                - Retrieves the event from messasge queue - chunkId
                - get the chunk from object storage
                - convert it into different quality
                - stores them to object storage
                - upload the chunkId to message queue
                ![alt text](HLD/Content_creators/Quality_convertor_service.png)
        - CDN Uploader service
            - Get the chunks of different format and resolution and upload it to CDN
            - Along with uploading - CDN saves this location in VideoDB (help to create manifest file)
            - Steps
                - Retrives all the video chunks in different format and resolution from object storage
                - Now each chunk have different location it also saves them to videoDB
                - since video processing is complete the CDN uploader service add a message to message queue that processing is completed
                ![alt text](HLD/Content_creators/CDN_uploader_service.png)
    
![alt text](HLD/Content_creators/Combined.png)
        

- Streaming a content
    ![alt text](HLD/streaming/ABR.png)
