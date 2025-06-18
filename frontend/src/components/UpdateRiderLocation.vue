<template>

    <v-card outlined>
        <v-card-title>
            UpdateRiderLocation
        </v-card-title>

        <v-card-text>
            <Date label="LastLocationUpdate" v-model="value.lastLocationUpdate" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="updateRiderLocation"
            >
                UpdateRiderLocation
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
    name: 'UpdateRiderLocationCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.lastLocationUpdate = '2025-06-18';
    },
    watch: {
    },
    methods: {
        updateRiderLocation() {
            this.$emit('updateRiderLocation', this.value);
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

