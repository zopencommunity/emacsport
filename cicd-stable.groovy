
node('linux')
{
  stage ('Poll') {
                // Poll for local changes
                checkout([
                        $class: 'GitSCM',
                        branches: [[name: '*/main']],
                        doGenerateSubmoduleConfigurations: false,
                        extensions: [],
                        userRemoteConfigs: [[url: 'https://github.com/zopencommunity/emacsport.git']]])
  }

  stage('Build') {
    build job: 'Port-Pipeline', parameters: [
      string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/emacsport.git'), 
      string(name: 'PORT_DESCRIPTION', value: 'emacs editor' ),
      string(name: 'BUILD_LINE', value: 'STABLE'), string(name: 'NODE_LABEL', value: "v2r5") 
    ]
  }
}

