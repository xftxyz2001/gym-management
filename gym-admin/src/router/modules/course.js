import Layout from "@/layout/index.vue";
import { createNameComponent } from "../createNode";
const route = [
  {
    path: "/course",
    component: Layout,
    meta: { title: "课程综合管理", icon: "Gundamfont icon-bianjixunlianxiangmu" },
    children: [
      {
        path: "project",
        component: createNameComponent(() => import("@/views/main/course/project/index.vue")),
        meta: { title: "训练项目", icon: "Gundamfont icon-xunlianxiangmu", hideClose: true }
      },
      {
        path: "course",
        component: createNameComponent(() => import("@/views/main/course/course/index.vue")),
        meta: { title: "课程管理", icon: "Gundamfont icon-jilu1", hideClose: true }
      },
      {
        path: "coach",
        component: createNameComponent(() => import("@/views/main/course/coach/index.vue")),
        meta: { title: "教练管理", icon: "Gundamfont icon-jiaolian", hideClose: true }
      }
    ]
  }
];

export default route;
