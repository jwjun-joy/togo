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
                <v-btn style="margin-left: 5px;" @click="createReportDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Admin')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>통계 리포트 생성
                </v-btn>
                <v-dialog v-model="createReportDialog" width="500">
                    <CreateReport
                        @closeDialog="createReportDialog = false"
                        @createReport="createReport"
                    ></CreateReport>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="changeReportStatusDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Admin')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>리포트 상태 변경
                </v-btn>
                <v-dialog v-model="changeReportStatusDialog" width="500">
                    <ChangeReportStatus
                        @closeDialog="changeReportStatusDialog = false"
                        @changeReportStatus="changeReportStatus"
                    ></ChangeReportStatus>
                </v-dialog>
            </div>
            <ReportList @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></ReportList>
            <ReportDetails @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></ReportDetails>
            <ReportVisualization @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></ReportVisualization>
            <ReportDownload @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></ReportDownload>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
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
                        <div style="color:white; font-size:17px; font-weight:700;">Report 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <Report :offline="offline"
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
                        <div style="color:white; font-size:17px; font-weight:700;">Report 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="ReportId" v-model="selectedRow.reportId" :editMode="true"/>
                            <String label="GeneratedBy" v-model="selectedRow.generatedBy" :editMode="true"/>
                            <Date label="CreatedAt" v-model="selectedRow.createdAt" :editMode="true"/>
                            <ReportType offline label="ReportType" v-model="selectedRow.reportType" :editMode="true"/>
                            <PeriodType offline label="PeriodType" v-model="selectedRow.periodType" :editMode="true"/>
                            <ReportPeriod offline label="ReportPeriod" v-model="selectedRow.reportPeriod" :editMode="true"/>
                            <ReportStatus offline label="Status" v-model="selectedRow.status" :editMode="true"/>
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
    name: 'reportGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'reports',
        createReportDialog: false,
        changeReportStatusDialog: false,
    }),
    watch: {
    },
    methods:{
        async createReport(params){
            try{
                var path = "createReport".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','CreateReport 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.createReportDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async changeReportStatus(params){
            try{
                var path = "changeReportStatus".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','ChangeReportStatus 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.changeReportStatusDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>