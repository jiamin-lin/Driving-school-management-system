const base = {
    get() {
        return {
            url : "http://localhost:8080/springboot83r70751/",
            name: "springboot83r70751",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springboot83r70751/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "智慧驾校管理系统的设计与实现"
        } 
    }
}
export default base
