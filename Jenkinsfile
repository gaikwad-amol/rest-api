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
        		//sh 'cp target/*.war /opt/tomcat8/webapps'
        }

        stage ('Notification'){
        		emailext (
        		      subject: "Job Completed",
        		      body: "Jenkins Pipeline Job for Maven Build got completed !!!",
        		      to: "amol.gaikwad.31@gmail.com"
        		    )
        }
    }
}
