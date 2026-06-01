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
                bat 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests'
                bat 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying Spring Boot application'
                 bat '''

                    for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8081') do (
                        taskkill /PID %%a /F
                    )
                    cd target
                    java -jar SpringbootEcommercePlatform-0.0.1-SNAPSHOT.jar
                    '''
            }
        }
    }
}