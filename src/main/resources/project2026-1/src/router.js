import { createRouter, createWebHistory } from "vue-router";


const routes = [
{
    path: "/",
    name: "home",
    component:()=>import("./components/v-main-home")
},
{
    path: "/category",
    name: "category",
    component:()=>import("./components/v-main-category")
}


];

const router = createRouter({
    history: createWebHistory(),
    routes

});

export default router;


