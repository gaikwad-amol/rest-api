pipeline {
    agent {
        docker {
            image 'maven:3-alpine' 
            args '-v /root/.m2:/root/.m2' 
        }
    }
    stages {
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                success {
                    jacoco()
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
        stage('Package') {
            steps {
                sh 'mvn -B -DskipTests clean package'
                archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false

            }
        }
        stage('Deployment'){
             steps {
                sh 'echo "Copy the war to some machine and run the JAR file!"'
                //sh 'cp target/*.jar /opt/tomcat8/webapps'
             }
        }
        stage ('Notification'){
            steps {
                emailext body: 'Jenkins Pipeline Job for Maven Build got completed !!!', recipientProviders: [buildUser()], subject: 'Build is successful!', to: 'amol.gaikwad.31@gmail.com'
            }
        }
    }
}
