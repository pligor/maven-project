pipeline {
    //declarative pipeline: good balance between fully scripted (in groovy language) and jenkins ui
    agent any  // any available code

    stages {
        stage('Init') {
            steps {
                echo 'Testing...'
            }
        }

        stage('Build') {
            steps {
                echo 'Building...'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Code deployed'
            }
        }
    }
}