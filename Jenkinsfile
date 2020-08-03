pipeline {
  agent any
  stages {
    stage('source') {
      steps {
        git(url: 'https://github.com/fucct/2020-14f-guys.git', branch: 'jenkins-test', credentialsId: '7761e63343ea830558e429d9228450224c553b18')
      }
    }

    stage('Build') {
      steps {
        tool 'gradle'
      }
    }

    stage('Deploy') {
      steps {
        sh 'echo "Deploy Success"'
      }
    }

  }
}