git clone https://github.com/isicju/java_course_2025
cd java_course_2025
git checkout home_work_akalavan

cd ..
sudo adduser app_user --disabled-password --gecos "app_user,,,"
echo "app_user:password" | sudo chpasswd
sudo usermod -aG sudo app_user
find java_course_2025 -type d -exec chmod 755 {} +
find java_course_2025 -type f -exec chmod 644 {} +
sudo chown -R app_user: java_course_2025

su - app_user -c './scratch_build_and_run.sh'