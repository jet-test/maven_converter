import jetbrains.buildServer.configs.kotlin.v2018_2.*

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
        step {
            type = "Maven2"
            param("runnerArgs", "-Dmaven.test.failure.ignore=true")
            param("maven.path", "%teamcity.tool.maven.DEFAULT%")
            param("goals", "clean test")
        }
    }
})

object RunTestsDefaultDockerWrapper : BuildType({
    name = "run tests, default, docker wrapper"

    vcs {
        root(MavenProject)
    }

    steps {
        step {
            type = "Maven2"
            param("plugin.docker.imagePlatform", "linux")
            param("runnerArgs", "-Dmaven.test.failure.ignore=true")
            param("plugin.docker.imageId", "maven:3-ibmjava-8-alpine")
            param("maven.path", "%teamcity.tool.maven.DEFAULT%")
            param("goals", "clean test")
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
        step {
            type = "Maven2"
            param("runnerArgs", "-Dmaven.test.failure.ignore=true")
            param("useOwnLocalRepo", "true")
            param("maven.path", "%teamcity.tool.maven.DEFAULT%")
            param("goals", "clean test")
        }
    }
})

object RunTestsUseOwnDockerWrapper : BuildType({
    name = "run tests, use own, docker wrapper"

    vcs {
        root(MavenProject)
    }

    steps {
        step {
            type = "Maven2"
            param("runnerArgs", "-Dmaven.test.failure.ignore=true")
            param("plugin.docker.imageId", "maven:3-ibmjava-8-alpine")
            param("maven.path", "%teamcity.tool.maven.DEFAULT%")
            param("plugin.docker.imagePlatform", "linux")
            param("useOwnLocalRepo", "true")
            param("goals", "clean test")
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
        step {
            id = "RUNNER_1"
            type = "Maven2"
            param("plugin.docker.imagePlatform", "linux")
            param("runnerArgs", "-Dmaven.test.failure.ignore=true")
            param("plugin.docker.imageId", "maven:3-ibmjava-8-alpine")
            param("maven.path", "%teamcity.tool.maven.DEFAULT%")
            param("goals", "clean test")
        }
    }
})

object RunTestsDefaultTemplate : Template({
    name = "run tests, default, template"

    vcs {
        root(MavenProject)
    }

    steps {
        step {
            id = "RUNNER_1"
            type = "Maven2"
            param("runnerArgs", "-Dmaven.test.failure.ignore=true")
            param("maven.path", "%teamcity.tool.maven.DEFAULT%")
            param("goals", "clean test")
        }
    }
})

object RunTestsUseOwnDockerWrapperTemplate : Template({
    name = "run tests, use own, docker wrapper, template"

    vcs {
        root(MavenProject)
    }

    steps {
        step {
            id = "RUNNER_1"
            type = "Maven2"
            param("runnerArgs", "-Dmaven.test.failure.ignore=true")
            param("plugin.docker.imageId", "maven:3-ibmjava-8-alpine")
            param("maven.path", "%teamcity.tool.maven.DEFAULT%")
            param("plugin.docker.imagePlatform", "linux")
            param("useOwnLocalRepo", "true")
            param("goals", "clean test")
        }
    }
})

object RunTestsUseOwnTemplate : Template({
    name = "run tests, use own, template"

    vcs {
        root(MavenProject)
    }

    steps {
        step {
            id = "RUNNER_1"
            type = "Maven2"
            param("runnerArgs", "-Dmaven.test.failure.ignore=true")
            param("useOwnLocalRepo", "true")
            param("maven.path", "%teamcity.tool.maven3_5%")
            param("goals", "clean test")
        }
    }
})

object MavenProject : VcsRoot({
    type = "jetbrains.git"
    name = "maven project"
    param("url", "https://github.com/burnasheva/maven_unbalanced_messages.git")
})
