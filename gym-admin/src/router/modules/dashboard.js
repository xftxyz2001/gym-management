import Layout from "@/layout/index.vue";
import { createNameComponent } from "../createNode";
const route = [
  {
    path: "/",
    component: Layout,
    redirect: "/dashboard",
    meta: { title: "仪表盘", icon: "sfont system-home" },
    children: [
      {
        path: "dashboard",
        component: createNameComponent(() => import("@/views/main/dashboard/index.vue")),
        meta: { title: "首页", icon: "sfont system-home", hideClose: true }
      }
    ]
  }
];

export default route;
