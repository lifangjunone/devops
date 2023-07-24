package org.devops

// 发送HTTP请求
def HttpReq(method, apiUrl) {


    withCredentials([string(credentialsId: '56e440a7-6c21-4931-91f3-9b836bba9a0e', variable: 'gitlabApiToken')]) {
        // some block
                            
        response = sh returnStdout: true, script: """
            curl --location --request GET   http://god.gitlab.com//api/v4/projects/${apiUrl}  --header "PRIVATE-TOKEN: ${gitlabApiToken}"
        """
        response = readJSON text: response - "\n"
        return response
    }
}

// 获取分支 CommitID
def GetBranchCommitID(projectID, branchName) {
    println(branchName)
    apiUrl = "${projectID}/repository/branches/${branchName}"
    response = HttpReq("GET", apiUrl)
    println(response)
    return response.commit.short_id
}


// 获取项目ID通过项目名
def GetProjectIDByName(projectName, groupName) {
    apiUrl = "?search=${projectName}"
    response = HttpReq("GET", apiUrl)
    if (response != []) {
        for (p in response) {
            if (p["namespace"]["name"] == groupName) {
                return response[0]["id"]
            }
        }
    }
}