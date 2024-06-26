# jenkins-shared-library
install jenkins process:
Jenkins Debian Packages
This is the Debian package repository of Jenkins to automate installation and upgrade. To use this repository, first add the key to your system (for the Weekly Release Line):

    
 1 sudo wget -O /usr/share/keyrings/jenkins-keyring.asc \
    https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key
  
Then add a Jenkins apt repository entry:
    
2  echo "deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc]" \
    https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
    /etc/apt/sources.list.d/jenkins.list > /dev/null
  
Update your local package index, then finally install Jenkins:

   
3 sudo apt-get update
4  sudo apt-get install fontconfig openjdk-17-jre
5 sudo apt-get install jenkins
6 java -version
7 sudo systemctl start jenkins
8 sudo systemctl enable jenkins
9 sudo systemctl status jenkins
