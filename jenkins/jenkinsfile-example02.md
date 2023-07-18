```grovvy
// print value
println("object_kind: ${object_kind}")
println("after: ${after}")
println("before: ${before}")

// gitlab 发送过来的数据转JSON
jsonData = readJSON text: "${Data}"
// Get git repo url
gitUrl = jsonData["project"]["git_http_url"]
// Get git repo branch
gitBranchName = jsonData["ref"] - "refs/heads/"
// Get commit id
gitCommitId = jsonData["checkout_sha"]
// Get git user email
userEmail = jsonData["user_email"]
currentBuild.displayName = gitCommitId
currentBuild.description = "branchName: ${gitBranchName}"
pipeline {
    agent { label 'python' }
    stages {
        stage('CheckOut') {
            steps {
                script {
                    checkout(
                        [
                            $class: 'GitSCM',
                            branches: [[name: "${gitBranchName}"]],
                            extensions: [],
                            userRemoteConfigs: [[credentialsId: 'gitlab-admin', url: "${gitUrl}"]]
                        ]
                    )
                    sh "ls -l "
                }
            }
        }
    }
    post {
        always {
            script {
                echo "start send"
                EmailUser(userEmail, "${currentBuild.currentResult}")
                echo "userEmil: ${userEmail}"
                echo "send send"
            }
        }
    }
}

def EmailUser(userEmail, status) {
    echo "my userEmail ${userEmail}"
    emailext body: "BUILD_ID: ${BUILD_ID} -JOB_NAME: ${JOB_NAME} - BUILD_RESULT: ${currentBuild.result} GIT_COMMIT_ID: ${gitCommitId}",
    subject: 'Jenkins Build',
    to: userEmail
}
```
