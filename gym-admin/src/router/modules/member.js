import Layout from "@/layout/index.vue";
import { createNameComponent } from "../createNode";
const route = [
  {
    path: "/member",
    component: Layout,
    meta: { title: "会员管理", icon: "sfont system-home" },
    children: [
      {
        path: "member",
        component: createNameComponent(() => import("@/views/main/member/member/index.vue")),
        meta: { title: "会员管理", icon: "sfont system-home", hideClose: true }
      },
      {
        path: "card",
        component: createNameComponent(() => import("@/views/main/member/card/index.vue")),
        meta: { title: "会员卡管理", icon: "sfont system-home", hideClose: true }
      }
    ]
  }
];

export default route;
