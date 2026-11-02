import http from "@/http-common";

class CategoryService {
    findAllCategory() {
        return http.get("/category");
    }
    saveNewCategory(data){
        return http.post("/category", data);
    }
    
    updateCategory(id, data){
        return http.put(`/category/${id}`,data);
    }

  
    deleteCategory(id){
        return http.delete(`/category/${id}`);
    }  



}

export default new CategoryService();