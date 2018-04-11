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
    }
}