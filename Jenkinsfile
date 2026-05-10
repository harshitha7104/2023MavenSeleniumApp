pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Package') {
            steps {
                // This will now create the 'jar-with-dependencies' file
                sh 'mvn clean package'
            }
        }

        stage('Run Selenium App') {
            steps {
                script {
                    sh 'ls -l target/'
                    // Running the fat JAR that contains Selenium
                    sh 'java -jar target/2023MavenSeleniumApp-1.0-SNAPSHOT-jar-with-dependencies.jar'
                }
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
        success {
            echo 'Selenium execution successful!'
        }
        failure {
            echo 'Execution failed. Check if headless mode is enabled in your Java code.'
        }
    }
}
