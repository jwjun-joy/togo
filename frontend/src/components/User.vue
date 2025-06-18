<template>
    <div>
        <Number
            label="UserId"
            v-model="value.userId"
            :editMode="editMode"
        />
        <String
            label="Name"
            v-model="value.name"
            :editMode="editMode"
        />
        <String
            label="PhoneNumber"
            v-model="value.phoneNumber"
            :editMode="editMode"
        />
        <String
            label="Grade"
            v-model="value.grade"
            :editMode="editMode"
        />
        <Date
            label="RegisteredAt"
            v-model="value.registeredAt"
            :editMode="editMode"
        />
        <String
            label="SuspendedReason"
            v-model="value.suspendedReason"
            :editMode="editMode"
        />
        <UserType
            offline
            label="UserType"
            v-model="value.userType"
            :editMode="editMode"
            @change="change"
        />
        <UserStatus
            offline
            label="Status"
            v-model="value.status"
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
    name: 'User',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "users",
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
