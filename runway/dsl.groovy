def gitUrl = 'https://github.com/mijo2/devops_projects/'

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
        scm('47 15 * * *')
    }
    steps {
        shell("cd week1_work")
        maven('-e clean install')
    }
}