<template>
    <div>
        <Number
            label="ReportDataId"
            v-model="value.reportDataId"
            :editMode="editMode"
        />
        <Number
            label="Value"
            v-model="value.value"
            :editMode="editMode"
        />
        <Date
            label="RecordDate"
            v-model="value.recordDate"
            :editMode="editMode"
        />
        <ReportDataType
            offline
            label="DataType"
            v-model="value.dataType"
            :editMode="editMode"
            @change="change"
        />
        <ReportId
            offline
            label="ReportId"
            v-model="value.reportId"
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
    name: 'ReportData',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "reportData",
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
