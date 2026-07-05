To access h2 database follow : http://localhost:8080/h2-console

docker build . -t manumax95/accounts:s4
docker run -d -p 8081:8081 manumax95/accounts:s4

8081:8081 -> external port: container port