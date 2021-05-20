import axios from 'axios'


class HelloWorldService {
    executeHelloWorldService() {
      //  let username = 'nataliia'
      //  let password = '1'

     //   let basicAuthHeader = 'Basic ' +  window.btoa(username + ":" + password)

        return axios.get('http://localhost:8080/hello-world'
      //  , 
      //  {
      //      headers : {
      //          authorization: basicAuthHeader
      //      }
      //  }
        )
    }

    executeHelloWorldBeanService() {
        return axios.get('http://localhost:8080/hello-world-bean')
    }

    executeHelloWorldWithVariable(name){
      

     //   return axios.get(`http://localhost:8080/hello-world/path-variable/${name}`)       
     return axios.get(`http://localhost:8080/userr/${name}`)       
    }
}

 
 export default new HelloWorldService()