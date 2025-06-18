<template>

    <v-card outlined>
        <v-card-title>
            RequestOrder
        </v-card-title>

        <v-card-text>
            <Number label="CustomerId" v-model="value.customerId" :editMode="editMode"/>
            <RestaurantId offline label="RestaurantId" v-model="value.restaurantId" :editMode="editMode" @change="change"/>
            <OrderItemList offline label="OrderItems" v-model="value.orderItems" :editMode="editMode" @change="change"/>
            <String label="DeliveryAddress" v-model="value.deliveryAddress" :editMode="editMode"/>
            <PaymentMethod offline label="PaymentMethod" v-model="value.paymentMethod" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="requestOrder"
            >
                RequestOrder
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
    name: 'RequestOrderCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.customerId = 0;
        this.value.restaurantId = {};
        this.value.orderItems = [];
        this.value.deliveryAddress = '';
        this.value.paymentMethod = {};
    },
    watch: {
    },
    methods: {
        requestOrder() {
            this.$emit('requestOrder', this.value);
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

