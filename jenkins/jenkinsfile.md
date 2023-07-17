## jenkinsfile 学习

### jenkinsfile 结构

```text
pipeline {
    // 指定运行此流水线的节点
    agent any
    // 管道运行选项
    options {
        skipStagesAfterUnstable()
    }
    // 流水线阶段
    stages {
        // 构建阶段
        stage('Build') {
            steps {
                echo 'Build stage'
                // groovy code
                script {
                    name = "zhangsan"
                    if (name=="zhangsan") {
                        println("is right")
                    }
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Test stage'
            }
        }
    }
}
```
