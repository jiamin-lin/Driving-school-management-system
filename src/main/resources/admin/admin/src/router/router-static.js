import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
import adminexam from '@/views/modules/exampaperlist/exam'
    import kaoshiyuyue from '@/views/modules/kaoshiyuyue/list'
    import news from '@/views/modules/news/list'
    import examfailrecord from '@/views/modules/examfailrecord/list'
    import lianxikecheng from '@/views/modules/lianxikecheng/list'
    import examquestion from '@/views/modules/examquestion/list'
    import liancheyuyue from '@/views/modules/liancheyuyue/list'
    import biyexueyuan from '@/views/modules/biyexueyuan/list'
    import exampaper from '@/views/modules/exampaper/list'
    import kaoshianpai from '@/views/modules/kaoshianpai/list'
    import users from '@/views/modules/users/list'
    import jiaxiaojiaolian from '@/views/modules/jiaxiaojiaolian/list'
    import jiaolianpingjia from '@/views/modules/jiaolianpingjia/list'
    import baomingxinxi from '@/views/modules/baomingxinxi/list'
    import yonghu from '@/views/modules/yonghu/list'
    import exampaperlist from '@/views/modules/exampaperlist/list'
    import xueyuanxinxi from '@/views/modules/xueyuanxinxi/list'
    import kaoshichengji from '@/views/modules/kaoshichengji/list'
    import examquestionbank from '@/views/modules/examquestionbank/list'
    import config from '@/views/modules/config/list'
    import examrecord from '@/views/modules/examrecord/list'
    import newstype from '@/views/modules/newstype/list'


//2.配置路由   注意：名字
export const routes = [{
    path: '/',
    name: '系统首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '系统首页',
      component: Home,
      meta: {icon:'', title:'center', affix: true}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }
      ,{
	path: '/kaoshiyuyue',
        name: '考试预约',
        component: kaoshiyuyue
      }
      ,{
	path: '/news',
        name: '驾校公告',
        component: news
      }
      ,{
	path: '/examfailrecord',
        name: '错题本',
        component: examfailrecord
      }
      ,{
	path: '/lianxikecheng',
        name: '练习课程',
        component: lianxikecheng
      }
      ,{
	path: '/examquestion',
        name: '试题管理',
        component: examquestion
      }
      ,{
	path: '/liancheyuyue',
        name: '练车预约',
        component: liancheyuyue
      }
      ,{
	path: '/biyexueyuan',
        name: '毕业学员',
        component: biyexueyuan
      }
      ,{
	path: '/exampaper',
        name: '模拟测试管理',
        component: exampaper
      }
      ,{
	path: '/kaoshianpai',
        name: '考试安排',
        component: kaoshianpai
      }
      ,{
	path: '/users',
        name: '管理员',
        component: users
      }
      ,{
	path: '/jiaxiaojiaolian',
        name: '驾校教练',
        component: jiaxiaojiaolian
      }
      ,{
	path: '/jiaolianpingjia',
        name: '教练评价',
        component: jiaolianpingjia
      }
      ,{
	path: '/baomingxinxi',
        name: '报名信息',
        component: baomingxinxi
      }
      ,{
	path: '/yonghu',
        name: '用户',
        component: yonghu
      }
      ,{
	path: '/exampaperlist',
        name: '模拟测试列表',
        component: exampaperlist
      }
      ,{
	path: '/xueyuanxinxi',
        name: '学员信息',
        component: xueyuanxinxi
      }
      ,{
	path: '/kaoshichengji',
        name: '考试成绩',
        component: kaoshichengji
      }
      ,{
	path: '/examquestionbank',
        name: '试题库管理',
        component: examquestionbank
      }
      ,{
	path: '/config',
        name: '轮播图管理',
        component: config
      }
      ,{
	path: '/examrecord',
        name: '考试记录',
        component: examrecord
      }
      ,{
	path: '/newstype',
        name: '驾校公告分类',
        component: newstype
      }
    ]
  },
  {
    path: '/adminexam',
    name: 'adminexam',
    component: adminexam,
    meta: {icon:'', title:'adminexam'}
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
   return originalPush.call(this, location).catch(err => err)
}
export default router;
