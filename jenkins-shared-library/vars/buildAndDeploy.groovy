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
        sh 'docker build -t <your-ecr-repo>:${env.BUILD_TAG} .'
    }
    stage('Push Docker Image') {
        withDockerRegistry([credentialsId: 'ecr-credentials', url: '<your-ecr-url>']) {
            sh 'docker push <your-ecr-repo>:${env.BUILD_TAG}'
        }
    }
    stage('Deploy with Helm') {
        sh """
        helm repo add my-app https://charts.example.com
        helm upgrade --install my-app ./helm/my-app --set image.tag=${env.BUILD_TAG}
        """
    }
}

