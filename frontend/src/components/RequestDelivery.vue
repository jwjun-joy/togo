<template>

    <v-card outlined>
        <v-card-title>
            RequestDelivery
        </v-card-title>

        <v-card-text>
            <OrderId offline label="OrderId" v-model="value.orderId" :editMode="editMode" @change="change"/>
            <String label="DeliveryAddress" v-model="value.deliveryAddress" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="requestDelivery"
            >
                RequestDelivery
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
    name: 'RequestDeliveryCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.orderId = {};
        this.value.deliveryAddress = '';
    },
    watch: {
    },
    methods: {
        requestDelivery() {
            this.$emit('requestDelivery', this.value);
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

