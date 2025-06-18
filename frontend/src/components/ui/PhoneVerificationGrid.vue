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
                <v-btn style="margin-left: 5px;" @click="createPhoneVerificationDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Admin')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>휴대폰 인증 요청
                </v-btn>
                <v-dialog v-model="createPhoneVerificationDialog" width="500">
                    <CreatePhoneVerification
                        @closeDialog="createPhoneVerificationDialog = false"
                        @createPhoneVerification="createPhoneVerification"
                    ></CreatePhoneVerification>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="verifyPhoneNumberDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Admin')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>휴대폰 번호 인증 완료
                </v-btn>
                <v-dialog v-model="verifyPhoneNumberDialog" width="500">
                    <VerifyPhoneNumber
                        @closeDialog="verifyPhoneNumberDialog = false"
                        @verifyPhoneNumber="verifyPhoneNumber"
                    ></VerifyPhoneNumber>
                </v-dialog>
            </div>
            <PhoneVerificationList @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></PhoneVerificationList>
            <PhoneVerificationDetails @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></PhoneVerificationDetails>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>PhoneNumber</th>
                        <th>Verified</th>
                        <th>VerifiedAt</th>
                        <th>UserId</th>
                        <th>사용자</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="PhoneNumber">{{ val.phoneNumber }}</td>
                            <td class="whitespace-nowrap" label="Verified">{{ val.verified }}</td>
                            <td class="whitespace-nowrap" label="VerifiedAt">{{ val.verifiedAt }}</td>
                            <td class="whitespace-nowrap" label="UserId">{{ val.userId }}</td>
                            <td class="whitespace-nowrap" label="사용자">
                                <UserId :editMode="editMode" v-model="val.userId"></UserId>
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
                        <div style="color:white; font-size:17px; font-weight:700;">PhoneVerification 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <PhoneVerification :offline="offline"
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
                        <div style="color:white; font-size:17px; font-weight:700;">PhoneVerification 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="VerificationId" v-model="selectedRow.verificationId" :editMode="true"/>
                            <String label="PhoneNumber" v-model="selectedRow.phoneNumber" :editMode="true"/>
                            <Boolean label="Verified" v-model="selectedRow.verified" :editMode="true"/>
                            <Date label="VerifiedAt" v-model="selectedRow.verifiedAt" :editMode="true"/>
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
    name: 'phoneVerificationGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'phoneVerifications',
        createPhoneVerificationDialog: false,
        verifyPhoneNumberDialog: false,
    }),
    watch: {
    },
    methods:{
        async createPhoneVerification(params){
            try{
                var path = "createPhoneVerification".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','CreatePhoneVerification 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.createPhoneVerificationDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async verifyPhoneNumber(params){
            try{
                var path = "verifyPhoneNumber".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','VerifyPhoneNumber 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.verifyPhoneNumberDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>