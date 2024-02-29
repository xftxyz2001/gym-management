/**
 * 前端路由管理
 **/

/** 引入需要权限的Modules */
import Dashboard from "../modules/dashboard";

import Member from "../modules/member";
import Course from "../modules/course";
import Points from "../modules/points";
import Finance from "../modules/finance";

import Statistics from "../modules/statistics";

import Business from "../modules/business";

/** 登录后需要动态加入的本地路由 */
const FrontRoutes = [...Dashboard, ...Member, ...Course, ...Points, ...Finance, ...Statistics, ...Business];

export default FrontRoutes;
