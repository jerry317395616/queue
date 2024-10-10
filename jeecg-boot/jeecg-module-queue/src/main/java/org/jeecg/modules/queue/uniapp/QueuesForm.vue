<template>
    <view>
        <!--标题和返回-->
		<cu-custom :bgColor="NavBarColor" isBack :backRouterName="backRouteName">
			<block slot="backText">返回</block>
			<block slot="content">队列管理</block>
		</cu-custom>
		 <!--表单区域-->
		<view>
			<form>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">患者：</text></view>
                  <input  placeholder="请输入患者" v-model="model.patient"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">排队序号：</text></view>
                  <input type="number" placeholder="请输入排队序号" v-model="model.queueNumber"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">科室名称：</text></view>
                  <input  placeholder="请输入科室名称" v-model="model.department"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">当前队列状态：</text></view>
                  <input  placeholder="请输入当前队列状态" v-model="model.queueStatus"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">排队优先级：</text></view>
                  <input  placeholder="请输入排队优先级" v-model="model.queuePriority"/>
                </view>
              </view>
              <my-date label="患者签到时间：" v-model="model.checkTime" placeholder="请输入患者签到时间"></my-date>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">挂号医生：</text></view>
                  <input  placeholder="请输入挂号医生" v-model="model.doctor"/>
                </view>
              </view>
              <view class="cu-form-group">
                <view class="flex align-center">
                  <view class="title"><text space="ensp">诊室：</text></view>
                  <input  placeholder="请输入诊室" v-model="model.room"/>
                </view>
              </view>
				<view class="padding">
					<button class="cu-btn block bg-blue margin-tb-sm lg" @click="onSubmit">
						<text v-if="loading" class="cuIcon-loading2 cuIconfont-spin"></text>提交
					</button>
				</view>
			</form>
		</view>
    </view>
</template>

<script>
    import myDate from '@/components/my-componets/my-date.vue'

    export default {
        name: "QueuesForm",
        components:{ myDate },
        props:{
          formData:{
              type:Object,
              default:()=>{},
              required:false
          }
        },
        data(){
            return {
				CustomBar: this.CustomBar,
				NavBarColor: this.NavBarColor,
				loading:false,
                model: {},
                backRouteName:'index',
                url: {
                  queryById: "/queue/queues/queryById",
                  add: "/queue/queues/add",
                  edit: "/queue/queues/edit",
                },
            }
        },
        created(){
             this.initFormData();
        },
        methods:{
           initFormData(){
               if(this.formData){
                    let dataId = this.formData.dataId;
                    this.$http.get(this.url.queryById,{params:{id:dataId}}).then((res)=>{
                        if(res.data.success){
                            console.log("表单数据",res);
                            this.model = res.data.result;
                        }
                    })
                }
            },
            onSubmit() {
                let myForm = {...this.model};
                this.loading = true;
                let url = myForm.id?this.url.edit:this.url.add;
				this.$http.post(url,myForm).then(res=>{
				   console.log("res",res)
				   this.loading = false
				   this.$Router.push({name:this.backRouteName})
				}).catch(()=>{
					this.loading = false
				});
            }
        }
    }
</script>
