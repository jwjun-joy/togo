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
                <v-btn style="margin-left: 5px;" @click="createMenuDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Restaurant')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>메뉴 추가
                </v-btn>
                <v-dialog v-model="createMenuDialog" width="500">
                    <CreateMenu
                        @closeDialog="createMenuDialog = false"
                        @createMenu="createMenu"
                    ></CreateMenu>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="updateMenuDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Restaurant')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>메뉴 수정
                </v-btn>
                <v-dialog v-model="updateMenuDialog" width="500">
                    <UpdateMenu
                        @closeDialog="updateMenuDialog = false"
                        @updateMenu="updateMenu"
                    ></UpdateMenu>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="deleteMenuDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Restaurant')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>메뉴 삭제
                </v-btn>
                <v-dialog v-model="deleteMenuDialog" width="500">
                    <DeleteMenu
                        @closeDialog="deleteMenuDialog = false"
                        @deleteMenu="deleteMenu"
                    ></DeleteMenu>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="updateRestaurantInfoDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>식당 정보 수정
                </v-btn>
                <v-dialog v-model="updateRestaurantInfoDialog" width="500">
                    <UpdateRestaurantInfo
                        @closeDialog="updateRestaurantInfoDialog = false"
                        @updateRestaurantInfo="updateRestaurantInfo"
                    ></UpdateRestaurantInfo>
                </v-dialog>
            </div>
            <NearbyRestaurantList @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></NearbyRestaurantList>
            <RestaurantDetails @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></RestaurantDetails>
            <MenuList @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></MenuList>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>RestaurantInfo</th>
                        <th>MenuList</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="RestaurantInfo">{{ val.restaurantInfo }}</td>
                            <td class="whitespace-nowrap" label="MenuList">{{ val.menuList }}</td>
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
                        <div style="color:white; font-size:17px; font-weight:700;">Restaurant 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <Restaurant :offline="offline"
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
                        <div style="color:white; font-size:17px; font-weight:700;">Restaurant 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="RestaurantId" v-model="selectedRow.restaurantId" :editMode="true"/>
                            <RestaurantInfo offline label="RestaurantInfo" v-model="selectedRow.restaurantInfo" :editMode="true"/>
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
    name: 'restaurantGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'restaurants',
        createMenuDialog: false,
        updateMenuDialog: false,
        deleteMenuDialog: false,
        updateRestaurantInfoDialog: false,
    }),
    watch: {
    },
    methods:{
        async createMenu(params){
            try{
                var path = "createMenu".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','CreateMenu 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.createMenuDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async updateMenu(params){
            try{
                var path = "updateMenu".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','UpdateMenu 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.updateMenuDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async deleteMenu(params){
            try{
                var path = "deleteMenu".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','DeleteMenu 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.deleteMenuDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async updateRestaurantInfo(params){
            try{
                var path = "updateRestaurantInfo".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','UpdateRestaurantInfo 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.updateRestaurantInfoDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>