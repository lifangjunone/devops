```groovy
pipeline {
    // 执行的agent
    agent any
    // 配置选项
    options {
        buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '2', numToKeepStr: '2')
    }
    // 构建参数
    parameters {
        choice choices: ['dev', 'test', 'stage', 'prod'], description: 'please choice a env', name: 'ENV'
        string defaultValue: '1.1.1', name: 'VERSION'
    }
    // 触发器
    triggers {
        cron '_/1 _ \* \* \*'
    }
    // 阶段
    stages {
        stage('Build') {
            // 用户输入交互
            input {
                message 'Please choice your options'
                ok '提交'
                parameters {
                    choice choices: ['rollback', 'stop'], name: 'runOptions'
                }
            }
            steps {
                echo "${env.ENV}"
                echo "${params.VERSION}"
                echo 'Build stage'
                echo "your choice: ${runOptions}"
                // groovy 脚本
                script{
                    name = "zhangsan"
                    if (name=="zhangsan") {
                        println("name is right")
                    }

                    // 选择
                    if ("${runOptions}" == "stop") {
                        println("stop ...")
                        env.runOptions = "${runOptions}"
                    } else {
                        println("rollback ...")
                        env.runOptions = "${runOptions}"
                    }
                }
            }
        }
        stage('rollback') {
            // 当环境变量 runOptions:为rollback 时执行的阶段
            when {
                environment name: 'runOptions', value: 'rollback'
            }
            steps {
                script {
                    println("rollback ...")
                }
            }
        }
    }

}
```
