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
                <v-btn style="margin-left: 5px;" @click="createReportDataDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Admin')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>리포트 데이터 생성
                </v-btn>
                <v-dialog v-model="createReportDataDialog" width="500">
                    <CreateReportData
                        @closeDialog="createReportDataDialog = false"
                        @createReportData="createReportData"
                    ></CreateReportData>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="updateReportDataDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Admin')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>리포트 데이터 수정
                </v-btn>
                <v-dialog v-model="updateReportDataDialog" width="500">
                    <UpdateReportData
                        @closeDialog="updateReportDataDialog = false"
                        @updateReportData="updateReportData"
                    ></UpdateReportData>
                </v-dialog>
            </div>
            <StatisticsDataList @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></StatisticsDataList>
            <StatisticsDataVisualization @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></StatisticsDataVisualization>
            <StatisticsDataDownload @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></StatisticsDataDownload>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>DataType</th>
                        <th>Value</th>
                        <th>RecordDate</th>
                        <th>ReportId</th>
                        <th>리포트</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="DataType">{{ val.dataType }}</td>
                            <td class="whitespace-nowrap" label="Value">{{ val.value }}</td>
                            <td class="whitespace-nowrap" label="RecordDate">{{ val.recordDate }}</td>
                            <td class="whitespace-nowrap" label="ReportId">{{ val.reportId }}</td>
                            <td class="whitespace-nowrap" label="리포트">
                                <ReportId :editMode="editMode" v-model="val.reportId"></ReportId>
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
                        <div style="color:white; font-size:17px; font-weight:700;">ReportData 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <ReportData :offline="offline"
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
                        <div style="color:white; font-size:17px; font-weight:700;">ReportData 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="ReportDataId" v-model="selectedRow.reportDataId" :editMode="true"/>
                            <Number label="Value" v-model="selectedRow.value" :editMode="true"/>
                            <Date label="RecordDate" v-model="selectedRow.recordDate" :editMode="true"/>
                            <ReportDataType offline label="DataType" v-model="selectedRow.dataType" :editMode="true"/>
                            <ReportId offline label="ReportId" v-model="selectedRow.reportId" :editMode="true"/>
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
    name: 'reportDataGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'reportData',
        createReportDataDialog: false,
        updateReportDataDialog: false,
    }),
    watch: {
    },
    methods:{
        async createReportData(params){
            try{
                var path = "createReportData".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','CreateReportData 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.createReportDataDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async updateReportData(params){
            try{
                var path = "updateReportData".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','UpdateReportData 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.updateReportDataDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>