[![Project Build Status][github-actions-image]][github-actions-url]

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=io.mendirl%3Ajupiter-parent&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=io.mendirl%3Ajupiter-parent)

[github-actions-url]: https://github.com/mendirl/project/actions

[github-actions-image]: https://github.com/mendirl/project/workflows/Java%20CI/badge.svg

#### DEPLOY & RELEASE

+ https://github.com/marketplace/actions/setup-java-jdk
+ https://docs.github.com/en/free-pro-team@latest/actions/guides/publishing-java-packages-with-maven


+ branch deploy used to deploy current version (maven & docker packages)
+ branch release used to create a release version and deploy it (maven & docker packages)

#### COMMAND

    ./mvnw verify
    ./mvnw verify -DskipTests
    ./mvnw verify -Pdocker
    ./mvnw verify -Pdocker,native
    ./mvnw verify -Pnative

#### SOME ISSUES

###### maven ci friendly

+ https://maven.apache.org/maven-ci-friendly.html
+ https://issues.apache.org/jira/browse/MRELEASE-935
+ https://issues.apache.org/jira/browse/MNG-6656

