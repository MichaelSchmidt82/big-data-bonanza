# Big Data Bonanza
A demo platform that decompresses data in various formats and recompresses them into a single compressed (Bzip2) format.  The mixed format dataset is 571 GB.

### Platform, components, versions
Some are subject to change and upgrades.

- ☸️ Orchestrator: Kubernetes
- ✨ Platform: Apache Spark 3.4.0
- 📦 Containers: Docker 24.0.2
- 📢 Language: Scala 2.13.11
- 💿 Database: MongoDB 6.1, Scala driver 4.8 (not yet though)
- ☕️ Java JDK: openjdk 11.0.19 2023-04-18
- 💻 OS: Ubuntu 22.04, macOS 13
- 👷 Build tool: SBT 1.8.3

### The Data
- 557.3gb in 8083 files (Disc images)
- 4.3gb in 926 files (fixed sizes 8k, 16k, etc.), and they are in compressed formats.
- 7.8gb in 500 files (fixed sizes 16m, 32m, etc.), and they too are in compressed formats.
- 1.9gb of a mysterous single compressed file (7zip).  I dunno, maybe somethings goofy and has compressed files in iteself.

__All files are in various formats:__ ISO, zip, gzip, LZMA, RAR, uncompressed. 

### State of the Project
- Spark Driver:
  - Build, package, and execute a hello world app within a docker container using SBT
    
- Spark Workers:
  - An SMB client worker that can login, determine share contents, and filter out annoying macOS metadata files.
  - File type classes that represent archive properties.
    - `trait Archive` enforces methods `compress()` and `decompress()` declared.
     - The default the `compress()` method is Bzip2, (the target compression).  Should the target format change, it would mean updating the trait, not each and every filetype.     
    - Bzip2 files can `compress()` and `decompress()`
    - Zip files can `decompress()` fulfilling one filetype found.
    - Skeleton code for 7Zip `decompress()`.
    - **Bonus Points:** All compress/decompress methods use **TAIL-END RECURSION**. [See why this matters](https://www.baeldung.com/cs/tail-vs-non-tail-recursion).
 
- Kubernetes
  - Created a Deployment which execute the spark hello world app in a single node.

### Cluster & Hardware
- [ODroid H3+](https://www.hardkernel.com/shop/odroid-h3-plus/) with 64gb RAM, 14TB space
- RaspberryPi 4b
- PC: 64gb memory, 6c/12t
- A recent MacBookPro
- An older MacBookPro
- 1gbit WAN / 2.5gbit LAN / 600mbit (typical) WLAN


### Current findings:

- JVM option needs `--add-exports java.base/sun.nio.ch=ALL-UNNAMED` in Build configurations.  See [here](https://stackoverflow.com/questions/73465937/apache-spark-3-3-0-breaks-on-java-17-with-cannot-access-class-sun-nio-ch-direct)

- SBT needs `fork := true` or `fork / run := true` for the app to run in it's own thread (not in the SBT build thread).
