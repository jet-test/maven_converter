import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.MavenBuildStep
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2018_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2018.2"

project {

    vcsRoot(MavenProject)

    buildType(RunTestsUseOwn)
    buildType(RunTestsDefault)
    buildType(RunTestsDefaultMetaRunner)
    buildType(RunTestsDefaultDockerWrapperMetaRunner)
    buildType(RunTestsUseOwnFromTemplate)
    buildType(RunTestsUseOwnDockerWrapperFromTemplate)
    buildType(RunTestsDefaultFromTemplate)
    buildType(RunTestsUseOwnMetaRunner)
    buildType(RunTestsUseOwnDockerWrapper)
    buildType(RunTestsUseOwnDockerWrapperMetaRunner)
    buildType(RunTestsDefaultDockerWrapper)
    buildType(RunTestsDefaultDockerWrapperFromTemplate)

    template(RunTestsUseOwnTemplate)
    template(RunTestsUseOwnDockerWrapperTemplate)
    template(RunTestsDefaultTemplate)
    template(RunTestsDefaultDockerWrapperTemplate)
}

object RunTestsDefault : BuildType({
    name = "run tests, default"

    vcs {
        root(MavenProject)
    }

    steps {
        maven {
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            mavenVersion = defaultProvidedVersion()
        }
    }
})

object RunTestsDefaultDockerWrapper : BuildType({
    name = "run tests, default, docker wrapper"

    vcs {
        root(MavenProject)
    }

    steps {
        maven {
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            mavenVersion = defaultProvidedVersion()
            dockerImage = "maven:3-ibmjava-8-alpine"
            dockerImagePlatform = MavenBuildStep.ImagePlatform.Linux
        }
    }
})

object RunTestsDefaultDockerWrapperFromTemplate : BuildType({
    templates(RunTestsDefaultDockerWrapperTemplate)
    name = "run tests, default, docker wrapper, from template"
})

object RunTestsDefaultDockerWrapperMetaRunner : BuildType({
    name = "run tests, default, docker wrapper, meta-runner"

    vcs {
        root(MavenProject)
    }

    steps {
        step {
            type = "MavenWithVersionedSettings_RunTestsDefaultDockerWrapperMetaRunner1"
        }
    }
})

object RunTestsDefaultFromTemplate : BuildType({
    templates(RunTestsDefaultTemplate)
    name = "run tests, default, from template"
})

object RunTestsDefaultMetaRunner : BuildType({
    name = "run tests, default, meta-runner"

    vcs {
        root(MavenProject)
    }

    steps {
        step {
            type = "MavenWithVersionedSettings_RunTestsDefaultMetaRunner1"
        }
    }
})

object RunTestsUseOwn : BuildType({
    name = "run tests, use own"

    vcs {
        root(MavenProject)
    }

    steps {
        maven {
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            mavenVersion = defaultProvidedVersion()
            useOwnLocalRepo = true
        }
    }
})

object RunTestsUseOwnDockerWrapper : BuildType({
    name = "run tests, use own, docker wrapper"

    vcs {
        root(MavenProject)
    }

    steps {
        maven {
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            mavenVersion = defaultProvidedVersion()
            useOwnLocalRepo = true
            dockerImage = "maven:3-ibmjava-8-alpine"
            dockerImagePlatform = MavenBuildStep.ImagePlatform.Linux
        }
    }
})

object RunTestsUseOwnDockerWrapperFromTemplate : BuildType({
    templates(RunTestsUseOwnDockerWrapperTemplate)
    name = "run tests, use own, docker wrapper, from template"
})

object RunTestsUseOwnDockerWrapperMetaRunner : BuildType({
    name = "run tests, use own, docker wrapper, meta-runner"

    vcs {
        root(MavenProject)
    }

    steps {
        step {
            type = "MavenWithVersionedSettings_RunTestsUseOwnMetaRunner1"
        }
    }
})

object RunTestsUseOwnFromTemplate : BuildType({
    templates(RunTestsUseOwnTemplate)
    name = "run tests, use own, from template"
})

object RunTestsUseOwnMetaRunner : BuildType({
    name = "run tests, use own, meta-runner"

    vcs {
        root(MavenProject)
    }

    steps {
        step {
            type = "MavenWithVersionedSettings_RunTestsUseOwnMetaRunner1"
        }
    }
})

object RunTestsDefaultDockerWrapperTemplate : Template({
    name = "run tests, default, docker wrapper, template"

    vcs {
        root(MavenProject)
    }

    steps {
        maven {
            id = "RUNNER_1"
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            mavenVersion = defaultProvidedVersion()
            dockerImage = "maven:3-ibmjava-8-alpine"
            dockerImagePlatform = MavenBuildStep.ImagePlatform.Linux
        }
    }
})

object RunTestsDefaultTemplate : Template({
    name = "run tests, default, template"

    vcs {
        root(MavenProject)
    }

    steps {
        maven {
            id = "RUNNER_1"
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            mavenVersion = defaultProvidedVersion()
        }
    }
})

object RunTestsUseOwnDockerWrapperTemplate : Template({
    name = "run tests, use own, docker wrapper, template"

    vcs {
        root(MavenProject)
    }

    steps {
        maven {
            id = "RUNNER_1"
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            mavenVersion = defaultProvidedVersion()
            useOwnLocalRepo = true
            dockerImage = "maven:3-ibmjava-8-alpine"
            dockerImagePlatform = MavenBuildStep.ImagePlatform.Linux
        }
    }
})

object RunTestsUseOwnTemplate : Template({
    name = "run tests, use own, template"

    vcs {
        root(MavenProject)
    }

    steps {
        maven {
            id = "RUNNER_1"
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            mavenVersion = bundled_3_5()
            useOwnLocalRepo = true
        }
    }
})

object MavenProject : GitVcsRoot({
    name = "maven project"
    url = "https://github.com/burnasheva/maven_unbalanced_messages.git"
})
