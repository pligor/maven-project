pipeline {
    //declarative pipeline: good balance between fully scripted (in groovy language) and jenkins ui
    agent any  // any available code

    stages {
        /*stage('Init') {
            steps {
                echo 'Testing...'
            }
        }*/

        stage('Build') {
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

        stage('Deploy to staging') {
            steps {
                build job: 'deploy_to_staging'
            }
        }

        stage('Deploy to production') {
            steps {
                timeout(time: 5, unit:'DAYS') {
                    input message: 'Approve PRODUCTION Deployment?'
                }

                build job: 'deploy_to_prod'
            }

            post {
                success {
                    echo 'Code deployed to Production successfully'
                }

                failure {
                    echo 'Deployment to production failed'
                }
            }
        }
    }
}