<template>
    <div>
        <Number
            label="RiderId"
            v-model="value.riderId"
            :editMode="editMode"
        />
        <RiderStatus
            offline
            label="Status"
            v-model="value.status"
            :editMode="editMode"
            @change="change"
        />
        <RiderProfile
            offline
            label="Profile"
            v-model="value.profile"
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
    name: 'Rider',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "riders",
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
