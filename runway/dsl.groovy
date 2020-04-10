def gitUrl = 'git://github.com/mijo2/devops_projects/'

job('test-job') {
    scm {
        git{
            remote{
                url(gitUrl)
                credentials('GitHub')
            }
            branch('*/week-1')
        }
    }
    triggers {
        scm('02 15 * * *')
    }
    steps {
        shell("cd week1_work")
        maven('-e clean install')
    }
}