def call() {
    stage('Checkout') {
        checkout scm
    }
    stage('Compile') {
        sh 'mvn clean compile'
    }
    stage('Test') {
        sh 'mvn test'
    }
    stage('Package') {
        sh 'mvn package'
    }
    stage('Build Docker Image') {
        sh 'docker build -t 058264127488.dkr.ecr.us-west-1.amazonaws.com/my-app:${env.BUILD_TAG} .'
    }
    stage('Push Docker Image') {
        withDockerRegistry([credentialsId: 'ecr-credentials', url: '058264127488.dkr.ecr.us-west-1.amazonaws.com/my-app']) {
            sh 'docker push 058264127488.dkr.ecr.us-west-1.amazonaws.com/my-app:${env.BUILD_TAG}'
        }
    }
    stage('Deploy with Helm') {
        sh """
        helm repo add my-app https://charts.example.com
        helm upgrade --install my-app ./helm/my-app --set image.tag=${env.BUILD_TAG}
        """
    }
}

