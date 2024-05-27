import Layout from "@/layout/index.vue";
import { createNameComponent } from "../createNode";
const route = [
  {
    path: "/",
    component: Layout,
    redirect: "/index",
    meta: { title: "教练信息", icon: "Gundamfont icon-jiaolian" },
    children: [
      {
        path: "index",
        component: createNameComponent(() => import("@/views/main/coach/info/index.vue")),
        meta: { title: "教练信息", hideClose: true }
      }
    ]
  },
  {
    path: "/courseCourse",
    component: Layout,
    redirect: "/courseCourse/index",
    meta: { title: "教练课程", icon: "Gundamfont icon-jilu1" },
    children: [
      {
        path: "index",
        component: createNameComponent(() => import("@/views/main/coach/course/index.vue")),
        meta: { title: "教练课程", hideClose: true }
      }
    ]
  },
  {
    path: "/courseProject",
    component: Layout,
    redirect: "/courseProject/index",
    meta: { title: "训练项目", icon: "Gundamfont icon-xunlianxiangmu" },
    children: [
      {
        path: "index",
        component: createNameComponent(() => import("@/views/main/coach/project/index.vue")),
        meta: { title: "训练项目", hideClose: true }
      }
    ]
  }
];

export default route;
