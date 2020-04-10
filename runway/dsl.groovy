def gitUrl = 'git://github.com/mijo2/devops_projects/'

job('test-job') {
    scm {
        git(gitUrl)
        credentialsId("GitHub")
        branches("week-1")
    }
    triggers {
        scm('02 15 * * *')
    }
    steps {
        shell("cd week1_work")
        maven('-e clean install')
    }
}