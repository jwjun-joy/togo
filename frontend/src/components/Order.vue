<template>
    <div>
        <Number
            label="OrderId"
            v-model="value.orderId"
            :editMode="editMode"
        />
        <Number
            label="CustomerId"
            v-model="value.customerId"
            :editMode="editMode"
        />
        <Number
            label="DeliveryPersonId"
            v-model="value.deliveryPersonId"
            :editMode="editMode"
        />
        <Date
            label="OrderTime"
            v-model="value.orderTime"
            :editMode="editMode"
        />
        <String
            label="DeliveryAddress"
            v-model="value.deliveryAddress"
            :editMode="editMode"
        />
        <Number
            label="TotalAmount"
            v-model="value.totalAmount"
            :editMode="editMode"
        />
        <String
            label="CancelReason"
            v-model="value.cancelReason"
            :editMode="editMode"
        />
        <RestaurantId
            offline
            label="RestaurantId"
            v-model="value.restaurantId"
            :editMode="editMode"
            @change="change"
        />
        <OrderStatus
            offline
            label="OrderStatus"
            v-model="value.orderStatus"
            :editMode="editMode"
            @change="change"
        />
        <PaymentMethod
            offline
            label="PaymentMethod"
            v-model="value.paymentMethod"
            :editMode="editMode"
            @change="change"
        />
        <PaymentStatus
            offline
            label="PaymentStatus"
            v-model="value.paymentStatus"
            :editMode="editMode"
            @change="change"
        />
        <UserId
            offline
            label="UserId"
            v-model="value.userId"
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
    name: 'Order',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "orders",
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
