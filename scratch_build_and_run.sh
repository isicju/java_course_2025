mvn clean install -DskipTests
mvn test -Dtest=*Unit*
sudo mvn test -Dtest=*Integration*
cd target
sudo chmod 744 http_server-jar-with-dependencies.jar
sudo java -jar http_server-jar-with-dependencies.jar