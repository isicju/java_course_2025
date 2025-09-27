mvn clean install -DskipTests
mvn test -Dtest=*Unit*
mvn test -Dtest=*Integration*
cd target
java -jar http_server-jar-with-dependencies.jar &

URL="http://84.247.180.194:8080/"

sleep 5

response=$(curl -s "$URL")


if [ -n "$response" ]; then
    echo "Server is UP"
else
    echo "No response received from $URL"
fi
