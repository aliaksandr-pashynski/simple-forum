import axios from "axios";

export class ApiService {

    constructor(keycloak) {
        this.keycloak = keycloak;
        this.axiosInstance = axios.create({
            baseURL: "http://localhost:8080/forum-service/api/v1/",
            timeout: 5000,
            headers: {
                "Content-Type": "application/json",
            },
        })
        this.axiosInstance.interceptors.request.use(
            async (config) => {
                // refresh token if needed
                //   await keycloak.updateToken(30);
                console.log('inside interceptor');
                if (this.keycloak?.authenticated) {
                    config.headers.Authorization = `Bearer ${this.keycloak.token}`;
                }

                return config;
            },
            (error) => Promise.reject(error)
        );
    }

    getCategories() {
        return this.axiosInstance.get("/categories").then(resp => resp.data['categories']);
    }

    getTopics(categoryId, page, size) {
        return this.axiosInstance.get("/topics", {
            params: {
                categoryId,
                page: page ?? 0,
                size: size ?? 15,
            }
        });
    }
}