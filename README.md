# spark-recompressor
A demo platform using:

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
Prototyping uses Minikube 1.30.1

### Cluster & Hardware
- ODroid H3+ with 64gb RAM, 14TB space
- RaspberryPi 4b
- PC: 64gb memory, 6c/12t
- A recent MacBookPro
- An older MacBookPro
- 1gbit WAN / 2.5gbit LAN / (550mbit) typical WLAN

### Settings
JVM option needs `--add-exports java.base/sun.nio.ch=ALL-UNNAMED` in Build configurations.  See [here](https://stackoverflow.com/questions/73465937/apache-spark-3-3-0-breaks-on-java-17-with-cannot-access-class-sun-nio-ch-direct)
SBT needs `fork := true` or `fork / run := true`
