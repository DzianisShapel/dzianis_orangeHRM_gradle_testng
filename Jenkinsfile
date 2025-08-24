pipeline {
    agent any

    tools {
        allure 'allure'   // Must match the name you configured in Jenkins → Manage Jenkins → Tools → Allure Commandline
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
                git branch: 'master', url: 'https://github.com/DzianisShapel/dzianis_orangeHRM_gradle_testng.git', credentialsId: 'github-token'
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
            post {
                always {
                    allure includeProperties:
                            false,
                            jdk: '',
                            results: [[path: 'build/allure-results']]
                }
            }
        }
    }
}