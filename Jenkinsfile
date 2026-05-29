pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Pulling latest code from GitHub'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building Spring Boot application'
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests'
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying Spring Boot application'
                sh '''
                    pkill -f app.jar || true
                    mkdir -p ~/springboot-deploy
                    cp target/*.jar ~/springboot-deploy/app.jar
                    nohup java -jar ~/springboot-deploy/app.jar > ~/springboot-deploy/app.log 2>&1 &
                '''
            }
        }
    }
}