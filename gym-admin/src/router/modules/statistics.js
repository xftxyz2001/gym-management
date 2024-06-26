import Layout from "@/layout/index.vue";
import { createNameComponent } from "../createNode";
const route = [
  {
    path: "/statistics",
    component: Layout,
    redirect: "/statistics/index",
    meta: { title: "统计分析", icon: "Gundamfont icon-tongjifenxi" },
    children: [
      {
        path: "index",
        component: createNameComponent(() => import("@/views/main/statistics/index.vue")),
        meta: { title: "统计分析", hideClose: true }
      }
    ]
  }
];

export default route;
