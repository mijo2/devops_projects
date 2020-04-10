def gitUrl = 'git://github.com/mijo2/devops_projects/tree/week-1'

job('test-job') {
    scm {
        git(gitUrl)
    }
    triggers {
        scm('45 14 * * *')
    }
    steps {
        shell("cd week1_work")
        maven('-e clean install')
    }
}