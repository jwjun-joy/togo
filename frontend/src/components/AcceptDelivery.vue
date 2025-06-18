<template>

    <v-card outlined>
        <v-card-title>
            AcceptDelivery
        </v-card-title>

        <v-card-text>
            <RiderId offline label="RiderId" v-model="value.riderId" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="acceptDelivery"
            >
                AcceptDelivery
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
    name: 'AcceptDeliveryCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.riderId = {};
    },
    watch: {
    },
    methods: {
        acceptDelivery() {
            this.$emit('acceptDelivery', this.value);
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

