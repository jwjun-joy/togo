<script setup>
import {
  VerticalNavLink,
  VerticalNavSectionTitle,
} from '@layouts'
</script>

<template>
    <ul>
        <VerticalNavSectionTitle :item="{ heading: '주문 관리' }" />
        <VerticalNavLink
            :item="{
                title: '주문',
                to: '/orders',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '리뷰',
                to: '/reviews',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '장바구니',
                to: '/carts',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '주문 현황 리스트',
                to: '/orderStatusLists',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '주문 상세 정보',
                to: '/orderDetails',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '식당별 주문 현황',
                to: '/restaurantOrderStatusLists',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '리뷰 목록',
                to: '/reviewLists',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '리뷰 상세',
                to: '/reviewDetails',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '장바구니 상세정보',
                to: '/cartDetails',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '장바구니 목록',
                to: '/cartLists',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '배달' }" />
        <VerticalNavLink
            :item="{
                title: '배달',
                to: '/deliveries',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '배달원',
                to: '/riders',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '배달 실시간 추적',
                to: '/deliveryTrackings',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '배달 경로/목적지 정보',
                to: '/deliveryOrderPaths',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '배달원 배정 주문 목록',
                to: '/riderDeliveryLists',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '배달 상세 정보',
                to: '/riderDeliveryDetails',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '식당/메뉴' }" />
        <VerticalNavLink
            :item="{
                title: '식당',
                to: '/restaurants',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '근처 식당 목록',
                to: '/nearbyRestaurantLists',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '식당 상세 정보',
                to: '/restaurantDetails',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '메뉴 목록',
                to: '/menuLists',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '사용자/권한' }" />
        <VerticalNavLink
            :item="{
                title: '사용자',
                to: '/users',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '휴대폰 인증',
                to: '/phoneVerifications',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '사용자 목록 조회',
                to: '/userListViews',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '사용자 상세 정보 조회',
                to: '/userDetailViews',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '휴대폰 인증 목록',
                to: '/phoneVerificationLists',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '휴대폰 인증 상세정보',
                to: '/phoneVerificationDetails',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '통계/리포트' }" />
        <VerticalNavLink
            :item="{
                title: '리포트',
                to: '/reports',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '리포트 데이터',
                to: '/reportData',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '통계 리포트 목록',
                to: '/reportLists',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '통계 리포트 상세',
                to: '/reportDetails',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '리포트 시각화 데이터',
                to: '/reportVisualizations',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '리포트 다운로드 데이터',
                to: '/reportDownloads',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '통계 데이터 리스트',
                to: '/statisticsDataLists',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '통계 데이터 시각화',
                to: '/statisticsDataVisualizations',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '통계 데이터 다운로드',
                to: '/statisticsDataDownloads',
            }"
        />
    </ul>
</template>
