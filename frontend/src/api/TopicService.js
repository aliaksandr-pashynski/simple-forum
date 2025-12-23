import axiosInstance from "./ApiClient";

export const TopicService = {
    getTopics(categoryId, page, size) {
        return axiosInstance.get("/topics", {
            params: {
                categoryId,
                page: page ?? 0,
                size: size ?? 15,
            }
        });
    }
};