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
                <v-btn style="margin-left: 5px;" @click="requestDeliveryDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>배달 요청
                </v-btn>
                <v-dialog v-model="requestDeliveryDialog" width="500">
                    <RequestDelivery
                        @closeDialog="requestDeliveryDialog = false"
                        @requestDelivery="requestDelivery"
                    ></RequestDelivery>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="acceptDeliveryDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Rider')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>배달 수락
                </v-btn>
                <v-dialog v-model="acceptDeliveryDialog" width="500">
                    <AcceptDelivery
                        @closeDialog="acceptDeliveryDialog = false"
                        @acceptDelivery="acceptDelivery"
                    ></AcceptDelivery>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="rejectDeliveryDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Rider')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>배달 거절
                </v-btn>
                <v-dialog v-model="rejectDeliveryDialog" width="500">
                    <RejectDelivery
                        @closeDialog="rejectDeliveryDialog = false"
                        @rejectDelivery="rejectDelivery"
                    ></RejectDelivery>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="startDelivery" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Rider')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>배달 시작
                </v-btn>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="updateDeliveryLocationDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Rider')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>배달 위치 갱신
                </v-btn>
                <v-dialog v-model="updateDeliveryLocationDialog" width="500">
                    <UpdateDeliveryLocation
                        @closeDialog="updateDeliveryLocationDialog = false"
                        @updateDeliveryLocation="updateDeliveryLocation"
                    ></UpdateDeliveryLocation>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="completeDelivery" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Rider')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>배달 완료
                </v-btn>
            </div>
            <DeliveryTracking @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></DeliveryTracking>
            <DeliveryOrderPath @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></DeliveryOrderPath>
            <RiderDeliveryList @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></RiderDeliveryList>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>OrderId</th>
                        <th>주문</th>
                        <th>배달원</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="OrderId">{{ val.orderId }}</td>
                            <td class="whitespace-nowrap" label="주문">
                                <OrderId :editMode="editMode" v-model="val.orderId"></OrderId>
                            </td>
                            <td class="whitespace-nowrap" label="배달원">
                                <RiderId :editMode="editMode" v-model="val.riderId"></RiderId>
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
                        <div style="color:white; font-size:17px; font-weight:700;">Delivery 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <Delivery :offline="offline"
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
                        <div style="color:white; font-size:17px; font-weight:700;">Delivery 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="DeliveryId" v-model="selectedRow.deliveryId" :editMode="true"/>
                            <Date label="LastLocationUpdate" v-model="selectedRow.lastLocationUpdate" :editMode="true"/>
                            <RiderId offline label="RiderId" v-model="selectedRow.riderId" :editMode="true"/>
                            <DeliveryStatus offline label="Status" v-model="selectedRow.status" :editMode="true"/>
                            <CurrentLocation offline label="CurrentLocation" v-model="selectedRow.currentLocation" :editMode="true"/>
                            <OrderId offline label="OrderId" v-model="selectedRow.orderId" :editMode="true"/>
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
    name: 'deliveryGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'deliveries',
        requestDeliveryDialog: false,
        acceptDeliveryDialog: false,
        rejectDeliveryDialog: false,
        updateDeliveryLocationDialog: false,
    }),
    watch: {
    },
    methods:{
        async requestDelivery(params){
            try{
                var path = "requestDelivery".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','RequestDelivery 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.requestDeliveryDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async acceptDelivery(params){
            try{
                var path = "acceptDelivery".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','AcceptDelivery 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.acceptDeliveryDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async rejectDelivery(params){
            try{
                var path = "rejectDelivery".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','RejectDelivery 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.rejectDeliveryDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async startDelivery(){
            try{
                var path = "startDelivery".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, null)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','StartDelivery 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
            }catch(e){
                console.log(e)
            }
        },
        async updateDeliveryLocation(params){
            try{
                var path = "updateDeliveryLocation".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','UpdateDeliveryLocation 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.updateDeliveryLocationDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async completeDelivery(){
            try{
                var path = "completeDelivery".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, null)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','CompleteDelivery 성공적으로 처리되었습니다.')
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