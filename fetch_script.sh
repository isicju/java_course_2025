useradd app_user

mkdir /home/app_user
chown app_user:app_user /home/app_user
chmod 755 /home/app_user

cd /home/app_user
git clone https://github.com/isicju/java_course_2025.git
cd java_course_2025
git checkout home_work_1_answer
cd ..

chown -R app_user:app_user java_course_2025
chmod -R u=rwx,g=r,o=r java_course_2025