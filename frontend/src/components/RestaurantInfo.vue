<template>

    <div>
        <div class="detail-title">
        RestaurantInfo
        </div>
        <v-col>
            <String label="Name" v-model="value.name" :editMode="editMode"/>
            <String label="Address" v-model="value.address" :editMode="editMode"/>
            <Number label="Latitude" v-model="value.latitude" :editMode="editMode"/>
            <Number label="Longitude" v-model="value.longitude" :editMode="editMode"/>
            <String label="OpeningTime" v-model="value.openingTime" :editMode="editMode"/>
            <String label="ClosingTime" v-model="value.closingTime" :editMode="editMode"/>
            <Number label="Rating" v-model="value.rating" :editMode="editMode"/>
            <Number label="Popularity" v-model="value.popularity" :editMode="editMode"/>
        </v-col>

        <v-card-actions v-if="inList">
            <slot name="actions"></slot>
        </v-card-actions>
    </div>
</template>

<script>
import BaseEntity from './base-ui/BaseEntity.vue'
import BasePicker from './base-ui/BasePicker.vue'

export default {
    name: 'RestaurantInfo',
    mixins:[BaseEntity],
    components:{
        BasePicker
    },
    data: () => ({
        path: 'RestaurantInfos',
    }),
    props: {
    },
    watch: {
        value(val){
            this.value = val;
            this.change();
        },
    },
    async created() {
        if (Array.isArray(this.modelValue)) {
            this.value = await Promise.all(this.modelValue.map(async (item) => {
                if (item && item.id !== undefined) {
                    return await this.repository.findById(item.id);
                }
                return item;
            }));
        } else {
            this.value = this.modelValue;
            if (this.value && this.value.id !== undefined) {
                this.value = await this.repository.findById(this.value.id);
            }
        }
    },
    methods: {
        pick(val){
            this.value = val;
            this.change();
        },
    }
}
</script>

