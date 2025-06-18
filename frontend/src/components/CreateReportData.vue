<template>

    <v-card outlined>
        <v-card-title>
            CreateReportData
        </v-card-title>

        <v-card-text>
            <ReportDataType offline label="DataType" v-model="value.dataType" :editMode="editMode" @change="change"/>
            <Number label="Value" v-model="value.value" :editMode="editMode"/>
            <Date label="RecordDate" v-model="value.recordDate" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="createReportData"
            >
                CreateReportData
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
    name: 'CreateReportDataCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.dataType = {};
        this.value.value = 0;
        this.value.recordDate = '2025-06-18';
    },
    watch: {
    },
    methods: {
        createReportData() {
            this.$emit('createReportData', this.value);
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

