# spark-demo
A demo platform that decompresses data in various formats and recompresses them into a sing compressed format

### State of the Project
- Spark Driver, a hello world that
  - Builds and packages using SBT
  - executes a JAR within a docker container
    
- Spark Workers:
  - A SMB client worker that can login, read, and filter out (annoying) metadata macOS files.
 
- Kubernetes
  - Created a Deployment which execute the spark hello world app in a single node.


### The Data
- 557.3gb in 8083 files (Disc images)
- 4.3gb in 926 files (fixed sizes 8k, 16k, etc.)
- 7.8gb in 500 files (fixed sizes 16m, 32m, etc.)
- 1.9gb of a mysterous single compressed file (7zip)

__All files are in varios formats:__ ISO, zip, gzip, LZMA, RAR, uncompressed. 

### Platform, components, versions
Some are subject to upgrades

- OS: Ubuntu 22.04, macOS 13
- Language: Scala 2.13.11
- Build tool: SBT 1.8.3
- Java JDK: openjdk 11.0.19 2023-04-18
- Platform: Apache Spark 3.4.0
- Containers: Docker 24.0.2
- Orchestrator: Kubernetes
- Database: MongoDB 6.1, Scala driver 4.8


### Cluster & Hardware
- ODroid H3+ with 64gb RAM, 14TB space
- RaspberryPi 4b
- PC: 64gb memory, 6c/12t
- A recent MacBookPro
- An older MacBookPro
- 1gbit WAN / 2.5gbit LAN / (550mbit) typical WLAN


### Current findings:

- JVM option needs `--add-exports java.base/sun.nio.ch=ALL-UNNAMED` in Build configurations.  See [here](https://stackoverflow.com/questions/73465937/apache-spark-3-3-0-breaks-on-java-17-with-cannot-access-class-sun-nio-ch-direct)

- SBT needs `fork := true` or `fork / run := true
