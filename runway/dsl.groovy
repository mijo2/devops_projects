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
        scm('* * * * *')
    }
    steps {
        dir('week1_work'){
            sh "pwd"
        }
        maven('-e clean install')
    }
}