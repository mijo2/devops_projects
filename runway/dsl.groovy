def gitUrl = 'https://github.com/mijo2/devops_projects/'
def path_to_working = '/var/jenkins_home/workspace/test-job/week1_work'

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
        dir(path_to_working){
            sh "pwd"
        }
        maven('-e clean install')
    }
}