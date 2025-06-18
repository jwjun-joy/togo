<template>
    <div>
        <Number
            label="VerificationId"
            v-model="value.verificationId"
            :editMode="editMode"
        />
        <String
            label="PhoneNumber"
            v-model="value.phoneNumber"
            :editMode="editMode"
        />
        <Boolean
            label="Verified"
            v-model="value.verified"
            :editMode="editMode"
        />
        <Date
            label="VerifiedAt"
            v-model="value.verifiedAt"
            :editMode="editMode"
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
    name: 'PhoneVerification',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "phoneVerifications",
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
