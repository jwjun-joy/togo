import { createRouter, createWebHashHistory } from 'vue-router';

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      component: () => import('../components/pages/Index.vue'),
    },
    {
      path: '/orders',
      component: () => import('../components/ui/OrderGrid.vue'),
    },
    {
      path: '/reviews',
      component: () => import('../components/ui/ReviewGrid.vue'),
    },
    {
      path: '/carts',
      component: () => import('../components/ui/CartGrid.vue'),
    },
    {
      path: '/deliveries',
      component: () => import('../components/ui/DeliveryGrid.vue'),
    },
    {
      path: '/riders',
      component: () => import('../components/ui/RiderGrid.vue'),
    },
    {
      path: '/restaurants',
      component: () => import('../components/ui/RestaurantGrid.vue'),
    },
    {
      path: '/users',
      component: () => import('../components/ui/UserGrid.vue'),
    },
    {
      path: '/phoneVerifications',
      component: () => import('../components/ui/PhoneVerificationGrid.vue'),
    },
    {
      path: '/reports',
      component: () => import('../components/ui/ReportGrid.vue'),
    },
    {
      path: '/reportData',
      component: () => import('../components/ui/ReportDataGrid.vue'),
    },
  ],
})

export default router;
