import axios from "axios";

export class ApiForumService {

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

    createTopic(request) {
        return this.axiosInstance.post("/topics", request).then(resp => resp.data);
    }

    getPosts(topicId, page, size) {
        return this.axiosInstance.get("/posts", {
            params: {
                topicId,
                page: page ?? 0,
                size: size ?? 10,
            }
        }).then(resp => resp.data);
    }

    createPost(request) {
        return this.axiosInstance.post("/posts", request).then(resp => resp.data);
    }

    uploadAvatar(request) {
        return this.axiosInstance.post("/users/avatar", request, {
            headers: { 'Content-Type': 'multipart/form-data' },
        }).then(resp => resp.data);
    }
}