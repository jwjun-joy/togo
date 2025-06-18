<template>
    <div>
        <Number
            label="ReportId"
            v-model="value.reportId"
            :editMode="editMode"
        />
        <String
            label="GeneratedBy"
            v-model="value.generatedBy"
            :editMode="editMode"
        />
        <Date
            label="CreatedAt"
            v-model="value.createdAt"
            :editMode="editMode"
        />
        <ReportType
            offline
            label="ReportType"
            v-model="value.reportType"
            :editMode="editMode"
            @change="change"
        />
        <PeriodType
            offline
            label="PeriodType"
            v-model="value.periodType"
            :editMode="editMode"
            @change="change"
        />
        <ReportPeriod
            offline
            label="ReportPeriod"
            v-model="value.reportPeriod"
            :editMode="editMode"
            @change="change"
        />
        <ReportStatus
            offline
            label="Status"
            v-model="value.status"
            :editMode="editMode"
            @change="change"
        />
        <v-row class="ma-0 pa-0">
            <v-spacer></v-spacer>
            <v-btn width="64px" color="primary" @click="save">
                저장
            </v-btn>
        </v-row>
    </div>
</template>


<script>
import BaseEntity from './base-ui/BaseEntity.vue'

export default {
    name: 'Report',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "reports",
        value: {
        }
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
