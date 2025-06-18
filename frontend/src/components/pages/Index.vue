<template>
  <v-container fluid>
    <v-row>
      <v-card
          v-for="(card, index) in cards"
          :key="index"
          class="mx-auto"
          style="height:300px; width:300px; margin-bottom:20px;"
          outlined
      >
        <v-list-item>
          <v-icon style="margin-top: 30%; margin-left: 37.5%;" color="primary" size="64">mdi-apps</v-icon>
        </v-list-item>
        <v-card-actions>
          <v-btn
              class="mx-auto"
              outlined
              rounded
              :to="card.link"
              style="font-weight:500; font-size:20px; border:solid 2px; max-width:250px; overflow:hidden; margin-top: 10%;"
          >
              {{ card.text }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'Index',
  data: () => ({
    cards: [
      {
          text: "주문",
          link: "/orders",
      },
      {
          text: "리뷰",
          link: "/reviews",
      },
      {
          text: "장바구니",
          link: "/carts",
      },
      {
          text: "주문 현황 리스트",
          link: "/orderStatusLists",
      },
      {
          text: "주문 상세 정보",
          link: "/orderDetails",
      },
      {
          text: "식당별 주문 현황",
          link: "/restaurantOrderStatusLists",
      },
      {
          text: "리뷰 목록",
          link: "/reviewLists",
      },
      {
          text: "리뷰 상세",
          link: "/reviewDetails",
      },
      {
          text: "장바구니 상세정보",
          link: "/cartDetails",
      },
      {
          text: "장바구니 목록",
          link: "/cartLists",
      },
      {
          text: "배달",
          link: "/deliveries",
      },
      {
          text: "배달원",
          link: "/riders",
      },
      {
          text: "배달 실시간 추적",
          link: "/deliveryTrackings",
      },
      {
          text: "배달 경로/목적지 정보",
          link: "/deliveryOrderPaths",
      },
      {
          text: "배달원 배정 주문 목록",
          link: "/riderDeliveryLists",
      },
      {
          text: "배달 상세 정보",
          link: "/riderDeliveryDetails",
      },
      {
          text: "식당",
          link: "/restaurants",
      },
      {
          text: "근처 식당 목록",
          link: "/nearbyRestaurantLists",
      },
      {
          text: "식당 상세 정보",
          link: "/restaurantDetails",
      },
      {
          text: "메뉴 목록",
          link: "/menuLists",
      },
      {
          text: "사용자",
          link: "/users",
      },
      {
          text: "휴대폰 인증",
          link: "/phoneVerifications",
      },
      {
          text: "사용자 목록 조회",
          link: "/userListViews",
      },
      {
          text: "사용자 상세 정보 조회",
          link: "/userDetailViews",
      },
      {
          text: "휴대폰 인증 목록",
          link: "/phoneVerificationLists",
      },
      {
          text: "휴대폰 인증 상세정보",
          link: "/phoneVerificationDetails",
      },
      {
          text: "리포트",
          link: "/reports",
      },
      {
          text: "리포트 데이터",
          link: "/reportData",
      },
      {
          text: "통계 리포트 목록",
          link: "/reportLists",
      },
      {
          text: "통계 리포트 상세",
          link: "/reportDetails",
      },
      {
          text: "리포트 시각화 데이터",
          link: "/reportVisualizations",
      },
      {
          text: "리포트 다운로드 데이터",
          link: "/reportDownloads",
      },
      {
          text: "통계 데이터 리스트",
          link: "/statisticsDataLists",
      },
      {
          text: "통계 데이터 시각화",
          link: "/statisticsDataVisualizations",
      },
      {
          text: "통계 데이터 다운로드",
          link: "/statisticsDataDownloads",
      },
    ],
  }),
};
</script>
