import axios from 'axios'
import { JPA_API_URL } from '../../Constants'

class CityDataService {
    
    retrieveAllCities() {
      return axios.get(`${JPA_API_URL}/cities`);
     
    }
  
}

export default new CityDataService()