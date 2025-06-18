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
                <v-btn style="margin-left: 5px;" @click="addCartItemDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Customer')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>장바구니에 메뉴 추가
                </v-btn>
                <v-dialog v-model="addCartItemDialog" width="500">
                    <AddCartItem
                        @closeDialog="addCartItemDialog = false"
                        @addCartItem="addCartItem"
                    ></AddCartItem>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="removeCartItemDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Customer')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>장바구니 항목 삭제
                </v-btn>
                <v-dialog v-model="removeCartItemDialog" width="500">
                    <RemoveCartItem
                        @closeDialog="removeCartItemDialog = false"
                        @removeCartItem="removeCartItem"
                    ></RemoveCartItem>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="clearCart" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Customer')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>장바구니 비우기
                </v-btn>
                <v-btn style="margin-left: 5px;" @click="checkoutCartDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Customer')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>장바구니 결제 및 주문 확정
                </v-btn>
                <v-dialog v-model="checkoutCartDialog" width="500">
                    <CheckoutCart
                        @closeDialog="checkoutCartDialog = false"
                        @checkoutCart="checkoutCart"
                    ></CheckoutCart>
                </v-dialog>
            </div>
            <CartDetails @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></CartDetails>
            <CartList @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></CartList>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>CustomerId</th>
                        <th>CreatedAt</th>
                        <th>CartItems</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="CustomerId">{{ val.customerId }}</td>
                            <td class="whitespace-nowrap" label="CreatedAt">{{ val.createdAt }}</td>
                            <td class="whitespace-nowrap" label="CartItems">{{ val.cartItems }}</td>
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
                        <div style="color:white; font-size:17px; font-weight:700;">Cart 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <Cart :offline="offline"
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
                        <div style="color:white; font-size:17px; font-weight:700;">Cart 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="CartId" v-model="selectedRow.cartId" :editMode="true"/>
                            <Number label="CustomerId" v-model="selectedRow.customerId" :editMode="true"/>
                            <Date label="CreatedAt" v-model="selectedRow.createdAt" :editMode="true"/>
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
    name: 'cartGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'carts',
        addCartItemDialog: false,
        removeCartItemDialog: false,
        checkoutCartDialog: false,
    }),
    watch: {
    },
    methods:{
        async addCartItem(params){
            try{
                var path = "addCartItem".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','AddCartItem 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.addCartItemDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async removeCartItem(params){
            try{
                var path = "removeCartItem".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','RemoveCartItem 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.removeCartItemDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async clearCart(){
            try{
                var path = "clearCart".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, null)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','ClearCart 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
            }catch(e){
                console.log(e)
            }
        },
        async checkoutCart(params){
            try{
                var path = "checkoutCart".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','CheckoutCart 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.checkoutCartDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>