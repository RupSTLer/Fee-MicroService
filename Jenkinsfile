pipeline {
    agent any
    tools{
        maven 'mvn'
    }
    stages {
        stage('build') {
            steps {
                script {
                    echo "building the application..."
                    bat "mvn package"
                }
            }
        }
        stage('test') {
            steps {
                script {
                    echo "Testing the application..."
                }
            }
        }
    }
}