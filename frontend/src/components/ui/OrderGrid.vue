<template>
    <v-container>
        <v-snackbar
            v-model="snackbar.status"
            :timeout="snackbar.timeout"
            :color="snackbar.color"
        >
            
            <v-btn style="margin-left: 80px;" text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
        <div class="panel">
            <div class="gs-bundle-of-buttons" style="max-height:10vh;">
                <v-btn @click="addNewRow" @class="contrast-primary-text" small color="primary">
                    <v-icon small style="margin-left: -5px;">mdi-plus</v-icon>등록
                </v-btn>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="openEditDialog()" class="contrast-primary-text" small color="primary">
                    <v-icon small>mdi-pencil</v-icon>수정
                </v-btn>
                <v-btn style="margin-left: 5px;" @click="requestOrderDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Customer')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>주문 요청
                </v-btn>
                <v-dialog v-model="requestOrderDialog" width="500">
                    <RequestOrder
                        @closeDialog="requestOrderDialog = false"
                        @requestOrder="requestOrder"
                    ></RequestOrder>
                </v-dialog>
                <v-btn style="margin-left: 5px;" @click="placeOrderDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Customer')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>주문 완료
                </v-btn>
                <v-dialog v-model="placeOrderDialog" width="500">
                    <PlaceOrder
                        @closeDialog="placeOrderDialog = false"
                        @placeOrder="placeOrder"
                    ></PlaceOrder>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="cancelOrderDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Customer')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>주문 취소
                </v-btn>
                <v-dialog v-model="cancelOrderDialog" width="500">
                    <CancelOrder
                        @closeDialog="cancelOrderDialog = false"
                        @cancelOrder="cancelOrder"
                    ></CancelOrder>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="acceptOrder" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Restaurant')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>주문 접수
                </v-btn>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="rejectOrderDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Restaurant')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>주문 거절
                </v-btn>
                <v-dialog v-model="rejectOrderDialog" width="500">
                    <RejectOrder
                        @closeDialog="rejectOrderDialog = false"
                        @rejectOrder="rejectOrder"
                    ></RejectOrder>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="prepareOrder" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Restaurant')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>주문 준비 시작
                </v-btn>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="completePreparation" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Restaurant')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>주문 준비 완료
                </v-btn>
            </div>
            <OrderStatusList @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></OrderStatusList>
            <OrderDetails @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></OrderDetails>
            <RestaurantOrderStatusList @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></RestaurantOrderStatusList>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>UserId</th>
                        <th>사용자</th>
                        <th>식당</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="UserId">{{ val.userId }}</td>
                            <td class="whitespace-nowrap" label="사용자">
                                <UserId :editMode="editMode" v-model="val.userId"></UserId>
                            </td>
                            <td class="whitespace-nowrap" label="식당">
                                <RestaurantId :editMode="editMode" v-model="val.restaurantId"></RestaurantId>
                            </td>
                            <v-row class="ma-0 pa-4 align-center">
                                <v-spacer></v-spacer>
                                <Icon style="cursor: pointer;" icon="mi:delete" @click="deleteRow(val)" />
                            </v-row>
                        </tr>
                    </tbody>
                </v-table>
            </div>
        </div>
        <v-col>
            <v-dialog
                v-model="openDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0 pa-4"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">Order 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <Order :offline="offline"
                            :isNew="!value.idx"
                            :editMode="true"
                            :inList="false"
                            v-model="newValue"
                            @add="append"
                        />
                    </v-card-text>
                </v-card>
            </v-dialog>
            <v-dialog
                v-model="editDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0 pa-4"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">Order 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="OrderId" v-model="selectedRow.orderId" :editMode="true"/>
                            <Number label="CustomerId" v-model="selectedRow.customerId" :editMode="true"/>
                            <Number label="DeliveryPersonId" v-model="selectedRow.deliveryPersonId" :editMode="true"/>
                            <Date label="OrderTime" v-model="selectedRow.orderTime" :editMode="true"/>
                            <String label="DeliveryAddress" v-model="selectedRow.deliveryAddress" :editMode="true"/>
                            <Number label="TotalAmount" v-model="selectedRow.totalAmount" :editMode="true"/>
                            <String label="CancelReason" v-model="selectedRow.cancelReason" :editMode="true"/>
                            <RestaurantId offline label="RestaurantId" v-model="selectedRow.restaurantId" :editMode="true"/>
                            <OrderStatus offline label="OrderStatus" v-model="selectedRow.orderStatus" :editMode="true"/>
                            <PaymentMethod offline label="PaymentMethod" v-model="selectedRow.paymentMethod" :editMode="true"/>
                            <PaymentStatus offline label="PaymentStatus" v-model="selectedRow.paymentStatus" :editMode="true"/>
                            <UserId offline label="UserId" v-model="selectedRow.userId" :editMode="true"/>
                            <v-divider class="border-opacity-100 my-divider"></v-divider>
                            <v-layout row justify-end>
                                <v-btn
                                    width="64px"
                                    color="primary"
                                    @click="save"
                                >
                                    수정
                                </v-btn>
                            </v-layout>
                        </div>
                    </v-card-text>
                </v-card>
            </v-dialog>
        </v-col>
    </v-container>
</template>

<script>
import { ref } from 'vue';
import { useTheme } from 'vuetify';
import BaseGrid from '../base-ui/BaseGrid.vue'


export default {
    name: 'orderGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'orders',
        requestOrderDialog: false,
        placeOrderDialog: false,
        cancelOrderDialog: false,
        rejectOrderDialog: false,
    }),
    watch: {
    },
    methods:{
        async requestOrder(params){
            try{
                var path = "requestOrder".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','RequestOrder 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.requestOrderDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async placeOrder(params){
            try{
                var path = "placeOrder".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','PlaceOrder 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.placeOrderDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async cancelOrder(params){
            try{
                var path = "cancelOrder".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','CancelOrder 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.cancelOrderDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async acceptOrder(){
            try{
                var path = "acceptOrder".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, null)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','AcceptOrder 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
            }catch(e){
                console.log(e)
            }
        },
        async rejectOrder(params){
            try{
                var path = "rejectOrder".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','RejectOrder 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.rejectOrderDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async prepareOrder(){
            try{
                var path = "prepareOrder".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, null)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','PrepareOrder 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
            }catch(e){
                console.log(e)
            }
        },
        async completePreparation(){
            try{
                var path = "completePreparation".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, null)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','CompletePreparation 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>