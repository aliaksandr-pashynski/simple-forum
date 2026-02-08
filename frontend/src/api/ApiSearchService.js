import axios from "axios";

export class ApiSearchService {

    constructor() {
        this.axiosInstance = axios.create({
            baseURL: "http://localhost:8081/search-service/",
            timeout: 5000,
            headers: {
                "Content-Type": "application/json",
            }
        })
    }

    searchTopics(request) {
        return this.axiosInstance.post("/topics/search", request).then(resp => resp.data);
    }
}