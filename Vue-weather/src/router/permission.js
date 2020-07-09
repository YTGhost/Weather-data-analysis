import router from "./index";
router.beforeEach(async(to, from, next)=>{
    let type=window.document.cookie
    const whiteList=['/']
    const userList=['/users']
    const adminList=['/admin']
    console.log('1111',to)
    console.log(typeof type,type,type.length)
    if(type===''){
        if(whiteList.indexOf(to.path)!==-1){
            next()
        }else{
            next('/')
        }
    }else if(type==='1'){
        if(userList.indexOf(to.path)!==-1){
            next()
        }else {
            next('/')
        }
    }else if(type==='2'){
        if(adminList.indexOf(to.path)!==-1||userList.indexOf(to.path)!==-1){
            next()
        }else {
            next('/')
        }
    }
    next()
})
