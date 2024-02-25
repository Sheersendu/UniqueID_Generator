# Unique ID Generator
A unique ID generator for distributed systems

## Requirements
1. Generated ID must be unique
2. Generated ID must have numericals values only
3. Generated ID must fit in 64 bits
4. Generated ID must be ordered by date
5. 10,000 unique IDs can be generated per second

## Approaches:

### 1. Multi-master replication:
* We utilise the database's `auto-increment` feature
* Instead of incrementing the next ID by `1` increment it by `K`, where `K` is the number of DB servers

![image](https://github.com/Sheersendu/UniqueID_Generator/assets/62256588/3b077734-ac95-4ad8-96ee-1d6f625e8f01)


* Drawbacks:
    - Hard to scale with multiple Data centers
    - Scalability is an issue(adding/removing server) 

### 2. Universally Unique Identifier (UUID)
* Its a 128 bit number used to identify information in computer systems
* Each web server willl contain its own ID generator - Scalable
* No coordination needed b/w servers hence no synchronization issues
* Drawbacks:
    - Ids are 128 bits but we require 64 bits
    - Ids can contain non-numeric characters

### 3. Ticket Server
* A centralized `auto-increment` feature in a single DB server (ticket server) which will be accessed by other DB servers for ID
* Works well for small-medium level applications
* Drawbacks:
    - SPOF
    - To solve SPOF, we can have multiple ticket servers leading to data synchronization issue

### 4. Twitter Snowflake Algorithm

![image](https://github.com/Sheersendu/UniqueID_Generator/assets/62256588/b5885da3-a8aa-4464-8804-24eccbdb4429)


* **Sign bit** : It will always be 0. Reserved for future uses.
* **Timestamp** : Milliseconds since the epoch/custom epoch. We can use twitter's default epoch. Maximum timestamp can be represented in 41 bits ~= `69 years`(2^41), after that we need new epoch o radopt other techniques
* **Datacenter ID** : 5 bits = `32`(2^5) datacenters
* **Machine ID** : 5 bits = `32`(2^5) machines per datacenter
* **Sequence Number** : For every ID generated on that machine, the sequence number is incremented by `1`. Number is reset to `0` every millisecond. Sequence Number is 12 bits hence `4096`(2^12) combinations/ms/machine
