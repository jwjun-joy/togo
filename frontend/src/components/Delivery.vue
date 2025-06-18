<template>
    <div>
        <Number
            label="DeliveryId"
            v-model="value.deliveryId"
            :editMode="editMode"
        />
        <Date
            label="LastLocationUpdate"
            v-model="value.lastLocationUpdate"
            :editMode="editMode"
        />
        <RiderId
            offline
            label="RiderId"
            v-model="value.riderId"
            :editMode="editMode"
            @change="change"
        />
        <DeliveryStatus
            offline
            label="Status"
            v-model="value.status"
            :editMode="editMode"
            @change="change"
        />
        <CurrentLocation
            offline
            label="CurrentLocation"
            v-model="value.currentLocation"
            :editMode="editMode"
            @change="change"
        />
        <OrderId
            offline
            label="OrderId"
            v-model="value.orderId"
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
    name: 'Delivery',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "deliveries",
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
