<template>
    <div>
        <h1> Category </h1>

        <table class="table">
            <thead>
            <tr>
                <th>name</th>
                <th>description</th>
                <th>image</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(category, index) in categories" :key="index">
                <td> {{ category.name }} </td>
                <td> {{ category.description }} </td>
                <td> {{ category.image }} </td>
            </tr>
        </tbody>
        </table>

        <h2> Save new Category </h2>

        <table>
            <tbody>
            <tr>
                <td><label for="name">Name</label></td>
                <td><input type="text" 
                    id="name" 
                    name="name" 
                    required
                    v-model="category.name"
                    >  </td>
            </tr>
            <tr>
                <td><label for="description">description</label></td>
                <td><input type="text" 
                    id="description" 
                    name="description" 
                    required
                    v-model="category.description"
                    >  </td>
            </tr>
            <tr>
                <td><label for="image">image</label></td>
                <td><input type="text" 
                    id="image" 
                    name="image" 
                    required
                    v-model="category.image"
                    >  </td>
            </tr>
            <tr>
                <td>

                </td>
                <td>
                    <button @click="saveNewCategory"  class="btn btn-primary"> Add </button>
                </td>

            </tr>
            </tbody>
        </table>

        <h2> Update/Delete </h2>

        <div class="container-fluid">
            <div class="row">
                <div class="col-4">

                    <ul class="list-group">
                        <li v-for="(category, index) of categories" 
                        v-bind:class="{'active': selectCategoryIndex === index }"
                        :key="index"
                        @click=selectCategoryByIndex(index)
                        class="list-group-item">{{ category.name }}</li>
                    </ul>

                </div>
                <div class="col-8">
                    {{ category1 }}

                <div v-if="category1">  

                    <table>
                        <thead>
                            <tr>
                                <th>name</th>
                                <th>desc</th>
                                <th>image</th>
                            </tr>
                        </thead>
                        <tbody>
            <tr>
                <td><label for="name">Name</label></td>
                <td><input type="text" 
                    id="name" 
                    name="name" 
                    required
                    v-model="category1.name">  </td>
            </tr>
            <tr>
                <td><label for="description">description</label></td>
                <td><input type="text" 
                    id="description" 
                    name="description" 
                    required
                    v-model="category1.description"
                    >  </td>
            </tr>
            <tr>
                <td><label for="image">image</label></td>
                <td><input type="text" 
                    id="image" 
                    name="image" 
                    required
                    v-model="category1.image"
                    >  </td>
            </tr>
            <tr>
                <td>
                    
                </td>
                <td>
                    <button @click="updateCategory"  class="btn btn-primary"> Update </button>
                    <button @click="deleteCategory"  class="btn btn-primary"> Delete </button>
                </td>

            </tr>
        </tbody>
        </table>

    </div>  



                </div>
            </div>
        </div>

    </div>
</template>

<script>
import CategoryService from "../services/CategoryService"

export default {
    name: "v-main-category",
    components:{

    },
    data(){
        return{
            categories: [],
            category: {
                id: null,
                name: "",
                description: "",
                image: ""
            },
            category1:"",
            selectIndex: 0,
            selectCategoryIndex: 3
        }
    },
    methods:{
        getCategories (){
            CategoryService.findAllCategory()
            .then((responce)=>{
                this.categories = responce.data;
                console.log(responce.data);
            }
            )
            .catch((e)=>{
                console.log(e);
            })
        },
        saveNewCategory(){
            var myData = {
                name: this.category.name,
                description: this.category.description,
                image: this.category.image
            }
            CategoryService.saveNewCategory(myData)
            .then((responce)=>{
                this.categories = responce.data;
                console.log(responce.data);
                this.getCategories();
            }
            )
            .catch((e)=>{
                console.log(e);
            })
            this.category.name ="";
            this.category.description ="";
            this.category.image ="";

        },

        selectCategoryByIndex(index){
            this.category1 = this.categories[index];
            this.selectIndex = index;
            this.selectCategoryIndex = index;
        },

        updateCategory(){
            CategoryService.updateCategory(this.category1.id ,this.category1)
            .then((responce)=>{
                this.categories = responce.data;
                console.log(responce.data);
                this.getCategories();
            }
            )
            .catch((e)=>{
                console.log(e);
            })
        },

        deleteCategory(){
            CategoryService.deleteCategory(this.category1.id)
            .then((responce)=>{
                this.categories = responce.data;
                console.log(responce.data);
                this.getCategories();
            }
            )
            .catch((e)=>{
                console.log(e);
            });

            for(let index=0; index<this.categories.length; index++){
                if(this.categories[index].id ==this.category1.id){
                    this.categories.splice(index, 1);
                }
            }

            this.category1 = "";

        }

        
    },
    mounted(){
        this.getCategories();
    }
}
</script>

<style scoped>
</style>
