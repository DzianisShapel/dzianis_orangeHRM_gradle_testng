pipeline {
    agent any

    tools {
        allure 'Allure Jenkins Plugin'   // Must match the name you configured in Jenkins → Manage Jenkins → Tools → Allure Commandline
    }

    parameters {
        string(name: 'browser', defaultValue: 'chrome', description: 'Browser to use for tests')
        string(name: 'threadCount', defaultValue: '2', description: 'Number of parallel threads')
        string(name: 'config', defaultValue: 'all_tests', description: 'TestNG suite file (without .xml extension)')
        booleanParam(name: 'selenoidEnable', defaultValue: true, description: 'Enable running on Selenoid')
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://your.git.repo.url.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh """
                    ./gradlew clean test \
                        -Pbrowser=${params.browser} \
                        -PthreadCount=${params.threadCount} \
                        -Pconfig=${params.config} \
                        -PselenoidEnable=${params.selenoidEnable} \
                """
            }
        }

        stage('Allure Report') {
            steps {
                allure([
                    includeProperties: false,
                    jdk: '',
                    results: [[path: 'build/allure-results']]
                ])
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'build/reports/tests/test/**', allowEmptyArchive: true
        }
        failure {
            mail to: 'dzianis283@gmail.com',
                 subject: "❌ UI Tests Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "Check Jenkins job for details: ${env.BUILD_URL}"
        }
    }
}