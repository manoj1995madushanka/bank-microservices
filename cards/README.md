mvn compile jib:dockerBuild

docker run -d -p 9000:9000 manumax95/cards:s4

jib is recommeneded due to buildpacks takes more space 

docker image push docker.io/manumax95/cards:s4

docker pull manumax95/cards:s4