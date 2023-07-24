package org.devops

// check out
def Checkout() {
    println("CheckOut")
    checkout([
        $class: 'GitSCM',
        branches: [[name: '*/main']], 
        extensions: [], 
        userRemoteConfigs: [[credentialsId: 'gitlab-secret', 
        url: 'http://god.gitlab.com/backend/maven-demo.git'
        ]]])
    sh "ls -l "
}

// run build
def Build() {
    println("Build")
    sh "${params.buildCommand}"
}

// get branch version
def GetBranchVersion() {
    version = "${params.branchName}" - "origin/realse-"
    println(version)
    return version
}

// push repo
def PushRepo(commitID, version) {
    nexusArtifactUploader artifacts: [[artifactId: 'maven-demo', classifier: '', file: 'target/demo-0.0.1-SNAPSHOT.jar', type: 'jar']], 
    credentialsId: "61428597-8502-46ef-b47c-a6888ee073a9", 
    groupId: 'godit.com', 
    nexusUrl: '192.168.137.228:8081', 
    nexusVersion: 'nexus3', 
    protocol: 'http', 
    repository: 'maven-demo', 
    version: version + "-" + commitID
}