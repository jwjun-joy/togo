<template>

    <v-card outlined>
        <v-card-title>
            UpdateMenu
        </v-card-title>

        <v-card-text>
            <Number label="MenuId" v-model="value.menuId" :editMode="editMode"/>
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
                    @click="updateMenu"
            >
                UpdateMenu
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
    name: 'UpdateMenuCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.menuId = 0;
        this.value.name = '';
        this.value.description = '';
        this.value.price = 0;
        this.value.stock = 0;
        this.value.allergyInfo = '';
    },
    watch: {
    },
    methods: {
        updateMenu() {
            this.$emit('updateMenu', this.value);
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

