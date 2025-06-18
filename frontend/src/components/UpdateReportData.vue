<template>

    <v-card outlined>
        <v-card-title>
            UpdateReportData
        </v-card-title>

        <v-card-text>
            <Number label="Value" v-model="value.value" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="updateReportData"
            >
                UpdateReportData
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
    name: 'UpdateReportDataCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.value = 0;
    },
    watch: {
    },
    methods: {
        updateReportData() {
            this.$emit('updateReportData', this.value);
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

