<template>

    <v-card outlined>
        <v-card-title>
            RejectDelivery
        </v-card-title>

        <v-card-text>
            <RiderId offline label="RiderId" v-model="value.riderId" :editMode="editMode" @change="change"/>
            <String label="RejectReason" v-model="value.rejectReason" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="rejectDelivery"
            >
                RejectDelivery
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
    name: 'RejectDeliveryCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.riderId = {};
        this.value.rejectReason = '';
    },
    watch: {
    },
    methods: {
        rejectDelivery() {
            this.$emit('rejectDelivery', this.value);
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

