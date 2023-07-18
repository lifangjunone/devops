```grovvy
// gitlab 发送过来的数据转JSON
jsonData = readJSON text: "${Data}"
// Get git repo url
gitUrl = jsonData["project"]["git_http_url"]
// Get git repo branch
gitBranchName = jsonData["ref"] - "refs/heads/"
// Get commit id 
gitCommitId = jsonData["checkout_sha"]
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
}
```