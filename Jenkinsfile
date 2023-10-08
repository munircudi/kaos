pipeline {

    agent any

    tools{

       maven 'maven'
    }

    stages {
        stage("Build"){
            steps {
                sh 'mvn clean install'
            }
        }

        stage("Test"){
            steps {
                sh 'echo selam dunya'
            }
        }

        stage('Build docker image') {
            steps {
                script {
                    sh "docker build -t huseyinbalicak/biosimage:0.${BUILD_ID} ."
                }
            }
        }

         stage('Push image to dockerHub') {
             steps {
                 script {
                     withCredentials([string(credentialsId: 'huseyinDockerHubPasswd', variable: 'onlyVar')]) {
                         sh "docker login -u huseyinbalicak -p ${onlyVar}"
                     }
                    sh "docker push huseyinbalicak/biosimage:0.${BUILD_ID}"
                }
            }
         }

         stage("Pusho Nexus Server"){
             steps {
                 sh 'echo selam dunya'
             }
         }

        stage("Clean Port and env."){
             steps {
                 sshagent(['ssh-agent']) {
                     sh 'ssh -o StrictHostKeyChecking=no -l root 146.190.35.233 docker stop $(docker ps -a -q)'
                     //sh 'ssh -o StrictHostKeyChecking=no -l root 146.190.35.233 uname -a'
                 }
             }
        }


         stage("Deploy"){
             steps {
                 sshagent(['ssh-agent']) {
                     //sh 'ssh -o StrictHostKeyChecking=no -l root 146.190.123.148 docker run --name jenko${BUILD_ID} -p ${BUILD_ID}:80 abbas1997/petclinicapplication:0.1'
                     //sh 'ssh -o StrictHostKeyChecking=no -l root 24.199.119.158 docker run --name jenko${BUILD_ID} -d -p 8181:80 abbas1997/testimage:${BUILD_ID}'
                     //sh 'ssh -o StrictHostKeyChecking=no -l root 24.199.119.158 docker run --name jenko${BUILD_ID} -d -p 8084:8087 abbas1997/petclinicapplication:0.1'
                     //sh 'ssh -o StrictHostKeyChecking=no -l root 146.190.123.148 docker rm $(docker ps -aq) && docker run --name jenko${BUILD_ID} -p 80:80 abbas1997/testimage'
                     //sh 'docker run --name jenko -p 80:80 abbas1997/testimage'
                     //sh 'ssh -o StrictHostKeyChecking=no -l root 146.190.45.242 docker run --name biosethos${BUILD_ID} -d -p 8099:8090 huseyinbalicak/biosimage:0.${BUILD_ID}'
                     //sh 'ssh -o StrictHostKeyChecking=no -l root 146.190.45.242 uname -a'
                     sh 'ssh -o StrictHostKeyChecking=no -l root 146.190.35.233 docker run --name biosethos${BUILD_ID} -d -p 8099:8090 huseyinbalicak/biosimage:0.${BUILD_ID}'
                     sh 'ssh -o StrictHostKeyChecking=no -l root 146.190.35.233 uname -a'
                 }
             }
         }


    }

}