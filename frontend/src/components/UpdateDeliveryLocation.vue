<template>

    <v-card outlined>
        <v-card-title>
            UpdateDeliveryLocation
        </v-card-title>

        <v-card-text>
            <CurrentLocation offline label="CurrentLocation" v-model="value.currentLocation" :editMode="editMode" @change="change"/>
            <Date label="LastLocationUpdate" v-model="value.lastLocationUpdate" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="updateDeliveryLocation"
            >
                UpdateDeliveryLocation
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
    name: 'UpdateDeliveryLocationCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.currentLocation = {};
        this.value.lastLocationUpdate = '2025-06-18';
    },
    watch: {
    },
    methods: {
        updateDeliveryLocation() {
            this.$emit('updateDeliveryLocation', this.value);
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

