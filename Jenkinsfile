pipeline {
    agent {
        docker {
            image 'maven:3-alpine' 
            args '-v /root/.m2:/root/.m2' 
        }
    }
    stages {
        stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                    success {
                      // publish html
                      publishHTML target: [
                          allowMissing: false,
                          alwaysLinkToLastBuild: false,
                          keepAll: true,
                          reportDir: 'target/site/jacoco',
                          reportFiles: 'index.html',
                          reportName: 'Code Coverage Report'
                        ]
                    }
            }
        }
    }
}
