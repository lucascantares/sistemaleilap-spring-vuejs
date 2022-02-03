import axios from 'axios'

axios.interceptors.response.use(
    function(response) { return response;}, 
    function(error) {
        // handle error
        if (error.response) {
            alert(error.response.data.message);
        }
});

export const http = axios.create({
    baseURL: 'http://localhost:8080/leilao'
})
