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
                    set -eu

                    docker rm -f nexora-ci-check || true

                    trap 'docker rm -f nexora-ci-check >/dev/null 2>&1 || true' EXIT

                    docker run -d \
                        --name nexora-ci-check \
                        --network nexora-digital-platform_default \
                        -p 18080:8080 \
                        -e SPRING_DATASOURCE_URL=jdbc:postgresql://nexora-postgres:5432/nexoradb \
                        -e SPRING_DATASOURCE_USERNAME=nexora \
                        -e SPRING_DATASOURCE_PASSWORD=nexora_local_password \
                        nexora-platform:${BUILD_NUMBER}

                    echo "Waiting for application health..."

                    for i in $(seq 1 45); do
                        if curl --fail --silent \
                            http://host.docker.internal:18080/actuator/health; then
                            echo "APPLICATION HEALTH CHECK PASSED"
                            exit 0
                        fi

                        sleep 2
                    done

                    echo "APPLICATION HEALTH CHECK FAILED"
                    docker logs nexora-ci-check
                    exit 1
                '''
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true,
                  testResults: 'target/surefire-reports/*.xml'
        }
    }
}
