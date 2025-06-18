<template>

    <v-card outlined>
        <v-card-title>
            CreatePhoneVerification
        </v-card-title>

        <v-card-text>
            <UserId offline label="UserId" v-model="value.userId" :editMode="editMode" @change="change"/>
            <String label="PhoneNumber" v-model="value.phoneNumber" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="createPhoneVerification"
            >
                CreatePhoneVerification
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
    name: 'CreatePhoneVerificationCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.userId = {};
        this.value.phoneNumber = '';
    },
    watch: {
    },
    methods: {
        createPhoneVerification() {
            this.$emit('createPhoneVerification', this.value);
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

