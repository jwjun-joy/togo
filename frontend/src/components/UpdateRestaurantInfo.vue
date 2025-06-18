<template>

    <v-card outlined>
        <v-card-title>
            UpdateRestaurantInfo
        </v-card-title>

        <v-card-text>
            <String label="Name" v-model="value.name" :editMode="editMode"/>
            <String label="Address" v-model="value.address" :editMode="editMode"/>
            <Number label="Latitude" v-model="value.latitude" :editMode="editMode"/>
            <Number label="Longitude" v-model="value.longitude" :editMode="editMode"/>
            <String label="OpeningTime" v-model="value.openingTime" :editMode="editMode"/>
            <String label="ClosingTime" v-model="value.closingTime" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="updateRestaurantInfo"
            >
                UpdateRestaurantInfo
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
    name: 'UpdateRestaurantInfoCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.name = '';
        this.value.address = '';
        this.value.latitude = 0;
        this.value.longitude = 0;
        this.value.openingTime = '';
        this.value.closingTime = '';
    },
    watch: {
    },
    methods: {
        updateRestaurantInfo() {
            this.$emit('updateRestaurantInfo', this.value);
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

