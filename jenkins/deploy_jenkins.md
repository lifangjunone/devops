# 部署jenkins
```sh
docker run -itd --name jenkins -p 8080:8080 -p 50000:50000 -e JAVA_OPTS="-Dorg.apache.commons.jelly.tags.fmt.timeZone='Asia/Shanghai'" --privileged=true --restart=always -v /Users/lifangjun/devops/jenkins_home:/var/jenkins_home jenkins/jenkins:2.346.3-2-lts-jdk11
```

