git clone https://github.com/isicju/java_course_2025.git

cd java_course_2025

git checkout home_work_alexey

sudo useradd -m -s /bin/bash app_user
sudo useradd -m -s /bin/bash test_user

CURRENT_DIR=$(pwd)

sudo chown -R app_user:app_user "$CURRENT_DIR"

sudo find "$CURRENT_DIR" -type d -exec chmod 755 {} +
sudo find "$CURRENT_DIR" -type f -exec chmod 644 {} +
sudo find "$CURRENT_DIR" -type f -name "*.sh" -exec chmod u+x {} +

sudo -u test_user

if [ -x "./execME.sh"]; then
	echo "U can execute script execME.sh"
else
	echo "U dont have permission to execute script execME.sh"
fi
