import Layout from "@/layout/index.vue";
import { createNameComponent } from "../createNode";
const route = [
  {
    path: "/course",
    component: Layout,
    meta: { title: "课程管理", icon: "sfont system-home" },
    children: [
      {
        path: "project",
        component: createNameComponent(() => import("@/views/main/course/project/index.vue")),
        meta: { title: "训练项目", icon: "sfont icon-xunlianxiangmu", hideClose: true }
      },
      {
        path: "course",
        component: createNameComponent(() => import("@/views/main/course/course/index.vue")),
        meta: { title: "课程管理", icon: "sfont system-home", hideClose: true }
      },
      {
        path: "coach",
        component: createNameComponent(() => import("@/views/main/course/coach/index.vue")),
        meta: { title: "教练管理", icon: "sfont system-home", hideClose: true }
      }
    ]
  }
];

export default route;
