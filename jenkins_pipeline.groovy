pipeline {
    agent any

    parameters {
        string(name: 'tomcat_dev', defaultValue: '34.244.212.162', description: 'staging server')
        string(name: 'tomcat_prod', defaultValue: '34.244.53.38', description: 'production server')
    }

    triggers {
        pollSCM('* * * * *') //every minute for demo purposes (cron job)
    }

    stages {
        stage('build') {
            steps {
                sh 'mvn clean package'
            }

            post {
                success {
                    echo 'Now Archiving...'
                    archiveArtifacts artifacts: '**/target/*.war'
                }
            }
        }

        stage('deployments') {
            parallel {
                stage('deploy to staging') {
                    steps {
                        sh "ls ~ -a"
                        sh "chmod 777 /home/student/.ssh/tomcat_dev.pem"
                        sh "scp -i /home/student/.ssh/tomcat_dev.pem **/target/*.war ec2-user@${params.tomcat_dev}:/var/lib/tomcat7/webapps"
                    }
                }

                stage('deploy to production') {
                    steps {
                        sh "chmod 777 /home/student/.ssh/tomcat_prod.pem"
                        sh "scp -i /home/student/.ssh/tomcat_prod.pem **/target/*.war ec2-user@${params.tomcat_prod}:/var/lib/tomcat7/webapps"
                    }
                }
            }
        }
    }
}