package org.devops


def GetCommitID() {
    commitIDSource = sh returnStdout: true, script: 'git rev-parse HEAD'
    commitID =  commitIDSource[0..7]
    return commitID
}

