import Layout from "@/layout/index.vue";
import { createNameComponent } from "../createNode";
const route = [
  {
    path: "/business",
    component: Layout,
    redirect: "/business/index",
    hideMenu: true,
    meta: { title: "业务" },
    children: [
      {
        name: "businessRegister",
        path: "register",
        component: createNameComponent(() => import("@/views/main/business/register.vue")),
        meta: { title: "训练项目", hideTabs: true }
      },
      {
        name: "businessIndex",
        path: "index/:id?",
        component: createNameComponent(() => import("@/views/main/business/index.vue")),
        meta: { title: "业务首页", hideTabs: true }
      }
    ]
  }
];

export default route;
