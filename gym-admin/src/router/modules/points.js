import Layout from "@/layout/index.vue";
import { createNameComponent } from "../createNode";
const route = [
  {
    path: "/points",
    component: Layout,
    meta: { title: "积分管理", icon: "sfont icon-jifenguanli" },
    children: [
      {
        path: "reward",
        component: createNameComponent(() => import("@/views/main/points/reward/index.vue")),
        meta: { title: "积分兑换奖品", icon: "sfont icon-jifenduihuan", hideClose: true }
      },
      {
        path: "exchange",
        component: createNameComponent(() => import("@/views/main/points/exchange/index.vue")),
        meta: { title: "积分兑换记录", icon: "sfont icon-jifenguanli3", hideClose: true }
      }
    ]
  }
];

export default route;
