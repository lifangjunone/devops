package org.devops


def GetCommitID() {
    commitIDSource = sh returnStdout: true, script: 'git rev-parse HEAD'
    commitID =  commitIDSource[0..7]
    return commitID
}

def GetCommitID2() {
    branchName = "${params.branchName}" - "origin/"
    projectName = "${JOB_NAME}".split('_')[0]
    projectID = mygitlab.GetProjectIDByName(projectName, "backend")
    println(projectID)
    commitId = mygitlab.GetBranchCommitID(projectID, branchName)
    return commitId
}