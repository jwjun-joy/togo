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
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="suspendUserDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Admin')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>사용자 계정 정지
                </v-btn>
                <v-dialog v-model="suspendUserDialog" width="500">
                    <SuspendUser
                        @closeDialog="suspendUserDialog = false"
                        @suspendUser="suspendUser"
                    ></SuspendUser>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="reactivateUser" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Admin')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>사용자 계정 해제
                </v-btn>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="changeUserGradeDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Admin')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>사용자 등급 변경
                </v-btn>
                <v-dialog v-model="changeUserGradeDialog" width="500">
                    <ChangeUserGrade
                        @closeDialog="changeUserGradeDialog = false"
                        @changeUserGrade="changeUserGrade"
                    ></ChangeUserGrade>
                </v-dialog>
            </div>
            <UserListView @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></UserListView>
            <UserDetailView @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></UserDetailView>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>PhoneNumber</th>
                        <th>UserType</th>
                        <th>Status</th>
                        <th>Grade</th>
                        <th>RegisteredAt</th>
                        <th>SuspendedReason</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="Name">{{ val.name }}</td>
                            <td class="whitespace-nowrap" label="PhoneNumber">{{ val.phoneNumber }}</td>
                            <td class="whitespace-nowrap" label="UserType">{{ val.userType }}</td>
                            <td class="whitespace-nowrap" label="Status">{{ val.status }}</td>
                            <td class="whitespace-nowrap" label="Grade">{{ val.grade }}</td>
                            <td class="whitespace-nowrap" label="RegisteredAt">{{ val.registeredAt }}</td>
                            <td class="whitespace-nowrap" label="SuspendedReason">{{ val.suspendedReason }}</td>
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
                        <div style="color:white; font-size:17px; font-weight:700;">User 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <User :offline="offline"
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
                        <div style="color:white; font-size:17px; font-weight:700;">User 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="UserId" v-model="selectedRow.userId" :editMode="true"/>
                            <String label="Name" v-model="selectedRow.name" :editMode="true"/>
                            <String label="PhoneNumber" v-model="selectedRow.phoneNumber" :editMode="true"/>
                            <String label="Grade" v-model="selectedRow.grade" :editMode="true"/>
                            <Date label="RegisteredAt" v-model="selectedRow.registeredAt" :editMode="true"/>
                            <String label="SuspendedReason" v-model="selectedRow.suspendedReason" :editMode="true"/>
                            <UserType offline label="UserType" v-model="selectedRow.userType" :editMode="true"/>
                            <UserStatus offline label="Status" v-model="selectedRow.status" :editMode="true"/>
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
    name: 'userGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'users',
        suspendUserDialog: false,
        changeUserGradeDialog: false,
    }),
    watch: {
    },
    methods:{
        async suspendUser(params){
            try{
                var path = "suspendUser".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','SuspendUser 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.suspendUserDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async reactivateUser(){
            try{
                var path = "reactivateUser".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, null)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','ReactivateUser 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
            }catch(e){
                console.log(e)
            }
        },
        async changeUserGrade(params){
            try{
                var path = "changeUserGrade".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','ChangeUserGrade 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.changeUserGradeDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>