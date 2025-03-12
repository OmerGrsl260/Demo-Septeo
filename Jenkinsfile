pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9'
        jdk 'JDK 17'
    }

    environment {
        ALLURE_RESULTS_DIR = 'target/allure-results'
        ALLURE_REPORT_DIR = 'target/allure-report'
        BROWSER = 'chrome'
        MAVEN_OPTS = '-Xmx2048m'
    }

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Sélectionnez le navigateur pour les tests')
        string(name: 'CUCUMBER_TAGS', defaultValue: '@all', description: 'Tags Cucumber à exécuter')
        booleanParam(name: 'SKIP_TESTS', defaultValue: false, description: 'Ignorer les tests ?')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Clean Workspace') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Run Tests') {
            when {
                expression { return !params.SKIP_TESTS }
            }
            steps {
                script {
                    try {
                        bat """
                            mvn test ^
                            -Dbrowser=${params.BROWSER} ^
                            -Dcucumber.filter.tags="${params.CUCUMBER_TAGS}" ^
                            -Dmaven.test.failure.ignore=true
                        """
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        error("Test execution failed: ${e.message}")
                    }
                }
            }
        }

        stage('Generate Allure Report') {
            steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: "${ALLURE_RESULTS_DIR}"]]
                    ])
                }
            }
        }
    }

    post {
        always {
            // Archiver les rapports Allure
            archiveArtifacts artifacts: "${ALLURE_RESULTS_DIR}/**/*", fingerprint: true, allowEmptyArchive: true
            archiveArtifacts artifacts: "${ALLURE_REPORT_DIR}/**/*", fingerprint: true, allowEmptyArchive: true

            // Envoyer les notifications
            emailext (
                subject: "Build ${currentBuild.result}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """
                    <p>Build Status: ${currentBuild.result}</p>
                    <p>Build Number: ${env.BUILD_NUMBER}</p>
                    <p>Check console output at <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>
                """,
                recipientProviders: [[$class: 'DevelopersRecipientProvider']]
            )

            // Nettoyer l'espace de travail
            cleanWs()
        }

        success {
            echo 'Pipeline exécuté avec succès !'
        }

        failure {
            echo 'Le pipeline a échoué. Veuillez vérifier les logs pour plus de détails.'
        }
    }
} 