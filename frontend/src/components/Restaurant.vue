<template>
    <div>
        <Number
            label="RestaurantId"
            v-model="value.restaurantId"
            :editMode="editMode"
        />
        <RestaurantInfo
            offline
            label="RestaurantInfo"
            v-model="value.restaurantInfo"
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
    name: 'Restaurant',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "restaurants",
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
