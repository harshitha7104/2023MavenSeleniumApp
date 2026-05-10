pipeline {
    agent any

    tools {
        // Ensure these names match 'Manage Jenkins' -> 'Global Tool Configuration'
        maven 'Maven'
        jdk 'JDK'
    }

    stages {
        stage('Checkout') {
            steps {
                // This pulls your code from the GitHub repository configured in the job
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                // Clean old builds and package the new JAR
                // We use -DskipTests=false to ensure Selenium/JUnit tests run
                sh 'mvn clean package'
            }
        }

        stage('Run Application') {
            steps {
                script {
                    // List files to verify the name in the console log (useful for debugging)
                    sh 'ls -l target/'
                    
                    // Execute the JAR using the name defined in your pom.xml
                    sh 'java -jar target/2023MavenSeleniumApp-1.0-SNAPSHOT.jar'
                }
            }
        }
    }

    post {
        always {
            // This archives the test results in Jenkins so you can view the charts
            junit '**/target/surefire-reports/*.xml'
        }
        success {
            echo 'Build, Test, and Execution completed successfully!'
        }
        failure {
            echo 'The build failed. Check the console output above for errors.'
        }
    }
}
