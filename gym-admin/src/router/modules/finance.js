import Layout from "@/layout/index.vue";
import { createNameComponent } from "../createNode";
const route = [
  {
    path: "/finance",
    component: Layout,
    meta: { title: "财务管理", icon: "sfont icon-caiwuguanli2" },
    children: [
      {
        path: "consume",
        component: createNameComponent(() => import("@/views/main/finance/consume/index.vue")),
        meta: { title: "消费记录", icon: "sfont icon-xiaofeijilu1", hideClose: true }
      },
      {
        path: "refund",
        component: createNameComponent(() => import("@/views/main/finance/refund/index.vue")),
        meta: { title: "退款记录", icon: "sfont icon-tuikuanjilu", hideClose: true }
      }
    ]
  }
];

export default route;
