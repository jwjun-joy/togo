<template>

    <v-card outlined>
        <v-card-title>
            VerifyPhoneNumber
        </v-card-title>

        <v-card-text>
            <Number label="VerificationId" v-model="value.verificationId" :editMode="editMode"/>
            <String label="VerificationCode" v-model="value.verificationCode" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="verifyPhoneNumber"
            >
                VerifyPhoneNumber
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
    name: 'VerifyPhoneNumberCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.verificationId = 0;
        this.value.verificationCode = '';
    },
    watch: {
    },
    methods: {
        verifyPhoneNumber() {
            this.$emit('verifyPhoneNumber', this.value);
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

