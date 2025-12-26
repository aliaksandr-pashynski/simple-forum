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
                // console.log(`keycloak.authenticated = ${keycloak.authenticated}`);
                // var expired = keycloak.isTokenExpired();
                // console.log(`keycloak.isTokenExpired() = ${expired}`);
                // if (keycloak.authenticated && keycloak.isTokenExpired()) {
                //     console.log('Token expired. Refreshing...');
                //     //   await keycloak.updateToken(30);
                // }
                //  await keycloak.updateToken(30);


                // console.log(`keycloak.isTokenExpired() = ${keycloak.isTokenExpired()}`);
                // console.log(`keycloak.authenticated = ${keycloak.authenticated}`);

                if (keycloak.refreshToken != null) {
                    try {
                        await keycloak.updateToken(30);
                    } catch (error) {
                        console.error('Failed to refresh token:', error);
                    }
                }

                console.log('Request:', {
                    method: config.method?.toUpperCase(),
                    url: config.url,
                    params: config.params,
                    // headers: config.headers
                });
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

    getInfoAboutMe() {
        return this.axiosInstance.get("/users/me").then(resp => resp.data);
    }
}