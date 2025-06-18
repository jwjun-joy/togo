<template>

    <v-card outlined>
        <v-card-title>
            CreateMenu
        </v-card-title>

        <v-card-text>
            <String label="Name" v-model="value.name" :editMode="editMode"/>
            <String label="Description" v-model="value.description" :editMode="editMode"/>
            <Number label="Price" v-model="value.price" :editMode="editMode"/>
            <Number label="Stock" v-model="value.stock" :editMode="editMode"/>
            <String label="AllergyInfo" v-model="value.allergyInfo" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="createMenu"
            >
                CreateMenu
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
    name: 'CreateMenuCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.name = '';
        this.value.description = '';
        this.value.price = 0;
        this.value.stock = 0;
        this.value.allergyInfo = '';
    },
    watch: {
    },
    methods: {
        createMenu() {
            this.$emit('createMenu', this.value);
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

