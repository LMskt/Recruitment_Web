import store from "@/store"
import axios from "axios";
import el from "element-ui/src/locale/lang/el";
import router from "@/router";

// let localbaseURL='https://localhost:8881/';
// let localbaseURL='https://129.28.159.48:8881/';
// let localbaseURL='https://wantyou.csust-iotlab.com/';
let localbaseURL='https://yidaodao.top/';
axios.defaults.baseURL=localbaseURL;

export var host=localbaseURL;
const Axios=axios.create({
    baseURL:localbaseURL,
    timeout:20000,
})


Axios.interceptors.request.use(config=>{
    //如果storage里面存在token，将其放在请求头里
    if(store.getters.get_jwt!=''){
        config.headers.common['jwt']=store.getters.get_jwt;
    }else {
        //不存在token跳转登录页
        //登录页的axios不要用封装的Axios来写
        router.push("/homepage")
    }
    return config;
},error => {
    console.log(error)
   return Promise.reject(error)
})


Axios.interceptors.response.use(response=>{
    if(response.data.errorCode==1){
        alert("请求失败，请重试")
    }
    return response;
    },error => {
    return Promise.reject(error)
})


export function httpGet({
    url,
    params={}
}){
    return new Promise((resolve, reject) => {
       Axios({
           url:url,
           method:"get",
           params:params
       }).then(res=>{
           resolve(res.data)
       }).catch(err=>{
           reject(err)
           }
       )
    })
}

export function httpPost({
    url,
    params={}
}){
    return new Promise((resolve, reject) => {
        Axios({
            url:url,
            method:"post",
            params:params
        }).then(res=>{
            resolve(res.data)
        }).catch(err=>{
            reject(err)
        })
    })
}










