/**
 * 前端路由管理
 **/
import store from "@/store";
/** 引入需要权限的Modules */
import Dashboard from "../modules/dashboard";

import Member from "../modules/member";
import Course from "../modules/course";
import Points from "../modules/points";
import Finance from "../modules/finance";

import Statistics from "../modules/statistics";

import Business from "../modules/business";
// ---
import Coach from "../modules/coach";

/** 登录后需要动态加入的本地路由 */
const AdminRoutes = [...Dashboard, ...Member, ...Course, ...Points, ...Finance, ...Statistics, ...Business];
const CoachRoutes = [...Coach];

const FrontRoutes = [];
if (store.state.user.info.coachId == 0) {
  FrontRoutes.push(...AdminRoutes);
} else {
  FrontRoutes.push(...CoachRoutes);
}

export default FrontRoutes;
