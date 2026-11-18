pipeline {

    agent any

    stages {

        stage('Checkout') {

            steps {

                checkout scm

            }

        }

        stage('Test') {

            steps {

                sh 'mvn -B test'

            }

        }

        stage('Build Docker Image') {

            steps {

                sh 'docker build -t nexora-platform:${BUILD_NUMBER} .'

            }

        }

        stage('Container Health Check') {

            steps {

                sh '''
                    docker rm -f nexora-ci-check || true

                    docker run -d \
                    --name nexora-ci-check \
                    -p 18080:8080 \
                    nexora-platform:${BUILD_NUMBER}

                    sleep 15

                    curl --fail \
                    http://host.docker.internal:18080/actuator/health

                    docker rm -f nexora-ci-check
                '''

            }

        }

    }

    post {

        always {

            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'

        }

    }

}