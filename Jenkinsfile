pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9'
        jdk 'JDK 17'
        allure 'allure'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        disableConcurrentBuilds()
    }

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Sélectionnez le navigateur pour les tests')
        string(name: 'CUCUMBER_TAGS', defaultValue: '@all', description: 'Tags Cucumber à exécuter')
        booleanParam(name: 'SKIP_TESTS', defaultValue: false, description: 'Ignorer les tests ?')
    }

    environment {
        ALLURE_RESULTS_DIR = 'target/allure-results'
        ALLURE_REPORT_DIR = 'target/allure-report'
        MAVEN_OPTS = '-Xmx2048m'
        BRANCH_NAME = "${env.BRANCH_NAME}"
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
                allOf {
                    not { expression { return params.SKIP_TESTS } }
                    anyOf {
                        branch 'main'
                        branch 'QA'
                    }
                }
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
            when {
                anyOf {
                    branch 'main'
                    branch 'QA'
                }
            }
            steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: "${ALLURE_RESULTS_DIR}"]],
                        report: "${ALLURE_REPORT_DIR}"
                    ])
                }
            }
        }
    }

    post {
        always {
            script {
                if (env.BRANCH_NAME in ['main', 'QA']) {
                    archiveArtifacts artifacts: "${ALLURE_RESULTS_DIR}/**/*", fingerprint: true, allowEmptyArchive: true
                    archiveArtifacts artifacts: "${ALLURE_REPORT_DIR}/**/*", fingerprint: true, allowEmptyArchive: true
                }

                if (env.EMAIL_RECIPIENTS) {
                    emailext (
                        subject: "[${env.BRANCH_NAME}] Build ${currentBuild.result}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                        body: """
                            <p>Branch: ${env.BRANCH_NAME}</p>
                            <p>Build Status: ${currentBuild.result}</p>
                            <p>Build Number: ${env.BUILD_NUMBER}</p>
                            <p>Check console output at <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>
                        """,
                        to: env.EMAIL_RECIPIENTS
                    )
                }
            }
            cleanWs()
        }

        success {
            echo "Pipeline sur la branche ${env.BRANCH_NAME} exécuté avec succès !"
        }

        failure {
            echo "Le pipeline sur la branche ${env.BRANCH_NAME} a échoué. Veuillez vérifier les logs pour plus de détails."
        }
    }
} 