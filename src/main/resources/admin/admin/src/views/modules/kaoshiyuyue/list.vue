<template>
	<div class="main-content" :style='{"padding":"0px","margin":"0 auto","color":"#333","borderRadius":"0 0 10px 10px","background":"rgba(255,255,255,.0)","width":"96%","fontSize":"16px"}'>
		<!-- 列表页 -->
		<template v-if="showFlag">
			<el-form class="center-form-pv" :style='{"padding":"0px","margin":"20px 0 0","overflow":"hidden","color":"#666","flexWrap":"wrap","background":"none","display":"flex","fontSize":"inherit","justifyContent":"space-between"}' :inline="true" :model="searchForm">
				<el-row :style='{"border":"1px solid #dcd6c3","padding":"10px 20px","margin":"0 30px 30px 0","alignItems":"center","borderRadius":"8px","flexWrap":"wrap","background":"rgba(251,249,243,.9)","display":"flex","width":"auto","fontSize":"18px","justifyContent":"center"}' >
					<div :style='{"margin":"0 10px 0 0","fontSize":"inherit","display":"inline-block"}'>
						<label :style='{"margin":"0 10px 0 0","color":"inherit","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">用户姓名</label>
						<el-input v-model="searchForm.yonghuxingming" placeholder="用户姓名" @keydown.enter.native="search()" clearable></el-input>
					</div>
					<div :style='{"margin":"0 10px 0 0","fontSize":"inherit","display":"inline-block"}' class="select" label="考试科目" prop="kaoshikemu">
						<label :style='{"margin":"0 10px 0 0","color":"inherit","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">考试科目</label>
						<el-select clearable v-model="searchForm.kaoshikemu" placeholder="请选择考试科目" >
							<el-option v-for="(item,index) in kaoshikemuOptions" v-bind:key="index" :label="item" :value="item"></el-option>
						</el-select>
					</div>
					<div :style='{"margin":"0 10px 0 0","fontSize":"inherit","display":"inline-block"}' class="select">
						<label :style='{"margin":"0 10px 0 0","color":"inherit","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">是否通过</label>
						<el-select clearable v-model="searchForm.sfsh" placeholder="是否通过">
							<el-option v-for="(item,index) in sfshOptions" v-bind:key="index" :label="item" :value="item"></el-option>
						</el-select>
					</div>
					<el-button class="search" type="success" @click="search()">
						<span class="icon iconfont icon-chakan12" :style='{"margin":"0 2px","fontSize":"inherit","color":"inherit","height":"40px"}'></span>
						查找
					</el-button>
				</el-row>

				<el-row class="actions" :style='{"border":"1px solid #dcd6c3","padding":"10px 20px","margin":"0 0 30px","color":"#666","alignItems":"center","display":"flex","-webkitPerspective":"320px","justifyContent":"center","borderRadius":"8px","flexWrap":"wrap","flex":"1","background":"rgba(251,249,243,.9)","width":"auto","perspective":"320px","fontSize":"18px"}'>
					<el-button class="add" v-if="isAuth('kaoshiyuyue','新增')" type="success" @click="addOrUpdateHandler()">
						<span class="icon iconfont icon-tianjia17" :style='{"color":"inherit","margin":"0 2px","fontSize":"inherit"}'></span>
						添加
					</el-button>
					<el-button class="del" v-if="isAuth('kaoshiyuyue','删除')" :disabled="dataListSelections.length?false:true" type="danger" @click="deleteHandler()">
						<span class="icon iconfont icon-shanchu6" :style='{"margin":"0 2px","fontSize":"inherit","color":"inherit","height":"40px"}'></span>
						删除
					</el-button>


					<el-button class="btn18" v-if="isAuth('kaoshiyuyue','审核')" :disabled="dataListSelections.length?false:true" type="success" @click="shBatchDialog()">
						<span class="icon iconfont icon-shenhe2" :style='{"color":"inherit","margin":"0 2px","fontSize":"inherit"}'></span>
						审核
					</el-button>

				</el-row>
			</el-form>
			<div :style='{"padding":"0 30px","borderColor":"#eee","margin":"30px 0 0","borderRadius":"0px","background":"rgba(255,255,255,.69)","borderWidth":"0px 0 0 0px","width":"100%","borderStyle":"solid"}'>
				<el-table class="tables"
					:stripe='false'
					:style='{"padding":"0","borderColor":"rgba(19,161,230,.2)","color":"inherit","borderRadius":"0px","borderWidth":"0","background":"none","width":"100%","fontSize":"inherit","borderStyle":"solid"}' 
					:border='true'
					v-if="isAuth('kaoshiyuyue','查看')"
					:data="dataList"
					v-loading="dataListLoading"
				@selection-change="selectionChangeHandler">
					<el-table-column :resizable='true' type="selection" align="center" width="50"></el-table-column>
					<el-table-column :resizable='true' :sortable='true' label="序号" type="index" width="50" />
					<el-table-column :resizable='true' :sortable='true'  
						prop="yonghuzhanghao"
						label="用户账号">
						<template slot-scope="scope">
							{{scope.row.yonghuzhanghao}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="shoujihaoma"
						label="手机号码">
						<template slot-scope="scope">
							{{scope.row.shoujihaoma}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="yonghuxingming"
						label="用户姓名">
						<template slot-scope="scope">
							{{scope.row.yonghuxingming}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="kaoshikemu"
						label="考试科目">
						<template slot-scope="scope">
							{{scope.row.kaoshikemu}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="yuyueriqi"
						label="预约日期">
						<template slot-scope="scope">
							{{scope.row.yuyueriqi}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="beizhu"
						label="备注">
						<template slot-scope="scope">
							{{scope.row.beizhu}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="jiaolianzhanghao"
						label="教练账号">
						<template slot-scope="scope">
							{{scope.row.jiaolianzhanghao}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="jiaolianxingming"
						label="教练姓名">
						<template slot-scope="scope">
							{{scope.row.jiaolianxingming}}
						</template>
					</el-table-column>
					<!-- 无 -->
					<el-table-column :resizable='true' :sortable='true' prop="touxiang" width="200" label="头像">
						<template slot-scope="scope">
							<div v-if="scope.row.touxiang">
								<img v-if="scope.row.touxiang.substring(0,4)=='http'" :src="scope.row.touxiang.split(',')[0]" width="100" height="100" style="object-fit: cover">
								<img v-else :src="$base.url+scope.row.touxiang.split(',')[0]" width="100" height="100" style="object-fit: cover">
							</div>
							<div v-else>无图片</div>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true' prop="shhf" label="审核回复" show-overflow-tooltip>
						<template slot-scope="scope">
							<div style="white-space: nowrap;">{{scope.row.shhf}}</div>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true' prop="sfsh" label="审核状态">
						<template slot-scope="scope">
							<el-tag v-if="scope.row.sfsh=='否'" type="danger">未通过</el-tag>
							<el-tag v-if="scope.row.sfsh=='待审核'" type="warning">待审核</el-tag>
							<el-tag v-if="scope.row.sfsh=='是'" type="success">通过</el-tag>
						</template>
					</el-table-column>
					
					<el-table-column width="300" label="操作">
						<template slot-scope="scope">
							<el-button class="view" v-if=" isAuth('kaoshiyuyue','查看')" type="success" @click="addOrUpdateHandler(scope.row.id,'info')">
								<span class="icon iconfont icon-chakan18" :style='{"margin":"0 2px","fontSize":"inherit","color":"inherit","display":"inline-block","height":"40px"}'></span>
								查看
							</el-button>
							<el-button class="btn8" v-if="isAuth('kaoshiyuyue','考试安排')" @click="kaoshianpaiCrossAddOrUpdateHandler(scope.row,'cross','是','','[1]','已安排')" type="success">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"inherit","color":"inherit","display":"inline-block","height":"40px"}'></span>
								考试安排
							</el-button>
							<el-button class="edit" v-if=" isAuth('kaoshiyuyue','修改')  && scope.row.sfsh=='待审核' " type="success" @click="addOrUpdateHandler(scope.row.id)">
								<span class="icon iconfont icon-xiugai11" :style='{"margin":"0 2px","fontSize":"inherit","color":"inherit","display":"inline-block","height":"40px"}'></span>
								修改
							</el-button>




							<el-button class="del" v-if="isAuth('kaoshiyuyue','删除') " type="primary" @click="deleteHandler(scope.row.id )">
								<span class="icon iconfont icon-shanchu6" :style='{"margin":"0 2px","fontSize":"inherit","color":"inherit","display":"inline-block","height":"40px"}'></span>
								删除
							</el-button>
						</template>
					</el-table-column>
				</el-table>
			</div>
			<el-pagination
				@size-change="sizeChangeHandle"
				@current-change="currentChangeHandle"
				:current-page="pageIndex"
				background
				:page-sizes="[10, 50, 100, 200]"
				:page-size="pageSize"
				:layout="layouts.join()"
				:total="totalPage"
				prev-text="上一页 "
				next-text="下一页 "
				:hide-on-single-page="false"
				:style='{"padding":"10px","margin":"20px 0 0","whiteSpace":"nowrap","color":"inherit","textAlign":"center","background":"rgba(255,255,255,.69)","width":"100%","fontSize":"inherit","fontWeight":"500"}'
			></el-pagination>
		</template>
		
		<!-- 添加/修改页面  将父组件的search方法传递给子组件-->
		<add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>

		<kaoshianpai-cross-add-or-update v-if="kaoshianpaiCrossAddOrUpdateFlag" :parent="this" ref="kaoshianpaiCrossaddOrUpdate"></kaoshianpai-cross-add-or-update>

		
		<el-dialog :title="this.batchIds.length>1?'批量审核':'审核'" :visible.sync="sfshBatchVisiable" width="50%">
			<el-form ref="shBatchForm" :model="shBatchForm" :rules="shRules" label-width="80px">
				<el-form-item label="审核状态" prop="sfsh">
					<el-select v-model="shBatchForm.sfsh" placeholder="审核状态">
						<el-option label="通过" value="是"></el-option>
						<el-option label="不通过" value="否"></el-option>
						<el-option label="待审核" value="待审核"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="内容" prop="shhf">
					<el-input type="textarea" :rows="8" v-model="shBatchForm.shhf"></el-input>
				</el-form-item>
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button @click="sfshBatchVisiable=false">取 消</el-button>
				<el-button type="primary" @click="shBatchHandler">确 定</el-button>
			</span>
		</el-dialog>


	</div>
</template>

<script>
import axios from 'axios'
import AddOrUpdate from "./add-or-update";
import kaoshianpaiCrossAddOrUpdate from "../kaoshianpai/add-or-update";
	export default {
		data() {
			return {
				kaoshikemuOptions: [],
				searchForm: {
					key: ""
				},
				form:{},
				sfshOptions: [],
				dataList: [],
				pageIndex: 1,
				pageSize: 10,
				totalPage: 0,
				dataListLoading: false,
				dataListSelections: [],
				showFlag: true,
				sfshVisiable: false,
				shForm: {},
				sfshBatchVisiable: false,
				shBatchForm: {
					sfsh:'',
					shhf:''
				},
				shRules: {
					sfsh:[{ required: true, message: '审核状态不能为空', trigger: 'blur' },],
					shhf:[{ required: true, message: '审核回复不能为空', trigger: 'blur' },],
				},
				batchIds:[], 
				addOrUpdateFlag:false,
				kaoshianpaiCrossAddOrUpdateFlag: false,
				layouts: ["total","prev","pager","next","sizes","jumper"],
			};
		},
		created() {
			this.init();
			this.getDataList();
			this.contentStyleChange()
		},
		mounted() {
		},
		filters: {
			htmlfilter: function (val) {
				return val.replace(/<[^>]*>/g).replace(/undefined/g,'');
			}
		},
		computed: {
			tablename(){
				return this.$storage.get('sessionTable')
			},
		},
		components: {
			AddOrUpdate,
			kaoshianpaiCrossAddOrUpdate,
		},
		methods: {
			contentStyleChange() {
				this.contentPageStyleChange()
			},
			// 分页
			contentPageStyleChange(){
				let arr = []

				// if(this.contents.pageTotal) arr.push('total')
				// if(this.contents.pageSizes) arr.push('sizes')
				// if(this.contents.pagePrevNext){
				//   arr.push('prev')
				//   if(this.contents.pagePager) arr.push('pager')
				//   arr.push('next')
				// }
				// if(this.contents.pageJumper) arr.push('jumper')
				// this.layouts = arr.join()
				// this.contents.pageEachNum = 10
			},
			kaoshianpaiCrossAddOrUpdateHandler(row,type,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue){
				if(crossOptAudit=='是'&&row.sfsh!='是') {
					this.$message({
						message: "请审核通过后再操作",
						type: "success",
						duration: 1500,
						onClose: () => {
						}
					});
					return
				}
				this.showFlag = false;
				this.addOrUpdateFlag = false;
				this.kaoshianpaiCrossAddOrUpdateFlag = true;
				this.$storage.set('crossObj',row);
				this.$storage.set('crossTable','kaoshiyuyue');
				this.$storage.set('statusColumnName',statusColumnName);
				this.$storage.set('statusColumnValue',statusColumnValue);
				this.$storage.set('tips',tips);
				if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
					var obj = this.$storage.getObj('crossObj');
					for (var o in obj){
						if(o==statusColumnName && obj[o]==statusColumnValue){
							this.$message({
								message: tips,
								type: "success",
								duration: 1500,
								onClose: () => {
									this.getDataList();
								}
							});
							this.showFlag = true;
							this.kaoshianpaiCrossAddOrUpdateFlag = false;
							return;
						}
					}
				}
				this.$nextTick(() => {
					this.$refs.kaoshianpaiCrossaddOrUpdate.init(row.id,type);
				});
			},






    init () {
        this.sfshOptions = "是,否,待审核".split(',');
          this.kaoshikemuOptions = "科目一,科目二,科目三,科目四".split(',')
    },
    search() {
      this.pageIndex = 1;
      this.getDataList();
    },

    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      let params = {
        page: this.pageIndex,
        limit: this.pageSize,
        sort: 'id',
        order: 'desc',
      }
           if(this.searchForm.yonghuxingming!='' && this.searchForm.yonghuxingming!=undefined){
            params['yonghuxingming'] = '%' + this.searchForm.yonghuxingming + '%'
          }
           if(this.searchForm.kaoshikemu!='' && this.searchForm.kaoshikemu!=undefined){
            params['kaoshikemu'] = this.searchForm.kaoshikemu
          }
			if(this.searchForm.sfsh!='' && this.searchForm.sfsh!=undefined){
				params['sfsh'] = this.searchForm.sfsh
			}
			let user = JSON.parse(this.$storage.getObj('userForm'))
			console.log(user)
			this.$http({
				url: "kaoshiyuyue/page",
				method: "get",
				params: params
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.dataList = data.data.list;
					this.totalPage = data.data.total;
				} else {
					this.dataList = [];
					this.totalPage = 0;
				}
				this.dataListLoading = false;
			});
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    // 多选
    selectionChangeHandler(val) {
      this.dataListSelections = val;
    },
    // 添加/修改
    addOrUpdateHandler(id,type) {
      this.showFlag = false;
      this.addOrUpdateFlag = true;
      this.crossAddOrUpdateFlag = false;
      if(type!='info'){
        type = 'else';
      }
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id,type);
      });
    },
    //批量审核窗口
    shBatchDialog(){
		for(let x in this.dataListSelections){
			if(this.dataListSelections[x].sfsh&&this.dataListSelections[x].sfsh!='待审核'){
				this.$message.error('存在已审核数据，不能批量审核');
				this.batchIds = []
				return false
			}
			this.batchIds.push(this.dataListSelections[x].id)
		}
		this.sfshBatchVisiable = true
      
    },
    //批量审核
    shBatchHandler(){
		this.$refs["shBatchForm"].validate(valid => {
			if(valid){
				this.$confirm(`是否${this.batchIds.length>1?'一键审核':'审核'}选中数据?`, "提示", {
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					type: "warning"
				}).then(() => {
					this.$http({
						url: "kaoshiyuyue/shBatch?sfsh="+this.shBatchForm.sfsh+"&shhf="+this.shBatchForm.shhf,
						method: "post",
						data: this.batchIds
					}).then(({ data }) => {
						if (data && data.code === 0) {
							this.$message({
								message: "操作成功",
								type: "success",
								duration: 1500,
								onClose: () => {
									this.getDataList();
									this.sfshBatchVisiable = false
									this.batchIds = []
								}
							});
						} else {
							this.$message.error(data.msg);
						}
					});
				});
			}
		})
    },
    // 下载
    download(file){
      let arr = file.replace(new RegExp('upload/', "g"), "")
      axios.get(this.$base.url + 'file/download?fileName=' + arr, {
      	headers: {
      		token: this.$storage.get('Token')
      	},
      	responseType: "blob"
      }).then(({
      	data
      }) => {
      	const binaryData = [];
      	binaryData.push(data);
      	const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
      		type: 'application/pdf;chartset=UTF-8'
      	}))
      	const a = document.createElement('a')
      	a.href = objectUrl
      	a.download = arr
      	// a.click()
      	// 下面这个写法兼容火狐
      	a.dispatchEvent(new MouseEvent('click', {
      		bubbles: true,
      		cancelable: true,
      		view: window
      	}))
      	window.URL.revokeObjectURL(data)
      },err=>{
		  axios.get((location.href.split(this.$base.name).length>1 ? location.href.split(this.$base.name)[0] :'') + this.$base.name + '/file/download?fileName=' + arr, {
		  	headers: {
		  		token: this.$storage.get('Token')
		  	},
		  	responseType: "blob"
		  }).then(({
		  	data
		  }) => {
		  	const binaryData = [];
		  	binaryData.push(data);
		  	const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
		  		type: 'application/pdf;chartset=UTF-8'
		  	}))
		  	const a = document.createElement('a')
		  	a.href = objectUrl
		  	a.download = arr
		  	// a.click()
		  	// 下面这个写法兼容火狐
		  	a.dispatchEvent(new MouseEvent('click', {
		  		bubbles: true,
		  		cancelable: true,
		  		view: window
		  	}))
		  	window.URL.revokeObjectURL(data)
		  })
	  })
    },
	// 预览
	preClick(file){
		if(!file){
			return false
		}
		window.open((location.href.split(this.$base.name).length>1 ? location.href.split(this.$base.name)[0] + this.$base.name + '/' + file :this.$base.url + file))
	},
	kaoshiyuyuestatusChange(e,row){
		if(row.status==0){
			row.passwordwrongnum = 0
		}
		this.$http({
			url:'kaoshiyuyue/update',
			method:'post',
			data:row
		}).then(res=>{
			if(row.status==1){
				this.$message.error('该用户已锁定')
			}else{
				this.$message.success('该用户已解除锁定')
			}
		})
	},
    // 删除
    async deleteHandler(id ) {
		var ids = id? [Number(id)]: this.dataListSelections.map(item => {
			return Number(item.id);
		});
		await this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
			confirmButtonText: "确定",
			cancelButtonText: "取消",
			type: "warning"
		}).then(async () => {
			await this.$http({
				url: "kaoshiyuyue/delete",
				method: "post",
				data: ids
			}).then(async ({ data }) => {
				if (data && data.code === 0) {
					this.$message({
						message: "操作成功",
						type: "success",
						duration: 1500,
						onClose: () => {
							this.search();
						}
					});
            
				} else {
					this.$message.error(data.msg);
				}
			});
		});
    },


  }

};
</script>
<style lang="scss" scoped>
	
	.center-form-pv {
	  .el-date-editor.el-input {
	    width: auto;
	  }
	}
	
	.el-input {
	  width: auto;
	}
	
	// form
	.center-form-pv .el-input /deep/ .el-input__inner {
				border-radius: 0px;
				padding: 0 12px;
				box-shadow: inset 0px 0px 0px 0px #fff;
				outline: none;
				color: inherit;
				background: none;
				width: 170px;
				font-size: 16px;
				border-color: #ccc;
				border-width: 0 0 1px;
				border-style: solid;
				height: 40px;
			}
	
	.center-form-pv .el-select /deep/ .el-input__inner {
				border-radius: 0px;
				padding: 0 10px;
				box-shadow: inset 0px 0px 0px 0px #fff;
				outline: none;
				color: inherit;
				background: none;
				width: 170px;
				font-size: 16px;
				border-color: #ccc;
				border-width: 0 0 1px;
				border-style: solid;
				height: 40px;
			}
	
	.center-form-pv .el-date-editor /deep/ .el-input__inner {
				border-radius: 0px;
				padding: 0 10px 0 30px;
				box-shadow: inset 0px 0px 0px 0px #fff;
				outline: none;
				color: inherit;
				background: none;
				width: 170px;
				font-size: 16px;
				border-color: #ccc;
				border-width: 0 0 1px;
				border-style: solid;
				height: 40px;
			}
	
	.center-form-pv .search {
				border: 0px solid #47defa;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 24px;
				outline: none;
				clip-path: polygon(100% 0%, 95% 51%, 100% 100%, 5% 100%, 0% 50%, 5% 0);
				color: #fff;
				background: #666;
				width: auto;
				font-size: inherit;
				height: 40px;
			}
	
	.center-form-pv .search:hover {
				transform: scale(1.08);
				color: #fff;
				background: #bda75c;
			}
	
	.center-form-pv .actions .add {
				cursor: pointer;
				padding: 0 16px 0 10px;
				margin: 4px 4px 5px;
				color: inherit;
				font-size: inherit;
				border-color: #eee;
				transition: all 0.3s;
				border-radius: 4px;
				outline: none;
				background: rgba(255,255,255,.9);
				width: auto;
				border-width: 1px;
				border-style: solid;
				height: 32px;
			}
	
	.center-form-pv .actions .add:hover {
				padding: 0 16px 2px 10px;
				color: #bda75c;
				background: rgba(255,255,255,1);
				border-color: #bda75c;
				border-width: 1px;
				opacity: 1;
				border-style: solid;
			}
	
	.center-form-pv .actions .del {
				cursor: pointer;
				padding: 0 16px 0 10px;
				margin: 4px 4px 5px;
				color: inherit;
				font-size: inherit;
				border-color: #eee;
				transition: all 0.3s;
				border-radius: 4px;
				outline: none;
				background: rgba(255,255,255,.9);
				width: auto;
				border-width: 1px;
				border-style: solid;
				height: 32px;
			}
	
	.center-form-pv .actions .del:hover {
				padding: 0 16px 2px 10px;
				color: #bda75c;
				background: rgba(255,255,255,1);
				border-color: #bda75c;
				border-width: 1px;
				opacity: 1;
				border-style: dolid;
			}
	
	.center-form-pv .actions .statis {
				cursor: pointer;
				padding: 0 16px 0 10px;
				margin: 4px 4px 5px;
				color: inherit;
				font-size: inherit;
				border-color: #eee;
				transition: all 0.3s;
				border-radius: 4px;
				outline: none;
				background: rgba(255,255,255,.9);
				width: auto;
				border-width: 1px;
				border-style: solid;
				height: 32px;
			}
	
	.center-form-pv .actions .statis:hover {
				padding: 0 16px 2px 10px;
				color: #bda75c;
				background: rgba(255,255,255,1);
				border-color: #bda75c;
				border-width: 1px;
				opacity: 1;
				border-style: solid;
			}
	
	.center-form-pv .actions .btn18 {
				cursor: pointer;
				padding: 0 16px 0 10px;
				margin: 4px 4px 5px;
				color: inherit;
				font-size: inherit;
				border-color: #eee;
				transition: all 0.3s;
				border-radius: 4px;
				outline: none;
				background: rgba(255,255,255,.9);
				width: auto;
				border-width: 1px;
				border-style: solid;
				height: 32px;
			}
	
	.center-form-pv .actions .btn18:hover {
				padding: 0 16px 2px 10px;
				color: #bda75c;
				background: rgba(255,255,255,1);
				border-color: #bda75c;
				border-width: 1px;
				opacity: 1;
				border-style: solid;
			}
	
	// table
	.el-table /deep/ .el-table__header-wrapper thead {
				color: #333;
				background: rgba(254,169,34,0);
				width: 100%;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr {
				background: none;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr th {
				padding: 8px 0;
				background: none;
				border-color: rgba(189,168,92,.3);
				border-width: 0 0px 1px 0;
				border-style: solid;
				text-align: left;
			}

	.el-table /deep/ .el-table__header-wrapper thead tr th .cell {
				padding: 0 10px;
				word-wrap: normal;
				word-break: break-all;
				white-space: normal;
				font-weight: 600;
				display: inline-block;
				vertical-align: middle;
				width: 100%;
				font-size: 18px;
				line-height: 24px;
				position: relative;
				text-overflow: ellipsis;
			}

	
	.el-table /deep/ .el-table__body-wrapper tbody {
				padding: 0;
				width: 100%;
			}

	.el-table /deep/ .el-table__body-wrapper tbody tr {
				background: none;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding: 6px 0;
				color: inherit;
				background: none;
				font-size: inherit;
				border-color: rgba(189,168,92,.3);
				border-width: 0 0px 1px 0px;
				border-style: solid;
				text-align: left;
			}
	
		
	.el-table /deep/ .el-table__body-wrapper tbody tr:hover td {
				padding: 6px 0;
				color: #666;
				background: rgba(255,255,255,.3);
				border-color: rgba(189,168,92,.3);
				border-width: 0 0px 1px 0px;
				border-style: solid;
				text-align: left;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding: 6px 0;
				color: inherit;
				background: none;
				font-size: inherit;
				border-color: rgba(189,168,92,.3);
				border-width: 0 0px 1px 0px;
				border-style: solid;
				text-align: left;
			}

	.el-table /deep/ .el-table__body-wrapper tbody tr td .cell {
				padding: 0 10px;
				overflow: hidden;
				color: inherit;
				word-break: break-all;
				white-space: normal;
				line-height: 24px;
				text-overflow: ellipsis;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view {
				cursor: pointer;
				padding: 6px 6px 0 4px;
				margin: 6px 5px 6px 0;
				color: #333;
				font-size: 16px;
				border-color: #fff;
				transition: all 0.3s;
				border-radius: 4px;
				outline: none;
				background: rgba(255,255,255,.9);
				width: auto;
				border-width: 1px;
				border-style: solid;
				height: 32px;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view:hover {
				color: #bda75c;
				border-color: #bda75c;
				border-width: 1px;
				opacity: 0.8;
				border-style: solid;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .add {
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .add:hover {
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit {
				border: 0px solid rgba(254, 148, 34, .2);
				cursor: pointer;
				padding: 6px 6px 0 4px;
				margin: 6px 5px 6px 0;
				color: #333;
				font-size: 16px;
				border-color: #fff;
				transition: all 0.3s;
				border-radius: 4px;
				outline: none;
				background: rgba(255,255,255,.9);
				width: auto;
				border-width: 1px;
				border-style: solid;
				height: 32px;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit:hover {
				color: #bda75c;
				border-color: #bda75c;
				border-width: 1px;
				opacity: 0.8;
				border-style: solid;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del {
				cursor: pointer;
				padding: 6px 6px 0 4px;
				margin: 6px 5px 6px 0;
				color: #333;
				font-size: 16px;
				border-color: #fff;
				transition: all 0.3s;
				border-radius: 4px;
				outline: none;
				background: rgba(255,255,255,.9);
				width: auto;
				border-width: 1px;
				border-style: solid;
				height: 32px;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del:hover {
				color: #bda75c;
				border-color: #bda75c;
				border-width: 1px;
				opacity: 0.8;
				border-style: solid;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .btn8 {
				cursor: pointer;
				padding: 6px 6px 0 4px;
				margin: 2px 5px 6px 0;
				color: #333;
				font-size: 16px;
				border-color: #fff;
				transition: all 0.3s;
				border-radius: 4px;
				outline: none;
				background: rgba(255,255,255,.9);
				width: auto;
				border-width: 1px;
				border-style: solid;
				height: 32px;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .btn8:hover {
				color: #bda75c;
				border-color: #bda75c;
				border-width: 1px;
				opacity: 0.8;
				border-style: solid;
			}
	
	// pagination
	.main-content .el-pagination /deep/ .el-pagination__total {
				margin: 0 10px 0 0;
				color: inherit;
				font-weight: 400;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-prev {
				border: none;
				border-radius: 2px;
				padding: 0 5px;
				margin: 0 5px;
				color: inherit;
				background: none;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 28px;
				min-width: 35px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-next {
				border: none;
				border-radius: 2px;
				padding: 0 5px;
				margin: 0 5px;
				color: inherit;
				background: none;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 28px;
				min-width: 35px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-prev:disabled {
				border: none;
				cursor: not-allowed;
				border-radius: 2px;
				padding: 0 5px;
				margin: 0 5px;
				color: #999;
				background: none;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-next:disabled {
				border: none;
				cursor: not-allowed;
				border-radius: 2px;
				padding: 0 5px;
				margin: 0 5px;
				color: #999;
				background: none;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 28px;
				height: 28px;
			}

	.main-content .el-pagination /deep/ .el-pager {
				padding: 0;
				margin: 0;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
			}

	.main-content .el-pagination /deep/ .el-pager .number {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: inherit;
				background: none;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 24px;
				text-align: center;
				height: 24px;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number:hover {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: #fff;
				background: rgba(189,168,92,.6);
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 24px;
				text-align: center;
				height: 24px;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number.active {
				cursor: default;
				padding: 0 4px;
				margin: 0 5px;
				color: #fff;
				background: rgba(189,168,92,.6);
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 24px;
				text-align: center;
				height: 24px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes {
				color: inherit;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input {
				margin: 0 5px;
				color: inherit;
				width: 100px;
				font-size: inherit;
				position: relative;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__inner {
				border: 0px solid #ddd;
				cursor: pointer;
				padding: 0 25px 0 8px;
				color: inherit;
				display: inline-block;
				font-size: inherit;
				line-height: 28px;
				border-radius: 3px;
				outline: 0;
				background: none;
				width: 100%;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input span.el-input__suffix {
				top: 0;
				position: absolute;
				right: 0;
				height: 100%;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
				cursor: pointer;
				color: #C0C4CC;
				width: 25px;
				font-size: 14px;
				line-height: 28px;
				text-align: center;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump {
				margin: 0 0 0 24px;
				color: inherit;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input {
				border-radius: 3px;
				padding: 0 2px;
				margin: 0 2px;
				color: inherit;
				display: inline-block;
				width: 50px;
				font-size: inherit;
				line-height: 18px;
				position: relative;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input .el-input__inner {
				cursor: pointer;
				padding: 0 0px;
				color: inherit;
				display: inline-block;
				font-size: inherit;
				border-color: #eee;
				line-height: 24px;
				border-radius: 2px;
				background: none;
				width: auto;
				border-width: 1px;
				border-style: solid;
				text-align: center;
				height: 24px;
			}
	
	// list one
	.one .list1-view {
				cursor: pointer;
				padding: 0 6px 0 4px;
				margin: 6px 5px 6px 0;
				color: #333;
				font-size: inherit;
				border-color: #fbf9f3;
				transition: all 0.3s;
				border-radius: 4px;
				outline: none;
				background: rgba(86,178,198,.0);
				width: auto;
				border-width: 1px;
				border-style: solid;
				height: 32px;
			}
	
	.one .list1-view:hover {
				color: #bda75c;
				border-color: #bda75c;
				border-width: 1px;
				opacity: 0.8;
				border-style: solid;
			}
	
	.one .list1-edit {
				border: 0px solid rgba(254, 148, 34, .2);
				cursor: pointer;
				padding: 0 6px 0 4px;
				margin: 6px 5px 6px 0;
				color: #333;
				font-size: inherit;
				border-color: #fbf9f3;
				transition: all 0.3s;
				border-radius: 4px;
				outline: none;
				background: none;
				width: auto;
				border-width: 1px;
				border-style: solid;
				height: 32px;
			}
	
	.one .list1-edit:hover {
				color: #bda75c;
				border-color: #bda75c;
				border-width: 1px;
				opacity: 0.8;
				border-style: solid;
			}
	
	.one .list1-del {
				border: 0px solid rgba(180, 52, 31, .2);
				cursor: pointer;
				padding: 0 6px 0 4px;
				margin: 6px 5px 6px 0;
				color: #333;
				font-size: inherit;
				border-color: #fbf9f3;
				transition: all 0.3s;
				border-radius: 4px;
				outline: none;
				background: rgba(255, 0, 0, 0);
				width: auto;
				border-width: 1px;
				border-style: solid;
				height: 32px;
			}
	
	.one .list1-del:hover {
				color: #bda75c;
				border-color: #bda75c;
				border-width: 1px;
				opacity: 0.8;
				border-style: solid;
			}
	
	.one .list1-btn8 {
				border: 0px solid rgba(31, 160, 180, .2);
				cursor: pointer;
				padding: 0 6px 0 4px;
				margin: 6px 5px 6px 0;
				color: #333;
				font-size: inherit;
				border-color: #fbf9f3;
				transition: all 0.3s;
				border-radius: 4px;
				outline: none;
				background: rgba(255, 128, 0, 0);
				width: auto;
				border-width: 1px;
				border-style: solid;
				height: 32px;
			}
	
	.one .list1-btn8:hover {
				color: #bda75c;
				border-color: #bda75c;
				border-width: 1px;
				opacity: 0.8;
				border-style: solid;
			}
	
	.main-content .el-table .el-switch {
				display: inline-flex;
				vertical-align: middle;
				line-height: 30px;
				position: relative;
				align-items: center;
				height: 30px;
			}
	.main-content .el-table .el-switch /deep/ .el-switch__label--left {
				cursor: pointer;
				margin: 0 10px 0 0;
				color: #333;
				font-weight: 500;
				display: inline-block;
				vertical-align: middle;
				font-size: 16px;
				transition: .2s;
				height: 30px;
			}
	.main-content .el-table .el-switch /deep/ .el-switch__label--right {
				cursor: pointer;
				margin: 0 0 0 10px;
				color: #333;
				font-weight: 500;
				display: inline-block;
				vertical-align: middle;
				font-size: 16px;
				transition: .2s;
				height: 30px;
			}
	.main-content .el-table .el-switch /deep/ .el-switch__core {
				border: 1px solid #bda75c;
				cursor: pointer;
				border-radius: 15px;
				margin: 0;
				outline: 0;
				background: #bda75c;
				display: inline-block;
				width: 50px;
				box-sizing: border-box;
				transition: border-color .3s,background-color .3s;
				height: 24px;
			}
	.main-content .el-table .el-switch /deep/ .el-switch__core::after {
				border-radius: 100%;
				top: 1px;
				left: 1px;
				background: #fff;
				width: 20px;
				position: absolute;
				transition: all .3s;
				height: 20px;
			}
	.main-content .el-table .el-switch.is-checked /deep/ .el-switch__core::after {
				margin: 0 0 0 -27px;
				left: 100%;
				background: #fff;
			}
	
	.main-content .el-table .el-rate /deep/ .el-rate__item {
				cursor: pointer;
				display: inline-block;
				vertical-align: middle;
				font-size: 0;
				position: relative;
			}
	.main-content .el-table .el-rate /deep/ .el-rate__item .el-rate__icon {
				margin: 0 3px;
				color: #666;
				display: inline-block;
				font-size: 18px;
				position: relative;
				transition: .3s;
			}
</style>
