<template>

    <v-card outlined>
        <v-card-title>
            SuspendUser
        </v-card-title>

        <v-card-text>
            <String label="SuspendedReason" v-model="value.suspendedReason" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="suspendUser"
            >
                SuspendUser
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
    name: 'SuspendUserCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.suspendedReason = '';
    },
    watch: {
    },
    methods: {
        suspendUser() {
            this.$emit('suspendUser', this.value);
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

