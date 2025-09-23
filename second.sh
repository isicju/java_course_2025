apt update

apt install -y curl

apt install openjdk-21-jdk -y

echo "switching to app_user..."

sudo -u app_user

cd java_course_2025

echo "build without tests..."

mvn clean install -DskipTests

echo "run unit tests..."

mvn test -Dtest=*Unit*

echo "run integration tests..."

mvn test -Dtest=*Integration*

java -jar target/http_server-jar-with-dependencies.jar &

echo "Waits 5 sec for server to start"

sleep 5

echo "Send curl"

curl --location 'http://37.60.239.67:80/'