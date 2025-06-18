<template>

    <v-card outlined>
        <v-card-title>
            ChangeUserGrade
        </v-card-title>

        <v-card-text>
            <String label="Grade" v-model="value.grade" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="changeUserGrade"
            >
                ChangeUserGrade
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
    name: 'ChangeUserGradeCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.grade = '';
    },
    watch: {
    },
    methods: {
        changeUserGrade() {
            this.$emit('changeUserGrade', this.value);
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

