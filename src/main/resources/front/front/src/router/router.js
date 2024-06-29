import VueRouter from 'vue-router'

//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import ExamPaper from '../pages/exam/examPaper'
import Exam from '../pages/exam/exam'
import ExamList from '../pages/exam/examList'
import ExamRecord from '../pages/exam/examRecord'
import Storeup from '../pages/storeup/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import payList from '../pages/pay'

import yonghuList from '../pages/yonghu/list'
import yonghuDetail from '../pages/yonghu/detail'
import yonghuAdd from '../pages/yonghu/add'
import jiaxiaojiaolianList from '../pages/jiaxiaojiaolian/list'
import jiaxiaojiaolianDetail from '../pages/jiaxiaojiaolian/detail'
import jiaxiaojiaolianAdd from '../pages/jiaxiaojiaolian/add'
import lianxikechengList from '../pages/lianxikecheng/list'
import lianxikechengDetail from '../pages/lianxikecheng/detail'
import lianxikechengAdd from '../pages/lianxikecheng/add'
import baomingxinxiList from '../pages/baomingxinxi/list'
import baomingxinxiDetail from '../pages/baomingxinxi/detail'
import baomingxinxiAdd from '../pages/baomingxinxi/add'
import xueyuanxinxiList from '../pages/xueyuanxinxi/list'
import xueyuanxinxiDetail from '../pages/xueyuanxinxi/detail'
import xueyuanxinxiAdd from '../pages/xueyuanxinxi/add'
import liancheyuyueList from '../pages/liancheyuyue/list'
import liancheyuyueDetail from '../pages/liancheyuyue/detail'
import liancheyuyueAdd from '../pages/liancheyuyue/add'
import kaoshiyuyueList from '../pages/kaoshiyuyue/list'
import kaoshiyuyueDetail from '../pages/kaoshiyuyue/detail'
import kaoshiyuyueAdd from '../pages/kaoshiyuyue/add'
import kaoshianpaiList from '../pages/kaoshianpai/list'
import kaoshianpaiDetail from '../pages/kaoshianpai/detail'
import kaoshianpaiAdd from '../pages/kaoshianpai/add'
import kaoshichengjiList from '../pages/kaoshichengji/list'
import kaoshichengjiDetail from '../pages/kaoshichengji/detail'
import kaoshichengjiAdd from '../pages/kaoshichengji/add'
import biyexueyuanList from '../pages/biyexueyuan/list'
import biyexueyuanDetail from '../pages/biyexueyuan/detail'
import biyexueyuanAdd from '../pages/biyexueyuan/add'
import jiaolianpingjiaList from '../pages/jiaolianpingjia/list'
import jiaolianpingjiaDetail from '../pages/jiaolianpingjia/detail'
import jiaolianpingjiaAdd from '../pages/jiaolianpingjia/add'
import newstypeList from '../pages/newstype/list'
import newstypeDetail from '../pages/newstype/detail'
import newstypeAdd from '../pages/newstype/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'examPaper',
					component: ExamPaper
				},
				{
					path: 'examList',
					component:ExamList
				},
				{
					path: 'examRecord/:type',
					component:ExamRecord
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'yonghu',
					component: yonghuList
				},
				{
					path: 'yonghuDetail',
					component: yonghuDetail
				},
				{
					path: 'yonghuAdd',
					component: yonghuAdd
				},
				{
					path: 'jiaxiaojiaolian',
					component: jiaxiaojiaolianList
				},
				{
					path: 'jiaxiaojiaolianDetail',
					component: jiaxiaojiaolianDetail
				},
				{
					path: 'jiaxiaojiaolianAdd',
					component: jiaxiaojiaolianAdd
				},
				{
					path: 'lianxikecheng',
					component: lianxikechengList
				},
				{
					path: 'lianxikechengDetail',
					component: lianxikechengDetail
				},
				{
					path: 'lianxikechengAdd',
					component: lianxikechengAdd
				},
				{
					path: 'baomingxinxi',
					component: baomingxinxiList
				},
				{
					path: 'baomingxinxiDetail',
					component: baomingxinxiDetail
				},
				{
					path: 'baomingxinxiAdd',
					component: baomingxinxiAdd
				},
				{
					path: 'xueyuanxinxi',
					component: xueyuanxinxiList
				},
				{
					path: 'xueyuanxinxiDetail',
					component: xueyuanxinxiDetail
				},
				{
					path: 'xueyuanxinxiAdd',
					component: xueyuanxinxiAdd
				},
				{
					path: 'liancheyuyue',
					component: liancheyuyueList
				},
				{
					path: 'liancheyuyueDetail',
					component: liancheyuyueDetail
				},
				{
					path: 'liancheyuyueAdd',
					component: liancheyuyueAdd
				},
				{
					path: 'kaoshiyuyue',
					component: kaoshiyuyueList
				},
				{
					path: 'kaoshiyuyueDetail',
					component: kaoshiyuyueDetail
				},
				{
					path: 'kaoshiyuyueAdd',
					component: kaoshiyuyueAdd
				},
				{
					path: 'kaoshianpai',
					component: kaoshianpaiList
				},
				{
					path: 'kaoshianpaiDetail',
					component: kaoshianpaiDetail
				},
				{
					path: 'kaoshianpaiAdd',
					component: kaoshianpaiAdd
				},
				{
					path: 'kaoshichengji',
					component: kaoshichengjiList
				},
				{
					path: 'kaoshichengjiDetail',
					component: kaoshichengjiDetail
				},
				{
					path: 'kaoshichengjiAdd',
					component: kaoshichengjiAdd
				},
				{
					path: 'biyexueyuan',
					component: biyexueyuanList
				},
				{
					path: 'biyexueyuanDetail',
					component: biyexueyuanDetail
				},
				{
					path: 'biyexueyuanAdd',
					component: biyexueyuanAdd
				},
				{
					path: 'jiaolianpingjia',
					component: jiaolianpingjiaList
				},
				{
					path: 'jiaolianpingjiaDetail',
					component: jiaolianpingjiaDetail
				},
				{
					path: 'jiaolianpingjiaAdd',
					component: jiaolianpingjiaAdd
				},
				{
					path: 'newstype',
					component: newstypeList
				},
				{
					path: 'newstypeDetail',
					component: newstypeDetail
				},
				{
					path: 'newstypeAdd',
					component: newstypeAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
		{
			path: '/exam',
			component: Exam
		}
	]
})
