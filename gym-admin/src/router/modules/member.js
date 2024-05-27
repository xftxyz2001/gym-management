import Layout from "@/layout/index.vue";
import { createNameComponent } from "../createNode";
const route = [
  {
    path: "/member",
    component: Layout,
    meta: { title: "会员综合管理", icon: "Gundamfont icon-shouye" },
    children: [
      {
        path: "member",
        component: createNameComponent(() => import("@/views/main/member/member/index.vue")),
        meta: { title: "会员管理", icon: "Gundamfont icon-7", hideClose: true }
      },
      {
        path: "card",
        component: createNameComponent(() => import("@/views/main/member/card/index.vue")),
        meta: { title: "会员卡管理", icon: "Gundamfont icon-huiyuanqia", hideClose: true }
      },
      {
        path: "cardtype",
        component: createNameComponent(() => import("@/views/main/member/cardtype/index.vue")),
        meta: { title: "会员卡类型", icon: "Gundamfont icon-huiyuanqia2", hideClose: true }
      }
    ]
  }
];

export default route;
