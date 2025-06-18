<template>

    <v-card outlined>
        <v-card-title>
            CreateReport
        </v-card-title>

        <v-card-text>
            <ReportType offline label="ReportType" v-model="value.reportType" :editMode="editMode" @change="change"/>
            <PeriodType offline label="PeriodType" v-model="value.periodType" :editMode="editMode" @change="change"/>
            <ReportPeriod offline label="ReportPeriod" v-model="value.reportPeriod" :editMode="editMode" @change="change"/>
            <String label="GeneratedBy" v-model="value.generatedBy" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="createReport"
            >
                CreateReport
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>

export default {
    name: 'CreateReportCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.reportType = {};
        this.value.periodType = {};
        this.value.reportPeriod = {};
        this.value.generatedBy = '';
    },
    watch: {
    },
    methods: {
        createReport() {
            this.$emit('createReport', this.value);
        },
        close() {
            this.$emit('closeDialog');
        },
        change() {
            this.$emit("update:modelValue", this.value);
        },
    }
}
</script>

