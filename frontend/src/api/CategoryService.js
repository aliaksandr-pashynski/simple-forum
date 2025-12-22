import axiosInstance from "./ApiClient";

export const CategoryService = {
    getCategories() {
        return axiosInstance.get("/categories").then(resp => resp.data['categories']);
    }
};