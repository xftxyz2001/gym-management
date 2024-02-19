import Layout from "@/layout/index.vue";
import { createNameComponent } from "../createNode";
const route = [
  {
    path: "/points",
    component: Layout,
    redirect: "/dashboard",
    meta: { title: "dashboard", icon: "sfont system-home" },
    children: [
      {
        path: "dashboard",
        component: createNameComponent(() => import("@/views/main/dashboard/index.vue")),
        meta: { title: "积分管理", icon: "sfont system-home", hideClose: true }
      }
    ]
  }
];

export default route;
